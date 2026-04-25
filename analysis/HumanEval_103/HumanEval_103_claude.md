# HumanEval_103 — Claude

## Generation
- Source: `generated_code/claude/HumanEval_103.java`
- Log: `llm_logs/claude/HumanEval_103_initial.md`
- Approach: `roundedAvg(int n, int m)` returns `-1` when `n > m`; otherwise it
  sums the integers in `[n, m]` into a `long` accumulator, divides by the
  range count `m - n + 1`, applies `Math.round` to the double quotient
  (half-up rounding), and converts the rounded `long` to its binary string
  via `Long.toBinaryString`. Return type is `Object` because success and
  failure paths return different concrete types (`String` vs `Integer`).

## Base test
- File: `tests/base_tests/claude/HumanEval_103_BaseTest.java` (JUnit 6 port of
  `tests/base_tests/original/HumanEval_103/Main.java`).
- Adjusted dataset harness: `tests/base_tests/adjusted/HumanEval_103/Main.java`
  (added `import java.util.*;` so the dataset `Main.java`, which references
  `List`, `Arrays`, and `Objects`, compiles standalone). See
  `tests/base_tests/adjustment_log.md`.
- Adjusted-harness run: exit 0 (`ADJUSTED_OK`).
- JUnit 6 run: 12/12 pass — all dataset cases including the four spec
  examples, the half-up boundary at `(20, 33) -> 26.5 -> 27 -> "11011"` is
  exercised indirectly via the larger ranges, the two `n > m` failure
  cases `(7, 5)` and `(5, 1)`, and the singleton `(5, 5) -> "101"`.
- No code defects observed against the spec at this stage; no refactor loop triggered.

## Improved suite
- File: `tests/improved_tests/claude/HumanEval_103_ImprovedTest.java` — 19/19 pass.
- Smells addressed:
  - assertion roulette (dataset bundled twelve booleans into a single
    `List<Boolean> correct` then asserted `!contains(false)` →
    per-case `@Test` with descriptive `@DisplayName`),
  - magic numbers (each `(n, m)` pair is paired with an intent name:
    dataset, guard branch, return-type, rounding, singleton, larger),
  - eager test (split into nested classes by behavioral concern: dataset,
    guard branch coverage, return-type contract, rounding behavior,
    singleton ranges, larger ranges),
  - conditional logic in tests (no `if` in test bodies; `assertAll` is
    used for invariant batches; explicit per-case asserts replace looped
    assertions).
- Behavioral targets:
  - `if (n > m)` guard taken (failure path) and not-taken (success path),
  - guard at edge: `n == m` (just inside valid) and `n == m + 1` (just
    inside invalid),
  - `for`-loop guard `i <= m` taken (range size ≥ 1) and not-taken (after
    last iteration),
  - return-type contract: success path returns `String`, failure path
    returns `Integer(-1)`,
  - rounding: exact integer average (`avg(1..3) = 2`), half-up rule
    (`avg(1..4) = 2.5 -> 3`, `avg(1..2) = 1.5 -> 2`, `avg(1..6) = 3.5 -> 4`),
  - singleton ranges (loop body executes exactly once): `(1,1)`, `(2,2)`, `(8,8)`,
  - larger ranges: `(1, 100) -> 50.5 -> 51 -> "110011"`, `(50, 100) -> 75 -> "1001011"`,
  - cross-check: `Integer.parseInt("1111", 2) == 15` for `(10, 20)`.

## Manual black-box
- Notes: `tests/manual_tests/claude/HumanEval_103_blackbox.md` — equivalence
  classes V1–V9 covering singleton ranges (smallest-valid `n == m`, power-of-two),
  exact-integer averages, half-up averages, the four dataset spec cases,
  and larger ranges (1..100, 50..100). Boundary table covers `n vs m` at
  `n == m`, `n == m + 1`, well-inside invalid (`100, 1`), smallest fractional
  half (`1, 2`), and dataset half-up (`20, 33`). Invalid / undefined-by-spec
  partitions U1 (`n == 0`) and U2 (`n < 0` paired with positive `m`) are
  pinned as observed behavior; these are documented as observed-behavior
  pins rather than spec contracts.
- Tests: `tests/manual_tests/claude/HumanEval_103_ManualTest.java` — 18/18 pass.
- Pinned undefined-by-spec behavior: `(0, 5)` returns `"11"` (avg = 2.5 → 3),
  `(-2, 2)` returns `"0"` (avg = 0). Both behaviors fall out of the
  unconditional summation loop and `Math.round` half-up rule; they do not
  represent a spec contract.

## Coverage (JaCoCo 0.8.12, `--release 21`)
- Raw outputs: `coverage_reports/HumanEval_103/claude/{base,improved,manual}/{jacoco.exec,jacoco.xml,jacoco.csv,html,junit.txt,jacoco.log}`.
- All three suites cover `Solution` at 41/41 instructions, 4/4 branches,
  9/9 lines, 4/4 cyclomatic, 2/2 methods. The four branches are the
  taken / not-taken transitions of the two conditionals (`if (n > m)`
  and the `for`-loop guard `i <= m`); both are exercised in every suite
  by the dataset cases and the boundary cases.

## Defects vs. spec
- None observed. No refactor loop triggered. The spec example
  `roundedAvg(20, 33) == "11011"` requires half-up rounding (26.5 → 27);
  Python's banker's rounding would produce `26 -> "11010"`. The Java
  `Math.round` implementation correctly aligns with the dataset's expected
  output.
