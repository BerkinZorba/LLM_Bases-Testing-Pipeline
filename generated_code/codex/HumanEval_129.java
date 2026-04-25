import java.lang.*;
import java.util.ArrayList;
import java.util.List;

class Solution {

    public List<Integer> minPath(List<List<Integer>> grid, int k) {
        List<Integer> result = new ArrayList<>();

        if (grid == null || grid.isEmpty() || k <= 0) {
            return result;
        }

        int n = grid.size();

        int row = 0;
        int col = 0;

        // Find the cell containing 1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) == 1) {
                    row = i;
                    col = j;
                    break;
                }
            }
        }

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        for (int step = 0; step < k; step++) {
            result.add(grid.get(row).get(col));

            if (step == k - 1) {
                break;
            }

            int bestRow = row;
            int bestCol = col;
            int bestValue = Integer.MAX_VALUE;

            for (int d = 0; d < 4; d++) {
                int newRow = row + dr[d];
                int newCol = col + dc[d];

                if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < n) {
                    int value = grid.get(newRow).get(newCol);

                    if (value < bestValue) {
                        bestValue = value;
                        bestRow = newRow;
                        bestCol = newCol;
                    }
                }
            }

            row = bestRow;
            col = bestCol;
        }

        return result;
    }
}