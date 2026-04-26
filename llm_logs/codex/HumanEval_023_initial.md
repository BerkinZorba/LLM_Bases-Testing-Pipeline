---
prompt_id: HumanEval_023
model: codex
step: initial
iteration: 1
timestamp: 2026-04-26T00:00:00 (local)
output_files:
  - generated_code/codex/HumanEval_023.java
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
public int strlen(String string) {
    return string.length();
}
```

## Decision note
- Saved as-is; added class wrapper and imports for compilation.
- One-liner delegating to `String.length()`. Correct and idiomatic.
- Downstream: base test run, coverage collection, improved tests, manual black-box.
