# HumanEval_019 - Codex Phase 1 Notes

## Prompt

Function: `Solution.sortNumbers(String numbers)`

Task: sort a space-delimited string of valid numeral words from `zero` through `nine` by numeric value.

## Initial code status

- Existing generated source: `generated_code/codex/HumanEval_019.java`
- Initial log added: `llm_logs/codex/HumanEval_019_initial.md`
- The generated source was not modified before dataset base testing.

## Base test execution

- Dataset harness source: `tests/base_tests/adjusted/HumanEval_019/Main.java`
- Command used: compiled `generated_code/codex/HumanEval_019.java` with the adjusted dataset harness using `javac --release 21`, then ran `Main`.
- Result: passed, exit status 0.
- Compatibility notes: the original dataset harness lacked `java.util` imports. The adjusted copy adds imports only; assertion logic, expected values, and generated code are unchanged.

JUnit 6 base port:

- File: `tests/base_tests/codex/HumanEval_019_BaseTest.java`
- Cases: the four dataset examples as individually attributable tests.

## Test smell analysis

Dataset harness issues observed:

- Assertion roulette: four expectations are collected in one boolean list, so failure location is less direct.
- Eager test shape: one `main` method exercises all cases.
- No named test cases or failure messages from the dataset harness.
- Limited boundary coverage: no full vocabulary case, duplicate values, two-token swap, null input, invalid token, case mismatch, or spacing edge cases.

Improved suite response:

- `tests/improved_tests/codex/HumanEval_019_ImprovedTest.java` separates dataset cases from extended valid cases with parameterized JUnit 6 tests.
- Added reverse full-vocabulary ordering, duplicates, repeated zeros, alternating duplicates, and invalid-input behavior checks.
- Null, invalid words, and extra spaces are pinned because the prompt only defines valid space-delimited numeral words.

## Manual black-box assessment

- Design document: `tests/manual_tests/codex/HumanEval_019_blackbox.md`
- Executable suite: `tests/manual_tests/codex/HumanEval_019_ManualTest.java`
- Covered valid classes: empty input, one token, already sorted subset, full vocabulary reverse order, arbitrary subset, duplicate values, repeated zeros, and alternating duplicates.
- Covered invalid or underspecified classes: null input, invalid token, extra/leading/trailing spaces, single invalid token without comparison, and different case requiring comparison.

## Coverage evidence

Coverage artifacts are stored under:

- `coverage_reports/HumanEval_019/codex/base/`
- `coverage_reports/HumanEval_019/codex/improved/`
- `coverage_reports/HumanEval_019/codex/manual/`

Summary after JUnit 6 execution:

- Base suite: 4/4 tests passed; `Solution` coverage was 90/90 instructions, 3/4 branches, 17/17 lines, 3/4 complexity, and 2/2 methods.
- Improved suite: 13/13 tests passed; `Solution` coverage was 90/90 instructions, 4/4 branches, 17/17 lines, 4/4 complexity, and 2/2 methods.
- Manual suite: 16/16 tests passed; `Solution` coverage was 90/90 instructions, 4/4 branches, 17/17 lines, 4/4 complexity, and 2/2 methods.
- During manual-suite execution, one underspecified behavior was corrected in the artifact before accepting results: a single invalid token such as `"One"` is returned unchanged because no sort comparison is needed.

## Defect and refactor notes

- No defect was confirmed against the prompt specification.
- No refactor loop has been triggered.
