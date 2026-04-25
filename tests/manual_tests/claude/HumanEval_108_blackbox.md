# HumanEval_108 — Black-box test design (Claude)

## Function under test
`int countNums(List<Integer> arr)` — count elements whose "signed digit sum" > 0.
For negative numbers, the most-significant digit is negated; remaining digits are positive.
Example: -123 → signed digits -1, 2, 3 → sum = 4.

## Input domain
- `arr` is a `List<Integer>`. May be empty.
- Signed digit sum definition: for n < 0, most-significant digit is negative; rest positive.
  For n == 0, sum == 0. For n > 0, all digits positive.

## Equivalence classes

### Valid partitions (V)
| ID | Description | Representative | Expected count |
|----|-------------|----------------|----------------|
| V1 | Empty list | `[]` | `0` |
| V2 | All zeros | `[0, 0]` | `0` |
| V3 | Positive single-digit | `[1]`, `[9]` | `1` each |
| V4 | Positive multi-digit | `[11]`, `[123]` | `1` each |
| V5 | Negative with sum < 0 | `[-1]` (sum=-1), `[-9]` (sum=-9) | `0` each |
| V6 | Negative with sum == 0 | `[-11]` (-1+1=0) | `0` |
| V7 | Negative with sum > 0 | `[-123]` (-1+2+3=4), `[-19]` (-1+9=8) | `1` each |
| V8 | Docstring examples | `[-1,11,-11]` → 1; `[1,1,2]` → 3 | as shown |

### Invalid / undefined-by-spec partitions (I)
| ID | Description | Representative | Observed behavior |
|----|-------------|----------------|-------------------|
| I1 | Integer.MIN_VALUE | `[-2147483648]` | sum = -2+1+4+7+4+8+3+6+4+8=43 > 0 → count=1; safe via long cast |

## Boundary conditions
- `0`: digit sum == 0, not counted.
- `-1`: single-digit negative, sum = -1 → not counted.
- `-11`: two digits, -1+1 = 0 → not counted.
- `-19`: -1+9 = 8 → counted.
- `-123`: -1+2+3 = 4 → counted.
- `Integer.MIN_VALUE`: requires `Math.abs((long) n)` to avoid overflow.

## Coverage rationale
- V1: `arr` loop never entered.
- V2/V5/V6: `signedDigitSum(n) <= 0` → not counted.
- V3/V4/V7/V8: `signedDigitSum(n) > 0` → counted.
- I1: exercises the `n == 0` early-return guard and the long-cast safety.
- `n < 0` branch in `signedDigitSum`: V5/V6/V7/I1.
- `n > 0` branch: V3/V4.
- `n == 0` early return: V2.
