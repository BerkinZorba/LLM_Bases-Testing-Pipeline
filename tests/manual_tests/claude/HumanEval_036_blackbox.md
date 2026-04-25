# HumanEval_036 — Black-box test design (Claude)

## Function under test
`int fizzBuzz(int n)` — count occurrences of the digit `7` across the decimal
representations of all integers `i` with `1 <= i < n` such that `i % 11 == 0`
or `i % 13 == 0`.

## Input domain
- `n` is a Java `int` (32-bit signed). Practical interpretation: a non-negative
  upper bound. Behavior for `n <= 1` is "no integers in range, return 0";
  the spec gives no expectation for negative `n`, but the implementation's
  `for (i = 1; i < n; i++)` loop also returns 0 for negatives — we pin this
  as observed behavior, not as a spec requirement.

## Equivalence classes

### Valid partitions (V)
| ID | Description | Representative input | Expected |
|----|-------------|----------------------|----------|
| V1 | n where loop body never enters (n <= 1) | 0, 1 | 0 |
| V2 | n in range with no multiples of 11 or 13 | 11 (excludes 11 itself: i<11), 2 | 0 |
| V3 | n includes some multiples but none containing digit 7 | 14 (covers 11, 13) | 0 |
| V4 | n includes a number with one '7' from a divisor (78 = 6*13) | 79 | 3 |
| V5 | n includes a number with two '7's (77 = 7*11) | 78 | 2 |
| V6 | n includes a both-divisor overlap (143 = 11*13) | 144 | f(143) (counted once) |
| V7 | Dataset spot-check | 100, 200, 4000, 10000, 100000 | 3, 6, 192, 639, 8026 |
| V8 | Number with multiple '7's at non-adjacent positions (e.g., 7007) | 7008 (7007 ÷ 7? 7007/11=637, multiple of 11) | adds 2 at the 7007 step |

### Invalid / undefined-by-spec partitions (I)
| ID | Description | Representative input | Observed behavior |
|----|-------------|----------------------|--------------------|
| I1 | Negative n | -1, -100 | returns 0 (loop never enters) |
| I2 | n = Integer.MIN_VALUE | Integer.MIN_VALUE | returns 0 |
| I3 | Very large n (perf, not correctness) | not tested here | n/a |

## Boundary conditions
- `n = 0`, `n = 1`, `n = 2` — loop entry boundary.
- `n = 77`, `n = 78`, `n = 79` — first multiple-of-11 containing '7'; transition
  through 77 (adds 2) and 78 (adds 1).
- `n = 143`, `n = 144` — both-divisor overlap; verifies the `||` short-circuits
  correctly (count once, not twice).
- `n = 100000` — dataset upper end.

## Coverage rationale
Materialized cases drive every branch in `Solution.fizzBuzz`:
- Outer-loop entry: V1 (no entry) vs V2..V7 (entry).
- `i % 11 == 0` true / `i % 13 == 0` false: 11, 22, 33, 44, 55, 66 (no '7'); 77, 88, 99 (77 contains '7').
- `i % 11 == 0` false / `i % 13 == 0` true: 13, 26, ..., 78 (one '7'), 91.
- Both true: 143 (digits 1,4,3 — contributes 0 sevens but exercises the OR-overlap path).
- Inner-while branch: number with no '7' (e.g., 11), with one '7' (78), with two '7's (77).
