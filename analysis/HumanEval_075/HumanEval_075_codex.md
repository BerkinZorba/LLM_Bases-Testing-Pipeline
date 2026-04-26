# HumanEval_075 — Per-prompt analysis (Codex / GPT)

## Function
`boolean isMultiplyPrime(int a)` — return true if `a` is the product of exactly
3 prime numbers (with multiplicity); false otherwise. Constraint: a < 100.

## Generated code summary
Trial-division factorization: outer for-loop over candidate divisors i from 2 to a.
Inner while-loop divides a by i while possible, checking primality of i inline
via `i < 2` guard, even-composite guard, and j-loop (odd divisors from 3 to √i).
Returns false immediately if a non-prime divisor is found; returns `count==3 && a==1`
at the end.

## Base test results
- Suite: `tests/base_tests/codex/HumanEval_075_BaseTest.java`
- Result: **6/6 pass**

## Coverage (JaCoCo)
| Suite    | Tests | Instr  | Branch | Line  | CC   | Method |
|----------|-------|--------|--------|-------|------|--------|
| base     | 6     | 65/73  | 17/24  | 14/15 | 7/14 | 2/2   |
| improved | 12    | 65/73  | 19/24  | 14/15 | 9/14 | 2/2   |
| manual   | 13    | 65/73  | 19/24  | 14/15 | 9/14 | 2/2   |

**Missed branches (5, all structurally unreachable):**
- `if (i < 2)` true side: i starts at 2 and only increments, so i<2 is never true.
- `if (i % 2 == 0 && i != 2)` true side: by the time i reaches an even value > 2,
  all factors of 2 have been divided out of a, so even composite i can never
  divide the remaining a. The defensive non-prime path is unreachable.
- Related short-circuit branches and inner j-loop `prime=false` branch follow
  the same structural argument.

These are defensive guards, not defects.

## Improved test rationale
Smells: assertion roulette, magic numbers. Added more true cases (18, 50, 66) and
false cases (16, 60) to improve branch coverage. 66=2×3×11 exercises the j-loop
body (j=3 where j*j=9<11). Coverage improved from 17/24 to 19/24.

## Manual black-box
See `tests/manual_tests/codex/HumanEval_075_blackbox.md`. V_T1–V_T6 (true cases)
and V_F1–V_F6 (false cases). Boundaries: smallest true value (8=2×2×2), 2-factor
and 4-factor inputs.

## Defects
None.

## Refactor loop
Not triggered.
