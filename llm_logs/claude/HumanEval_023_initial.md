---
prompt_id: HumanEval_023
model: claude
step: initial
iteration: 1
timestamp: 2026-04-26T00:00:00 (local)
output_files:
  - generated_code/claude/HumanEval_023.java
---

## Prompt (verbatim)

```java
import java.util.*;
import java.lang.*;

class Solution {
    /**
    Return length of given string
    >>> strlen("")
    0
    >>> strlen("abc")
    3
     */
    public int strlen(String string) {
```

## Response (verbatim)

```java
import java.util.*;
import java.lang.*;

class Solution {
    public int strlen(String string) {
        return string.length();
    }
}
```

## Decision note
- Output saved as-is to `generated_code/claude/HumanEval_023.java`.
- Strategy: delegate to `String.length()` which returns the number of `char` units (UTF-16 code units). Exactly what the spec tests measure.
- No downstream artifacts invalidated.
