# HumanEval_007 - claude

## Generation

- Source: `generated_code/claude/HumanEval_007.java`
- Log: `llm_logs/claude/HumanEval_007_initial.md`
- Approach: create a new `ArrayList`, iterate over the input list with an enhanced for-loop, and append each string whose `String.contains(substring)` predicate returns true.
- Refactor loop: not triggered; all suites pass on the initial generated file.

## Base test

- Dataset harness, original: `tests/base_tests/original/HumanEval_007/Main.java`.
- Dataset harness, adjusted: `tests/base_tests/adjusted/HumanEval_007/Main.java`.
- Adjustment: added `import java.util.*;` so the standalone dataset harness compiles. Assertion logic, inputs, and expected values are unchanged; generated code was not modified.
- Adjusted-harness run: compiled with `javac --release 21`; `java Main` exits 0.
- JUnit 6 port: `tests/base_tests/claude/HumanEval_007_BaseTest.java` — 4/4 pass.
- Base coverage on `Solution`: 28/28 instructions, 4/4 branches, 7/7 lines, 4/4 cyclomatic, 2/2 methods.

## Test-smell analysis

| Smell                 | Where it appears in the dataset harness                                                        | How the follow-up tests address it                                                                             |
| --------------------- | ---------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------- |
| Assertion roulette    | Four checks are stored in a single `List<Boolean>`, then fail with a generic `AssertionError`. | Base JUnit port splits them into named tests; improved suite uses parameterized rows with labels.              |
| Magic expected values | Expected lists are embedded in boolean expressions.                                            | Tests use explicit `(input, substring, expected)` rows.                                                        |
| Eager test            | One `main` method exercises empty, exact, broader, and middle-substring cases.                 | Improved/manual suites group cases by dataset behavior, behavioral properties, boundaries, and invalid inputs. |
| Sparse edge coverage  | No empty-substring, duplicate-preservation, case-sensitivity, null, or result-aliasing checks. | Improved/manual suites cover these directly.                                                                   |

## Branch-coverage analysis

JaCoCo reports four branches in `Solution.filterBySubstring`:

1. The enhanced for-loop entry/exit branch.
2. The `if (s.contains(substring))` true/false branch.

The base suite already covers both loop states (empty and non-empty lists) and both predicate outcomes (matching and non-matching elements). Improved and manual suites therefore preserve 100% branch coverage while broadening behavioral evidence.

## Improved suite

- File: `tests/improved_tests/claude/HumanEval_007_ImprovedTest.java` — 11/11 pass.
- Adds order preservation, duplicate preservation, case-sensitive matching, empty-substring behavior, empty-string element behavior, returned-list identity, and repeated-call state isolation.
- Coverage on `Solution`: 28/28 instructions, 4/4 branches, 7/7 lines, 4/4 cyclomatic, 2/2 methods.

## Manual black-box

- Notes: `tests/manual_tests/claude/HumanEval_007_blackbox.md` — equivalence classes V1-V12, invalid/undefined I1-I3, and boundary table.
- Tests: `tests/manual_tests/claude/HumanEval_007_ManualTest.java` — 18/18 pass.
- Pinned undefined-by-spec behavior: `null` list, `null` substring, and `null` list element all throw `NullPointerException`.
- Coverage on `Solution`: 28/28 instructions, 4/4 branches, 7/7 lines, 4/4 cyclomatic, 2/2 methods.

## Defects vs. spec

- None observed against the prompt specification.
- The null-input behaviors are not specified by the prompt; manual tests pin current behavior only so future changes are visible.

## Coverage roll-up

| Suite    | Tests | Pass | Instr | Branch | Line | CC  | Method |
| -------- | ----- | ---- | ----- | ------ | ---- | --- | ------ |
| Base     | 4     | 4    | 28/28 | 4/4    | 7/7  | 4/4 | 2/2    |
| Improved | 11    | 11   | 28/28 | 4/4    | 7/7  | 4/4 | 2/2    |
| Manual   | 18    | 18   | 28/28 | 4/4    | 7/7  | 4/4 | 2/2    |

Raw artifacts: `coverage_reports/HumanEval_007/claude/{base,improved,manual}/{jacoco.exec,jacoco.xml,jacoco.csv,html,junit.txt,jacoco.log}`.
