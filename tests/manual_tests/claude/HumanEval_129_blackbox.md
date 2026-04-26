# HumanEval_129 - Manual black-box assessment (Claude)

Function under test: `Solution.minPath(List<List<Integer>> grid, int k)`

Spec from the prompt:

> Given an N×N grid (N ≥ 2) where every integer in [1, N²] appears exactly once. Find the minimum path of length k (visiting exactly k cells, revisits allowed, no off-grid moves). A path is lexicographically less if its value list is lexicographically smaller. The answer is guaranteed to be unique.

## 1. Key algorithmic insight

The lexicographically smallest path must start at the cell with value **1** (the global minimum). Once at 1, to minimise the next step we go to 1's **minimum-value neighbour**, call it `m`. Since we can revisit cells, the optimal strategy is to bounce: 1 → m → 1 → m → … producing the pattern:

- Even 0-based position: **1**
- Odd 0-based position: **m**

## 2. Input domain

| Dimension | Values |
|-----------|--------|
| Grid size N | 2, 3, 4 (minimum spec-defined, typical, larger) |
| Position of 1 | corner TL, corner TR, corner BL, corner BR, edge (non-corner), interior |
| k | 1 (single step), 2 (both parities), odd-length, even-length, large |
| Min-neighbour position relative to 1 | right, down, left, up (depends on placement) |

## 3. Equivalence classes

### Valid classes

| ID | Class | Notes |
|----|-------|-------|
| V1 | k=1, any position of 1 | Result is always `[1]` |
| V2 | k=2, 1 at corner | Minimal case with both parities |
| V3 | k odd > 1, 1 at corner TL | Ends with 1 |
| V4 | k even, 1 at corner TL | Ends with m |
| V5 | 1 at top-left corner (0,0) | Neighbours: right, down only |
| V6 | 1 at top-right corner (0,n-1) | Neighbours: left, down only |
| V7 | 1 at bottom-left corner (n-1,0) | Neighbours: right, up only |
| V8 | 1 at bottom-right corner (n-1,n-1) | Neighbours: left, up only |
| V9 | 1 at edge (not corner) | Three neighbours |
| V10 | 1 at interior | Four neighbours |
| V11 | Two neighbours of 1 tied at min value | Min-neighbour picks one deterministically |
| V12 | Minimum N=2 grid | Smallest allowed grid |
| V13 | N=3 grid | Medium size |
| V14 | N=4 grid | Larger size |

### Invalid / undefined-by-spec classes

| ID | Class | Behavior | How we treat it |
|----|-------|----------|-----------------|
| I1 | null grid | `NullPointerException` from `grid.size()` | Pin as NPE |
| I2 | k=0 | Loop runs 0 times → empty list `[]` | Pin as empty list |

## 4. Boundary analysis

| Boundary | Input | Expected |
|----------|-------|----------|
| Minimum k | k=1 | `[1]` |
| k=2 (first odd-indexed element) | 2x2 with 1 at TL | `[1, 2]` |
| k odd vs k even (last element differs) | k=3 vs k=4, same grid | `[1,m,1]` vs `[1,m,1,m]` |
| 1 at corner (2 neighbours) | 2x2, k=3 | min of 2 neighbours |
| 1 at edge (3 neighbours) | 3x3 with 1 at top-middle, k=3 | min of 3 neighbours |
| 1 at interior (4 neighbours) | 3x3 with 1 at centre, k=3 | min of 4 neighbours |
| Minimum grid N=2 | 2×2, k=1 | `[1]` |

## 5. Coverage rationale

- `i % 2 == 0` true branch (k ≥ 1 ensures at least position 0 is added).
- `i % 2 == 0` false branch (k ≥ 2).
- `ni >= 0 && ni < n && nj >= 0 && nj < n` true: at least one in-bounds neighbour.
- Bounds false: corner or edge placement ensures some directions go out of bounds.
- `grid.get(i).get(j) == 1` true: the cell containing 1 is found.
- `grid.get(i).get(j) == 1` false: other cells visited in the search loop.
