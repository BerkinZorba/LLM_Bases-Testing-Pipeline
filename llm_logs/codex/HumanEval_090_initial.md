---
prompt_id: HumanEval_090
model: codex
step: initial
iteration: 1
timestamp: 2026-04-26T00:00:00 (local)
output_files:
  - generated_code/codex/HumanEval_090.java
---

## Prompt (verbatim)
```java
import java.util.*;
import java.lang.*;

class Solution {
    /**
    You are given a list of integers.
    Write a function nextSmallest() that returns the 2nd smallest element of the list.
    Return null if there is no such element.
    <p>
    nextSmallest(Arrays.asList(1, 2, 3, 4, 5)) == Optional[2]
    nextSmallest(Arrays.asList(5, 1, 4, 3, 2)) == Optional[2]
    nextSmallest(Arrays.asList()) == Optional.empty
    nextSmallest(Arrays.asList(1, 1)) == Optional.empty
     */
    public Optional<Integer> nextSmallest(List<Integer> lst) {
```

## Response (verbatim)
```java
public Optional<Integer> nextSmallest(List<Integer> lst) {
    if (lst == null || lst.size() < 2) return Optional.empty();

    Integer min = null;
    Integer second = null;

    for (Integer num : lst) {
        if (min == null || num < min) {
            if (min != null && !num.equals(min)) {
                second = min;
            }
            min = num;
        } else if (!num.equals(min) && (second == null || num < second)) {
            second = num;
        }
    }

    return second == null ? Optional.empty() : Optional.of(second);
}
```

## Decision note
- Saved as-is; added class wrapper and imports for compilation.
- Single-pass two-variable tracking (min and second). `lst == null` guard creates
  an unreachable branch under spec (null input undefined).
- Downstream: base test run, coverage collection, improved tests, manual black-box.
