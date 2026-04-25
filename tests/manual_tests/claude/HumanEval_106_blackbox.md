# HumanEval_106 - Manual black-box assessment (Claude)

Function under test: `Solution.f(int n)`

Spec from the prompt:

> Implement the function f that takes n as a parameter, and returns a list of size n, such that the value of the element at **index i** is the **factorial of i** if i is even or the **sum of numbers from 1 to i** otherwise. i starts from 1.

Key observations:
- "index i" in the spec means the 1-based position, not the 0-based Java list index.
- Even indices (i = 2, 4, 6, …) → factorial(i).
- Odd indices (i = 1, 3, 5, …) → sum(1 + 2 + … + i) = i*(i+1)/2.
- The result list has exactly `n` elements.

## 1. Input domain

| Dimension | Possible values |
|-----------|-----------------|
| n | negative, 0, 1, 2, small positive, large positive |
| Parity of last index | last element is odd-indexed (sum) vs even-indexed (factorial) |

## 2. Equivalence classes

### Valid classes

| ID | Class | Input | Expected |
|----|-------|-------|----------|
| V1 | n = 0 (empty list) | `0` | `[]` |
| V2 | n = 1 (single odd index) | `1` | `[1]` |
| V3 | n = 2 (one odd + one even) | `2` | `[1, 2]` |
| V4 | n = 3 (ends on odd) | `3` | `[1, 2, 6]` |
| V5 | n = 4 (ends on even) | `4` | `[1, 2, 6, 24]` |
| V6 | n = 5 (from example) | `5` | `[1, 2, 6, 24, 15]` |
| V7 | n = 7 (from dataset) | `7` | `[1, 2, 6, 24, 15, 720, 28]` |
| V8 | n = 8 (ends on even, includes 8!) | `8` | `[1, 2, 6, 24, 15, 720, 28, 40320]` |

### Invalid / undefined-by-spec classes

| ID | Class | Behavior | How we treat it |
|----|-------|----------|-----------------|
| I1 | n < 0 | Loop condition `i <= n` is false for i=1 → returns `[]` | Pin as empty list. |

## 3. Element-level boundary analysis

| i | Parity | Rule | Expected element |
|---|--------|------|-----------------|
| 1 | odd | sum(1..1) = 1 | 1 |
| 2 | even | factorial(2) = 2 | 2 |
| 3 | odd | sum(1..3) = 6 | 6 |
| 4 | even | factorial(4) = 24 | 24 |
| 5 | odd | sum(1..5) = 15 | 15 |
| 6 | even | factorial(6) = 720 | 720 |
| 7 | odd | sum(1..7) = 28 | 28 |
| 8 | even | factorial(8) = 40320 | 40320 |

## 4. List-level boundaries

| Boundary | Input | Expected |
|----------|-------|----------|
| Minimum valid n | 0 | `[]` |
| n = 1 (odd terminus) | 1 | `[1]` |
| n = 2 (even terminus) | 2 | `[1, 2]` |
| n just past parity boundary | 3 and 4 | `[1,2,6]` vs `[1,2,6,24]` |

## 5. Coverage rationale

The manual cases exercise:
- `i % 2 == 0` true branch (even index → factorial),
- `i % 2 == 0` false branch (odd index → sum),
- outer loop executed 0 times (n=0),
- outer loop executed 1 time (n=1),
- outer loop executed many times (n=8),
- inner factorial loop for multiple even i values,
- negative n (I1: loop never runs → empty list).
