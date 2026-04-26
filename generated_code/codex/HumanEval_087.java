import java.util.*;
import java.lang.*;

class Solution {
    public List<List<Integer>> getRow(List<List<Integer>> lst, int x) {
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < lst.size(); i++) {
            List<Integer> row = lst.get(i);
            List<Integer> cols = new ArrayList<>();

            for (int j = 0; j < row.size(); j++) {
                if (row.get(j) == x) {
                    cols.add(j);
                }
            }

            Collections.sort(cols, Collections.reverseOrder());

            for (int col : cols) {
                result.add(Arrays.asList(i, col));
            }
        }

        return result;
    }
}
