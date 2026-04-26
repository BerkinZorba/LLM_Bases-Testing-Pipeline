# HumanEval_028 — Per-prompt analysis (Codex / GPT)

## Function
`String concatenate(List<String> strings)` — concatenate all strings in a list
into a single string; empty list returns `""`.

## Generated code summary
StringBuilder loop: iterates the list appending each element, returns
`result.toString()`. Correct and standard.

## Base test results
- Suite: `tests/base_tests/codex/HumanEval_028_BaseTest.java`
- Result: **4/4 pass**

## Coverage (JaCoCo)
| Suite    | Tests | Instr  | Branch | Line | CC  | Method |
|----------|-------|--------|--------|------|-----|--------|
| base     | 4     | 25/25  | 2/2    | 6/6  | 3/3 | 2/2   |
| improved | 9     | 25/25  | 2/2    | 6/6  | 3/3 | 2/2   |
| manual   | 10    | 25/25  | 2/2    | 6/6  | 3/3 | 2/2   |

Full coverage on all suites. Both loop branches covered: empty list (loop not
entered) and non-empty list (loop entered).

## Improved test rationale
Smells: assertion roulette, magic numbers. Separated by concern (empty list,
single element, multiple elements, order preservation). Added edge cases:
empty-string elements and all-empty list.

## Manual black-box
See `tests/manual_tests/codex/HumanEval_028_blackbox.md`. V1–V8 cover empty
list, single element, multi-element, and embedded empty strings. Boundaries:
loop-never-entered (V1), loop-entered-once (V2), empty-element no-op (V7).

## Defects
None.

## Refactor loop
Not triggered.
