# HumanEval_023 — Black-box test design (Codex / GPT)

## Function under test
`int strlen(String string)` — return the length of the given string.

## Input domain
- `string`: any Java `String`.
- Result is the number of characters (including spaces and special chars).

## Equivalence classes

### Valid partitions (V)
| ID | Description | Input | Expected |
|----|-------------|-------|----------|
| V1 | Empty string | `""` | `0` |
| V2 | Single character | `"a"` | `1` |
| V3 | Short alphabetic string | `"abc"` | `3` |
| V4 | String with spaces | `"hello world"` | `11` |
| V5 | All spaces | `"   "` | `3` |
| V6 | Digits-only string | `"12345"` | `5` |
| V7 | Special characters | `"!@#$%"` | `5` |
| V8 | Longer string | `"abcdefghij"` | `10` |

### Invalid / undefined-by-spec (I)
| ID | Description | Note |
|----|-------------|------|
| I1 | `null` input | NullPointerException; not tested |

## Boundary conditions
| Condition | Input | Why it's a boundary |
|-----------|-------|---------------------|
| Length = 0 | `""` | minimum possible length |
| Length = 1 | `"x"` | smallest non-empty string |
| Space counted as character | `" "` | space is not ignored |

## Coverage rationale
No branches — single return statement. Tests verify correctness of delegation
to `String.length()` across different character classes and lengths.
