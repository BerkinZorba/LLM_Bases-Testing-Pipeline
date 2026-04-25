---
prompt_id: HumanEval_081
model: claude
step: initial
iteration: 1
timestamp: 2026-04-26T00:00:00 (local)
output_files:
  - generated_code/claude/HumanEval_081.java
---

## Prompt (verbatim)
```java
import java.util.*;
import java.lang.*;

class Solution {
    /**
    It is the last week of the semester and the teacher has to give the grades
    to students. The teacher has been making her own algorithm for grading.
    The only problem is, she has lost the code she used for grading.
    She has given you a list of GPAs for some students and you have to write
    a function that can output a list of letter grades using the following table:
             GPA       |    Letter grade
              4.0                A+
            > 3.7                A
            > 3.3                A-
            > 3.0                B+
            > 2.7                B
            > 2.3                B-
            > 2.0                C+
            > 1.7                C
            > 1.3                C-
            > 1.0                D+
            > 0.7                D
            > 0.0                D-
              0.0                E


    Example:
    numericalLetterGrade(Arrays.asList(4.0, 3, 1.7, 2, 3.5)) ==> ["A+", "B", "C-", "C", "A-"]
     */
    public List<String> numericalLetterGrade(List<Double> grades) {
```

## Response (verbatim)
```java
import java.util.*;
import java.lang.*;

class Solution {
    /**
    It is the last week of the semester and the teacher has to give the grades
    to students. The teacher has been making her own algorithm for grading.
    The only problem is, she has lost the code she used for grading.
    She has given you a list of GPAs for some students and you have to write
    a function that can output a list of letter grades using the following table:
             GPA       |    Letter grade
              4.0                A+
            > 3.7                A
            > 3.3                A-
            > 3.0                B+
            > 2.7                B
            > 2.3                B-
            > 2.0                C+
            > 1.7                C
            > 1.3                C-
            > 1.0                D+
            > 0.7                D
            > 0.0                D-
              0.0                E


    Example:
    numericalLetterGrade(Arrays.asList(4.0, 3, 1.7, 2, 3.5)) ==> ["A+", "B", "C-", "C", "A-"]
     */
    public List<String> numericalLetterGrade(List<Double> grades) {
        List<String> result = new ArrayList<>();
        for (Double g : grades) {
            double gpa = g;
            String letter;
            if (gpa == 4.0) {
                letter = "A+";
            } else if (gpa > 3.7) {
                letter = "A";
            } else if (gpa > 3.3) {
                letter = "A-";
            } else if (gpa > 3.0) {
                letter = "B+";
            } else if (gpa > 2.7) {
                letter = "B";
            } else if (gpa > 2.3) {
                letter = "B-";
            } else if (gpa > 2.0) {
                letter = "C+";
            } else if (gpa > 1.7) {
                letter = "C";
            } else if (gpa > 1.3) {
                letter = "C-";
            } else if (gpa > 1.0) {
                letter = "D+";
            } else if (gpa > 0.7) {
                letter = "D";
            } else if (gpa > 0.0) {
                letter = "D-";
            } else {
                letter = "E";
            }
            result.add(letter);
        }
        return result;
    }
}
```

## Decision note
- How was this output used? Saved as-is to `generated_code/claude/HumanEval_081.java`.
- If changed before saving, what changed and why? No changes.
- What downstream artifacts did this produce or invalidate? Provides the input for `tests/base_tests/claude/HumanEval_081_BaseTest.java`, `tests/improved_tests/claude/HumanEval_081_ImprovedTest.java`, `tests/manual_tests/claude/HumanEval_081_*`, and the JaCoCo runs under `coverage_reports/HumanEval_081/claude/`.
- Approach note: the table uses strict `>` for the 12 mid-range bands and a closed top boundary at `4.0` (exact equality maps to `A+`). The bottom boundary is `0.0`, which maps to `E`; any positive value strictly less than `0.7` falls through to `D-`. Reading the example confirms the strict-`>` semantics: `3.0 -> B`, `1.7 -> C-`, `2.0 -> C`, all of which require `>` rather than `>=`.
