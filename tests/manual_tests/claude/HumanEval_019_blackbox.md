# HumanEval_019 - Manual black-box assessment (Claude)

Function under test: `Solution.sortNumbers(String numbers)`

Spec from the prompt:

> Input is a space-delimited string of numerals from 'zero' to 'nine'.
> Valid choices are 'zero', 'one', 'two', 'three', 'four', 'five', 'six', 'seven', 'eight' and 'nine'.
> Return the string with numbers sorted from smallest to largest.

The function splits the input on spaces, sorts by numeric value, and rejoins with single spaces.

## 1. Input domain

| Dimension | Possible values |
|-----------|-----------------|
| Input reference | `null`, non-`null` |
| Input content | empty string, whitespace-only, valid words, invalid words |
| Word count | 0, 1, 2, n ≤ 10, n > 10 (duplicates) |
| Initial order | already sorted, reverse sorted, random order |
| Duplicate words | none, one pair, all same |

## 2. Equivalence classes

### Valid classes

| ID  | Class | Example input | Expected output |
|-----|-------|---------------|-----------------|
| V1  | Empty string | `""` | `""` |
| V2  | Single word | `"seven"` | `"seven"` |
| V3  | Two words, already in order | `"two nine"` | `"two nine"` |
| V4  | Two words, reversed | `"nine two"` | `"two nine"` |
| V5  | Three words, random order | `"three one five"` | `"one three five"` |
| V6  | All ten words, forward | `"zero one two three four five six seven eight nine"` | same |
| V7  | All ten words, reverse | `"nine eight seven six five four three two one zero"` | `"zero one two three four five six seven eight nine"` |
| V8  | Duplicate words | `"five one three one five"` | `"one one three five five"` |
| V9  | All same word | `"three three three"` | `"three three three"` |
| V10 | Six-word unsorted mix | `"five zero four seven nine eight"` | `"zero four five seven eight nine"` |
| V11 | Whitespace-only input | `"   "` | `""` |

### Invalid / undefined-by-spec classes

| ID  | Class | Behavior | How we treat it |
|-----|-------|----------|-----------------|
| I1  | `null` input | Guard: `numbers == null` → return `""` | Pin as returning `""`. |
| I2  | Invalid word (not in map) | `wordToNum::get` returns `null`; `Comparator.comparingInt` unboxes null → `NullPointerException` | Pin as `NullPointerException`. |

## 3. Boundary analysis

| Boundary | Just below | On boundary | Just above |
|----------|------------|-------------|------------|
| Word count 0 | n/a | `""` → `""` | `"one"` → `"one"` |
| Word count 1 | n/a | `"one"` → `"one"` | `"one two"` → `"one two"` |
| Minimum value (zero) | n/a | `"zero"` → `"zero"` | `"zero one"` → `"zero one"` |
| Maximum value (nine) | `"eight nine"` → `"eight nine"` | `"nine"` → `"nine"` | n/a |
| Duplicate boundary | no duplicates: `"one two"` | one duplicate pair: `"one one"` | all duplicates: `"one one one"` |

## 4. Coverage rationale

The manual cases exercise:

- `numbers == null` true branch (not tested by dataset harness),
- `numbers.trim().isEmpty()` true branch (empty string and whitespace-only),
- normal path: split, sort, join,
- sort with already-correct order (no swaps needed),
- sort requiring all permutations (reverse order),
- duplicate values (sort stability),
- boundary words zero and nine,
- invalid word pinned as NPE.
