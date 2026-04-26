# HumanEval_087 — Black-box test design (Claude)

## Function under test
`List<List<Integer>> getRow(List<List<Integer>> lst, int x)` —
Find all `[row, col]` coordinates where `lst[row][col] == x`.
Sort by row ascending; within each row sort by column descending.

## Input domain
- `lst`: outer list may be empty; inner lists (rows) may be empty or have different lengths (jagged).
- `x`: any integer.

## Equivalence classes

### Valid partitions (V)
| ID | Description | Representative | Expected |
|----|-------------|----------------|----------|
| V1 | Empty outer list | `[], x=1` | `[]` |
| V2 | All rows empty | `[[], []], x=1` | `[]` |
| V3 | x not present | `[[1,2],[3,4]], x=9` | `[]` |
| V4 | x in exactly one cell | `[[7]], x=7` | `[[0,0]]` |
| V5 | x appears once per row, all rows | `[[2,3],[2,3]], x=2` | `[[0,0],[1,0]]` |
| V6 | x appears multiple times in one row | `[[1,2,1]], x=1` | `[[0,2],[0,0]]` |
| V7 | Jagged rows, x in longer row only | `[[], [1], [1,2,3]], x=3` | `[[2,2]]` |
| V8 | Multiple rows, each with multiple matches | docstring 3-row example | as specified |
| V9 | x at first and last column of a row | `[[5,2,5]], x=5` | `[[0,2],[0,0]]` |

### Invalid / undefined-by-spec partitions (I)
| ID | Description | Observed behavior |
|----|-------------|-------------------|
| I1 | Null outer list | NullPointerException (not tested) |
| I2 | Row containing null | NullPointerException on unboxing (not tested) |

## Boundary conditions
- Empty outer list: outer loop never entered.
- Empty inner row: inner loop never entered for that row.
- Single cell, match: one coordinate [0,0].
- Single cell, no match: [].
- `col` order: within a row, the highest matching index must appear first.
- Row order: rows with lower index appear before higher index in output.

## Coverage rationale
- V1: outer loop not entered.
- V2: outer loop entered, inner loop not entered for every row.
- V3: inner loop entered but `==` condition always false.
- V4: `==` condition true exactly once.
- V6/V9: multiple true paths in inner loop for same row → descending order verified.
- V8: combined multi-row, multi-match case.
