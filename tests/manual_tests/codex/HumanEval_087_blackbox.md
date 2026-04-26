# HumanEval_087 — Black-box test design (Codex)

## Function under test
`List<List<Integer>> getRow(List<List<Integer>> lst, int x)` —
Find all `[row, col]` coordinates where `lst[row][col] == x`.
Primary sort: row ascending. Secondary sort: column descending within row.

## Input domain
- `lst`: may be empty; rows may be empty or have different lengths.
- `x`: any integer value to search for.

## Equivalence classes

### Valid partitions (V)
| ID | Description | Representative | Expected |
|----|-------------|----------------|----------|
| V1 | Empty outer list | `[], x=5` | `[]` |
| V2 | Rows present but all empty | `[[], []], x=1` | `[]` |
| V3 | x not in any cell | `[[2,3]], x=7` | `[]` |
| V4 | x in exactly one cell | `[[9]], x=9` | `[[0,0]]` |
| V5 | x in multiple rows, one occurrence each | 6-row uniform grid, x=2 | `[[0,1]...[5,1]]` |
| V6 | x in multiple cells of same row, sorted desc | `[[3,1,3]], x=3` | `[[0,2],[0,0]]` |
| V7 | Mix of rows with and without x | `[[1],[2],[1,2,1]], x=1` | `[[0,0],[2,2],[2,0]]` |
| V8 | Jagged grid | `[[], [1], [1,2,3]], x=3` | `[[2,2]]` |

### Invalid / undefined-by-spec partitions (I)
| ID | Description | Observed behavior |
|----|-------------|-------------------|
| I1 | Null outer list | NullPointerException (not tested) |

## Boundary conditions
- Empty outer list: outer for-loop not entered; `cols` never allocated.
- Empty row: inner for-loop not entered; `cols` stays empty; no output.
- `cols` empty after inner loop: for-each over `cols` skipped.
- Single-element row with match: `cols = [0]`, sorted still `[0]` → `[[row,0]]`.
- Two matches in row: `cols` sorted descending → higher index first.

## Coverage rationale
- V1: outer loop not entered.
- V2/V3: inner loop entered but `cols` stays empty → for-each not entered.
- V4: single match path; `cols` has one element.
- V6: multiple matches → `Collections.sort(reverseOrder)` reorders `cols`.
- V7/V8: multi-row with mixed presence — rows index preserved.
