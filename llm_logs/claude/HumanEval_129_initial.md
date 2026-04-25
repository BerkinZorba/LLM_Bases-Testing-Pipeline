---
prompt_id: HumanEval_129
model: claude
step: initial
iteration: 1
timestamp: 2026-04-25T00:00:00 (UTC; date-only — interactive session, exact wall-clock not captured)
output_files:
  - generated_code/claude/HumanEval_129.java
---

## Prompt (verbatim)
```java
import java.util.*;
import java.lang.*;

class Solution {
    /**
    Given a grid with N rows and N columns (N >= 2) and a positive integer k,
    each cell of the grid contains a value. Every integer in the range [1, N * N]
    inclusive appears exactly once on the cells of the grid.

    You have to find the minimum path of length k in the grid. You can start
    from any cell, and in each step you can move to any of the neighbor cells,
    in other words, you can go to cells which share an edge with you current
    cell.
    Please note that a path of length k means visiting exactly k cells (not
    necessarily distinct).
    You CANNOT go off the grid.
    A path A (of length k) is considered less than a path B (of length k) if
    after making the ordered lists of the values on the cells that A and B go
    through (let's call them lst_A and lst_B), lst_A is lexicographically less
    than lst_B, in other words, there exist an integer index i (1 <= i <= k)
    such that lst_A[i] < lst_B[i] and for any j (1 <= j < i) we have
    lst_A[j] = lst_B[j].
    It is guaranteed that the answer is unique.
    Return an ordered list of the values on the cells that the minimum path go through.

    Examples:

        Input: grid = [ [1,2,3], [4,5,6], [7,8,9]], k = 3
        Output: [1, 2, 1]

        Input: grid = [ [5,9,3], [4,1,6], [7,8,2]], k = 1
        Output: [1]
     */
    public List<Integer> minPath(List<List<Integer>> grid, int k) {
```

## Response (verbatim)
```java
import java.util.*;
import java.lang.*;

class Solution {
    /**
     * Given a grid with N rows and N columns (N >= 2) and a positive integer k,
     * find the minimum path of length k in the grid (lexicographically smallest).
     * You can move to any neighbor cell sharing an edge.
     * A path visits exactly k cells (not necessarily distinct).
     */
    public List<Integer> minPath(List<List<Integer>> grid, int k) {
        int n = grid.size();
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        // Find the minimum neighbor value for each cell
        // Since we can revisit cells, optimal strategy is to bounce between
        // a cell and its minimum neighbor repeatedly
        int minVal = n * n + 1;
        int minRow = -1, minCol = -1;

        // Find cell with value 1 (global minimum)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) == 1) {
                    minRow = i;
                    minCol = j;
                }
            }
        }

        // Find minimum neighbor of cell with value 1
        int minNeighbor = n * n + 1;
        for (int[] dir : dirs) {
            int ni = minRow + dir[0];
            int nj = minCol + dir[1];
            if (ni >= 0 && ni < n && nj >= 0 && nj < n) {
                minNeighbor = Math.min(minNeighbor, grid.get(ni).get(nj));
            }
        }

        // Build result: alternate between 1 and its min neighbor
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            result.add(i % 2 == 0 ? 1 : minNeighbor);
        }

        return result;
    }
}
```

## Decision note
- How was this output used? Saved as-is to `generated_code/claude/HumanEval_129.java`. No edits.
- If changed before saving, what changed and why? No changes.
- What downstream artifacts did this produce or invalidate? Produces the candidate-under-test consumed by all three test suites.
