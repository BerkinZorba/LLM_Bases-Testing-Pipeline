# HumanEval_097 manual black-box assessment (Codex)

## Prompt contract

`multiply(int a, int b)` returns the product of the unit digits of two integers. The prompt examples show that a negative sign does not make the result negative for `multiply(14, -15) == 20`, so the observable contract is the product of the magnitudes of the ones digits.

## Equivalence classes

### Valid classes

- V1: both operands end in non-zero positive digits
  - Example: `(148, 412)` -> `16`
- V2: one operand ends in zero
  - Example: `(2020, 1851)` -> `0`
- V3: second operand negative, non-zero unit digit
  - Example: `(14, -15)` -> `20`
- V4: first operand negative, non-zero unit digit
  - Example: `(-23, 17)` -> `21`
- V5: both operands negative
  - Example: `(-18, -43)` -> `24`
- V6: one-digit operands
  - Example: `(5, -7)` -> `35`
- V7: large-magnitude operands where only the final decimal digit matters
  - Example: `(1006, 2006)` -> `36`

### Invalid / out-of-scope classes

- I1: non-integer input
  - Not representable at this Java method signature; excluded from executable tests.
- I2: missing operand / null input
  - Not representable for primitive `int`; excluded from executable tests.

## Boundary conditions

- B1: zero as an operand
  - `(0, 1)` -> `0`
- B2: both operands zero
  - `(0, 0)` -> `0`
- B3: maximum positive `int`
  - `Integer.MAX_VALUE` has unit digit `7`, so `(Integer.MAX_VALUE, 12)` -> `14`
- B4: minimum negative `int`
  - `Integer.MIN_VALUE % 10 == -8`, so using unit-digit magnitude gives `(Integer.MIN_VALUE, 19)` -> `72`
- B5: unit digit equal to 9 on both sides
  - `(999, 999)` -> `81`

## Expected black-box observations

- Tens and higher-order digits must not affect the result.
- The sign of each operand affects only the sign of the ones digit representation, not the magnitude used in the product.
- Zero in either ones place forces a zero result.
