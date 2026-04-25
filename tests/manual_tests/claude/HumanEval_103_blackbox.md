# HumanEval_103 — Manual black-box assessment (Claude)

## Specification recap
`roundedAvg(int n, int m)` returns:
- `-1` (Integer) when `n > m`.
- Otherwise the binary-string representation of the rounded average of the
  integers `n, n+1, ..., m`. Average is `sum(n..m) / (m - n + 1)`, rounded to
  the nearest integer (half-up per the dataset example, since `avg(20..33) = 26.5`
  is expected to map to `"11011"` = 27).

The declared return type is `Object`, so the success and failure return types
differ (`String` vs `Integer`).

## Input domain
Spec says "two positive integers". The implementation accepts any `int` for both
arguments. Behavior for `n <= 0` or `m <= 0` is not contractually defined but is
pinned below as observed.

## Equivalence classes

### Valid partitions (spec defines behavior)
| ID  | Class                                  | Sample (n, m) | Why it matters                                                |
| --- | -------------------------------------- | ------------- | ------------------------------------------------------------- |
| V1  | n == m (singleton range)               | (1, 1)        | loop body must execute exactly once; smallest valid n         |
| V2  | n == m, larger value                   | (8, 8)        | power-of-two boundary in binary representation                |
| V3  | n < m, exact integer average           | (1, 3)        | no rounding needed                                            |
| V4  | n < m, half-value average (half-up)    | (1, 4)        | exercises the rounding rule (2.5 -> 3)                        |
| V5  | dataset case                           | (1, 5)        | reference example                                             |
| V6  | dataset case                           | (10, 20)      | reference example, integer mid                                |
| V7  | dataset case (half-up at 26.5)         | (20, 33)      | reference example, fractional mid                             |
| V8  | larger range (size correctness)        | (1, 100)      | sweeps many loop iterations; 50.5 -> 51                       |
| V9  | shifted range                          | (50, 100)     | average not at start; integer result 75                       |

### Boundary partitions
| ID  | Boundary                       | Sample (n, m) | Reason                                                |
| --- | ------------------------------ | ------------- | ----------------------------------------------------- |
| B1  | n == m (lower-edge of valid)   | (1, 1)        | guard `n > m` is false, loop runs once                |
| B2  | n == m + 1 (just into invalid) | (11, 10)      | guard flips true; expected `-1`                       |
| B3  | n much greater than m          | (100, 1)      | well inside invalid region                            |
| B4  | range yields 0.5 fraction      | (1, 2)        | smallest fractional average; rounds up to 2           |
| B5  | range yields .5 fraction at higher value | (20, 33) | dataset boundary for half-up rule                  |

### Invalid / undefined-by-spec partitions
| ID  | Class                          | Sample (n, m) | Observed behavior of implementation                     |
| --- | ------------------------------ | ------------- | ------------------------------------------------------- |
| U1  | n == 0 (spec says positive)    | (0, 5)        | computes avg(0..5) = 15/6 = 2.5 -> 3 -> `"11"`          |
| U2  | negative n with positive m     | (-2, 2)       | computes avg(-2..2) = 0 -> `"0"`                        |

These are pinned to make any future regression visible. They are not part of
the spec contract.

## Boundary table
| Variable    | Just below | At edge   | Just above |
| ----------- | ---------- | --------- | ---------- |
| (n vs m)    | n < m      | n == m    | n == m + 1 |
| fractional half | exact int | .5 boundary | next int |

## Return-type contract
| Path     | Java type returned |
| -------- | ------------------ |
| success  | `java.lang.String` (binary representation) |
| failure  | `java.lang.Integer` (-1)                   |

## Coverage of partitions in `HumanEval_103_ManualTest.java`
- V1..V9 → one explicit assertion each.
- B1..B5 → covered by `boundary_*` tests.
- U1, U2 → pinned in `pinned_undefined_*` tests with observed-behavior comments.
- Return-type contract → exercised by dedicated `returnType_*` tests.
