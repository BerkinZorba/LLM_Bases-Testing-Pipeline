# HumanEval_039 — Per-prompt analysis (Claude)

## Function
`int primeFib(int n)` — return the n-th number that is both Fibonacci and prime.

## Generated code summary
Infinite loop generating Fibonacci sequence starting at (1,2); counts prime members
with trial-division isPrime helper. Cast `(long)i*i` in loop guard prevents overflow.

## Base test results
- Suite: `tests/base_tests/claude/HumanEval_039_BaseTest.java`
- Result: **5/5 pass** (all docstring examples: 2,3,5,13,89)

## Coverage (JaCoCo)
| Suite    | Tests | Instr  | Branch  | Line  | CC   | Method |
|----------|-------|--------|---------|-------|------|--------|
| base     | 5     | 63/65  | 13/14   | 16/16 | 9/10 | 3/3   |
| improved | 10    | 63/65  | 13/14   | 16/16 | 9/10 | 3/3   |
| manual   | 10    | 63/65  | 13/14   | 16/16 | 9/10 | 3/3   |

**Missed branch**: `if (n < 2) return false` in `isPrime` — the true path (n < 2)
is never reached because all Fibonacci numbers checked start from 2. This is correct
behavior: the sequence never presents a sub-2 value to `isPrime`. Pinned as structurally
unreachable under normal operation, not a defect.

## Improved test rationale
Smells: assertion roulette, magic numbers. Coverage targets: isPrime true/false, loop
skipping non-prime Fibonacci numbers (8, 21, 34, 55). Invariant tests: results are both
prime and Fibonacci.

## Manual black-box
See `tests/manual_tests/claude/HumanEval_039_blackbox.md`. V1–V7 span n=1..7.
Boundaries: n=1 (smallest), non-prime Fibs skipped between results.

## Defects
None against the spec.

## Refactor loop
Not triggered.
