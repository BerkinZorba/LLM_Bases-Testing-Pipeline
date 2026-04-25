# HumanEval_023 — Black-box test design (Claude)

## Function under test
`int strlen(String string)` — return the number of characters in the string.

## Input domain
- `string` is a Java `String`. No null guarantee from spec; empty string is valid.
- Result is always >= 0.

## Equivalence classes

### Valid partitions (V)
| ID | Description | Representative input | Expected |
|----|-------------|----------------------|----------|
| V1 | Empty string | `""` | `0` |
| V2 | Single character | `"a"`, `" "` | `1` |
| V3 | Multi-character ASCII | `"abc"` | `3` |
| V4 | String with spaces | `"hello world"` | `11` |
| V5 | Longer string | `"abcdefghij"` | `10` |

### Invalid / undefined-by-spec partitions (I)
| ID | Description | Representative input | Observed behavior |
|----|-------------|----------------------|--------------------|
| I1 | Null input | `null` | NullPointerException; not tested |

## Boundary conditions
- Empty string (length 0) — minimum valid input.
- Single character — minimum non-empty.
- Additivity: `strlen(a + b) == strlen(a) + strlen(b)`.

## Coverage rationale
- The implementation has no branches; behavioral coverage suffices.
- V1: zero-length path; V2–V5: positive-length paths.
