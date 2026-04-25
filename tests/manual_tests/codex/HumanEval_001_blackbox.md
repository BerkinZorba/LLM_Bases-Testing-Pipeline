# HumanEval_001 — Black-box test design (Codex / GPT)

## Function under test
`List<String> separateParenGroups(String paren_string)` — given a string of
balanced, non-nested top-level paren groups (spaces ignored), return each group
as a separate string in a list.

## Input domain
- `paren_string`: a Java `String`.
- Spec guarantees: all groups are balanced; groups are not nested within each other.
- Spaces may appear anywhere and must be ignored.
- Empty string is a valid input.

## Equivalence classes

### Valid partitions (V)
| ID | Description | Representative input | Expected output |
|----|-------------|----------------------|-----------------|
| V1 | Empty string | `""` | `[]` |
| V2 | Whitespace-only | `"   "` | `[]` |
| V3 | One flat group | `"()"` | `["()"]` |
| V4 | One group with internal nesting | `"(())"` | `["(())"]` |
| V5 | Two flat groups, no space | `"()()"` | `["()", "()"]` |
| V6 | Two flat groups, space-separated | `"() ()"` | `["()", "()"]` |
| V7 | Three mixed-depth groups (docstring) | `"( ) (( )) (( )( ))"` | `["()", "(())", "(()())"]` |
| V8 | Four groups with deep nesting | `"(()()) ((())) () ((())(()))"` | `["(()())", "((()))", "()", "((())(()))"]` |

### Invalid / undefined-by-spec (I)
| ID | Description | Note |
|----|-------------|------|
| I1 | Unbalanced input `"(()"` | Undefined by spec; not tested |
| I2 | `null` input | NullPointerException expected; not tested |

## Boundary conditions
| Condition | Input | Why it's a boundary |
|-----------|-------|---------------------|
| Empty loop | `""` | for-loop body never executed |
| Single-char-pair | `"()"` | depth counter goes 0→1→0 in two steps |
| No-space input | `"()()"` | space-skip branch never taken |
| All-space input | `"   "` | space-skip branch always taken, no group emitted |
| Deep nesting | `"(((())))"` | depth counter reaches 4; flush happens only at close of outermost |
| Adjacent groups after flush | `"()()"` | current is reset to empty immediately before next group starts |

## Coverage rationale
- V1: loop never entered (outer for-loop false branch).
- V2: space-skip true branch exercised continuously; flush condition never true.
- V3: minimal path through all branches once.
- V4 / V7: balance > 0 mid-group — flush condition false branch.
- V5 / V6: group count > 1, exercises StringBuilder reset.
- V8: four resets — most thorough group-flush sequence.
