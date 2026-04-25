# HumanEval_018 - Codex Phase 1 Notes

## Prompt

Function: `Solution.howManyTimes(String string, String substring)`

Task: count how many times `substring` appears in `string`, including overlapping occurrences.

## Initial code status

- Existing generated source: `generated_code/codex/HumanEval_018.java`
- Initial log added: `llm_logs/codex/HumanEval_018_initial.md`
- The generated source was not modified before dataset base testing.

## Base test execution

- Dataset harness source: `tests/base_tests/adjusted/HumanEval_018/Main.java`
- Command used: compiled `generated_code/codex/HumanEval_018.java` with the adjusted dataset harness using `javac --release 21`, then ran `Main`.
- Result: passed, exit status 0.
- Compatibility notes: adjusted harness already contains imports needed for standalone compilation. No generated-code change was made.

JUnit 6 base port:

- File: `tests/base_tests/codex/HumanEval_018_BaseTest.java`
- Cases: empty source, single-character repeated match, overlapping multi-character match, prefix word match.

## Test smell analysis

Dataset harness issues observed:

- Assertion roulette: four expectations are collected in one boolean list, so failure location is less direct.
- Eager test shape: one `main` method exercises all cases.
- No named test cases or failure messages from the dataset harness.
- Limited boundary coverage: no exact-length match, substring longer than source, absent substring, empty substring, null handling, punctuation, or case-sensitivity checks.

Improved suite response:

- `tests/improved_tests/codex/HumanEval_018_ImprovedTest.java` separates dataset cases from extended cases with parameterized JUnit 6 tests.
- Added exact match, longer substring, absent substring, repeated overlapping windows, whitespace, and case-sensitive examples.
- Empty substring is pinned to current behavior because the prompt does not define it.

## Manual black-box assessment

- Design document: `tests/manual_tests/codex/HumanEval_018_blackbox.md`
- Executable suite: `tests/manual_tests/codex/HumanEval_018_ManualTest.java`
- Covered valid classes: empty source, one-character matches, overlapping multi-character matches, exact match, substring longer than source, absent substring, prefix/suffix placement, repeated non-overlapping placement, whitespace, punctuation, and case sensitivity.
- Covered invalid or underspecified classes: null source, null substring, and empty substring.

## Coverage evidence

Coverage artifacts are stored under:

- `coverage_reports/HumanEval_018/codex/base/`
- `coverage_reports/HumanEval_018/codex/improved/`
- `coverage_reports/HumanEval_018/codex/manual/`

Summary after JUnit 6 execution:

- Base suite: 4/4 tests passed; `Solution` coverage was 36/38 instructions, 7/10 branches, 7/8 lines, 4/7 complexity, and 2/2 methods.
- Improved suite: 17/17 tests passed; `Solution` coverage was 38/38 instructions, 8/10 branches, 8/8 lines, 5/7 complexity, and 2/2 methods.
- Manual suite: 19/19 tests passed; `Solution` coverage was 38/38 instructions, 10/10 branches, 8/8 lines, 7/7 complexity, and 2/2 methods.
- During improved-suite execution, one authoring mistake was corrected before accepting results: `"mississippi"` contains `"issi"` twice.

## Defect and refactor notes

- No defect was confirmed against the prompt specification.
- No refactor loop has been triggered.
