# HumanEval_028 — Per-prompt analysis (Claude)

## Function
`String concatenate(List<String> strings)` — join list elements into one string;
empty list → `""`.

## Generated code summary
StringBuilder loop over elements; returns `sb.toString()`. Correct and efficient.

## Base test results
- Suite: `tests/base_tests/claude/HumanEval_028_BaseTest.java`
- Result: **4/4 pass**

## Coverage (JaCoCo)
| Suite    | Tests | Instr  | Branch | Line | CC  | Method |
|----------|-------|--------|--------|------|-----|--------|
| base     | 4     | 25/25  | 2/2    | 4/4  | 3/3 | 2/2   |
| improved | 9     | 25/25  | 2/2    | 4/4  | 3/3 | 2/2   |
| manual   | 10    | 25/25  | 2/2    | 4/4  | 3/3 | 2/2   |

Full coverage. Two branches: loop-entered vs loop-not-entered (empty list).

## Improved test rationale
Smells: assertion roulette, eager test. Branches: empty list (loop guard false),
single element (one iteration), multiple elements. Edge: mix of empty/non-empty strings.

## Manual black-box
See `tests/manual_tests/claude/HumanEval_028_blackbox.md`. V1–V7 cover empty list,
single element, empty element, multi-element, spaces, mixed empty, all empty.

## Defects
None.

## Refactor loop
Not triggered.
