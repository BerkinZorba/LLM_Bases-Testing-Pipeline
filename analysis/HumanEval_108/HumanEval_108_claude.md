# HumanEval_108 — Per-prompt analysis (Claude)

## Function
`int countNums(List<Integer> arr)` — count elements whose signed digit sum > 0.
Signed digit sum: for negatives, the most-significant digit is negative.

## Generated code summary
Outer loop calls `signedDigitSum(n)` per element. Helper: converts `Math.abs((long)n)`
to string (safe for MIN_VALUE), negates first digit if n < 0. Returns 0 early for n==0.

## Base test results
- Suite: `tests/base_tests/claude/HumanEval_108_BaseTest.java`
- Result: **5/5 pass** (all docstring examples pass)

## Coverage (JaCoCo)
| Suite    | Tests | Instr  | Branch  | Line  | CC  | Method |
|----------|-------|--------|---------|-------|-----|--------|
| base     | 5     | 62/62  | 12/12   | 13/13 | 9/9 | 3/3   |
| improved | 19    | 62/62  | 12/12   | 13/13 | 9/9 | 3/3   |
| manual   | 16    | 62/62  | 12/12   | 13/13 | 9/9 | 3/3   |

Full coverage on all suites. All branches covered: empty list, n==0, n<0, n>0,
single-digit, multi-digit, count-increment vs skip.

## Improved test rationale
Smells: assertion roulette, magic numbers. Branch targets: each signedDigitSum path
(n==0, n>0, n<0 with sum<0, ==0, >0), Integer.MIN_VALUE boundary via long cast.

## Manual black-box
See `tests/manual_tests/claude/HumanEval_108_blackbox.md`. V1–V8 and I1 cover all
cases including Integer.MIN_VALUE. Key pin: -11 has digit sum 0 (not counted).

## Defects
None.

## Refactor loop
Not triggered.
