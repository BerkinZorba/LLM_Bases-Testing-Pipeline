# HumanEval_007 - Manual black-box assessment (Claude)

Function under test: `Solution.filterBySubstring(List<String> strings, String substring)`

Spec from the prompt:

> Filter an input list of strings only for ones that contain given substring

The function returns the elements from the input list whose `String.contains(substring)` predicate is true.

## 1. Input domain

| Dimension | Possible values |
|-----------|-----------------|
| List reference | `null`, non-`null` |
| List size | `0`, `1`, `n > 1` |
| Element value | empty string, non-empty string, `null` |
| Match position | no match, prefix, middle, suffix, whole string |
| Match count | zero matches, one match, multiple matches, duplicate matches |
| Substring value | empty string, non-empty string, `null` |
| Case / character set | same case, different case, whitespace, punctuation, non-ASCII |

## 2. Equivalence classes

### Valid classes

| ID | Class | Example | Expected |
|----|-------|---------|----------|
| V1 | Empty list | `([], "john")` | `[]` |
| V2 | No matching element | `(["abc", "def"], "x")` | `[]` |
| V3 | Single exact match | `(["abc"], "abc")` | `["abc"]` |
| V4 | Prefix match | `(["abc", "zab"], "ab")` | `["abc", "zab"]` |
| V5 | Middle match | `(["grunt", "prune"], "run")` | `["grunt", "prune"]` |
| V6 | Suffix match | `(["hello", "shell"], "ell")` | `["hello", "shell"]` |
| V7 | Duplicate matching values | `(["aa", "b", "aa"], "aa")` | `["aa", "aa"]` |
| V8 | Empty substring | `(["", "a"], "")` | `["", "a"]` |
| V9 | Empty string element with non-empty substring | `(["", "abc"], "a")` | `["abc"]` |
| V10 | Case-sensitive filtering | `(["Alpha", "alpha", "ALPHA"], "A")` | `["Alpha", "ALPHA"]` |
| V11 | Whitespace / punctuation | `(["a b", "ab", "a-b"], "a ")` | `["a b"]` |
| V12 | Repeated strings with suffix match | `(["cafe", "cafe", "cafe"], "fe")` | all three strings containing `fe` |

### Invalid / undefined-by-spec classes

| ID | Class | Behavior | How we treat it |
|----|-------|----------|-----------------|
| I1 | `strings == null` | Enhanced for-loop dereferences the list | Pin as `NullPointerException`. |
| I2 | `substring == null` | `String.contains(null)` throws | Pin as `NullPointerException`. |
| I3 | A list element is `null` | The implementation calls `s.contains(...)` | Pin as `NullPointerException`. |

## 3. Boundary analysis

| Boundary | Just below | On boundary | Just above |
|----------|------------|-------------|------------|
| List size | n/a | `[]` -> `[]` | `["abc"]` with `"a"` -> `["abc"]` |
| Substring length | n/a | `""` matches all elements | one-character substring filters normally |
| Element length | `""` with non-empty substring -> no match | `""` with `""` -> match | `"a"` with `"a"` -> match |
| Match position | no occurrence | prefix / whole-string occurrence | middle / suffix occurrence |

## 4. Coverage rationale

The manual cases exercise:

- loop skipped (`[]`) and loop entered (`n > 0`),
- `if (s.contains(substring))` true and false outcomes,
- duplicate matching elements, preserving multiplicity,
- empty substring and empty element boundaries,
- invalid references that are undefined by the prompt but observable in the current implementation.
