---
prompt_id: HumanEval_059
model: claude
step: initial
iteration: 1
timestamp: 2026-04-26T00:00:00 (local)
output_files:
  - generated_code/claude/HumanEval_059.java
---

## Prompt (verbatim)
```java
import java.util.*;
import java.lang.*;

class Solution {
    /**
    Return the largest prime factor of n. Assume n > 1 and is not a prime.
    >>> largestPrimeFactor(13195)
    29
    >>> largestPrimeFactor(2048)
    2
     */
    public int largestPrimeFactor(int n) {
```

## Response (verbatim)
```java
import java.util.*;
import java.lang.*;

class Solution {
    /**
    Return the largest prime factor of n. Assume n > 1 and is not a prime.
    >>> largestPrimeFactor(13195)
    29
    >>> largestPrimeFactor(2048)
    2
     */
    public int largestPrimeFactor(int n) {
        int largest = 1;
        int x = n;
        int factor = 2;
        while ((long) factor * factor <= x) {
            while (x % factor == 0) {
                largest = factor;
                x /= factor;
            }
            factor++;
        }
        if (x > 1) {
            largest = x;
        }
        return largest;
    }
}
```

## Decision note
- How was this output used? Saved as-is to `generated_code/claude/HumanEval_059.java`. No edits to the LLM output before saving (per Section 9 guardrail: do not silently edit generated code before base testing).
- If changed before saving, what changed and why? No changes.
- What downstream artifacts did this produce or invalidate? Produces the input for `tests/base_tests/claude/HumanEval_059_BaseTest.java` execution (next step). Coverage and analysis artifacts for HumanEval_059/claude do not yet exist.
