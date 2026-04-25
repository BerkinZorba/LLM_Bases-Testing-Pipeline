# HumanEval_095 — Codex analysis

## 1. Generated artifacts

- Initial file: `generated_code/codex/HumanEval_095.java`
- Refined file: `generated_code/codex/HumanEval_095_v2.java`
- Initial-generation log: `llm_logs/codex/HumanEval_095_initial.md`
- Independent Codex test-generation logs:
  - `llm_logs/codex/HumanEval_095_test_improvement.md`
  - `llm_logs/codex/HumanEval_095_manual_assistance.md`
- Refactor log:
  - `llm_logs/codex/HumanEval_095_refactor_01.md`

The initial implementation passed the dataset base tests unchanged. A controlled refactor was triggered later because improved testing exposed acceptance of uncased-letter keys, which do not satisfy the stated lowercase-or-uppercase contract.

## 2. Base test results

- JUnit 6 base port: `tests/base_tests/codex/HumanEval_095_BaseTest.java`
- Dataset harness used for execution: `tests/base_tests/adjusted/HumanEval_095/Main.java`
- Original dataset harness: `tests/base_tests/original/HumanEval_095/Main.java`
- Adjustment: added `import java.util.*;` so the dataset harness compiles as a standalone Java file. Logged in `tests/base_tests/adjustment_log.md`. Generated code unchanged during the base-test step.
- Execution method: compiled the generated solution with the adjusted `Main.java`, then ran `Main`.
- Compiler/runtime used: `/opt/homebrew/opt/openjdk/bin/javac` and `/opt/homebrew/opt/openjdk/bin/java`
- Result: compile succeeded; `Main` exited with status 0 and no `AssertionError`.
- JUnit 6 result on the final refactored solution: 7/7 passed.
- Coverage on the base suite (`Solution`, v2): 100/103 instructions, 26/30 branches, 31/33 lines, 14/18 cyclomatic, 3/3 methods.

## 3. Observed behavior from base testing

The dataset-visible contract was satisfied by the initial Codex implementation:

- lowercase string keys only -> `true`
- uppercase string keys only -> `true`
- mixed lowercase/uppercase keys -> `false`
- non-string key present -> `false`
- mixed-case words such as `"Name"` -> `false`
- empty map -> `false`

No dataset expected-value incompatibility was observed. The only base-test issue was the missing-import compatibility fix in the original harness.

## 4. Test-smell analysis of the dataset harness

- Assertion roulette: the dataset `Main.java` aggregates all checks into one `List<Boolean>` and throws a bare `AssertionError`, so the failing case is not attributable.
- Eager test: all scenarios are packed into one `main` method instead of isolated cases.
- Limited partition coverage: the dataset examples do not explore empty-string keys, digit-only keys, separator/digit combinations, or uncased-letter keys.
- No explicit branch intent: the helper logic needed for case classification is not pressured by the base harness beyond a narrow sample.

The Codex improved/manual suites address these by using isolated assertions, named partitions, and explicit boundary classes.

## 5. Branch-coverage analysis

The final helper-based implementation has meaningful control flow in two places:

- `checkDictCase(...)`: empty-map rejection, non-string rejection, invalid-case rejection, first-key expectation capture, and later mismatch rejection.
- `getUniformCase(...)`: non-letter skipping, lowercase vs uppercase letter detection, mixed-case rejection, and no-cased-letter rejection.

Base coverage left the helper partially uncovered because the dataset inputs do not exercise:

- strings containing only non-letters (for example `""` or `"123"`)
- strings mixing letters with separators/digits
- strings containing letters that are neither lowercase nor uppercase

The improved/manual suites closed those gaps and reached full branch coverage on the refactored solution.

## 6. Improved and manual test execution

- Improved tests: `tests/improved_tests/codex/HumanEval_095_ImprovedTest.java`
- Improved JUnit 6 result: 17/17 passed.
- Improved coverage (`Solution`, v2): 103/103 instructions, 30/30 branches, 33/33 lines, 18/18 cyclomatic, 3/3 methods.
- Manual black-box notes: `tests/manual_tests/codex/HumanEval_095_blackbox.md`
- Manual executable tests: `tests/manual_tests/codex/HumanEval_095_ManualTest.java`
- Manual JUnit 6 result: 13/13 passed.
- Manual coverage (`Solution`, v2): 103/103 instructions, 30/30 branches, 33/33 lines, 18/18 cyclomatic, 3/3 methods.

Raw artifacts: `coverage_reports/HumanEval_095/codex/{base,improved,manual}/jacoco.{exec,xml,csv,html/}` plus `junit.txt`.

## 7. Defect observed and refactor loop

Improved testing exposed a real contract mismatch in the initial implementation:

- Observed failing case before refactor: `{"中": 1}` returned `true`.
- Why this is a defect: the prompt contract allows only lowercase-only keys or uppercase-only keys. A key made of letters that are neither lowercase nor uppercase should not be accepted as satisfying the uppercase class by default.
- Cause in `HumanEval_095.java`: `getUniformCase(...)` returned `false` whenever no lowercase letters were seen, even when no uppercase letters were seen either.

Refactor action:

- Created `generated_code/codex/HumanEval_095_v2.java`
- Tightened the helper to return `null` when a key contains letters but no lowercase and no uppercase characters
- Re-ran the dataset harness plus base/improved/manual JUnit 6 suites against `v2`

No further defect was observed after the refactor.

## 8. Coverage roll-up

| Suite | Tests | Pass | Instr | Branch | Line | CC | Method |
|-------|-------|------|-------|--------|------|----|--------|
| Base (JUnit 6, v2) | 7 | 7 | 100/103 | 26/30 | 31/33 | 14/18 | 3/3 |
| Improved (v2) | 17 | 17 | 103/103 | 30/30 | 33/33 | 18/18 | 3/3 |
| Manual (v2) | 13 | 13 | 103/103 | 30/30 | 33/33 | 18/18 | 3/3 |
