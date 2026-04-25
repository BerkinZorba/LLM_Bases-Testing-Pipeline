# HumanEval_020 - Manual black-box assessment (Codex)

Function under test: `Solution.findClosestElements(List<Double> numbers)`

Prompt requirement:
> From a supplied list of numbers (of length at least two) select and return two that are the closest to each other and return them in order (smaller number, larger number).

This document treats the prompt as a black-box specification and identifies input partitions that affect which pair should be returned.

## 1. Observable input dimensions

| Dimension | Values worth partitioning |
|-----------|----------------------------|
| List length | fewer than two, exactly two, more than two |
| Ordering | already sorted, reverse sorted, arbitrary order |
| Numeric sign | positive only, negative only, mixed sign |
| Distance shape | unique closest pair, duplicate values, tied closest pairs |
| Pair position after sorting | beginning, middle, end |
| Mutability expectation | result should not require mutating the input list |
| Reference validity | non-null vs null |

The output is a two-element list containing the closest pair in ascending order. When multiple pairs are equally closest, the prompt does not define tie-breaking; tests pin the current implementation's first-pair-after-sorting behavior.

## 2. Equivalence classes

### Valid classes

| ID | Description | Example | Expected |
|----|-------------|---------|----------|
| V1 | Exactly two values | `[8.0, 1.0]` | `[1.0, 8.0]` |
| V2 | Closest pair at beginning after sorting | `[1.0, 1.1, 5.0]` | `[1.0, 1.1]` |
| V3 | Closest pair in middle after sorting | `[1.1, 2.2, 3.1, 4.1, 5.1]` | `[2.2, 3.1]` |
| V4 | Closest pair at end after sorting | `[1.0, 4.0, 5.0, 5.9]` | `[5.0, 5.9]` |
| V5 | Duplicate values | `[1.0, 2.0, 2.0, 5.0]` | `[2.0, 2.0]` |
| V6 | Negative values | `[-5.0, -2.0, -2.5, 4.0]` | `[-2.5, -2.0]` |
| V7 | Mixed signs | `[-0.1, 0.0, 10.0, 9.75]` | `[-0.1, 0.0]` |
| V8 | Arbitrary input order | `[3.0, 1.0, 2.0]` | `[1.0, 2.0]` |
| V9 | Tied closest distances | `[1.0, 2.0, 3.0]` | `[1.0, 2.0]` |

### Invalid or underspecified classes

| ID | Description | Current implementation behavior |
|----|-------------|---------------------------------|
| I1 | `null` list | returns an empty list |
| I2 | Empty list | returns an empty list |
| I3 | Singleton list | returns an empty list |
| I4 | Tied closest pairs | returns the first closest pair in sorted order |

## 3. Boundary cases

| Boundary | Input | Expected |
|----------|-------|----------|
| Length 0 | `[]` | `[]` |
| Length 1 | `[42.0]` | `[]` |
| Length 2 | `[8.0, 1.0]` | `[1.0, 8.0]` |
| First zero-distance pair | `[4.4, 4.4, 1.0]` | `[4.4, 4.4]` |
| Tie between adjacent gaps | `[1.0, 2.0, 3.0]` | `[1.0, 2.0]` |

## 4. Black-box expectations to execute

- return values must be sorted as `(smaller, larger)` even when the input is not sorted;
- duplicate values are the closest possible pair because their distance is zero;
- negative and mixed-sign numbers should be handled by numeric distance, not original position;
- invalid-size and null inputs are outside the prompt's length-at-least-two precondition, so tests pin current behavior rather than treat it as a prompt defect.
