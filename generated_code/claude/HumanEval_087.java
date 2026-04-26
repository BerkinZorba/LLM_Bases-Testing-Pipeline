import java.util.*;
import java.lang.*;

class Solution {
    public List<List<Integer>> getRow(List<List<Integer>> lst, int x) {
        List<List<Integer>> result = new ArrayList<>();
        for (int row = 0; row < lst.size(); row++) {
            List<Integer> r = lst.get(row);
            for (int col = r.size() - 1; col >= 0; col--) {
                if (r.get(col) == x) {
                    result.add(Arrays.asList(row, col));
                }
            }
        }
        return result;
    }
}
