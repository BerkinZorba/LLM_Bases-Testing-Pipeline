# HumanEval_001 — Per-prompt analysis (Claude)

## Function
`List<String> separateParenGroups(String paren_string)` — split a balanced paren
string into top-level groups; ignore spaces.

## Generated code summary
Strategy: depth counter; append each non-space character; emit group when depth returns
to 0. Single pass, O(n) time and space.

## Base test results
- Suite: `tests/base_tests/claude/HumanEval_001_BaseTest.java`
- Result: **4/4 pass**
- No test-side adjustments needed; docstring examples and empty-input case all pass.

## Coverage (JaCoCo)
| Suite    | Tests | Instr   | Branch | Line  | CC  | Method |
|----------|-------|---------|--------|-------|-----|--------|
| base     | 4     | 60/60   | 9/10   | 14/14 | 6/7 | 2/2   |
| improved | 11    | 60/60   | 9/10   | 14/14 | 6/7 | 2/2   |
| manual   | 13    | 60/60   | 9/10   | 14/14 | 6/7 | 2/2   |

**Missed branch**: The `else if (c == ')')` condition is false when `c` is neither
`'('` nor `')'` nor `' '` (e.g. a letter). The spec guarantees only parens and spaces,
so this branch is unreachable under valid input. Pinned as spec-boundary, not a defect.

## Improved test rationale
Smells addressed: assertion roulette (each case separate @Test), magic numbers
(display names), eager test (nested classes by concern). Branch targets: space-skip,
depth-increment, depth-decrement with and without group emission.

## Manual black-box
See `tests/manual_tests/claude/HumanEval_001_blackbox.md`.
Cases: V1 empty, V2 spaces-only, V3 single flat, V4 single nested, V5 two flat,
V6 docstring, V7 deep nesting, V8 four groups. Boundaries: adjacent vs spaced groups,
space stripping.

## Defects
None found against the spec. Implementation handles all valid inputs correctly.

## Refactor loop
Not triggered.
