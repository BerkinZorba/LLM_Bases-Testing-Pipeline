# HumanEval_039 — Per-prompt analysis (Codex / GPT)

## Function
`int primeFib(int n)` — return the n-th number that is both a Fibonacci number
and a prime.

## Generated code summary
Infinite loop generating Fibonacci values (starting a=0, b=1). For each value
>= 2, performs inline primality check: skips even non-2 values immediately, then
trial division with odd i from 3 to sqrt(next). No separate helper method.

## Base test results
- Suite: `tests/base_tests/codex/HumanEval_039_BaseTest.java`
- Result: **5/5 pass**

## Coverage (JaCoCo)
| Suite    | Tests | Instr  | Branch | Line  | CC    | Method |
|----------|-------|--------|--------|-------|-------|--------|
| base     | 5     | 57/57  | 16/16  | 14/14 | 10/10 | 2/2   |
| improved | 9     | 57/57  | 16/16  | 14/14 | 10/10 | 2/2   |
| manual   | 9     | 57/57  | 16/16  | 14/14 | 10/10 | 2/2   |

**Full branch coverage on all suites** — unlike the Claude version (13/14) which
had an unreachable `isPrime(n<2)` branch in a helper method, this inline
implementation naturally exercises all branches from the docstring examples.

## Improved test rationale
Smells: assertion roulette. Added n=6,7 to exercise larger sequence values.
Added sequence-invariant test (strictly increasing). All branches covered by
base tests already; improved tests add confidence and smell fixes.

## Manual black-box
See `tests/manual_tests/codex/HumanEval_039_blackbox.md`. V1–V7 cover the
first seven prime Fibonacci numbers. Boundaries: n=1 (only even prime),
even-composite skip (8), odd-composite skip (21).

## Defects
None.

## Refactor loop
Not triggered.
