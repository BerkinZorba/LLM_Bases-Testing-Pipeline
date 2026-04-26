# HumanEval_002 — Black-box test design (Codex / GPT)

## Function under test
`double truncateNumber(double number)` — given a positive floating-point number,
return its decimal (fractional) part (i.e., `number - floor(number)`).

## Input domain
- `number`: positive `double`. Spec says "positive floating point number".
- Result must satisfy `0 <= result < 1`.

## Equivalence classes

### Valid partitions (V)
| ID | Description | Representative input | Expected |
|----|-------------|----------------------|----------|
| V1 | Exact integer (fractional part = 0) | `1.0` | `0.0` |
| V2 | Exact integer, larger value | `100.0` | `0.0` |
| V3 | Fraction only (0 < n < 1) | `0.5` | `0.5` |
| V4 | Near-one fraction | `0.999` | `0.999` |
| V5 | Tiny fraction | `0.001` | `0.001` |
| V6 | Small integer + half | `3.5` | `0.5` |
| V7 | Moderate integer + fraction | `1.33` | `0.33` |
| V8 | Large integer + fraction | `123.456` | `0.456` |

### Invalid / undefined-by-spec (I)
| ID | Description | Note |
|----|-------------|------|
| I1 | Zero (`0.0`) | Spec says "positive"; not tested |
| I2 | Negative number | Spec says "positive"; not tested |

## Boundary conditions
| Condition | Input | Why it's a boundary |
|-----------|-------|---------------------|
| Integer part = 0 | `0.5` | cast to long gives 0; result equals input |
| Fractional part → 0 | `1.0` | result exactly 0.0 |
| Fractional part → 1 | `0.999` | result just below 1.0 |
| Smallest representable fraction above 0 | `0.001` | exercises near-zero decimal |

## Coverage rationale
No branches in the implementation. Tests exercise the arithmetic correctness of
the cast-and-subtract approach across the full valid range.
