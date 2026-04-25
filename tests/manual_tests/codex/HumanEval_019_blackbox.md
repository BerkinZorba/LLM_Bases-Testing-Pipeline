# HumanEval_019 - Manual black-box assessment (Codex)

Function under test: `Solution.sortNumbers(String numbers)`

Prompt requirement:
> Input is a space-delimited string of numberals from 'zero' to 'nine'. Valid choices are 'zero', 'one', 'two', 'three', 'four', 'five', 'six', 'seven', 'eight' and 'nine'. Return the string with numbers sorted from smallest to largest.

This document treats the prompt as a black-box specification and identifies input partitions that affect the returned ordering.

## 1. Observable input dimensions

| Dimension | Values worth partitioning |
|-----------|----------------------------|
| Token count | empty string, one token, multiple tokens |
| Initial order | already sorted, reverse sorted, arbitrary order |
| Coverage of vocabulary | subset of words, all ten words |
| Multiplicity | unique values, duplicate values |
| Delimiter shape | single spaces, extra spaces, leading/trailing spaces |
| Reference validity | non-null vs null |
| Token validity | valid numeral words vs invalid words |

The output is the same words sorted by numeric value and joined with single spaces.

## 2. Equivalence classes

### Valid classes

| ID | Description | Example | Expected |
|----|-------------|---------|----------|
| V1 | Empty input | `""` | `""` |
| V2 | One valid token | `"three"` | `"three"` |
| V3 | Already sorted subset | `"three five nine"` | `"three five nine"` |
| V4 | Reverse sorted full vocabulary | `"nine eight seven six five four three two one zero"` | `"zero one two three four five six seven eight nine"` |
| V5 | Arbitrary unsorted subset | `"five zero four seven nine eight"` | `"zero four five seven eight nine"` |
| V6 | Duplicate values | `"two two one"` | `"one two two"` |
| V7 | Repeated zeros | `"zero zero zero"` | `"zero zero zero"` |
| V8 | Alternating duplicate values | `"one zero one zero"` | `"zero zero one one"` |

### Invalid or underspecified classes

| ID | Description | Current implementation behavior |
|----|-------------|---------------------------------|
| I1 | `null` input | returns `""` |
| I2 | Invalid token such as `ten` | throws `NullPointerException` |
| I3 | Extra spaces creating an empty token | throws `NullPointerException` |
| I4 | Leading or trailing spaces | throws `NullPointerException` |
| I5 | Single invalid or different-case token such as `One` | returns the token unchanged because no sort comparison is needed |
| I6 | Different-case token requiring comparison such as `One zero` | throws `NullPointerException` |

## 3. Boundary cases

| Boundary | Input | Expected |
|----------|-------|----------|
| Zero tokens by empty string | `""` | `""` |
| One token | `"zero"` | `"zero"` |
| Two tokens needing swap | `"one zero"` | `"zero one"` |
| Lowest and highest value | `"nine zero"` | `"zero nine"` |
| Full vocabulary | `"nine eight seven six five four three two one zero"` | `"zero one two three four five six seven eight nine"` |

## 4. Black-box expectations to execute

- valid numeral words must be ordered by numeric value, not lexicographic spelling;
- duplicate words are preserved;
- output uses single spaces between sorted tokens;
- invalid tokens, extra spaces, and case differences are outside the stated valid-choice contract, so tests pin current behavior rather than treating it as a prompt defect.
