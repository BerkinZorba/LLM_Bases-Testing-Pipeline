# HumanEval_016 — Manual black-box assessment (Codex)

Function under test: `Solution.countDistinctCharacters(String string)`

Prompt requirement:
> Given a string, find out how many distinct characters (regardless of case) does it consist of.

This document treats the prompt as a black-box specification and identifies input partitions that matter for observable behavior.

## 1. Observable input dimensions

| Dimension | Values worth partitioning |
|-----------|----------------------------|
| Presence | empty vs non-empty |
| Character type | letters, digits, whitespace, punctuation, mixed |
| Repetition | all unique, all same, partial duplicates |
| Case relation | same case only, mixed case collapsing to same letter |
| Encoding | ASCII, non-ASCII BMP letters, supplementary Unicode |
| Reference validity | non-null vs null |

The output is the integer size of the character set after case-insensitive normalization as implemented by the solution.

## 2. Equivalence classes

### Valid classes

| ID | Description | Example | Expected |
|----|-------------|---------|----------|
| V1 | Empty string | `""` | `0` |
| V2 | One character | `"Q"` | `1` |
| V3 | All distinct lowercase letters | `"abcde"` | `5` |
| V4 | Same letter repeated with mixed case | `"xXxXX"` | `1` |
| V5 | Mixed duplicates with spaces | `"Aa aA"` | `2` |
| V6 | Digits with repetition | `"001122"` | `3` |
| V7 | Punctuation only | `"!?.,;"` | `5` |
| V8 | Mixed letters and digits | `"A1a1B2"` | `4` |
| V9 | Distinct whitespace forms | `" \n\t"` | `3` |
| V10 | Non-ASCII case pairs | `"ÇçĞğ"` | `2` |

### Invalid or underspecified classes

| ID | Description | Current implementation behavior |
|----|-------------|---------------------------------|
| I1 | `null` input | throws `NullPointerException` |
| I2 | Supplementary code point | counted as UTF-16 code units |
| I3 | Locale-sensitive case family (`İIıi`) | current lowercasing yields `3` |

## 3. Boundary cases

| Boundary | Input | Expected |
|----------|-------|----------|
| Length 0 | `""` | `0` |
| Length 1 | `"a"` | `1` |
| First duplicate appears | `"abca"` | `3` |
| Same-set saturation by case duplicate | `"abcABCd"` | `4` |
| Digit saturation | `"0123456789"` | `10` |

## 4. Black-box expectations to execute

- empty and one-character inputs should establish the lower bound;
- mixed-case duplicates must collapse into one logical letter;
- spaces, tabs, and newlines should remain distinct characters from letters and from one another;
- digits and punctuation should count normally because the prompt says "characters", not "letters";
- `null`, emoji, and Turkish-I cases are outside the prompt’s precise wording, so tests pin current behavior rather than treat it as a spec violation.
