---
prompt_id: HumanEval_036
model: claude
step: initial
iteration: 1
timestamp: 2026-04-25T00:00:00 (local)
output_files:
  - generated_code/claude/HumanEval_036.java
---

## Prompt (verbatim)
```java
import java.util.*;
import java.lang.*;

class Solution {
    /**
    Return the number of times the digit 7 appears in integers less than n which are divisible by 11 or 13.
    >>> fizzBuzz(50)
    0
    >>> fizzBuzz(78)
    2
    >>> fizzBuzz(79)
    3
     */
    public int fizzBuzz(int n) {
```

## Response (verbatim)
```java
import java.util.*;
import java.lang.*;

class Solution {
    /**
    Return the number of times the digit 7 appears in integers less than n which are divisible by 11 or 13.
    >>> fizzBuzz(50)
    0
    >>> fizzBuzz(78)
    2
    >>> fizzBuzz(79)
    3
     */
    public int fizzBuzz(int n) {
        int count = 0;
        for (int i = 1; i < n; i++) {
            if (i % 11 == 0 || i % 13 == 0) {
                int num = i;
                while (num > 0) {
                    if (num % 10 == 7) {
                        count++;
                    }
                    num /= 10;
                }
            }
        }
        return count;
    }
}
```

## Decision note
- How was this output used? Saved as-is to `generated_code/claude/HumanEval_036.java`. No edits to the LLM output before saving (per Section 9 guardrail: do not silently edit generated code before base testing).
- If changed before saving, what changed and why? No changes.
- What downstream artifacts did this produce or invalidate? Produces the input for `tests/base_tests/claude/HumanEval_036_BaseTest.java` execution (next step). Coverage and analysis artifacts for HumanEval_036/claude do not yet exist.
