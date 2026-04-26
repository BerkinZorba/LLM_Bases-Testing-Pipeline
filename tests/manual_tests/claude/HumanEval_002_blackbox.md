# HumanEval_002 — Black-box test design (Claude)

## Function under test
`double truncateNumber(double number)` — return the decimal (fractional) part
of a positive floating-point number.

## Input domain
- `number` is a positive `double`. Spec guarantees positivity.
- Result must be in [0, 1).

## Equivalence classes

### Valid partitions (V)
| ID | Description | Representative input | Expected |
|----|-------------|----------------------|----------|
| V1 | Exact integer — fraction is 0 | `1.0`, `100.0` | `0.0` |
| V2 | Small fraction (< 0.5) | `3.5` → 0.5, `1.33` → 0.33 | fractional part |
| V3 | Large fraction (>= 0.5, < 1) | `0.5`, `7.9` | fractional part |
| V4 | Number < 1 (integer part = 0) | `0.5`, `0.999` | same as input |
| V5 | Large integer part | `123.456` | `0.456` |

### Invalid / undefined-by-spec partitions (I)
| ID | Description | Representative input | Observed behavior |
|----|-------------|----------------------|--------------------|
| I1 | Negative input | `-1.5` | spec says positive; `(-1.5) % 1.0 = -0.5` in Java — not tested |
| I2 | Zero | `0.0` | spec says positive; `0.0 % 1.0 = 0.0` — observed behavior |

## Boundary conditions
- `number` just above an integer: `1.0000001` — fraction ≈ 0.
- `number` just below next integer: `0.9999999` — fraction ≈ 1.
- `number == 0.5` — exactly halfway.
- Very large integer part (`1e15 + 0.5`) — floating-point precision limits apply.

## Coverage rationale
- V1: exercises the `result == 0.0` path.
- V2/V3: exercises `result != 0.0` path with varying fraction magnitude.
- V4: integer part is 0, result equals input.
- Invariant: `floor(n) + result == n` within floating-point tolerance.
