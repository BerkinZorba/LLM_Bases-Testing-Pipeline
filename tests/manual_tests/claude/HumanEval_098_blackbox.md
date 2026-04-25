# HumanEval_098 — Black-box test design (Claude)

## Function under test
`int countUpper(String s)` — count uppercase vowels (A, E, I, O, U) located at
even indices (0, 2, 4, ...) of the string.

## Input domain
- `s` is a Java `String`. May be empty.
- Only uppercase A, E, I, O, U are counted; lowercase vowels and consonants are not.

## Equivalence classes

### Valid partitions (V)
| ID | Description | Representative | Expected |
|----|-------------|----------------|----------|
| V1 | Empty string | `""` | `0` |
| V2 | No uppercase vowels | `"abcdefg"`, `"BcDeFg"` | `0` |
| V3 | Uppercase vowels only at odd indices | `"xAxExI"` | `0` |
| V4 | Uppercase vowel at index 0 | `"A"`, `"Abc"` | `1` |
| V5 | Uppercase vowels at multiple even indices | `"AAAAA"` → 0,2,4 | `3` |
| V6 | Mix: vowels at even and odd | `"aBCdEf"` → E at idx 4 | `1` |
| V7 | Each vowel at index 0 | `"A"`,`"E"`,`"I"`,`"O"`,`"U"` | `1` each |
| V8 | Uppercase consonants at even — not counted | `"dBBE"` | `0` |

### Invalid / undefined-by-spec partitions (I)
| ID | Description | Representative | Observed behavior |
|----|-------------|----------------|-------------------|
| I1 | Null input | `null` | NullPointerException; not tested |

## Boundary conditions
- Empty string — loop never entered.
- Single character at index 0 — one iteration.
- `"EcEcE"` — vowels at 0, 2, 4 → count=3.
- `"xAxExI"` — vowels at 1, 3, 5 (odd) → count=0.
- Even-index step: i += 2 skips all odd positions.

## Coverage rationale
- V1: loop-never-entered branch.
- V2/V3/V8: `vowels.indexOf(c) == -1` branch (no increment).
- V4/V5/V6/V7: `vowels.indexOf(c) != -1` branch (count++).
- Each of the 5 vowels tested at index 0 to cover indexOf paths.
