# HumanEval_028 — Black-box test design (Claude)

## Function under test
`String concatenate(List<String> strings)` — concatenate a list of strings
into a single string; empty list returns `""`.

## Input domain
- `strings` is a `List<String>`. May be empty. Elements may be empty strings.

## Equivalence classes

### Valid partitions (V)
| ID | Description | Representative input | Expected |
|----|-------------|----------------------|----------|
| V1 | Empty list | `[]` | `""` |
| V2 | Single element | `["x"]` | `"x"` |
| V3 | Single empty string element | `[""]` | `""` |
| V4 | Multiple elements | `["a","b","c"]` | `"abc"` |
| V5 | Elements with spaces | `["hello"," ","world"]` | `"hello world"` |
| V6 | Mix of empty and non-empty | `["a","","b"]` | `"ab"` |
| V7 | All empty string elements | `["","",""]` | `""` |

### Invalid / undefined-by-spec partitions (I)
| ID | Description | Representative input | Observed behavior |
|----|-------------|----------------------|--------------------|
| I1 | Null list | `null` | NullPointerException; not tested |
| I2 | List containing null element | `[null]` | NullPointerException; not tested |

## Boundary conditions
- Empty list — loop never entered, returns `""`.
- Single element — loop body executed exactly once.
- Result length equals sum of element lengths.

## Coverage rationale
- V1: loop-never-entered branch.
- V2/V4/V5: loop enters; single vs multiple iterations.
- V3/V6/V7: empty-element handling — StringBuilder.append("") is a no-op.
