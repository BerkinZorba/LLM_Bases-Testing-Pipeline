---
prompt_id: HumanEval_090
model: claude
step: initial
iteration: 1
timestamp: 2026-04-26T00:00:00 (local)
output_files:
  - generated_code/claude/HumanEval_090.java
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
import java.util.*;
import java.lang.*;

class Solution {
    public Optional<Integer> nextSmallest(List<Integer> lst) {
        if (lst == null || lst.size() < 2) return Optional.empty();
        TreeSet<Integer> distinct = new TreeSet<>(lst);
        if (distinct.size() < 2) return Optional.empty();
        distinct.pollFirst();
        return Optional.of(distinct.first());
    }
}
```

## Decision note
- Output saved as-is to `generated_code/claude/HumanEval_090.java`.
- Strategy: deduplicate via TreeSet (natural order); if fewer than 2 distinct values exist, return empty. Remove smallest and return new first (= 2nd distinct smallest).
- Key design choice: "2nd smallest" means 2nd DISTINCT value — confirmed by the `[1,1]` → `Optional.empty` example.
- No downstream artifacts invalidated.
