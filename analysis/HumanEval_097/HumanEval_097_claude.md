# HumanEval_097 — Claude

## Generation
- Source: `generated_code/claude/HumanEval_097.java`
- Log: `llm_logs/claude/HumanEval_097_initial.md`
- Approach: single-expression `return Math.abs(a % 10) * Math.abs(b % 10);`.
  `Math.abs` neutralises the sign that Java's `%` inherits from the dividend,
  so the dataset case `multiply(14, -15) == 20` works without an explicit sign
  branch. No method-local conditionals.

## Base test
- File: `tests/base_tests/claude/HumanEval_097_BaseTest.java` (JUnit 6 port of
  `tests/base_tests/original/HumanEval_097/Main.java`).
- Adjusted dataset harness: `tests/base_tests/adjusted/HumanEval_097/Main.java`
  (added `import java.util.*;`). See `tests/base_tests/adjustment_log.md`.
- Adjusted-harness run: exit 0.
- JUnit 6 run: 8/8 pass — cases mirror dataset values
  (`(148,412)→16`, `(19,28)→72`, `(2020,1851)→0`, `(14,-15)→20`,
  `(76,67)→42`, `(17,27)→49`, `(0,1)→0`, `(0,0)→0`).
- No code defects observed against the spec at this stage; no refactor loop triggered.

## Improved suite
- File: `tests/improved_tests/claude/HumanEval_097_ImprovedTest.java` — 22/22 pass.
- Smells addressed:
  - assertion roulette (single-bag `List<Boolean>` in dataset → per-case
    `@Test` with descriptive `@DisplayName`),
  - magic numbers (each input pair is paired with a behavioral-intent name
    explaining the sign axis or the units-zero short-circuit),
  - eager test (split into nested classes by behavioral concern: both
    non-negative, zero-valued unit digit, negative inputs, dataset parity,
    invariants),
  - conditional logic in tests (no loops/ifs in test bodies; `assertAll`
    is used for invariant batches instead).
- Behavioral targets: `Math.abs` on a non-negative remainder vs. on a
  negative remainder; product == 0 path (zero operand or multiple of 10);
  product != 0 path; full sign-cross matrix `(±,±)`; commutativity;
  sign-insensitivity; "higher-order digits ignored"; result is always
  non-negative; result is bounded by 81.

## Manual black-box
- Notes: `tests/manual_tests/claude/HumanEval_097_blackbox.md` — equivalence
  classes V1–V18 covering single-digit positives, multi-digit positives,
  unit-digit-zero short-circuits (a or b a multiple of ten, a or b zero),
  the full sign-cross matrix, units-only invariance, and extreme magnitudes.
  Invalid/undefined-by-spec partitions: `Integer.MIN_VALUE` operand pinned
  as observed behavior (`MIN_VALUE % 10 == -8`, `Math.abs(-8) == 8`).
- Tests: `tests/manual_tests/claude/HumanEval_097_ManualTest.java` — 19/19 pass.
- Pinned undefined-by-spec behavior: extremely large magnitudes
  (`(99999, -99999)`) work because the implementation never multiplies the
  raw inputs, only their unit digits — no overflow path exists.

## Coverage (JaCoCo 0.8.12, `--release 21`)
- Raw outputs: `coverage_reports/HumanEval_097/claude/{base,improved,manual}/{jacoco.exec,jacoco.xml,jacoco.csv,html,junit.txt,jacoco.log}`.
- All three suites cover `Solution` at 13/13 instructions, 0/0 branches,
  2/2 lines, 2/2 cyclomatic, 2/2 methods. The implementation has no
  in-method branches (single-expression body), so JaCoCo reports zero
  branch counters; behavioral path coverage is documented in the suite
  rationale above and in the black-box notes.

## Defects vs. spec
- None observed. No refactor loop triggered.
