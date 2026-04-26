# HumanEval_129 - Manual black-box assessment (Codex)

Function under test: `Solution.minPath(List<List<Integer>> grid, int k)`

Prompt requirement:
> Given an `N x N` grid containing each integer in `[1, N * N]` exactly once and a positive integer `k`, return the lexicographically minimum path of exactly `k` visited cells, moving only to edge-sharing neighbors.

Because `1` is the smallest value, a lexicographically minimum path starts at the cell containing `1`. For `k > 1`, it then chooses the smallest-valued neighbor of `1` and alternates back to `1` whenever possible.

## 1. Observable input dimensions

| Dimension | Values worth partitioning |
|-----------|----------------------------|
| Grid size | 2x2, 3x3, 4x4 |
| Position of 1 | corner, edge, interior |
| Path length | 1, small > 1, longer alternating path |
| Neighbor values of 1 | smallest neighbor above, below, left, right |
| Validity | valid square grid, null grid, empty grid, non-positive k |

The output is an ordered list of cell values of length `k` for valid positive `k`.

## 2. Equivalence classes

### Valid classes

| ID | Description | Example | Expected |
|----|-------------|---------|----------|
| V1 | `k = 1` returns only `1` | `[[5,9,3],[4,1,6],[7,8,2]], 1` | `[1]` |
| V2 | 1 in top-left corner | `[[1,2],[3,4]], 5` | `[1,2,1,2,1]` |
| V3 | 1 in bottom-left corner | `[[4,3],[1,2]], 6` | `[1,2,1,2,1,2]` |
| V4 | 1 on top edge | `[[9,1,8],[4,2,3],[7,5,6]], 4` | `[1,2,1,2]` |
| V5 | 1 in interior | `[[9,2,8],[4,1,3],[7,5,6]], 6` | `[1,2,1,2,1,2]` |
| V6 | Dataset 3x3 prompt example | `[[1,2,3],[4,5,6],[7,8,9]], 3` | `[1,2,1]` |
| V7 | Larger 4x4 alternating case | dataset 4x4 with neighbor `6`, `k=12` | `[1,6,1,6,1,6,1,6,1,6,1,6]` |

### Invalid or underspecified classes

| ID | Description | Current implementation behavior |
|----|-------------|---------------------------------|
| I1 | `k = 0` | returns empty list |
| I2 | negative `k` | returns empty list |
| I3 | `null` grid | returns empty list |
| I4 | empty grid | returns empty list |

## 3. Boundary cases

| Boundary | Input | Expected |
|----------|-------|----------|
| Minimum valid k | valid grid, `k = 1` | `[1]` |
| First move | valid grid, `k = 2` | `[1, minNeighbor(1)]` |
| Long alternating path | valid grid, `k = 10` | alternates `1` and smallest neighbor |
| Non-positive k | `k <= 0` | `[]` |

## 4. Black-box expectations to execute

- valid paths begin with `1`;
- when `k > 1`, the second value is the smallest edge-neighbor of the cell containing `1`;
- long paths should alternate between `1` and that smallest neighbor in the tested valid grids;
- null, empty, and non-positive `k` inputs are outside the stated valid contract, so tests pin current behavior rather than treating it as a prompt defect.
