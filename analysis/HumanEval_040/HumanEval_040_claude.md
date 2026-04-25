# HumanEval_040 - claude

## Generation

- Source (initial, truncated): `generated_code/claude/HumanEval_040.java`
- Source (v2, complete): `generated_code/claude/HumanEval_040_v2.java`
- Initial log: `llm_logs/claude/HumanEval_040_initial.md`
- Refactor log: `llm_logs/claude/HumanEval_040_refactor_01.md`
- Approach: guard `n < 3` → return false; copy + sort the list; outer loop over anchor index `i`; inner two-pointer scan (`left = i+1`, `right = n-1`) comparing `sorted[i] + sorted[left] + sorted[right]` against zero.
- Refactor loop: **triggered** — the initial response was truncated and did not compile (`reached end of file while parsing` at `while (left < right)`). A corrected complete implementation was generated and saved as `HumanEval_040_v2.java`. All subsequent test suites run against v2.

## Base test

- Dataset harness, original: `tests/base_tests/original/HumanEval_040/Main.java`.
- Dataset harness, adjusted: `tests/base_tests/adjusted/HumanEval_040/Main.java`.
- Adjustment: added `import java.util.*;` and `import java.lang.*;` so the standalone harness compiles. Assertion logic, inputs, and expected values are unchanged.
- Adjusted-harness run (v2): compiled with `javac --release 21`; `java Main` exits 0.
- JUnit 6 port: `tests/base_tests/claude/HumanEval_040_BaseTest.java` — 9/9 pass against v2.
- Base coverage on `Solution` (v2): 68/68 instructions, 10/10 branches, 17/17 lines, 7/7 cyclomatic, 2/2 methods.

## Test-smell analysis

| Smell | Where it appears in the dataset harness | How the follow-up tests address it |
| ----- | --------------------------------------- | ---------------------------------- |
| Assertion roulette | Nine checks in one `List<Boolean>`, failing with a generic `AssertionError`. | Base JUnit port splits into named tests; improved suite uses separate parameterized streams for true/false expectations. |
| Magic expected values | Expected booleans embedded in negated/non-negated expressions. | Tests use `assertTrue`/`assertFalse` with descriptive names and labels. |
| Eager test | One `main` covers nine distinct inputs. | Improved/manual suites group by dataset cases, guard branch, three-element boundary, behavioral properties, and invalid inputs. |
| Missing guard-branch coverage | No empty, one-element, or two-element inputs in the dataset harness (only `[1]` for single-element). | Improved suite adds all three short-list cases; manual suite re-verifies them via boundary group. |

## Branch-coverage analysis

JaCoCo reports 10 branches in `Solution.triplesSumToZero` (v2):

| # | Branch | Covered by base? |
|---|--------|-----------------|
| 1 | `n < 3` → true (early return false) | yes (`[1]`) |
| 2 | `n < 3` → false | yes |
| 3 | Outer for-loop: condition true | yes |
| 4 | Outer for-loop: condition false (loop ends) | yes |
| 5 | Inner while: condition true | yes |
| 6 | Inner while: condition false (pointers meet) | yes |
| 7 | `sum == 0` → true (return true) | yes |
| 8 | `sum == 0` → false, falls to `sum < 0` | yes |
| 9 | `sum < 0` → true (left++) | yes |
| 10 | `sum < 0` → false (right--) | yes |

All 10 branches are covered by the base suite. Improved and manual suites preserve 100% branch coverage while broadening behavioral evidence.

## Improved suite

- File: `tests/improved_tests/claude/HumanEval_040_ImprovedTest.java` — 21/21 pass.
- Adds empty/two-element guard branches, three-element boundary, all-positive/all-negative/duplicate-value cases, input immutability.
- Coverage on `Solution` (v2): 68/68 instructions, 10/10 branches, 17/17 lines, 7/7 cyclomatic, 2/2 methods.

## Manual black-box

- Notes: `tests/manual_tests/claude/HumanEval_040_blackbox.md` — equivalence classes V1-V15, invalid/undefined I1, and boundary table.
- Tests: `tests/manual_tests/claude/HumanEval_040_ManualTest.java` — 21/21 pass.
- Pinned undefined-by-spec behavior: `null` list throws `NullPointerException`.
- Coverage on `Solution` (v2): 68/68 instructions, 10/10 branches, 17/17 lines, 7/7 cyclomatic, 2/2 methods.

## Defects vs. spec

- **Generation defect (iteration 1)**: initial Claude output was truncated mid-method. The file did not compile. Refactor loop triggered; v2 completes the two-pointer body.
- No logical defects in v2 against the prompt specification. All nine dataset cases pass, plus all behavioral and boundary cases.

## Coverage roll-up

| Suite    | Tests | Pass | Instr | Branch | Line  | CC  | Method |
| -------- | ----- | ---- | ----- | ------ | ----- | --- | ------ |
| Base     | 9     | 9    | 68/68 | 10/10  | 17/17 | 7/7 | 2/2    |
| Improved | 21    | 21   | 68/68 | 10/10  | 17/17 | 7/7 | 2/2    |
| Manual   | 21    | 21   | 68/68 | 10/10  | 17/17 | 7/7 | 2/2    |

All suites tested against `generated_code/claude/HumanEval_040_v2.java`.
Raw artifacts: `coverage_reports/HumanEval_040/claude/{base,improved,manual}/{jacoco.exec,jacoco.xml,jacoco.csv,html,junit.txt,jacoco.log}`.
