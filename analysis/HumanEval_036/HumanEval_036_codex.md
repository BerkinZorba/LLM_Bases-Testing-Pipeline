# HumanEval_036 — Codex analysis

## 1. Generated artifact

- File: `generated_code/codex/HumanEval_036.java`
- Initial-generation log: `llm_logs/codex/HumanEval_036_initial.md`
- Independent Codex test-generation logs:
  - `llm_logs/codex/HumanEval_036_test_improvement.md`
  - `llm_logs/codex/HumanEval_036_manual_assistance.md`
- Refactor loop: not triggered.

## 2. Base test results

- JUnit 6 base port: `tests/base_tests/codex/HumanEval_036_BaseTest.java`
- Dataset harness used for execution: `tests/base_tests/adjusted/HumanEval_036/Main.java`
- Original dataset harness: `tests/base_tests/original/HumanEval_036/Main.java`
- Adjustment: added `import java.util.*;` so the dataset harness compiles as a standalone Java file. Logged in `tests/base_tests/adjustment_log.md`. Generated code unchanged.
- Execution method: compiled `generated_code/codex/HumanEval_036.java` with the adjusted `Main.java`, then ran `Main`.
- Compiler/runtime used: `/opt/homebrew/opt/openjdk/bin/javac` and `/opt/homebrew/opt/openjdk/bin/java`
- Result: compile succeeded; `Main` exited with status 0 and no `AssertionError`.
- JUnit 6 result: 8/8 passed.
- Coverage on base suite (`Solution`): 37/37 instructions, 10/10 branches, 10/10 lines, 7/7 cyclomatic, 2/2 methods.

## 3. Observed behavior from base testing

The Codex implementation matches the dataset expectations covered by the base harness:

- `50` -> `0`
- `78` -> `2`
- `79` -> `3`
- `100` -> `3`
- `200` -> `6`
- `4000` -> `192`
- `10000` -> `639`
- `100000` -> `8026`

No base-test incompatibility beyond the pre-existing missing import in the original dataset harness was observed.

## 4. Test-smell analysis of the dataset harness

- Assertion roulette: `Main.java` aggregates eight boolean checks and only throws a bare `AssertionError`, so the failing input would be hidden.
- Magic numbers: expected totals are inline without names or diagnostic messages.
- Eager test: one `main` method exercises multiple behaviors at once.
- Poor failure diagnostics: there is no per-case isolation or explanatory output.

The Codex improved suite addresses these with individually attributable assertions, explicit boundary/delta checks, and separated dataset vs branch-focused inputs.

## 5. Branch-coverage analysis

The implementation’s control flow is small but non-trivial:

1. outer `for` loop entered vs skipped
2. divisibility filter true vs false
3. inner digit loop entered vs skipped
4. digit-is-seven check true vs false
5. repeated digit-loop iterations for multi-digit values

The base suite already achieves full structural coverage on `Solution`. The improved and manual suites therefore focus on making those paths behaviorally attributable:

- loop skipped at `n <= 1`
- eligible values before and after `77`
- one-`7` vs two-`7` contributors
- overlap of divisibility by both 11 and 13 at `143`
- later qualifying contributor at `176`

JaCoCo coverage was generated for all three Codex suites. To avoid Java 25 runtime-class instrumentation issues, the agent was restricted to `Solution*`, which is the only class reported.

## 6. Improved and manual test execution

- Improved tests: `tests/improved_tests/codex/HumanEval_036_ImprovedTest.java`
- Improved JUnit 6 result: 25/25 passed.
- Improved coverage (`Solution`): 37/37 instructions, 10/10 branches, 10/10 lines, 7/7 cyclomatic, 2/2 methods.
- Manual black-box notes: `tests/manual_tests/codex/HumanEval_036_blackbox.md`
- Manual executable tests: `tests/manual_tests/codex/HumanEval_036_ManualTest.java`
- Manual JUnit 6 result: 14/14 passed.
- Manual coverage (`Solution`): 37/37 instructions, 10/10 branches, 10/10 lines, 7/7 cyclomatic, 2/2 methods.

Raw artifacts: `coverage_reports/HumanEval_036/codex/{base,improved,manual}/jacoco.{exec,xml,csv,html/}` plus `junit.txt`.

## 7. Defects observed

No defect was observed against the prompt specification during base, improved, or manual testing.

The following behaviors are not specified by the prompt and are pinned in the Codex-authored improved/manual suites:

- `n <= 1` returns `0`
- negative bounds return `0`
- `Integer.MIN_VALUE` returns `0`

These do not justify a refactor because the prompt frames the task in terms of positive counting below `n`.

## 8. Coverage roll-up

| Suite | Tests | Pass | Instr | Branch | Line | CC | Method |
|-------|-------|------|-------|--------|------|----|--------|
| Base (JUnit 6) | 8 | 8 | 37/37 | 10/10 | 10/10 | 7/7 | 2/2 |
| Improved | 25 | 25 | 37/37 | 10/10 | 10/10 | 7/7 | 2/2 |
| Manual | 14 | 14 | 37/37 | 10/10 | 10/10 | 7/7 | 2/2 |
