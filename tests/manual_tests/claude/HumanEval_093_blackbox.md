# HumanEval_093 - Manual black-box assessment (Claude)

Function under test: `Solution.encode(String message)`

Spec from the prompt:

> Write a function that takes a message, and encodes in such a way that it **swaps case** of all letters, **replaces all vowels** in the message with the letter that appears **2 places ahead** of that vowel in the english alphabet. Assume only letters.

Examples confirm the two-step order: (1) swap case, (2) if original was a vowel, shift the case-swapped result by +2.

The spec says "Assume only letters" but the dataset tests include spaces. The implementation passes spaces through unchanged — this is pinned as an implementation-defined behavior.

## 1. Input domain

| Dimension | Possible values |
|-----------|-----------------|
| Input reference | `null`, non-`null` |
| Input content | empty, letters only, letters + spaces (spec-undefined), other chars |
| Letter type | lowercase vowel, uppercase vowel, lowercase consonant, uppercase consonant |
| Vowel set | a, e, i, o, u / A, E, I, O, U (y/Y are **not** vowels) |
| String length | 0, 1, short, long |

## 2. Equivalence classes

### Valid classes (spec-defined: letters only)

| ID  | Class | Example input | Expected |
|-----|-------|---------------|----------|
| V1  | Empty string | `""` | `""` |
| V2  | Single lowercase vowel | `"a"` | `"C"` (a→A+2) |
| V3  | Single uppercase vowel | `"E"` | `"g"` (E→e+2) |
| V4  | Single lowercase consonant | `"b"` | `"B"` (swap only) |
| V5  | Single uppercase consonant | `"T"` | `"t"` (swap only) |
| V6  | All five lowercase vowels | `"aeiou"` | `"CGKQW"` |
| V7  | All five uppercase vowels | `"AEIOU"` | `"cgkqw"` |
| V8  | Mixed consonants only | `"TEST"` | `"tgst"` — wait, E is a vowel. `"bTsT"` → `"BtSt"` |
| V9  | Mixed vowels and consonants, all lowercase | `"message"` | `"MGSSCGG"` (no space) |
| V10 | Mixed vowels and consonants, all uppercase | `"YES"` | `"ygs"` |
| V11 | Mixed case, vowels and consonants | `"This is a message"` | `"tHKS KS C MGSSCGG"` |
| V12 | Y / y — not vowels, only case-swapped | `"yYy"` | `"YyY"` |

### Extended / pinned classes (space pass-through, spec-undefined)

| ID  | Class | Example input | Expected |
|-----|-------|---------------|----------|
| V13 | Single space | `" "` | `" "` |
| V14 | String with leading/trailing spaces | `" a "` | `" C "` |

### Invalid / undefined-by-spec classes

| ID  | Class | Behavior | How we treat it |
|-----|-------|----------|-----------------|
| I1  | `null` input | Guard `message == null` → return `""` | Pin as `""`. |

## 3. Boundary analysis

| Boundary | Input | Expected |
|----------|-------|----------|
| String length = 0 | `""` | `""` |
| String length = 1, consonant | `"b"` | `"B"` |
| String length = 1, vowel | `"a"` | `"C"` |
| Last vowel before alphabetic edge | `"u"` → `'U'+2='W'` | `"W"` |
| `y` / `Y` (not a vowel, no shift) | `"y"` | `"Y"` |
| All 5 lowercase vowels in one string | `"aeiou"` | `"CGKQW"` |
| All 5 uppercase vowels in one string | `"AEIOU"` | `"cgkqw"` |

## 4. Coverage rationale

The manual cases exercise:

- null guard true branch,
- empty-string guard true branch,
- space pass-through branch (c == ' '),
- uppercase-consonant branch,
- lowercase-consonant branch,
- lowercase-vowel branch (all five vowels),
- uppercase-vowel branch (all five vowels),
- mixed case with spaces (full dataset examples),
- Y/y to confirm non-vowel treatment.
