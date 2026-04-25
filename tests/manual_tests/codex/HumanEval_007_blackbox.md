# HumanEval_007 - Manual black-box assessment (Codex)

Function under test: `Solution.filterBySubstring(List<String> strings, String substring)`

Prompt requirement:

> Filter an input list of strings only for ones that contain given substring

This analysis treats the prompt as a black-box contract over a list of Java strings and a substring. A returned list should contain exactly those original elements for which containment is true.

## 1. Observable input dimensions

| Dimension | Partitions |
|-----------|------------|
| List size | empty, one element, multiple elements |
| Match count | no matches, one match, many matches |
| Match location | whole string, prefix, middle, suffix |
| Multiplicity | unique matching values, duplicate matching values |
| Substring length | empty string, one character, multiple characters |
| Character category | letters, spaces, punctuation, non-ASCII text |
| Case relationship | exact case vs different case |
| Reference validity | null list, null substring, null element |

## 2. Equivalence classes

### Valid classes

| ID | Class | Example | Expected |
|----|-------|---------|----------|
| V1 | Empty list | `([], "x")` | `[]` |
| V2 | No element contains substring | `(["red", "blue"], "z")` | `[]` |
| V3 | Single exact element match | `(["same"], "same")` | `["same"]` |
| V4 | Prefix match | `(["start", "branch"], "sta")` | `["start"]` |
| V5 | Middle match | `(["planet", "lane", "plain"], "lan")` | `["planet", "lane"]` |
| V6 | Suffix match | `(["ending", "pending", "end"], "ing")` | `["ending", "pending"]` |
| V7 | Duplicate matching values | `(["aa", "bb", "aa"], "aa")` | `["aa", "aa"]` |
| V8 | Empty substring | `(["", "abc"], "")` | `["", "abc"]` |
| V9 | Empty element with non-empty substring | `(["", "abc"], "a")` | `["abc"]` |
| V10 | Case-sensitive search | `(["Dog", "dog", "hotDog"], "Dog")` | `["Dog", "hotDog"]` |
| V11 | Whitespace and punctuation | `(["a b", "a-b", "ab"], "a-")` | `["a-b"]` |
| V12 | Non-ASCII text | `(["ışık", "isik", "güz"], "ış")` | `["ışık"]` |

### Invalid / undefined-by-spec classes

| ID | Class | Current behavior |
|----|-------|------------------|
| I1 | `strings == null` | `NullPointerException` |
| I2 | `substring == null` | `NullPointerException` from `String.contains` |
| I3 | Matching path sees a `null` element | `NullPointerException` |

## 3. Boundary cases

| Boundary | Cases |
|----------|-------|
| List length | `[]`, one element, three elements |
| Substring length | `""`, `"a"`, `"abc"` |
| Element length | empty element, one-character element, longer element |
| Match position | whole string, prefix, middle, suffix, absent |

## 4. Coverage rationale

The manual suite exercises loop entry and exit, the true and false outcomes of `contains`, duplicate retention, order preservation, empty substring behavior, and undefined null-handling behavior.
