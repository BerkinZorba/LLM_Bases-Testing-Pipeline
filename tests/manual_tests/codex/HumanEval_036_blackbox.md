# HumanEval_036 — Manual black-box assessment (Codex)

Function under test: `Solution.fizzBuzz(int n)`

Prompt requirement:
> Return the number of times the digit 7 appears in integers less than n which are divisible by 11 or 13.

This document treats the prompt as a black-box counting specification. The observable output depends on the upper bound, divisibility filter, and how many `'7'` digits appear in each qualifying integer below `n`.

## 1. Observable input dimensions

| Dimension | Values worth partitioning |
|-----------|----------------------------|
| Upper bound sign/range | negative, zero, one, small positive, large positive |
| Inclusion boundary | `n` just before/at/after a qualifying value |
| Divisibility source | divisible by 11 only, 13 only, both, neither |
| Digit contribution | no `7`, one `7`, multiple `7`s |
| Scale | small hand-checkable ranges vs large aggregate ranges |

The prompt only specifies positive counting behavior for integers less than `n`. Negative inputs are therefore treated as underspecified and documented rather than used to drive a code change.

## 2. Valid equivalence classes

| ID | Class | Representative `n` | Expected |
|----|-------|--------------------|----------|
| V1 | No positive integers below bound | `0` | `0` |
| V2 | Small positive bound before first eligible number | `11` | `0` |
| V3 | Bound includes eligible numbers but none with digit `7` | `14` | `0` |
| V4 | Boundary just before `77` | `77` | `0` |
| V5 | Boundary including `77` only | `78` | `2` |
| V6 | Boundary including `78` after `77` | `79` | `3` |
| V7 | Later qualifying number with one `7` in tens/ones mix | `118` | `4` |
| V8 | Larger range with several contributors | `200` | `6` |
| V9 | Large aggregate dataset case | `1000` | `47` |

## 3. Invalid or underspecified classes

| ID | Class | Representative `n` | Observed behavior |
|----|-------|--------------------|-------------------|
| I1 | Negative upper bound | `-5` | returns `0` |
| I2 | Extreme negative upper bound | `Integer.MIN_VALUE` | returns `0` |

These are outside the prompt’s stated positive-integer framing, so the executable suite records current behavior instead of treating it as a defect.

## 4. Boundary conditions

| Boundary | Why it matters | Expected |
|----------|----------------|----------|
| `n = 77` vs `78` | crossing the first double-`7` contributor | delta `+2` |
| `n = 78` vs `79` | crossing `78`, which contributes one more `7` | delta `+1` |
| `n = 176` vs `177` | crossing a later qualifying number with one trailing `7` | delta `+1` |
| `n = 143` vs `144` | 143 is divisible by both 11 and 13, but contributes zero `7`s and must not be double-counted | delta `0` |

## 5. Executable test mapping

- Valid classes V1-V9 are covered in `HumanEval_036_ManualTest.validEquivalenceClasses`.
- Underspecified classes I1-I2 are covered in `negativeBoundsReturnZero`.
- The listed boundaries are covered by dedicated delta assertions in the same test suite.
