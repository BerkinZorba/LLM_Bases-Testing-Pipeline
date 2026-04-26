# HumanEval_098 — Per-prompt analysis (Claude)

## Function
`int countUpper(String s)` — count uppercase vowels (A,E,I,O,U) at even indices.

## Generated code summary
Loop with step i+=2; checks `"AEIOU".indexOf(s.charAt(i)) != -1`. Clean and correct.

## Base test results
- Suite: `tests/base_tests/claude/HumanEval_098_BaseTest.java`
- Result: **5/5 pass** (all docstring examples pass)

## Coverage (JaCoCo)
| Suite    | Tests | Instr  | Branch | Line | CC  | Method |
|----------|-------|--------|--------|------|-----|--------|
| base     | 5     | 25/25  | 4/4    | 6/6  | 4/4 | 2/2   |
| improved | 19    | 25/25  | 4/4    | 6/6  | 4/4 | 2/2   |
| manual   | 13    | 25/25  | 4/4    | 6/6  | 4/4 | 2/2   |

Full branch coverage achieved from base tests. Branches: loop-guard (empty string),
loop body vowel check (true/false).

## Improved test rationale
Smells: assertion roulette, magic numbers. Branch targets: empty string, vowels at
even vs odd indices, all 5 uppercase vowels at index 0, uppercase consonants (not counted).

## Manual black-box
See `tests/manual_tests/claude/HumanEval_098_blackbox.md`. V1–V8 cover all cases;
parameterized test exercises each of A,E,I,O,U individually.

## Defects
None.

## Refactor loop
Not triggered.
