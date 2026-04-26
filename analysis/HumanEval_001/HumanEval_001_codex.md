# HumanEval_001 — Per-prompt analysis (Codex / GPT)

## Function
`List<String> separateParenGroups(String paren_string)` — split a string of
balanced, non-nested top-level parenthesis groups (ignore spaces) into a list
of group strings.

## Generated code summary
Balance-counter approach: single pass over characters. Spaces skipped via
`continue`. '(' increments balance, ')' decrements. When balance returns to 0
and the buffer is non-empty, the accumulated group is flushed to the result list.
Clean and correct.

## Base test results
- Suite: `tests/base_tests/codex/HumanEval_001_BaseTest.java`
- Result: **4/4 pass**

## Coverage (JaCoCo)
| Suite    | Tests | Instr  | Branch | Line  | CC  | Method |
|----------|-------|--------|--------|-------|-----|--------|
| base     | 4     | 62/62  | 10/12  | 13/13 | 6/8 | 2/2   |
| improved | 12    | 62/62  | 10/12  | 13/13 | 6/8 | 2/2   |
| manual   | 10    | 62/62  | 10/12  | 13/13 | 6/8 | 2/2   |

**Missed branches (2)**: The `else if (c == ')')` chain has a false branch when c is
neither '(' nor ')' nor ' ' (no such input provided by spec). The short-circuit
`balance == 0 && current.length() > 0` has an unreachable false on `current.length() > 0`
because a non-space character is always appended before the check. Both are
spec-boundary cases — not defects.

## Improved test rationale
Smells: assertion roulette, magic numbers in base test. Branch targets: space-skip
(true via whitespace-only input), loop-never-entered (empty string), flush condition
false (mid-group when balance > 0), reset-and-continue (multi-group). Nested classes
separate concerns (empty, single group, multiple groups, space-stripping).

## Manual black-box
See `tests/manual_tests/codex/HumanEval_001_blackbox.md`. V1–V8 cover all valid
equivalence classes; two boundary cases cover deep nesting and space-stripping.

## Defects
None.

## Refactor loop
Not triggered.
