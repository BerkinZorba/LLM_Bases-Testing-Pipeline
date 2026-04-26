# HumanEval_040 - Manual black-box assessment (Codex)

Function under test: `Solution.triplesSumToZero(List<Integer> l)`

Prompt requirement:
> triplesSumToZero takes a list of integers as an input. it returns True if there are three distinct elements in the list that sum to zero, and False otherwise.

This document treats the prompt as a black-box specification and identifies input partitions that affect whether a valid zero-sum triple exists.

## 1. Observable input dimensions

| Dimension | Values worth partitioning |
|-----------|----------------------------|
| List length | fewer than three, exactly three, more than three |
| Sign distribution | all positive, all negative, mixed signs, includes zero |
| Multiplicity | unique values, duplicate values, all zeros |
| Triple location | apparent before sorting, after sorting across low/mid/high values |
| Result | at least one valid triple, no valid triple |
| Mutability expectation | result should not require mutating the input list |
| Reference validity | non-null vs null |

The output is a boolean indicating whether any three distinct list positions contain values whose sum is zero.

## 2. Equivalence classes

### Valid classes

| ID | Description | Example | Expected |
|----|-------------|---------|----------|
| V1 | Fewer than three elements | `[1, 2]` | `false` |
| V2 | Exactly three elements summing to zero | `[-1, 0, 1]` | `true` |
| V3 | Exactly three elements not summing to zero | `[1, 2, 4]` | `false` |
| V4 | All positive values | `[1, 2, 3, 7]` | `false` |
| V5 | All negative values | `[-5, -4, -1]` | `false` |
| V6 | Duplicate values needed as distinct elements | `[-2, -2, 4, 8]` | `true` |
| V7 | Three zeros | `[0, 0, 0]` | `true` |
| V8 | Zero present but no triple | `[0, 1, 2]` | `false` |
| V9 | Mixed values with hidden triple | `[2, 4, -5, 3, 9, 7]` | `true` |
| V10 | Opposite pair without zero | `[100, 3, 5, -100]` | `false` |

### Invalid or underspecified classes

| ID | Description | Current implementation behavior |
|----|-------------|---------------------------------|
| I1 | `null` list | returns `false` |
| I2 | Empty list | returns `false` |
| I3 | Singleton list | returns `false` |

## 3. Boundary cases

| Boundary | Input | Expected |
|----------|-------|----------|
| Length 0 | `[]` | `false` |
| Length 1 | `[1]` | `false` |
| Length 2 | `[1, -1]` | `false` |
| Length 3 true | `[-1, 0, 1]` | `true` |
| Length 3 false | `[1, 2, 4]` | `false` |
| Duplicate values as distinct elements | `[-2, -2, 4]` | `true` |

## 4. Black-box expectations to execute

- the three elements must be distinct positions, but duplicate numeric values are allowed when present in separate positions;
- lists shorter than three cannot contain a valid triple;
- all-positive and all-negative lists should return false;
- zero values should only return true when a full three-element sum exists;
- null and shorter-than-three inputs are outside the strongest dataset examples, so tests pin current behavior rather than treating it as a prompt defect.
