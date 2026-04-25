# HumanEval_019 - claude

## Generation

- Source: `generated_code/claude/HumanEval_019.java`
- Log: `llm_logs/claude/HumanEval_019_initial.md`
- Approach: guard for `null` or empty/whitespace input (returns `""`); build a `HashMap<String,Integer>` mapping each word to its numeric value; split the trimmed input on spaces; sort the array using `Comparator.comparingInt(wordToNum::get)`; rejoin with spaces.
- Implementation note: a `numToWord` reverse-lookup array is declared but never used — dead code, harmless.
- Refactor loop: not triggered; all suites pass on the initial generated file.

## Base test

- Dataset harness, original: `tests/base_tests/original/HumanEval_019/Main.java`.
- Dataset harness, adjusted: `tests/base_tests/adjusted/HumanEval_019/Main.java`.
- Adjustment: added `import java.util.*;` and `import java.lang.*;` so the standalone harness compiles. Assertion logic, inputs, and expected values unchanged.
- Adjusted-harness run: compiled with `javac --release 21`; `java Main` exits 0.
- JUnit 6 port: `tests/base_tests/claude/HumanEval_019_BaseTest.java` — 4/4 pass.
- Base coverage on `Solution`: 135/135 instructions, **3/4 branches**, 17/17 lines, 3/4 cyclomatic, 2/2 methods.

## Test-smell analysis

| Smell | Where it appears in the dataset harness | How the follow-up tests address it |
| ----- | --------------------------------------- | ---------------------------------- |
| Assertion roulette | Four checks in one `List<Boolean>`, failing with a generic `AssertionError`. | Base JUnit port splits into named tests; improved suite uses parameterized rows with labels. |
| Magic expected values | Expected strings embedded in boolean expressions. | Tests use explicit `(input, expected)` pairs. |
| Eager test | One `main` covers four different scenarios. | Improved/manual suites group by dataset cases, guard branches, sorting correctness, boundaries, and invalid inputs. |
| Missing null-guard coverage | No `null` input in dataset harness → `numbers == null` true branch uncovered. | Improved `GuardBranches` and manual `InvalidOrPinned` both exercise null, closing the gap. |

## Branch-coverage analysis

JaCoCo reports 4 branches in `Solution.sortNumbers`:

| # | Branch | Covered by base? | Covered by improved/manual? |
|---|--------|------------------|-----------------------------|
| 1 | `numbers == null` → true (return `""`) | **no** | yes (`null` input) |
| 2 | `numbers == null` → false | yes | yes |
| 3 | `numbers.trim().isEmpty()` → true (return `""`) | yes (`""`) | yes |
| 4 | `numbers.trim().isEmpty()` → false | yes | yes |

The base dataset suite misses branch 1 (null guard). Improved and manual suites close that gap to 4/4.

## Improved suite

- File: `tests/improved_tests/claude/HumanEval_019_ImprovedTest.java` — 12/12 pass.
- Adds null input (closes missing branch), whitespace-only, all-ten-words forward/reverse, duplicates, two-equal-words cases.
- Coverage on `Solution`: 135/135 instructions, 4/4 branches, 17/17 lines, 4/4 cyclomatic, 2/2 methods.

## Manual black-box

- Notes: `tests/manual_tests/claude/HumanEval_019_blackbox.md` — equivalence classes V1-V11, invalid/undefined I1-I2, and boundary table.
- Tests: `tests/manual_tests/claude/HumanEval_019_ManualTest.java` — 16/16 pass.
- Pinned behaviors:
  - `null` input → `""` (not specified by prompt; Claude guards it).
  - Unknown word (e.g., `"ten"`) → `NullPointerException` because `wordToNum.get("ten")` returns `null` and `Comparator.comparingInt` unboxes it.
- Coverage on `Solution`: 135/135 instructions, 4/4 branches, 17/17 lines, 4/4 cyclomatic, 2/2 methods.

## Defects vs. spec

- None observed against the prompt specification. The prompt example (`"three one five"` → `"one three five"`) passes correctly.
- The dead `numToWord` array is a minor code-quality issue but does not affect output.
- Null and unknown-word behaviors are undefined by the spec; tests pin current behavior only.

## Coverage roll-up

| Suite    | Tests | Pass | Instr   | Branch | Line  | CC  | Method |
| -------- | ----- | ---- | ------- | ------ | ----- | --- | ------ |
| Base     | 4     | 4    | 135/135 | 3/4    | 17/17 | 3/4 | 2/2    |
| Improved | 12    | 12   | 135/135 | 4/4    | 17/17 | 4/4 | 2/2    |
| Manual   | 16    | 16   | 135/135 | 4/4    | 17/17 | 4/4 | 2/2    |

Raw artifacts: `coverage_reports/HumanEval_019/claude/{base,improved,manual}/{jacoco.exec,jacoco.xml,jacoco.csv,html,junit.txt,jacoco.log}`.
