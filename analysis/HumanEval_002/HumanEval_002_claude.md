# HumanEval_002 — Per-prompt analysis (Claude)

## Function
`double truncateNumber(double number)` — return the fractional part of a positive
floating-point number.

## Generated code summary
Single expression: `number % 1.0`. Equivalent to `number - Math.floor(number)` for
positive inputs; O(1).

## Base test results
- Suite: `tests/base_tests/claude/HumanEval_002_BaseTest.java`
- Result: **5/5 pass**
- No adjustments needed.

## Coverage (JaCoCo)
| Suite    | Tests | Instr | Branch | Line | CC  | Method |
|----------|-------|-------|--------|------|-----|--------|
| base     | 5     | 7/7   | 0/0    | 2/2  | 2/2 | 2/2   |
| improved | 11    | 7/7   | 0/0    | 2/2  | 2/2 | 2/2   |
| manual   | 10    | 7/7   | 0/0    | 2/2  | 2/2 | 2/2   |

No branches in implementation — JaCoCo reports 0 branch counters. Behavioral coverage
documented: exact integers (result=0), small fraction, large fraction, number < 1.

## Improved test rationale
Smells: assertion roulette, magic numbers. Targets: result-in-[0,1) invariant,
additive reconstruction invariant (floor+frac==n).

## Manual black-box
See `tests/manual_tests/claude/HumanEval_002_blackbox.md`. V1 exact integers,
V2 small fraction, V3 large fraction, V4 number < 1, V5 large integer part.

## Defects
None. `number % 1.0` is correct for all positive double inputs.

## Refactor loop
Not triggered.
