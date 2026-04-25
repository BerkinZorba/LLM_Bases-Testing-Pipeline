---
prompt_id: HumanEval_019
model: claude
step: initial
iteration: 1
timestamp: 2026-04-25T00:00:00 (UTC; date-only — interactive session, exact wall-clock not captured)
output_files:
  - generated_code/claude/HumanEval_019.java
---

## Prompt (verbatim)
```java
import java.util.*;
import java.lang.*;

class Solution {
    /**
    Input is a space-delimited string of numberals from 'zero' to 'nine'.
    Valid choices are 'zero', 'one', 'two', 'three', 'four', 'five', 'six', 'seven', 'eight' and 'nine'.
    Return the string with numbers sorted from smallest to largest
    >>> sortNumbers("three one five")
    "one three five"
     */
    public String sortNumbers(String numbers) {
```

## Response (verbatim)
```java
import java.util.*;
import java.lang.*;

class Solution {
    /**
     * Input is a space-delimited string of numerals from 'zero' to 'nine'.
     * Valid choices are 'zero', 'one', 'two', 'three', 'four', 'five', 'six', 'seven', 'eight' and 'nine'.
     * Return the string with numbers sorted from smallest to largest
     * >>> sortNumbers("three one five")
     * "one three five"
     */
    public String sortNumbers(String numbers) {
        if (numbers == null || numbers.trim().isEmpty()) return "";

        Map<String, Integer> wordToNum = new HashMap<>();
        wordToNum.put("zero",  0);
        wordToNum.put("one",   1);
        wordToNum.put("two",   2);
        wordToNum.put("three", 3);
        wordToNum.put("four",  4);
        wordToNum.put("five",  5);
        wordToNum.put("six",   6);
        wordToNum.put("seven", 7);
        wordToNum.put("eight", 8);
        wordToNum.put("nine",  9);

        String[] numToWord = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

        String[] words = numbers.trim().split(" ");
        Arrays.sort(words, Comparator.comparingInt(wordToNum::get));

        return String.join(" ", words);
    }
}
```

## Decision note
- How was this output used? Saved as-is to `generated_code/claude/HumanEval_019.java`. No edits.
- If changed before saving, what changed and why? No changes.
- What downstream artifacts did this produce or invalidate? Produces the candidate-under-test consumed by `tests/base_tests/claude/HumanEval_019_BaseTest.java` and the improved/manual suites. Note: the `numToWord` array is declared but never used — dead code, but does not affect correctness.
