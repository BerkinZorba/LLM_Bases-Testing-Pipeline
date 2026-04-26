# HumanEval_090 — Black-box test design (Claude)

## Function under test
`Optional<Integer> nextSmallest(List<Integer> lst)` — return the 2nd distinct
smallest element, or `Optional.empty()` if no such element exists.

## Input domain
- `lst` is a `List<Integer>`. May be empty. Elements may repeat.
- "2nd smallest" means the 2nd smallest DISTINCT value.

## Equivalence classes

### Valid partitions — returns Optional.empty (V_E)
| ID | Description | Representative | Expected |
|----|-------------|----------------|----------|
| V_E1 | Empty list | `[]` | empty |
| V_E2 | Single element | `[5]` | empty |
| V_E3 | All elements identical (two) | `[1,1]` | empty |
| V_E4 | All elements identical (many) | `[7,7,7,7]` | empty |

### Valid partitions — returns a value (V_R)
| ID | Description | Representative | Expected |
|----|-------------|----------------|----------|
| V_R1 | Two distinct, ascending | `[1,2]` | `Optional[2]` |
| V_R2 | Two distinct, descending | `[3,1]` | `Optional[3]` |
| V_R3 | Many distinct, sorted | `[1,2,3,4,5]` | `Optional[2]` |
| V_R4 | Many distinct, unsorted | `[5,1,4,3,2]` | `Optional[2]` |
| V_R5 | Duplicates of smallest | `[1,1,1,1,0]` | `Optional[1]` |
| V_R6 | Negative values | `[-5,-3,-1]` | `Optional[-3]` |
| V_R7 | Mixed negative and positive | `[-1,0,1]` | `Optional[0]` |

### Invalid / undefined-by-spec partitions (I)
| ID | Description | Representative | Observed behavior |
|----|-------------|----------------|-------------------|
| I1 | Null list | `null` | NullPointerException; pinned guard returns empty |

## Boundary conditions
- Empty and single-element: size < 2 guard.
- All identical: distinct set size < 2 guard.
- Exactly two distinct values: smallest removed → only one left.
- Many duplicates of the minimum: deduplication must not lose the 2nd value.

## Coverage rationale
- V_E1/V_E2: `lst.size() < 2` branch (returns empty).
- V_E3/V_E4: `distinct.size() < 2` branch (returns empty).
- V_R1–V_R7: normal path: pollFirst + first.
