# HumanEval_075 — Black-box test design (Claude)

## Function under test
`boolean isMultiplyPrime(int a)` — return true if `a` (< 100) is the product
of exactly 3 prime numbers (counting multiplicity); false otherwise.

## Input domain
- `a` is a positive integer less than 100.
- "3 prime numbers" means 3 prime factors counting multiplicity (e.g. 8 = 2*2*2 → true).

## Equivalence classes

### Valid partitions (V)
| ID | Description | Representative | Expected |
|----|-------------|----------------|----------|
| V1 | Single prime (1 factor) | 2, 3, 5, 7 | false |
| V2 | Product of 2 primes | 4=2*2, 6=2*3, 22=2*11 | false |
| V3 | Product of 3 distinct primes | 30=2*3*5, 42=2*3*7 | true |
| V4 | Product of 3 primes with repeats | 8=2³, 12=2²*3, 18=2*3², 27=3³ | true |
| V5 | Product of 4+ primes | 16=2⁴, 48=2⁴*3, 90=2*3²*5 | false |
| V6 | Near-100 boundary | 98=2*7*7 | true |

### Invalid / undefined-by-spec partitions (I)
| ID | Description | Representative | Observed behavior |
|----|-------------|----------------|-------------------|
| I1 | a = 1 | 1 | returns false (no prime factors) |
| I2 | a >= 100 | 100 | spec says a < 100; not tested |

## Boundary conditions
- a=8: smallest number with exactly 3 prime factors (all equal).
- a=30: smallest product of 3 distinct primes.
- a=16: first number with 4 prime factors — early-return path.
- a=98: largest valid true case (2*7*7).
- a=2: single prime, smallest possible false result.

## Coverage rationale
- V1/V2: count < 3 → false.
- V3/V4: count == 3 → true; exercises while-inner-loop for repeated divisors.
- V5: count > 3 → early false return.
- V6: near upper bound, verifies no off-by-one on p <= a loop.
