# HumanEval_020 - claude

## Generation

- Source: `generated_code/claude/HumanEval_020.java`
- Log: `llm_logs/claude/HumanEval_020_initial.md`
- Approach: copy the input into a new `ArrayList`, sort it with `Collections.sort`, then scan adjacent pairs to find the pair with the minimum difference; return `Arrays.asList(first, second)`. Sorting guarantees the result is already in `(smaller, larger)` order and that the closest pair is always adjacent in the sorted list.
- Refactor loop: not triggered; all suites pass on the initial generated file.

## Base test

- Dataset harness, original: `tests/base_tests/original/HumanEval_020/Main.java`.
- Dataset harness, adjusted: `tests/base_tests/adjusted/HumanEval_020/Main.java`.
- Adjustment: added `import java.util.*;` and `import java.lang.*;` so the standalone dataset harness compiles. Assertion logic, inputs, and expected values are unchanged; generated code was not modified.
- Adjusted-harness run: compiled with `javac --release 21`; `java Main` exits 0.
- JUnit 6 port: `tests/base_tests/claude/HumanEval_020_BaseTest.java` — 5/5 pass.
- Base coverage on `Solution`: 74/74 instructions, 4/4 branches, 12/12 lines, 4/4 cyclomatic, 2/2 methods.

## Test-smell analysis

| Smell | Where it appears in the dataset harness | How the follow-up tests address it |
| ----- | --------------------------------------- | ---------------------------------- |
| Assertion roulette | Five checks in one `List<Boolean>`, failing with a generic `AssertionError`. | Base JUnit port splits into named tests; improved suite uses parameterized rows with labels. |
| Magic expected values | Expected lists embedded in boolean expressions. | Tests use explicit `(input, expected)` pairs. |
| Eager test | One `main` exercises five inputs covering overlapping scenarios. | Improved/manual suites group by dataset cases, list-size boundary, pair position, behavioral properties, and invalid inputs. |
| Missing boundary cases | No 2-element list, no negative numbers, no tie-breaking check. | Improved and manual suites add these explicitly. |

## Branch-coverage analysis

JaCoCo reports 4 branches in `Solution.findClosestElements`:

| # | Branch | Covered by base? | Covered by improved/manual? |
|---|--------|------------------|-----------------------------|
| 1 | Loop condition true (at least one iteration) | yes | yes |
| 2 | Loop condition false (loop terminates) | yes | yes |
| 3 | `if (diff < minDiff)` → true | yes (first iteration always fires) | yes |
| 4 | `if (diff < minDiff)` → false | yes (later iterations with larger diff) | yes |

All four branches are covered by the base suite. The improved and manual suites preserve 100% branch coverage while broadening behavioral evidence.

## Improved suite

- File: `tests/improved_tests/claude/HumanEval_020_ImprovedTest.java` — 16/16 pass.
- Adds 2-element boundary (natural, reversed, identical), closest-pair-at-start/end/middle, negative numbers, mixed-sign numbers, tie-breaking behavior, and input-list immutability.
- Coverage on `Solution`: 74/74 instructions, 4/4 branches, 12/12 lines, 4/4 cyclomatic, 2/2 methods.

## Manual black-box

- Notes: `tests/manual_tests/claude/HumanEval_020_blackbox.md` — equivalence classes V1-V12, invalid/undefined I1, and boundary table.
- Tests: `tests/manual_tests/claude/HumanEval_020_ManualTest.java` — 19/19 pass.
- Pinned undefined-by-spec behavior: `null` list throws `NullPointerException` (no null guard in implementation).
- Note: a single-element list (spec says ≥ 2) returns `[0.0, 0.0]` due to uninitialized `first`/`second` variables; documented in blackbox.md as I2 but not tested since it violates the spec precondition.
- Coverage on `Solution`: 74/74 instructions, 4/4 branches, 12/12 lines, 4/4 cyclomatic, 2/2 methods.

## Defects vs. spec

- None observed against the prompt specification. Both prompt examples (`[1.0,2.0,3.0,4.0,5.0,2.2] → [2.0,2.2]` and `[1.0,2.0,3.0,4.0,5.0,2.0] → [2.0,2.0]`) pass correctly.
- The null-input behavior is not specified by the prompt; manual tests pin current behavior only.

## Coverage roll-up

| Suite    | Tests | Pass | Instr | Branch | Line  | CC  | Method |
| -------- | ----- | ---- | ----- | ------ | ----- | --- | ------ |
| Base     | 5     | 5    | 74/74 | 4/4    | 12/12 | 4/4 | 2/2    |
| Improved | 16    | 16   | 74/74 | 4/4    | 12/12 | 4/4 | 2/2    |
| Manual   | 19    | 19   | 74/74 | 4/4    | 12/12 | 4/4 | 2/2    |

Raw artifacts: `coverage_reports/HumanEval_020/claude/{base,improved,manual}/{jacoco.exec,jacoco.xml,jacoco.csv,html,junit.txt,jacoco.log}`.
