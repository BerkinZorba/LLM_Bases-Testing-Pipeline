# HumanEval_099 - Manual black-box assessment (Codex)

Function under test: `Solution.closest_integer(String value)`

Prompt requirement:
> Create a function that takes a value (string) representing a number and returns the closest integer to it. If the number is equidistant from two integers, round it away from zero.

## 1. Observable input dimensions

| Dimension | Values worth partitioning |
|-----------|----------------------------|
| Sign | positive, negative, zero |
| Fractional distance | below `.5`, exactly `.5`, above `.5` |
| Representation | integer string, decimal string, leading zeros |
| Validity | parseable numeric string, null, empty, invalid string |

The output is the nearest integer. Exact half values round away from zero.

## 2. Equivalence classes

### Valid classes

| ID | Description | Example | Expected |
|----|-------------|---------|----------|
| V1 | Positive integer | `"10"` | `10` |
| V2 | Positive fraction below half | `"15.3"` | `15` |
| V3 | Positive half | `"14.5"` | `15` |
| V4 | Positive fraction above half | `"15.6"` | `16` |
| V5 | Negative fraction below half in magnitude | `"-14.49"` | `-14` |
| V6 | Negative half | `"-14.5"` | `-15` |
| V7 | Negative fraction above half in magnitude | `"-14.51"` | `-15` |
| V8 | Positive half near zero | `"0.5"` | `1` |
| V9 | Negative half near zero | `"-0.5"` | `-1` |
| V10 | Leading zeros | `"001.50"` | `2` |

### Invalid or underspecified classes

| ID | Description | Current implementation behavior |
|----|-------------|---------------------------------|
| I1 | `null` input | returns `0` |
| I2 | Empty string | returns `0` |
| I3 | Non-numeric string | throws `NumberFormatException` |

## 3. Boundary cases

| Boundary | Input | Expected |
|----------|-------|----------|
| Exact zero | `"0"` | `0` |
| Just below positive half | `"0.49"` | `0` |
| Positive half | `"0.5"` | `1` |
| Just below negative half magnitude | `"-0.49"` | `0` |
| Negative half | `"-0.5"` | `-1` |

## 4. Black-box expectations to execute

- `.5` ties must round away from zero;
- non-tie decimals must round to the nearest integer normally;
- positive and negative values should be symmetric around zero;
- null, empty, and invalid numeric strings are outside the valid numeric-string contract, so tests pin current behavior rather than treating it as a prompt defect.
