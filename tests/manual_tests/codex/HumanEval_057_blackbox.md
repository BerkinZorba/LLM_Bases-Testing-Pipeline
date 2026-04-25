# HumanEval_057 — Manual black-box assessment (Codex)

Function under test: `Solution.monotonic(List<Integer> l)`

Prompt requirement:
> Return True is list elements are monotonically increasing or decreasing.

Interpretation used for black-box testing:

- Equality is allowed within a monotone run because the dataset examples accept `[4, 1, 1, 0]` and `[9, 9, 9, 9]`.
- The function should therefore return `true` for non-decreasing and non-increasing lists, not only for strictly ordered ones.
- The prompt does not define `null`; this assessment avoids `null` because the observable contract is stated in terms of lists of integers.

## Equivalence classes

### Valid classes

- `V1`: empty list -> vacuously monotone -> expected `true`
- `V2`: singleton list -> no direction conflict possible -> expected `true`
- `V3`: strictly increasing list -> expected `true`
- `V4`: strictly decreasing list -> expected `true`
- `V5`: constant list -> expected `true`
- `V6`: non-decreasing with plateaus -> expected `true`
- `V7`: non-increasing with plateaus -> expected `true`
- `V8`: extreme integer values but still ordered -> expected `true`

### Invalid classes

- `I1`: increase followed by decrease -> expected `false`
- `I2`: decrease followed by increase -> expected `false`
- `I3`: non-decreasing prefix followed by violating tail -> expected `false`
- `I4`: plateau with a later direction reversal -> expected `false`

## Boundary conditions

- `B1`: size `0`
- `B2`: size `1`
- `B3`: size `2` with equal values
- `B4`: first violating element appears only at the last position
- `B5`: values near `Integer.MIN_VALUE` and `Integer.MAX_VALUE`

## Manual test mapping

- `V1` -> `[]`
- `V2` -> `[4]`
- `V3` -> `[1, 2, 3, 4]`
- `V4` -> `[9, 7, 5, 1]`
- `V5` -> `[2, 2, 2, 2]`
- `V6` -> `[1, 1, 2, 2, 3]`
- `V7` -> `[5, 4, 4, 3, 1]`
- `V8` -> `[Integer.MIN_VALUE, 0, Integer.MAX_VALUE]`
- `I1` -> `[1, 3, 2]`
- `I2` -> `[5, 2, 4]`
- `I3` -> `[1, 2, 2, 1]`
- `I4` -> `[3, 3, 5, 4]`
- `B3` -> `[6, 6]`
- `B4` -> `[0, 1, 2, 1]`
