---
prompt_id: HumanEval_087
model: claude
step: initial
iteration: 1
timestamp: 2026-04-26T00:00:00 (local)
output_files:
  - generated_code/claude/HumanEval_087.java
---

## Prompt (verbatim)
```java
import java.util.*;
import java.lang.*;

class Solution {
    /**
    You are given a 2 dimensional data, as a nested lists,
    which is similar to matrix, however, unlike matrices,
    each row may contain a different number of columns.
    Given lst, and integer x, find integers x in the list,
    and return list of lists, [[x1, y1], [x2, y2] ...] such that
    each list is a coordinate - (row, columns), starting with 0.
    Sort coordinates initially by rows in ascending order.
    Also, sort coordinates of the row by columns in descending order.

    Examples:
    getRow([
      [1,2,3,4,5,6],
      [1,2,3,4,1,6],
      [1,2,3,4,5,1]
    ], 1) == [[0, 0], [1, 4], [1, 0], [2, 5], [2, 0]]
    getRow([], 1) == []
    getRow([[], [1], [1, 2, 3]], 3) == [[2, 2]]
     */
    public List<List<Integer>> getRow(List<List<Integer>> lst, int x) {
```

## Response (verbatim)
```java
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
```

## Decision note
- Saved as-is; added class wrapper and imports for compilation.
- Iterates each row's columns in reverse order (col = size-1 downto 0), which naturally
  produces descending column order without a separate sort step.
- Rows are visited in ascending order by the outer loop, satisfying the primary sort.
- `r.get(col) == x`: `r.get(col)` returns Integer, `x` is int — unboxing occurs, so this
  is an int-to-int comparison (correct for all values).
- Downstream: adjusted harness, base test run, coverage, improved tests, manual black-box.
