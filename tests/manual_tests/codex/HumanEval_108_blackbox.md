# HumanEval_108 — Black-box test design (Codex)

## Function under test
`int countNums(List<Integer> arr)` — count elements whose "signed digit sum" > 0.
For negative numbers, the most-significant digit is negated; remaining digits are positive.
Example: -123 → signed digits -1, 2, 3 → sum = 4.

## Input domain
- `arr` is a `List<Integer>`. May be empty.
- Signed digit sum: for n < 0, negate the most-significant digit; for n == 0, sum = 0;
  for n > 0, all digits positive.

## Equivalence classes

### Valid partitions (V)
| ID | Description | Representative | Expected count |
|----|-------------|----------------|----------------|
| V1 | Empty list | `[]` | `0` |
| V2 | All zeros | `[0, 0]` | `0` |
| V3 | Positive single-digit | `[7]` | `1` |
| V4 | Positive multi-digit | `[99]` | `1` |
| V5 | Negative — signed sum < 0 | `[-5]` (sum=-5) | `0` |
| V6 | Negative — signed sum == 0 | `[-11]` (-1+1=0) | `0` |
| V7 | Negative — signed sum > 0 | `[-123]` (-1+2+3=4) | `1` |
| V8 | Mixed: pos, neg, zero | `[1, -1, 0]` | `1` |
| V9 | Mixed with neg-sum-pos and neg-sum-neg | `[-19, -9, 5]` | `2` |

### Invalid / undefined-by-spec partitions (I)
| ID | Description | Representative | Observed behavior |
|----|-------------|----------------|-------------------|
| I1 | `Integer.MIN_VALUE` | `-2147483648` | `Math.abs()` overflows → sum=0 → not counted (BUG vs expected 1). Pinned; not tested. |

## Boundary conditions
- `0`: `temp = Math.abs(0) = 0`; first while not entered; sum = 0 → not counted.
- `-1`: sum=1, firstDigit=1, correction: sum=1-2=-1 → not counted.
- `-11`: sum=2, firstDigit=1, correction: sum=0 → not counted.
- `-123`: sum=6, firstDigit=1, correction: sum=4 → counted.
- `-9`: sum=9, firstDigit=9, correction: sum=-9 → not counted.
- `Integer.MIN_VALUE`: overflow defect — pinned, not included in test suite.

## Coverage rationale
- V1: outer loop not entered.
- V2: `temp=0`, inner while not entered; `num >= 0` path; `sum <= 0` path.
- V3/V4: `temp > 0` path; `num >= 0` path; `sum > 0` path.
- V5/V6: negative path; `firstDigit` extraction; correction reduces sum to <= 0.
- V7: negative path; correction leaves sum > 0.
- V8/V9: exercises multiple elements including interaction of positive / negative / zero.
