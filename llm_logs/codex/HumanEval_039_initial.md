---
prompt_id: HumanEval_039
model: codex
step: initial
iteration: 1
timestamp: 2026-04-26T00:00:00 (local)
output_files:
  - generated_code/codex/HumanEval_039.java
---

## Prompt (verbatim)
```java
import java.util.*;
import java.lang.*;

class Solution {
    /**
    primeFib returns n-th number that is a Fibonacci number and it's also prime.
    >>> primeFib(1)
    2
    >>> primeFib(2)
    3
    >>> primeFib(3)
    5
    >>> primeFib(4)
    13
    >>> primeFib(5)
    89
     */
    public int primeFib(int n) {
```

## Response (verbatim)
```java
public int primeFib(int n) {
    int count = 0, a = 0, b = 1;

    while (true) {
        int next = a + b;
        a = b;
        b = next;

        if (next >= 2) {
            boolean prime = true;
            if (next % 2 == 0 && next != 2) prime = false;
            for (int i = 3; i * i <= next && prime; i += 2) {
                if (next % i == 0) prime = false;
            }
            if (prime) {
                count++;
                if (count == n) return next;
            }
        }
    }
}
```

## Decision note
- Saved as-is; added class wrapper and imports for compilation.
- Inline primality check (no helper method). Generates Fibonacci sequence starting
  from a=0,b=1 and tests each value >= 2 for primality.
- Downstream: base test run, coverage collection, improved tests, manual black-box.
