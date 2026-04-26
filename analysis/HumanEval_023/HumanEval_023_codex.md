# HumanEval_023 — Per-prompt analysis (Codex / GPT)

## Function
`int strlen(String string)` — return the length of the given string.

## Generated code summary
One-liner: `return string.length();`. Direct delegation to Java's `String.length()`.
Correct and idiomatic.

## Base test results
- Suite: `tests/base_tests/codex/HumanEval_023_BaseTest.java`
- Result: **4/4 pass**

## Coverage (JaCoCo)
| Suite    | Tests | Instr | Branch | Line | CC  | Method |
|----------|-------|-------|--------|------|-----|--------|
| base     | 4     | 6/6   | 0/0    | 2/2  | 2/2 | 2/2   |
| improved | 8     | 6/6   | 0/0    | 2/2  | 2/2 | 2/2   |
| manual   | 9     | 6/6   | 0/0    | 2/2  | 2/2 | 2/2   |

Full coverage on all suites. No branches — single return statement.

## Improved test rationale
Smells: assertion roulette. Separated into named tests. Added invariant test
confirming result matches `String.length()` across multiple inputs. Added
special-character and all-spaces cases.

## Manual black-box
See `tests/manual_tests/codex/HumanEval_023_blackbox.md`. V1–V8 cover empty,
single-char, alphabetic, spaces, digits, and special characters. Boundary:
empty string (length 0), single space (length 1).

## Defects
None.

## Refactor loop
Not triggered.
