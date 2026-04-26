# HumanEval_028 — Black-box test design (Codex / GPT)

## Function under test
`String concatenate(List<String> strings)` — concatenate all strings in the list
into a single string, in order. Empty list returns `""`.

## Input domain
- `strings`: a `List<String>`. May be empty. Elements may themselves be empty strings.
- Result: concatenation of all elements in list order.

## Equivalence classes

### Valid partitions (V)
| ID | Description | Input | Expected |
|----|-------------|-------|----------|
| V1 | Empty list | `[]` | `""` |
| V2 | Single non-empty element | `["hello"]` | `"hello"` |
| V3 | Single empty-string element | `[""]` | `""` |
| V4 | Multiple single-char elements | `["a","b","c"]` | `"abc"` |
| V5 | Elements with spaces | `["hello"," ","world"]` | `"hello world"` |
| V6 | Two multi-char elements | `["foo","bar"]` | `"foobar"` |
| V7 | List containing empty strings mixed in | `["a","","c"]` | `"ac"` |
| V8 | All elements are empty strings | `["","",""]` | `""` |

### Invalid / undefined-by-spec (I)
| ID | Description | Note |
|----|-------------|------|
| I1 | `null` list | NullPointerException; not tested |
| I2 | `null` element in list | NullPointerException; not tested |

## Boundary conditions
| Condition | Input | Why it's a boundary |
|-----------|-------|---------------------|
| Empty list | `[]` | loop never entered |
| Single element | `["x"]` | loop entered exactly once |
| Empty element within list | `["a","","b"]` | empty string appended (no visible effect) |
| All elements empty | `["",""]` | result is empty despite non-empty list |

## Coverage rationale
- V1: loop-never-entered branch.
- V2–V8: loop-entered branch; V7/V8 additionally exercise empty-element append.
- Order-preservation: V4 verifies left-to-right order.
