# HumanEval_040 - Manual black-box assessment (Claude)

Function under test: `Solution.triplesSumToZero(List<Integer> l)`
Implementation tested: `generated_code/claude/HumanEval_040_v2.java`

Spec from the prompt:

> triplesSumToZero takes a list of integers as an input. It returns True if there are three **distinct elements** in the list that sum to zero, and False otherwise.

"Distinct elements" means elements at three distinct indices (`i < j < k`), not necessarily distinct values. The algorithm uses a sort + two-pointer approach.

## 1. Input domain

| Dimension | Possible values |
|-----------|-----------------|
| List reference | `null`, non-`null` |
| List size | 0, 1, 2 (too small), 3 (minimum valid), n > 3 |
| Element values | all positive, all negative, mixed sign, zeros, duplicates |
| Triple existence | no triple, exactly one triple, multiple triples |
| Triple position (sorted) | first/middle/last positions, spanning all positions |
| Sum of triple | < 0, = 0, > 0 |

## 2. Equivalence classes

### Valid classes

| ID  | Class | Example input | Expected |
|-----|-------|---------------|----------|
| V1  | List too short — empty | `[]` | false |
| V2  | List too short — one element | `[5]` | false |
| V3  | List too short — two elements | `[0, 0]` | false |
| V4  | Exactly three elements summing to zero | `[-3, 1, 2]` | true |
| V5  | Exactly three elements not summing to zero | `[1, 2, 4]` | false |
| V6  | Three zeros | `[0, 0, 0]` | true |
| V7  | All positive — no triple possible | `[1, 2, 3, 4, 5]` | false |
| V8  | All negative with mixed list — triple exists | `[-3, -2, -1, 5, 6]` | true |
| V9  | Mixed sign — triple exists | `[1, 3, -2, 1]` | true |
| V10 | Mixed sign — no triple | `[1, 3, 5, 0]` | false |
| V11 | Duplicate values forming the triple | `[-2, 1, 1, 5]` | true |
| V12 | Triple uses smallest and largest sorted values | `[-10, 3, 7, 100]` | true |
| V13 | Large-magnitude values with no triple | `[1, 3, 5, -100]` | false |
| V14 | Multiple triples in list | `[-3, -1, 0, 1, 2, 3]` | true |
| V15 | Negative + two positives forming zero | `[2, 4, -5, 3, 9, 7]` | true (−5+2+3=0) |

### Invalid / undefined-by-spec classes

| ID  | Class | Behavior | How we treat it |
|-----|-------|----------|-----------------|
| I1  | `l == null` | `l.size()` dereferences null | Pin as `NullPointerException`. |
| I2  | Element is `null` | Unboxing during sort or addition throws | Pin as `NullPointerException`. |

## 3. Boundary analysis

| Boundary | Just below | On boundary | Just above |
|----------|------------|-------------|------------|
| List size < 3 | size = 2: no result possible | size = 3: minimum valid | size = 4: first non-trivial case |
| Sum = 0 | sum = −1: not a triple | sum = 0: triple found | sum = 1: not a triple |
| Two-pointer: left meets right | left = right − 1: last iteration of inner loop | left = right: loop exits without match | n/a |
| Triple at sorted-index boundary | triple uses indices 0,1,2 (smallest three) | triple uses indices 0,1,n−1 (smallest two + largest) | triple uses indices n−3,n−2,n−1 (largest three) |

## 4. Coverage rationale

The manual cases exercise:

- `n < 3` guard (empty, size-1, size-2) causing early return false,
- `n == 3` triggering the loop exactly once (one outer iteration, one two-pointer step),
- outer loop: first-element minimum (negative anchor), last-element maximum anchor,
- inner while: `sum == 0` (return true), `sum < 0` (left++), `sum > 0` (right--),
- zero-valued elements,
- duplicate values,
- null input pinned as NPE.
