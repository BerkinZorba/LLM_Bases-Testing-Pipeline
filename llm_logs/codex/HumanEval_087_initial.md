---
prompt_id: HumanEval_087
model: codex
step: initial
iteration: 1
timestamp: 2026-04-26T00:00:00 (local)
output_files:
  - generated_code/codex/HumanEval_087.java
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
```

## Decision note
- Saved as-is; added class wrapper and imports for compilation.
- Collects matching column indices for each row into a temporary `cols` list (ascending),
  then sorts it with `Collections.reverseOrder()` to achieve descending column order.
- A third for-each loop adds the sorted coordinates to the result.
- Functionally equivalent to Claude's solution for all spec-defined inputs; differs in
  that it uses an explicit sort rather than reverse iteration.
- Downstream: base test run, coverage, improved tests, manual black-box.
