# HumanEval_106 - Manual black-box assessment (Codex)

Function under test: `Solution.f(int n)`

Prompt requirement:
> Implement the function f that takes n as a parameter, and returns a list of size n, such that the value of the element at index i is the factorial of i if i is even or the sum of numbers from 1 to i otherwise. i starts from 1.

The observable list position for `i` is zero-based Java index `i - 1`.

## 1. Observable input dimensions

| Dimension | Values worth partitioning |
|-----------|----------------------------|
| Size | negative, zero, one, several elements |
| Parity of position `i` | odd triangular-number positions, even factorial positions |
| Boundary | first element, first even element, larger even factorial, larger odd triangular number |
| Numeric growth | small factorials and triangular sums within `int` range |

The output is a list of length `n` for positive `n`; odd `i` entries are triangular sums and even `i` entries are factorials.

## 2. Equivalence classes

### Valid classes

| ID | Description | Example | Expected |
|----|-------------|---------|----------|
| V1 | `n = 1` first odd triangular value | `1` | `[1]` |
| V2 | `n = 2` first even factorial value | `2` | `[1, 2]` |
| V3 | Mixed first three values | `3` | `[1, 2, 6]` |
| V4 | Prompt example | `5` | `[1, 2, 6, 24, 15]` |
| V5 | Includes factorial of six and triangular seven | `7` | `[1, 2, 6, 24, 15, 720, 28]` |
| V6 | Includes factorial of eight | `8` | `[1, 2, 6, 24, 15, 720, 28, 40320]` |

### Invalid or underspecified classes

| ID | Description | Current implementation behavior |
|----|-------------|---------------------------------|
| I1 | `n = 0` | returns empty list |
| I2 | negative `n` | returns empty list |

## 3. Boundary cases

| Boundary | Input | Expected |
|----------|-------|----------|
| Negative | `-1` | `[]` |
| Zero | `0` | `[]` |
| First element | `1` | `[1]` |
| First even position | `2` | `[1, 2]` |
| First larger odd triangular value | `5` | `[1, 2, 6, 24, 15]` |

## 4. Black-box expectations to execute

- the result length should equal `n` for positive `n`;
- odd `i` values use `1 + ... + i`;
- even `i` values use `1 * ... * i`;
- zero and negative `n` are outside the prompt's examples, so tests pin current behavior rather than treating it as a prompt defect.
