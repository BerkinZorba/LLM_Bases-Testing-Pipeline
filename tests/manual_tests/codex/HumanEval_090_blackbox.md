# HumanEval_090 — Black-box test design (Codex / GPT)

## Function under test
`Optional<Integer> nextSmallest(List<Integer> lst)` — return the 2nd smallest
distinct element of the list, or `Optional.empty()` if it does not exist.

## Input domain
- `lst`: a `List<Integer>`. May be empty or contain duplicates.
- "2nd smallest" means the second distinct value in sorted order.

## Equivalence classes

### Valid partitions — Optional.empty (V_E)
| ID   | Description | Input | Expected |
|------|-------------|-------|----------|
| V_E1 | Empty list | `[]` | `Optional.empty` |
| V_E2 | Single element | `[5]` | `Optional.empty` |
| V_E3 | All elements identical | `[3,3,3]` | `Optional.empty` |
| V_E4 | Exactly two identical elements | `[7,7]` | `Optional.empty` |

### Valid partitions — value returned (V_R)
| ID   | Description | Input | Expected |
|------|-------------|-------|----------|
| V_R1 | Two distinct, ascending | `[1,2]` | `Optional[2]` |
| V_R2 | Two distinct, descending | `[2,1]` | `Optional[2]` |
| V_R3 | Ascending list | `[1,2,3,4,5]` | `Optional[2]` |
| V_R4 | Unsorted list | `[5,1,4,3,2]` | `Optional[2]` |
| V_R5 | Descending list | `[5,4,3,2,1]` | `Optional[2]` |
| V_R6 | Min repeated, one different | `[1,1,1,1,0]` | `Optional[1]` |
| V_R7 | Negative numbers | `[-2,-1,0,1]` | `Optional[-1]` |

### Invalid / undefined-by-spec (I)
| ID | Description | Note |
|----|-------------|------|
| I1 | `null` list | NullPointerException (or empty); not tested |

## Boundary conditions
| Condition | Input | Why it's a boundary |
|-----------|-------|---------------------|
| Exactly 2 elements, distinct | `[1,2]` | minimum non-empty case that returns a value |
| Exactly 2 elements, same | `[3,3]` | minimum case that returns empty |
| All same but one different | `[1,1,0]` | deduplication boundary |
| Min at end | `[3,2,1]` | new minimum found late in traversal |

## Coverage rationale
- V_E1/V_E2: `lst.size() < 2` branch.
- V_E3/V_E4: `second == null` at return → empty.
- V_R1/V_R2: `num < min` new-minimum branch in both orderings.
- V_R6: exercises repeated minimum with one outlier.
- V_R7: negative values — no special handling but validates arithmetic comparison.
- Unreachable: `lst == null` true branch (spec guarantees non-null).
