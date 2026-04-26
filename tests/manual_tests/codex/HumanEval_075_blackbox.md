# HumanEval_075 — Black-box test design (Codex / GPT)

## Function under test
`boolean isMultiplyPrime(int a)` — return true if `a` is the product of exactly
3 prime numbers (with multiplicity); false otherwise. Constraint: a < 100.

## Input domain
- `a`: positive integer less than 100.
- "Product of 3 primes" includes repeated primes (e.g. 8 = 2*2*2).

## Equivalence classes

### Valid partitions — true (V_T)
| ID  | Description | Input | Expected |
|-----|-------------|-------|----------|
| V_T1 | Three distinct primes | `30` (2×3×5) | `true` |
| V_T2 | One prime repeated three times | `8` (2×2×2) | `true` |
| V_T3 | One prime repeated twice, one distinct | `12` (2×2×3) | `true` |
| V_T4 | Different repeated pattern | `18` (2×3×3) | `true` |
| V_T5 | Two same larger prime | `50` (2×5×5) | `true` |
| V_T6 | Includes large prime factor | `66` (2×3×11) | `true` |

### Valid partitions — false (V_F)
| ID  | Description | Input | Expected |
|-----|-------------|-------|----------|
| V_F1 | Only 2 prime factors | `22` (2×11) | `false` |
| V_F2 | Only 2 prime factors, small | `4` (2×2) | `false` |
| V_F3 | Single prime | `3` | `false` |
| V_F4 | Smallest prime | `2` | `false` |
| V_F5 | Four prime factors | `16` (2^4) | `false` |
| V_F6 | Four prime factors, mixed | `60` (2×2×3×5) | `false` |

### Invalid / undefined-by-spec (I)
| ID | Description | Note |
|----|-------------|------|
| I1 | a >= 100 | Outside spec range; not tested |
| I2 | a <= 0 | Undefined; not tested |

## Boundary conditions
| Condition | Input | Why it's a boundary |
|-----------|-------|---------------------|
| Smallest true value | `8` (2×2×2) | minimum product of 3 primes |
| Exactly 2 factors | `4` | one below minimum count |
| Exactly 4 factors | `16` | one above maximum count |
| Single prime | `2` or `3` | fewest possible prime factors |

## Coverage rationale
- V_T1–V_T6: exercises prime=true path, count increment, a/=i factoring.
- V_F1–V_F6: exercises count!=3 return false path and 4-factor overflow.
- V_T6 (66=2×3×11): exercises j-loop body (j=3, 3*3=9<11, 11%3≠0).
- Unreachable branches noted: `i<2` true side and `i%2==0 && i!=2` true side
  (composite i can never divide remaining a in correct trial division).
