# HumanEval_089 - Manual black-box assessment (Claude)

Function under test: `Solution.encrypt(String s)`

Spec from the prompt:

> Create a function encrypt that takes a string as an argument and returns a string encrypted with the alphabet being rotated. The alphabet should be rotated in a manner such that the letters shift down by **two multiplied to two** places.

Shift = 2 × 2 = 4. The spec examples involve only lowercase letters. The Claude implementation also rotates uppercase letters with the same shift and passes non-alphabetic characters through unchanged (both behaviours are undefined by the spec).

## 1. Input domain

| Dimension | Possible values |
|-----------|-----------------|
| Input reference | `null`, non-`null` |
| Input content | empty, lowercase only, uppercase only, mixed case, digits, spaces, punctuation, mixed |
| Alphabet position | letters near start (a-d), mid-alphabet, near end (w-z, wrap-around) |
| String length | 0, 1, short, long |

## 2. Equivalence classes

### Valid classes (spec-defined: lowercase letters)

| ID  | Class | Example input | Expected |
|-----|-------|---------------|----------|
| V1  | Empty string | `""` | `""` |
| V2  | Single lowercase mid-alphabet | `"a"` | `"e"` |
| V3  | Single lowercase near end (wrap) | `"z"` | `"d"` |
| V4  | All lowercase, no wrap | `"hi"` | `"lm"` |
| V5  | All lowercase, with wrap | `"wxyz"` | `"abcd"` |
| V6  | Longer lowercase string | `"hellomyfriend"` | `"lippsqcjvmirh"` |
| V7  | Repeated chars | `"aaa"` | `"eee"` |

### Extended classes (undefined by spec — pinned behavior)

| ID  | Class | Example input | Expected |
|-----|-------|---------------|----------|
| V8  | Uppercase letters rotated same shift | `"AB"` | `"EF"` |
| V9  | Uppercase wrap-around | `"WXYZ"` | `"ABCD"` |
| V10 | Mixed lower + upper | `"aAbB"` | `"eEfF"` |
| V11 | Digits pass through | `"a1e"` | `"e1i"` |
| V12 | Spaces pass through | `"hi no"` | `"lm rs"` |
| V13 | Punctuation passes through | `"a!e."` | `"e!i."` |

### Invalid / undefined-by-spec classes

| ID  | Class | Behavior | How we treat it |
|-----|-------|----------|-----------------|
| I1  | `null` input | Guard `s == null` → return `""` | Pin as `""`. |

## 3. Boundary analysis

| Boundary | Just below | On boundary | Just above |
|----------|------------|-------------|------------|
| String length | n/a | 0 (empty → `""`) | 1 (`"a"` → `"e"`) |
| Lowercase wrap point | `"v"` → `"z"` (no wrap) | `"w"` → `"a"` (first wrap) | n/a |
| Uppercase wrap point | `"V"` → `"Z"` (no wrap) | `"W"` → `"A"` (first wrap) | n/a |
| First letter of alphabet | n/a | `"a"` → `"e"` | n/a |
| Last letter before wrap | `"v"` → `"z"` | `"w"` → `"a"` | `"x"` → `"b"` |

## 4. Coverage rationale

The manual cases exercise:

- null guard true branch,
- empty-string guard true branch,
- lowercase branch (includes wrap and non-wrap),
- uppercase branch (includes wrap),
- non-alphabetic else branch (digit, space, punctuation),
- single-character input (loop runs exactly once),
- long input (loop runs many times),
- all four end-of-alphabet wrap letters w, x, y, z.
