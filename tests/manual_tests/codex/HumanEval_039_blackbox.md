# HumanEval_039 — Black-box test design (Codex / GPT)

## Function under test
`int primeFib(int n)` — return the n-th number that is both a Fibonacci number
and a prime number. n is 1-indexed.

## Input domain
- `n`: positive integer (1-indexed position in the prime-Fibonacci sequence).
- Spec does not define behavior for n <= 0.

## Known prime Fibonacci sequence
2, 3, 5, 13, 89, 233, 1597, 28657, ...

## Equivalence classes

### Valid partitions (V)
| ID | Description | Input | Expected |
|----|-------------|-------|----------|
| V1 | First element | `1` | `2` |
| V2 | Second element | `2` | `3` |
| V3 | Third element (skips 8=even composite) | `3` | `5` |
| V4 | Fourth element (skips several composites) | `4` | `13` |
| V5 | Fifth element | `5` | `89` |
| V6 | Sixth element | `6` | `233` |
| V7 | Seventh element | `7` | `1597` |

### Invalid / undefined-by-spec (I)
| ID | Description | Note |
|----|-------------|------|
| I1 | n = 0 | Undefined; not tested |
| I2 | n < 0 | Undefined; not tested |

## Boundary conditions
| Condition | Input | Why it's a boundary |
|-----------|-------|---------------------|
| n=1 (smallest valid) | `1` | Returns 2, the only even prime |
| 2 is the only even prime Fibonacci | `1` | Exercises the `next==2` special case in primality check |
| 8 is skipped (even composite) | `n=4` | Forces the even-composite branch |
| 21 is skipped (odd composite: 3×7) | `n=4` | Forces the for-loop divisor-found branch |

## Coverage rationale
- V1: exercises next==2 case (even prime).
- V4: exercises 8 (even composite → prime=false) and 21 (odd composite → prime=false via loop).
- V3–V7: exercise for-loop false branch (prime odd Fibonacci, i*i exceeds before divisor found).
- Strictly-increasing invariant: validates overall sequence correctness.
