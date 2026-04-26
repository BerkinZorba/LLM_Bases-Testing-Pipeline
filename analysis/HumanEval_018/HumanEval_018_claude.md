# HumanEval_018 - claude

## Generation

- Source: `generated_code/claude/HumanEval_018.java`
- Log: `llm_logs/claude/HumanEval_018_initial.md`
- Approach: guard clause returns 0 for empty string, empty substring, or substring longer than string; then iterates `i` from 0 to `string.length() - substring.length()` inclusive, counting positions where `string.startsWith(substring, i)` is true (correctly counts overlapping occurrences).
- Refactor loop: not triggered; all suites pass on the initial generated file.

## Base test

- Dataset harness, original: `tests/base_tests/original/HumanEval_018/Main.java`.
- Dataset harness, adjusted: `tests/base_tests/adjusted/HumanEval_018/Main.java`.
- Adjustment: added `import java.util.*;` and `import java.lang.*;` so the standalone dataset harness compiles. Assertion logic, inputs, and expected values are unchanged; generated code was not modified.
- Adjusted-harness run: compiled with `javac --release 21`; `java Main` exits 0.
- JUnit 6 port: `tests/base_tests/claude/HumanEval_018_BaseTest.java` — 4/4 pass.
- Base coverage on `Solution`: 37/37 instructions, **8/10 branches**, 8/8 lines, 5/7 cyclomatic, 2/2 methods.

## Test-smell analysis

| Smell                 | Where it appears in the dataset harness | How the follow-up tests address it |
| --------------------- | --------------------------------------- | ---------------------------------- |
| Assertion roulette    | Four checks stored in a single `List<Boolean>`, failing with a generic `AssertionError`. | Base JUnit port splits into named tests; improved suite uses parameterized rows with labels. |
| Magic expected values | Expected values embedded in boolean expressions. | Tests use explicit `(string, substring, expected)` rows. |
| Eager test            | One `main` method exercises four distinct behaviors. | Improved/manual suites group by dataset cases, prompt examples, guard branches, behavioral properties, boundaries, and invalid inputs. |
| Sparse guard coverage | No test for empty substring or substring-longer-than-string guards. | Guard-branch `@Nested` class exercises both missing branches explicitly. |

## Branch-coverage analysis

JaCoCo reports 10 branches in `Solution.howManyTimes`:

| # | Branch | Covered by base? | Covered by improved/manual? |
|---|--------|------------------|-----------------------------|
| 1 | `string.isEmpty()` → true | yes (`("", "x")`) | yes |
| 2 | `string.isEmpty()` → false | yes | yes |
| 3 | `substring.isEmpty()` → true | **no** | yes (`("abc", "")`) |
| 4 | `substring.isEmpty()` → false | yes | yes |
| 5 | `substring.length() > string.length()` → true | **no** | yes (`("a", "abc")`) |
| 6 | `substring.length() > string.length()` → false | yes | yes |
| 7 | Loop entry (iterations > 0) | yes | yes |
| 8 | Loop exit (condition false) | yes | yes |
| 9 | `startsWith(substring, i)` → true | yes | yes |
| 10 | `startsWith(substring, i)` → false | yes | yes |

The base suite misses the two guard-condition true branches (3 and 5). Both are covered by the improved and manual suites.

## Improved suite

- File: `tests/improved_tests/claude/HumanEval_018_ImprovedTest.java` — 16/16 pass.
- Adds all three guard-branch paths (empty string, empty substring, substring > string), prompt overlap examples, no-match, single-char, prefix/suffix, non-overlapping multiples, and consecutive-overlap cases.
- Coverage on `Solution`: 37/37 instructions, 10/10 branches, 8/8 lines, 7/7 cyclomatic, 2/2 methods.

## Manual black-box

- Notes: `tests/manual_tests/claude/HumanEval_018_blackbox.md` — equivalence classes V1-V15, invalid/undefined I1-I2, and boundary table.
- Tests: `tests/manual_tests/claude/HumanEval_018_ManualTest.java` — 22/22 pass.
- Pinned undefined-by-spec behavior: `null` string and `null` substring both throw `NullPointerException` (no null guards in the implementation).
- Coverage on `Solution`: 37/37 instructions, 10/10 branches, 8/8 lines, 7/7 cyclomatic, 2/2 methods.

## Defects vs. spec

- None observed against the prompt specification. All three prompt examples (`("", "a") → 0`, `("aaa", "a") → 3`, `("aaaa", "aa") → 3`) pass correctly.
- The empty-substring behavior (returns 0) is not specified by the prompt; the test pins current behavior only.
- The null-input behaviors are not specified by the prompt; manual tests pin current behavior only so future changes are visible.

## Coverage roll-up

| Suite    | Tests | Pass | Instr | Branch | Line | CC  | Method |
| -------- | ----- | ---- | ----- | ------ | ---- | --- | ------ |
| Base     | 4     | 4    | 37/37 | 8/10   | 8/8  | 5/7 | 2/2    |
| Improved | 16    | 16   | 37/37 | 10/10  | 8/8  | 7/7 | 2/2    |
| Manual   | 22    | 22   | 37/37 | 10/10  | 8/8  | 7/7 | 2/2    |

Raw artifacts: `coverage_reports/HumanEval_018/claude/{base,improved,manual}/{jacoco.exec,jacoco.xml,jacoco.csv,html,junit.txt,jacoco.log}`.
