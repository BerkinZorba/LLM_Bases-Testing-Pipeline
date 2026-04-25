# HumanEval_100 manual black-box assessment (Codex)

## Prompt contract

`makeAPile(int n)` should return a list with `n` levels. The first element must be `n`, and each following element must advance to the next integer with the same parity, so the observable sequence increases by `2` at every step.

## Equivalence classes

### Valid classes

- V1: smallest positive odd input
  - Example: `1` -> `[1]`
- V2: smallest positive even input
  - Example: `2` -> `[2, 4]`
- V3: general odd input with multiple levels
  - Example: `5` -> `[5, 7, 9, 11, 13]`
- V4: general even input with multiple levels
  - Example: `6` -> `[6, 8, 10, 12, 14, 16]`
- V5: larger odd input to confirm length and progression scale together
  - Example: `9` -> `[9, 11, 13, 15, 17, 19, 21, 23, 25]`
- V6: larger even input to confirm parity is preserved across many levels
  - Example: `12` -> `[12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34]`

### Invalid / out-of-scope classes

- I1: zero input
  - Outside the stated "positive integer" contract.
  - Current implementation behavior: returns `[]`.
- I2: negative input
  - Outside the stated "positive integer" contract.
  - Current implementation behavior: returns `[]`.

## Boundary conditions

- B1: minimum in-scope value
  - `1` -> `[1]`
- B2: first even in-scope value
  - `2` -> `[2, 4]`
- B3: odd-to-odd progression boundary
  - `3` -> `[3, 5, 7]`
- B4: even-to-even progression boundary
  - `4` -> `[4, 6, 8, 10]`
- B5: out-of-scope boundary at zero
  - `0` -> `[]`

## Expected black-box observations

- The list length should match `n` for every positive input.
- The first level should always equal `n`.
- Every adjacent pair should differ by exactly `2`.
- Odd inputs should produce only odd levels; even inputs should produce only even levels.
- For out-of-scope non-positive inputs, the current implementation returns an empty list because the construction loop never executes.
