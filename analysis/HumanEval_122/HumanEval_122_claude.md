# HumanEval_122 — Claude

## Generation
- Source: `generated_code/claude/HumanEval_122.java`
- Log: `llm_logs/claude/HumanEval_122_initial.md`
- Approach: `addElements(List<Integer> arr, int k)` runs a single `for` loop
  over the prefix `arr[0..k-1]` and accumulates each value `v` with
  `Math.abs(v) < 100`. The predicate interprets "at most two digits" as
  `|v| < 100` (range `[-99, 99]`), so negative two-digit values like
  `-99` qualify and are added with their sign, matching the dataset case
  `addElements([1, -2, -3, 41, 57, 76, 87, 88, 99], 3) == -4`.

## Base test
- File: `tests/base_tests/claude/HumanEval_122_BaseTest.java` (JUnit 6 port of
  `tests/base_tests/original/HumanEval_122/Main.java`).
- Adjusted dataset harness: `tests/base_tests/adjusted/HumanEval_122/Main.java`
  (added `import java.util.*;` so the dataset `Main.java`, which references
  `List` and `Arrays`, compiles standalone). See
  `tests/base_tests/adjustment_log.md`.
- Adjusted-harness run: exit 0 (`ADJUSTED_OK`).
- JUnit 6 run: 5/5 pass — all dataset cases including the negatives-count
  case `(-4)`, the all-three-digit prefix case `(0)`, the all-qualifying
  prefix case `(125)`, the mixed dataset example `(24)`, and the
  singleton `([1], 1) -> 1`.
- No code defects observed against the spec at this stage; no refactor loop triggered.

## Improved suite
- File: `tests/improved_tests/claude/HumanEval_122_ImprovedTest.java` — 21/21 pass.
- Smells addressed:
  - assertion roulette (the dataset bundled five booleans into a single
    `List<Boolean> correct` and asserted `!contains(false)` →
    per-case `@Test` with descriptive `@DisplayName`),
  - magic numbers (each `(arr, k)` pair is paired with an intent name:
    dataset, two-digit branch, loop bound, singleton, mixed signs, larger),
  - eager test (split into nested classes by behavioral concern: dataset,
    branch coverage of the predicate, loop bound, singleton, mixed signs,
    larger / homogeneous inputs),
  - conditional logic in tests (no `if` in test bodies; `assertAll` is
    used for invariant batches; explicit per-case asserts replace looped
    assertions).
- Behavioral targets:
  - predicate `|v| < 100` taken (qualifying values) and not-taken
    (rejected values), at both positive and negative edges (99/100 and
    -99/-100),
  - `for`-loop guard `i < k` taken (range size ≥ 1) and not-taken (after
    last iteration),
  - loop truncation by `k`: `k=1` ignores tail; `k == arr.size()` scans
    everything; `k=3` ignores qualifying tail elements past index 2,
  - singleton ranges: single qualifying / non-qualifying / negative
    two-digit values,
  - mixed-sign arithmetic: cancellation to zero, all-negative results,
    invariant batch on `[10, 20, 1000]` over `k ∈ {1, 2, 3}`,
  - constraint upper bound: 100-element list (`Collections.nCopies`),
  - all-out-of-range and symmetric edge mix `[-99, -100, 99, 100]`.

## Manual black-box
- Notes: `tests/manual_tests/claude/HumanEval_122_blackbox.md` — equivalence
  classes V1–V11 covering single qualifying / non-qualifying elements,
  every-prefix-qualifies, none-qualifies, mixed dataset rows, sign
  preservation, sign cancellation, tail truncation by `k`, and the
  constraint upper bound (length-100 list). Boundary table covers the
  predicate edges at `99 / 100 / -99 / -100`, the symmetric mix, and the
  loop boundaries `k == arr.size()` and `k = 1`. Invalid /
  undefined-by-spec partitions U1 (`k = 0`), U2 (`Integer.MAX_VALUE`),
  and U3 (`Integer.MIN_VALUE`) are pinned as observed behavior; U3 in
  particular documents the `Math.abs(Integer.MIN_VALUE) == Integer.MIN_VALUE`
  quirk that lets `MIN_VALUE` slip past the predicate.
- Tests: `tests/manual_tests/claude/HumanEval_122_ManualTest.java` — 21/21 pass.
- Pinned undefined-by-spec behavior:
  - `(arr, 0) -> 0` because the loop body never runs.
  - `[Integer.MAX_VALUE]` -> `0` because the predicate excludes large positives.
  - `[Integer.MIN_VALUE]` -> `Integer.MIN_VALUE` because
    `Math.abs(Integer.MIN_VALUE)` overflows back to `Integer.MIN_VALUE`,
    which is `< 100` (signed) and so survives the predicate. This is a
    Java-level quirk, not a spec contract.

## Coverage (JaCoCo 0.8.12, `--release 21`)
- Raw outputs: `coverage_reports/HumanEval_122/claude/{base,improved,manual}/{jacoco.exec,jacoco.xml,jacoco.csv,html,junit.txt,jacoco.log}`.
- All three suites cover `Solution` at 28/28 instructions, 4/4 branches,
  7/7 lines, 4/4 cyclomatic, 2/2 methods. The four branches are the
  taken / not-taken transitions of the two conditionals (`for`-loop guard
  `i < k` and the `if (Math.abs(v) < 100)` predicate); both are
  exercised in every suite by the dataset cases and the boundary cases.

## Defects vs. spec
- None observed. No refactor loop triggered. The `Math.abs(v) < 100`
  predicate aligns with the dataset's negatives-count case, and the
  prefix-only iteration matches the "first k elements" wording of the
  spec.
