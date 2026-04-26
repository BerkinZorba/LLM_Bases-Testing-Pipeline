---
prompt_id: HumanEval_093
model: claude
step: initial
iteration: 1
timestamp: 2026-04-25T00:00:00 (UTC; date-only — interactive session, exact wall-clock not captured)
output_files:
  - generated_code/claude/HumanEval_093.java
---

## Prompt (verbatim)
```java
import java.util.*;
import java.lang.*;

class Solution {
    /**
    Write a function that takes a message, and encodes in such a
    way that it swaps case of all letters, replaces all vowels in
    the message with the letter that appears 2 places ahead of that
    vowel in the english alphabet.
    Assume only letters.

    Examples:
    >>> encode("test")
    "TGST"
    >>> encode("This is a message")
    "tHKS KS C MGSSCGG"
     */
    public String encode(String message) {
```

## Response (verbatim)
```java
import java.util.*;
import java.lang.*;

class Solution {
    /**
     * Write a function that takes a message, and encodes in such a
     * way that it swaps case of all letters, replaces all vowels in
     * the message with the letter that appears 2 places ahead of that
     * vowel in the english alphabet.
     * Assume only letters.
     *
     * Examples:
     * >>> encode("test")
     * "TGST"
     * >>> encode("This is a message")
     * "tHKS KS C MGSSCGG"
     */
    public String encode(String message) {
        if (message == null || message.isEmpty()) return "";

        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
        StringBuilder result = new StringBuilder();

        for (char c : message.toCharArray()) {
            if (c == ' ') {
                result.append(c);
                continue;
            }

            // Step 1: swap case
            char swapped = Character.isUpperCase(c) ? Character.toLowerCase(c) : Character.toUpperCase(c);

            // Step 2: if original was a vowel, shift the swapped char by 2
            if (vowels.contains(c)) {
                swapped = (char) (swapped + 2);
            }

            result.append(swapped);
        }

        return result.toString();
    }
}
```

## Decision note
- How was this output used? Saved as-is to `generated_code/claude/HumanEval_093.java`. No edits.
- If changed before saving, what changed and why? No changes.
- What downstream artifacts did this produce or invalidate? Produces the candidate-under-test consumed by the base, improved, and manual test suites. Note: the spec says "Assume only letters" but the test examples include spaces, and the implementation passes spaces through unchanged — this is a spec-undefined but necessary extension.
