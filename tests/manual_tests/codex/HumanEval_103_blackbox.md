# HumanEval_103 manual black-box assessment (Codex)

## Prompt contract

`roundedAvg(int n, int m)` returns `-1` when `n > m`. Otherwise it computes the inclusive average of the integers from `n` through `m`, rounds that average to the nearest integer, and returns the rounded value encoded as a binary string.

## Equivalence classes

### Valid classes

- V1: ascending range whose average is already an integer
  - Example: `(10, 20)` -> `"1111"`
- V2: ascending range whose average ends in `.5` and must round upward
  - Example: `(5, 6)` -> `"110"`
- V3: single-value range
  - Example: `(5, 5)` -> `"101"`
- V4: larger ascending range with multi-bit binary output
  - Example: `(560, 851)` -> `"1011000010"`

### Invalid / out-of-scope classes

- I1: descending range
  - Example: `(7, 5)` -> `-1`
- I2: non-positive inputs
  - The prompt says inputs are positive integers, so zero/negative cases are out of scope and are not used to define correctness.

## Boundary conditions

- B1: smallest in-scope single-value range
  - `(1, 1)` -> `"1"`
- B2: smallest increasing range
  - `(1, 2)` -> `"10"`
- B3: first `.5` rounding boundary
  - `(1, 2)` averages to `1.5`, so the rounded result is `2`
- B4: equal endpoints at a non-trivial value
  - `(9, 9)` -> `"1001"`
- B5: very large positive endpoints near `Integer.MAX_VALUE`
  - `(Integer.MAX_VALUE - 2, Integer.MAX_VALUE)` should still return the binary form of `Integer.MAX_VALUE - 1`

## Expected black-box observations

- The return type is polymorphic by contract: `String` for valid ascending/equal ranges, `Integer` `-1` for descending ranges.
- Only the endpoints matter for the inclusive average because the mean of an arithmetic progression is `(n + m) / 2`.
- Positive `.5` averages should round upward.
