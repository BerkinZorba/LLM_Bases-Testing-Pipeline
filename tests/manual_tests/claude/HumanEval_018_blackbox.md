# HumanEval_018 - Manual black-box assessment (Claude)

Function under test: `Solution.howManyTimes(String string, String substring)`

Spec from the prompt:

> Find how many times a given substring can be found in the original string. Count overlapping cases.

The function counts all positions `i` at which `string` starts with `substring`, including overlapping occurrences.

## 1. Input domain

| Dimension | Possible values |
|-----------|-----------------|
| `string` reference | `null`, non-`null` |
| `string` length | 0, 1, n > 1 |
| `substring` reference | `null`, non-`null` |
| `substring` length | 0, 1, n > 1 |
| Relative lengths | `substring.length` < `string.length`, equal, greater |
| Match count | 0, 1, multiple non-overlapping, multiple overlapping |
| Match position | no match, prefix only, suffix only, middle only, multiple positions |

## 2. Equivalence classes

### Valid classes

| ID  | Class | Example | Expected |
|-----|-------|---------|----------|
| V1  | Empty string | `("", "x")` | 0 |
| V2  | Empty substring (undefined by spec — Claude returns 0) | `("abc", "")` | 0 |
| V3  | Substring longer than string | `("a", "abc")` | 0 |
| V4  | Substring equals entire string | `("abc", "abc")` | 1 |
| V5  | No match in non-empty string | `("abcdef", "xyz")` | 0 |
| V6  | Single non-overlapping match — prefix | `("abcxyz", "abc")` | 1 |
| V7  | Single non-overlapping match — suffix | `("xyzabc", "abc")` | 1 |
| V8  | Single non-overlapping match — middle | `("xabcz", "abc")` | 1 |
| V9  | Multiple non-overlapping matches | `("abcabcabc", "abc")` | 3 |
| V10 | Overlapping matches — two chars | `("aaa", "aa")` | 2 |
| V11 | Overlapping matches — prompt example | `("aaaa", "aa")` | 3 |
| V12 | Overlapping matches — three chars | `("cacacacac", "cac")` | 4 |
| V13 | Single-char string and substring — match | `("a", "a")` | 1 |
| V14 | Alternating single-char occurrences | `("xyxyxyx", "x")` | 4 |
| V15 | All chars same — single char substring | `("aaa", "a")` | 3 |

### Invalid / undefined-by-spec classes

| ID  | Class | Behavior | How we treat it |
|-----|-------|----------|-----------------|
| I1  | `string == null` | `string.isEmpty()` dereferences `null` | Pin as `NullPointerException`. |
| I2  | `substring == null` | Reached after `string.isEmpty()` check; `substring.isEmpty()` dereferences `null` | Pin as `NullPointerException`. |

## 3. Boundary analysis

| Boundary | Just below | On boundary | Just above |
|----------|------------|-------------|------------|
| String length 0 | n/a | `("", "x")` → 0 | `("x", "x")` → 1 |
| Substring length 0 | n/a | `("abc", "")` → 0 | `("abc", "a")` → 1 (if present) |
| Substring length = string length | `substring.length < string.length`: multiple possible matches | `("abc", "abc")` → 1 | `substring.length > string.length`: `("a", "abc")` → 0 |
| Overlap start | No overlap: `("abcabc", "abc")` → 2 | One-char overlap: `("aa", "aa")` → 1 | Full overlap run: `("aaa", "aa")` → 2 |
| Match at position 0 | No match at 0: `("xabc", "abc")` → 1 (at pos 1) | Match at 0: `("abcx", "abc")` → 1 | n/a |
| Match at last valid position | No match at last: `("xabc", "abc")` → 1 (ends at last) | Match at last valid: `("xabc", "abc")` → 1 | n/a |

## 4. Coverage rationale

The manual cases exercise:

- all three early-return guard conditions independently (`string.isEmpty()`, `substring.isEmpty()`, `substring.length() > string.length()`),
- loop body never entered (no-match result = 0),
- loop body entered with `startsWith` returning false only,
- `startsWith` returning true at first position, last position, and every position,
- overlapping occurrences (each position advances by 1, not by substring length),
- null references that are undefined by the spec but observable in the current implementation.
