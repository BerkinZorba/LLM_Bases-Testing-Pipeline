---
prompt_id: HumanEval_087
model: claude
step: test_improve
iteration: 1
timestamp: 2026-04-26T00:00:00 (local)
output_files:
  - tests/improved_tests/claude/HumanEval_087_ImprovedTest.java
---

## Prompt (verbatim)

The following Java solution for HumanEval_087 (getRow) passes all 6 base tests:

```java
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
```

The base test suite has 6 tests. Please write an improved JUnit 5 test suite that:
1. Addresses test smells: assertion roulette, magic numbers, eager test.
2. Improves branch coverage by targeting each branch in the implementation:
   - Outer for-loop guard: empty list vs non-empty list.
   - Inner for-loop guard: empty row vs non-empty row.
   - if-condition: x matches vs x does not match.
3. Uses @Nested classes to group related test cases.
4. Each test method tests exactly one concern.

## Response (verbatim)

The improved test suite was designed based on branch analysis of the Claude implementation. Branches identified:
1. Outer loop: `row < lst.size()` — enters 0 or more times (empty list covers not-entering)
2. Inner loop: `col >= 0` after `r.size() - 1` — empty row means col starts at -1, loop never entered
3. if: `r.get(col) == x` — true or false per cell

The suite uses five @Nested groups: EmptyInputs, NoMatches, SingleMatch, MultipleMatchesInRow, MultiRow. Tests are isolated with one assertion each, and Display Names replace magic numbers.

Branch coverage: 6/6 branches (100%) on Claude's getRow.

## Decision note
- Output saved as-is to tests/improved_tests/claude/HumanEval_087_ImprovedTest.java.
- No changes needed; all 13 tests pass, 6/6 branches covered.
- Downstream: analysis/HumanEval_087_claude.md updated with coverage findings.
