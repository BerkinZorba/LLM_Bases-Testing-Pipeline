# HumanEval_059 — Claude

## Generation
- Source: `generated_code/claude/HumanEval_059.java`
- Log: `llm_logs/claude/HumanEval_059_initial.md`
- Approach: trial division from `factor = 2` upward. Records the most recent
  successful divisor in `largest`, divides `x` by `factor` until it no longer
  divides, then advances `factor`. The outer guard uses
  `(long) factor * factor <= x` to avoid `int` overflow when `factor` grows
  past `46340`. After the loop, any remaining `x > 1` is itself a prime
  factor of the original `n` greater than `sqrt(n)` and becomes the answer.

## Base test
- File: `tests/base_tests/claude/HumanEval_059_BaseTest.java` (JUnit 6 port of
  `tests/base_tests/original/HumanEval_059/Main.java`).
- Adjusted dataset harness: `tests/base_tests/adjusted/HumanEval_059/Main.java`
  (added `import java.util.*;`). See `tests/base_tests/adjustment_log.md`.
- Adjusted-harness run: exit 0.
- JUnit 6 run: 5/5 pass — `15→5`, `27→3`, `63→7`, `330→11`, `13195→29`.
- No code defects observed against the spec at this stage; no refactor loop
  triggered.

## Improved suite
- File: `tests/improved_tests/claude/HumanEval_059_ImprovedTest.java` —
  26/26 pass.
- Smells addressed:
  - **Assertion roulette**: dataset Main packed 5 booleans into a single
    `List<Boolean>` with one `AssertionError`. Improved suite uses one
    `@Test` per case with descriptive `@DisplayName` containing the
    factorisation under test.
  - **Magic numbers**: every numeric input is paired with a display name
    explaining its prime factorisation (e.g., `"63 = 3^2 * 7 -> 7"`).
  - **Eager test**: cases are partitioned into nested classes by behavioural
    concern (DatasetParity, SmallestComposites, PrimePowers, LargeRemainder,
    SqrtBoundary, MultiPrime, Invariants).
  - **Conditional logic in tests**: no loops/ifs in test bodies; values are
    listed explicitly so a failing assertion points to a single input.
- Branch targets exercised:
  - **Outer-while** `factor*factor <= x`: false-exit covered by every input
    (loop must terminate); true-many-times covered by `2048` (2^11), `1024`
    (2^10), `27` (3^3), `3125` (5^5).
  - **Inner-while** `x % factor == 0`:
    - true (divisor found) — every composite input.
    - false (factor does not divide `x`) — `15` (factor=2 misses), `9991`
      (97 misses through factor=3..96), `13195` (factor=2,3,4,6 miss).
  - **Final `if (x > 1)`**:
    - true — `15→5`, `22→11`, `9991→103`, `13195→29` (a prime > sqrt(n)
      survives the loop).
    - false — `4`, `8`, `27`, `1024`, `2048`, `3125` (perfect prime powers;
      `x` reaches 1 before the outer loop exits).

## Manual black-box
- Notes: `tests/manual_tests/claude/HumanEval_059_blackbox.md` — equivalence
  classes V1–V12, invalid I1–I4, boundary table.
- Tests: `tests/manual_tests/claude/HumanEval_059_ManualTest.java` —
  20/20 pass.
- Pinned undefined-by-spec behaviors (observation only — the spec assumes
  `n > 1` and not prime):
  - `n = 7` (prime): returns `7` (the loop's inner while never fires; the
    final `if (x > 1)` returns the input itself).
  - `n = 1`: returns `1` (loop guard `4 <= 1` false; final `x > 1` false;
    `largest` stays at its initial `1`).
  - `n = 0`: returns `1` (same path as `n = 1`).
  - `n = -15`: returns `1` (loop guard `4 <= -15` false; final `x > 1` false).

## Coverage (JaCoCo 0.8.12, `--release 21`)
- Raw outputs: `coverage_reports/HumanEval_059/claude/{base,improved,manual}/{jacoco.exec,jacoco.xml,jacoco.csv,html,junit.txt,jacoco.log}`.
- All three suites cover `Solution` at 38/38 instructions, 6/6 branches,
  12/12 lines, 5/5 cyclomatic, 2/2 methods.

## Defects vs. spec
- None observed. No refactor loop triggered.
