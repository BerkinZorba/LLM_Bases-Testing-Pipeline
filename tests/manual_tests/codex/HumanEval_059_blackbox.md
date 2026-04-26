# HumanEval_059 — Manual black-box assessment (Codex)

Function under test: `Solution.largestPrimeFactor(int n)`

Prompt requirement:
> Return the largest prime factor of n. Assume n > 1 and is not a prime.

Interpretation used for black-box testing:

- In-scope inputs are composite integers greater than `1`.
- The expected output is the numerically largest prime divisor of the input.
- Prime inputs and non-positive values are outside the prompt contract; they are documented as out-of-scope observations rather than required behavior.

## Equivalence classes

### Valid classes

- `V1`: even composites whose largest prime factor is `2` -> expected `2`
- `V2`: composites with one repeated odd prime factor -> expected that odd prime
- `V3`: composites with mixed small factors and a larger odd prime tail -> expected the tail prime
- `V4`: composites that are perfect squares of a prime -> expected the repeated prime
- `V5`: composites with several distinct prime factors -> expected the largest distinct factor

### Invalid or out-of-scope classes

- `O1`: prime input -> prompt says this will not be provided
- `O2`: `n <= 1` -> outside the stated domain

## Boundary conditions

- `B1`: smallest composite input (`4`)
- `B2`: smallest odd composite input (`9`)
- `B3`: power of two with many repeated factors
- `B4`: perfect square of an odd prime
- `B5`: composite that reduces to a leftover prime after smaller factors are stripped

## Manual test mapping

- `V1` -> `4`, `2048`
- `V2` -> `27`, `81`
- `V3` -> `15`, `255`
- `V4` -> `49`
- `V5` -> `330`, `1287`
- `B1` -> `4`
- `B2` -> `9`
- `B3` -> `2048`
- `B4` -> `49`
- `B5` -> `299`
- `O1` -> `13` (observed current implementation returns `13`)
- `O2` -> `1` (observed current implementation returns `1`)
