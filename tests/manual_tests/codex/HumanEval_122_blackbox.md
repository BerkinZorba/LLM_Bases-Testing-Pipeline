# HumanEval_122 manual black-box assessment (Codex)

## Prompt contract

`addElements(List<Integer> arr, int k)` should inspect only the first `k` elements of `arr` and return the sum of those elements whose decimal representation uses at most two digits. The dataset behavior shows digit counting must be based on absolute value, because negative one- and two-digit numbers are included while their sign is ignored for digit length.

## Equivalence classes

### Valid classes

- V1: prefix where every inspected element has at most two digits
  - Example: `[11, 21, 3, 90]`, `k = 4` -> `125`
- V2: mixed prefix containing eligible and ineligible magnitudes
  - Example: `[111, 21, 3, 4000]`, `k = 4` -> `24`
- V3: prefix with eligible negative single-digit values
  - Example: `[1, -2, -3]`, `k = 3` -> `-4`
- V4: prefix with eligible negative two-digit values
  - Example: `[-10, -20, 300]`, `k = 3` -> `-30`
- V5: full-list inspection when `k == arr.size()`
  - Example: `[12, 123, -4, -56, 700, 99, -100]`, `k = 7` -> `51`

### Invalid / out-of-scope classes

- I1: `k == 0`
  - Outside the stated contract `1 <= k`.
- I2: `k > arr.size()`
  - Outside the stated contract and would throw `IndexOutOfBoundsException` in the current implementation.
- I3: `arr == null`
  - Outside the stated contract and would throw `NullPointerException` in the current implementation.

## Boundary conditions

- B1: smallest allowed list and prefix
  - `[1]`, `k = 1` -> `1`
- B2: first excluded three-digit positive value
  - `[99, 100]`, `k = 2` -> `99`
- B3: first excluded three-digit negative magnitude
  - `[-99, -100]`, `k = 2` -> `-99`
- B4: prefix boundary excludes later eligible values
  - `[1000, 2000, 7, 8, 9]`, `k = 2` -> `0`
- B5: prefix equal to the entire list
  - all elements up to the end are considered exactly once

## Expected black-box observations

- Only positions `0` through `k - 1` affect the result.
- Eligible values are those whose absolute decimal magnitude is one or two digits long.
- Three-digit and larger magnitudes are excluded regardless of sign.
- The method preserves the sign of included elements when summing.
