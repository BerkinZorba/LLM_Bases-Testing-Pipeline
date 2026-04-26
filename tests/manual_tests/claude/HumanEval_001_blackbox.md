# HumanEval_001 — Black-box test design (Claude)

## Function under test
`List<String> separateParenGroups(String paren_string)` — split a balanced,
non-nested paren string into its top-level groups; ignore spaces.

## Input domain
- `paren_string` is a Java `String`. Spec guarantees groups are balanced and
  not nested within each other. Spaces are to be ignored.
- No constraint on string length; empty string is valid.

## Equivalence classes

### Valid partitions (V)
| ID | Description | Representative input | Expected |
|----|-------------|----------------------|----------|
| V1 | Empty string | `""` | `[]` |
| V2 | Only spaces | `"   "` | `[]` |
| V3 | Single flat group | `"()"` | `["()"]` |
| V4 | Single group with internal nesting | `"(())"` | `["(())"]` |
| V5 | Multiple flat groups | `"() ()"` | `["()", "()"]` |
| V6 | Multiple groups with varying depth | `"( ) (( )) (( )( ))"` | `["()", "(())", "(()())"]` |
| V7 | Groups with deep nesting | `"((())) (()())"` | `["((()))", "(()())"]` |
| V8 | Four groups (docstring extended example) | `"(()()) ((())) () ((())(()))"` | `["(()())", "((()))", "()", "((())(()))"]` |

### Invalid / undefined-by-spec partitions (I)
| ID | Description | Representative input | Observed behavior |
|----|-------------|----------------------|--------------------|
| I1 | Unbalanced parens | `"(()"` | undefined by spec; not tested |
| I2 | Null input | `null` | NullPointerException; not tested |

## Boundary conditions
- Empty string — loop never enters.
- Single `"()"` — minimal valid group.
- Spaces between, before, and after groups — all stripped.
- Deeply nested single group `"(((())))"` — depth counter climbs to 4 and back.
- Immediately adjacent groups `"()()"` vs space-separated `"() ()"` — same result.

## Coverage rationale
- V1/V2: loop-never-entered branch.
- V3/V5: depth reaches 0 after single `)` — group emitted immediately.
- V4/V7: depth > 1 mid-group, group emitted only when fully closed.
- V6/V8: multiple groups, exercises reset of `current` StringBuilder.
- Space boundary: V2 exercises space-skip with no groups; V6 has embedded spaces.
