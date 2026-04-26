# HumanEval_039 — Black-box test design (Claude)

## Function under test
`int primeFib(int n)` — return the n-th number that is both a Fibonacci number
and a prime. 1-indexed.

## Input domain
- `n` is a positive integer (spec gives n=1..5 as examples).
- The sequence: 2, 3, 5, 13, 89, 233, 1597, ...

## Equivalence classes

### Valid partitions (V)
| ID | Description | Representative input | Expected |
|----|-------------|----------------------|----------|
| V1 | First element | `1` | `2` |
| V2 | Second element | `2` | `3` |
| V3 | Third element | `3` | `5` |
| V4 | Fourth element | `4` | `13` |
| V5 | Fifth element | `5` | `89` |
| V6 | Sixth element (beyond docstring) | `6` | `233` |
| V7 | Seventh element | `7` | `1597` |

### Invalid / undefined-by-spec partitions (I)
| ID | Description | Representative input | Observed behavior |
|----|-------------|----------------------|--------------------|
| I1 | n <= 0 | `0`, `-1` | infinite loop; undefined by spec — not tested |

## Boundary conditions
- n=1: smallest prime Fibonacci (2).
- Fibonacci numbers 8, 21, 34, 55 lie between results and must be skipped (not prime).
- All results must be both prime and Fibonacci.

## Coverage rationale
- V1–V5: docstring values — exercises isPrime true/false on multiple Fibonacci numbers.
- V6–V7: exercises the loop beyond docstring, confirms non-prime Fibs (144, 377...) are skipped.
- Invariants: every result is prime; every result is a Fibonacci number.
