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