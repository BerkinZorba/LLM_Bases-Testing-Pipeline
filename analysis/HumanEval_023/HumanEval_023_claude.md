# HumanEval_023 — Per-prompt analysis (Claude)

## Function
`int strlen(String string)` — return the number of UTF-16 code units in the string.

## Generated code summary
Single delegation: `string.length()`. Correct per spec; O(1) in Java.

## Base test results
- Suite: `tests/base_tests/claude/HumanEval_023_BaseTest.java`
- Result: **4/4 pass**

## Coverage (JaCoCo)
| Suite    | Tests | Instr | Branch | Line | CC  | Method |
|----------|-------|-------|--------|------|-----|--------|
| base     | 4     | 6/6   | 0/0    | 2/2  | 2/2 | 2/2   |
| improved | 8     | 6/6   | 0/0    | 2/2  | 2/2 | 2/2   |
| manual   | 9     | 6/6   | 0/0    | 2/2  | 2/2 | 2/2   |

No branches. Behavioral coverage: empty string, single char, multi-char, spaces.

## Improved test rationale
Smells: assertion roulette, magic numbers. Invariants: non-negativity,
additive property strlen(a+b)==strlen(a)+strlen(b).

## Manual black-box
See `tests/manual_tests/claude/HumanEval_023_blackbox.md`.

## Defects
None. Note: `String.length()` counts UTF-16 code units; surrogate pairs count as 2.
This matches HumanEval test expectations.

## Refactor loop
Not triggered.
