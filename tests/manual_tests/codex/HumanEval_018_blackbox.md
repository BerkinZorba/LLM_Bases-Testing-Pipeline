# HumanEval_018 - Manual black-box assessment (Codex)

Function under test: `Solution.howManyTimes(String string, String substring)`

Prompt requirement:
> Find how many times a given substring can be found in the original string. Count overlaping cases.

This document treats the prompt as a black-box specification and identifies input partitions that affect the observable count.

## 1. Observable input dimensions

| Dimension | Values worth partitioning |
|-----------|----------------------------|
| Source length | empty, shorter than substring, equal length, longer |
| Substring length | empty, one character, multiple characters |
| Match placement | none, prefix, middle, suffix, repeated |
| Overlap | non-overlapping only, overlapping windows |
| Character type | letters, whitespace, punctuation, digits |
| Case | exact case match vs case mismatch |
| Reference validity | non-null vs null |

The output is the number of start positions in `string` where `substring` appears exactly.

## 2. Equivalence classes

### Valid classes

| ID | Description | Example | Expected |
|----|-------------|---------|----------|
| V1 | Empty source with non-empty substring | `("", "a")` | `0` |
| V2 | One-character substring repeated | `("aaa", "a")` | `3` |
| V3 | Multi-character overlapping substring | `("aaaa", "aa")` | `3` |
| V4 | Source equals substring | `("abc", "abc")` | `1` |
| V5 | Substring longer than source | `("abc", "abcd")` | `0` |
| V6 | No occurrence after full scan | `("abcdef", "gh")` | `0` |
| V7 | Prefix occurrence | `("john doe", "john")` | `1` |
| V8 | Suffix occurrence | `("hello.java", ".java")` | `1` |
| V9 | Multiple non-overlapping occurrences | `("cat dog cat", "cat")` | `2` |
| V10 | Whitespace participates in matching | `("a b a b", "a b")` | `2` |
| V11 | Punctuation participates in matching | `("!!!", "!!")` | `2` |
| V12 | Case-sensitive matching | `("CaseCASEcase", "case")` | `1` |

### Invalid or underspecified classes

| ID | Description | Current implementation behavior |
|----|-------------|---------------------------------|
| I1 | `null` source | returns `0` |
| I2 | `null` substring | returns `0` |
| I3 | Empty substring | returns `0` |

## 3. Boundary cases

| Boundary | Input | Expected |
|----------|-------|----------|
| Source length 0 | `("", "x")` | `0` |
| Substring length 1 | `("xyxyxyx", "x")` | `4` |
| Equal source and substring length | `("abc", "abc")` | `1` |
| First possible overlapping pair | `("aa", "aa")` | `1` |
| One more overlapping window | `("aaa", "aa")` | `2` |
| Substring one character too long | `("abc", "abcd")` | `0` |

## 4. Black-box expectations to execute

- every possible start index must be considered, including overlapping windows;
- exact matching is case-sensitive because the prompt does not request normalization;
- whitespace and punctuation are normal characters;
- empty substring and `null` inputs are outside the prompt's precise wording, so tests pin current behavior rather than treat it as a specification violation.
