# HumanEval_097 — Black-box test design (Claude)

## Function under test
`int multiply(int a, int b)` — returns the product of the unit (ones-place)
digits of `a` and `b`. The dataset example `multiply(14, -15) == 20` pins
**sign-insensitive** semantics: the unit digit is taken as a non-negative
value (`|a % 10| * |b % 10|`). Spec is "always valid" so no input validation
is required.

## Input domain
- `a`, `b` are 32-bit signed integers (`int`).
- Practical sub-domains:
  - sign of `a`: negative, zero, positive.
  - sign of `b`: negative, zero, positive.
  - `|a| < 10` (single digit) vs `|a| >= 10` (multi-digit).
  - `a` is a multiple of 10 (unit digit zero) vs not.
  - Same axes for `b`.
- Spec says "input is always valid", so `null`/non-int inputs are out of scope.

## Equivalence classes

### Valid partitions (V)
| ID  | Description | Representative input | Expected |
|-----|-------------|----------------------|----------|
| V1  | Both single-digit positives | `(3, 7)` | `21` |
| V2  | Multi-digit positives, both non-zero unit digits | `(148, 412)` | `16` |
| V3  | Multi-digit positives, both non-zero unit digits | `(19, 28)` | `72` |
| V4  | `a` is a multiple of 10 (unit digit 0) | `(2020, 1851)` | `0` |
| V5  | `b` is a multiple of 10 (unit digit 0) | `(7, 30)` | `0` |
| V6  | Both multiples of 10 | `(50, 80)` | `0` |
| V7  | `a == 0`, `b != 0` | `(0, 1)` | `0` |
| V8  | `a == 0`, `b == 0` | `(0, 0)` | `0` |
| V9  | `a > 0`, `b < 0` (dataset) | `(14, -15)` | `20` |
| V10 | `a < 0`, `b > 0` | `(-14, 15)` | `20` |
| V11 | `a < 0`, `b < 0` | `(-14, -15)` | `20` |
| V12 | Negative single digits | `(-3, -7)` | `21` |
| V13 | Largest single-digit unit product | `(9, 9)` | `81` |
| V14 | Higher-order digits irrelevant | `(123, 1007)` vs `(3, 7)` | both `21` |
| V15 | Negative multiple of 10 | `(-2020, 1851)` | `0` |
| V16 | Extreme magnitudes (no overflow because we look at units) | `(99999, -99999)` | `81` |
| V17 | Dataset parity: `(76, 67)` | `(76, 67)` | `42` |
| V18 | Dataset parity: `(17, 27)` | `(17, 27)` | `49` |

### Invalid / undefined-by-spec partitions (I)
| ID | Description | Notes |
|----|-------------|-------|
| I1 | `Integer.MIN_VALUE` as either operand | `MIN_VALUE % 10 == -8`, `Math.abs(-8) == 8`, so `multiply(Integer.MIN_VALUE, 7) == 56`. Behavior is well-defined for this implementation but the spec does not explicitly cover it. Pinned as observed behavior. |
| I2 | `null` operands | Not applicable: `int` is a primitive. |

## Boundary conditions
- Unit-digit boundaries on each side: 0, 1, 9.
- Sign boundaries: 0 (both signs collapse), -1 (smallest negative magnitude),
  1 (smallest positive magnitude).
- Tens boundary: 10, -10 (smallest non-single-digit multiple of 10);
  9, -9 (largest single-digit), exercising the "tens-digit dropped" property.
- Magnitude boundary: `99999` and `-99999` confirm no overflow concerns
  (the implementation never multiplies the original `a`/`b`, only their units).

## Coverage rationale
`Solution.multiply` is a single expression `Math.abs(a % 10) * Math.abs(b % 10)`
with no in-method conditionals. JaCoCo therefore reports zero method-local
branches in `Solution`. The materialized cases still exercise every
*behaviorally distinct* path:
- `Math.abs` on a negative remainder: V9, V10, V11, V12, V15 (a or b < 0).
- `Math.abs` on a non-negative remainder: V1–V8, V13, V14, V16–V18.
- Product == 0 path: V4, V5, V6, V7, V8, V15.
- Product != 0 path: V1, V2, V3, V9–V14, V16–V18.
- Sign-cross matrix (all four combinations of `sign(a)`,`sign(b)`):
  (+,+): V1–V3, V13, V14, V17, V18; (+,−): V9; (−,+): V10;
  (−,−): V11, V12, V16.
- "Higher-order digits ignored" axiom: V14 vs V1 establishes that only the
  units affect the result.
