# HumanEval_020 - Manual black-box assessment (Claude)

Function under test: `Solution.findClosestElements(List<Double> numbers)`

Spec from the prompt:

> From a supplied list of numbers (of length at least two) select and return two that are the closest to each other and return them in order (smaller number, larger number).

The function identifies the pair `(a, b)` with the minimum absolute difference and returns `[min(a,b), max(a,b)]`. When multiple pairs share the minimum difference, the pair appearing first in sorted order is returned.

## 1. Input domain

| Dimension | Possible values |
|-----------|-----------------|
| List reference | `null`, non-`null` |
| List size | 2 (minimum), 3, n > 3 |
| Element values | all positive, all negative, mixed sign, duplicates, all equal |
| Closest pair position (sorted) | first pair, last pair, middle pair |
| Minimum-diff count | unique minimum, multiple pairs tied at minimum diff |
| Diff value | 0 (duplicates), small positive, large positive |

## 2. Equivalence classes

### Valid classes

| ID  | Class | Example input | Expected |
|-----|-------|---------------|----------|
| V1  | Two-element list, natural order | `[1.0, 3.0]` | `[1.0, 3.0]` |
| V2  | Two-element list, reversed | `[3.0, 1.0]` | `[1.0, 3.0]` |
| V3  | Duplicate elements — diff zero | `[2.0, 5.0, 2.0]` | `[2.0, 2.0]` |
| V4  | Closest pair at start of sorted list | `[1.0, 1.1, 3.0, 5.0]` | `[1.0, 1.1]` |
| V5  | Closest pair at end of sorted list | `[1.0, 3.0, 4.9, 5.0]` | `[4.9, 5.0]` |
| V6  | Closest pair in middle of sorted list | `[1.0, 2.0, 2.1, 4.0, 6.0]` | `[2.0, 2.1]` |
| V7  | Multiple non-overlapping matches, unique closest | `[1.0, 2.0, 3.9, 4.0, 5.0]` | `[3.9, 4.0]` |
| V8  | All negative numbers | `[-5.0, -3.0, -2.9, -1.0]` | `[-3.0, -2.9]` |
| V9  | Mixed positive and negative | `[-0.1, 0.0, 3.0, 5.0]` | `[-0.1, 0.0]` |
| V10 | All elements equally spaced — first pair wins tie | `[1.0, 2.0, 3.0, 4.0]` | `[1.0, 2.0]` |
| V11 | Large list, closest not adjacent in input | `[1.0, 2.0, 5.9, 4.0, 5.0]` | `[5.0, 5.9]` |
| V12 | Two identical elements only | `[4.0, 4.0]` | `[4.0, 4.0]` |

### Invalid / undefined-by-spec classes

| ID  | Class | Behavior | How we treat it |
|-----|-------|----------|-----------------|
| I1  | `numbers == null` | `new ArrayList<>(null)` throws `NullPointerException` | Pin as `NullPointerException`. |
| I2  | Single-element list | Loop runs 0 iterations (`size - 1 = 0`); returns `[0.0, 0.0]` (uninitialized defaults) | Pin as observable wrong behavior — spec guarantees length ≥ 2. |

## 3. Boundary analysis

| Boundary | Just below | On boundary | Just above |
|----------|------------|-------------|------------|
| List size | n/a (spec: ≥ 2) | 2 elements | 3 elements |
| Diff value (0) | diff just above 0: `[1.0, 1.001]` | diff = 0: `[2.0, 2.0]` | n/a |
| Closest at sorted index 0 | closest at index 1: `[1.0, 2.0, 2.05]` → `[2.0, 2.05]` | closest at index 0: `[1.0, 1.05, 3.0]` → `[1.0, 1.05]` | n/a |
| Closest at last sorted index | closest at second-to-last: `[1.0, 1.05, 3.0]` | closest at last: `[1.0, 3.0, 3.05]` → `[3.0, 3.05]` | n/a |
| Tie-break (all equal diffs) | one pair clearly closest | all adjacent diffs equal → first pair returned | n/a |

## 4. Coverage rationale

The manual cases exercise:

- loop body entered exactly once (2-element list) and multiple times (n > 2),
- `if (diff < minDiff)` true on first iteration (always, since `minDiff = MAX_VALUE`) and false on later iterations (tie and larger-diff cases),
- closest pair at each possible sorted position (first, middle, last),
- zero-diff case (duplicate elements),
- negative numbers and mixed-sign numbers,
- tie-breaking: first pair in sorted order wins,
- input list not mutated (sort is performed on a copy),
- null list behavior pinned as `NullPointerException`.
