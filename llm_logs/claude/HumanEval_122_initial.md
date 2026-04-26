---
prompt_id: HumanEval_122
model: claude
step: initial
iteration: 1
timestamp: 2026-04-26T00:00:00 (local)
output_files:
  - generated_code/claude/HumanEval_122.java
---

## Prompt (verbatim)
```java
import java.util.*;
import java.lang.*;

class Solution {
    /**
    Given a non-empty array of integers arr and an integer k, return
    the sum of the elements with at most two digits from the first k elements of arr.

    Example:

        Input: arr = [111,21,3,4000,5,6,7,8,9], k = 4
        Output: 24 # sum of 21 + 3

    Constraints:
        1. 1 <= len(arr) <= 100
        2. 1 <= k <= len(arr)
     */
    public int addElements(List<Integer> arr, int k) {
```

## Response (verbatim)
```java
import java.util.*;
import java.lang.*;

class Solution {
    /**
    Given a non-empty array of integers arr and an integer k, return
    the sum of the elements with at most two digits from the first k elements of arr.

    Example:

        Input: arr = [111,21,3,4000,5,6,7,8,9], k = 4
        Output: 24 # sum of 21 + 3

    Constraints:
        1. 1 <= len(arr) <= 100
        2. 1 <= k <= len(arr)
     */
    public int addElements(List<Integer> arr, int k) {
        int sum = 0;
        for (int i = 0; i < k; i++) {
            int v = arr.get(i);
            if (Math.abs(v) < 100) {
                sum += v;
            }
        }
        return sum;
    }
}
```

## Decision note
- How was this output used? Saved as-is to `generated_code/claude/HumanEval_122.java`.
- If changed before saving, what changed and why? No changes.
- What downstream artifacts did this produce or invalidate? Provides input for the base/improved/manual JUnit suites under `tests/.../claude/HumanEval_122_*.java` and the coverage runs in `coverage_reports/HumanEval_122/claude/`.
- Approach note: "at most two digits" is interpreted as `|v| < 100` (i.e., values in `[-99, 99]` are kept). Negative inputs like `-3` and `-99` therefore qualify and are added with sign, which matches the dataset case `addElements([1, -2, -3, 41, 57, 76, 87, 88, 99], 3) == -4`. Iteration is bounded by `k`, so elements past index `k-1` are never inspected.
