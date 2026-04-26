# HumanEval_098 — Black-box test design (Codex)

## Function under test
`int countUpper(String s)` — count uppercase vowels (A, E, I, O, U) located at
even indices (0, 2, 4, ...) of the string.

## Input domain
- `s` is a Java `String`. May be empty.
- Only A, E, I, O, U in uppercase are counted; lowercase and consonants are not.

## Equivalence classes

### Valid partitions (V)
| ID | Description | Representative | Expected |
|----|-------------|----------------|----------|
| V1 | Empty string | `""` | `0` |
| V2 | No uppercase at all | `"hello"` | `0` |
| V3 | Uppercase vowels only at odd indices | `"bAbE"` | `0` |
| V4 | Single uppercase vowel at index 0 | `"Abc"` | `1` |
| V5 | Multiple uppercase vowels at even indices | `"AaEaIa"` | `3` |
| V6 | Uppercase consonants at even indices | `"BcDeFg"` | `0` |
| V7 | Mix of vowel and consonant at even indices | `"AcBcE"` | `2` |
| V8 | Vowels O and U at even positions | `"OaUa"` | `2` |

### Invalid / undefined-by-spec partitions (I)
| ID | Description | Representative | Observed behavior |
|----|-------------|----------------|-------------------|
| I1 | Null input | `null` | NullPointerException (not tested) |

## Boundary conditions
- Empty string: loop guard `i < s.length()` immediately false; returns 0.
- Single character at index 0: one iteration only.
- Two-character string "xA": only index 0 visited; A is at index 1 → 0.
- `i += 2` invariant: index 1, 3, 5, ... are never visited regardless of content.

## Coverage rationale
- V1: loop-not-entered branch.
- V2/V3/V6: `||` chain fully false path (consonant or lowercase at even index).
- V4/V5/V7/V8: `||` chain true path at various vowel positions.
- To cover all five `||` short-circuit paths each vowel must appear at index 0: tested in improved suite.
