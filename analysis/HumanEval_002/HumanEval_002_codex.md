# HumanEval_002 — Per-prompt analysis (Codex / GPT)

## Function
`double truncateNumber(double number)` — return the decimal (fractional) part of
a positive floating-point number.

## Generated code summary
One-liner: `return number - (long) number;`. Casts to `long` to strip the integer
part, subtracts from the original. Correct for positive doubles within `long` range.

## Base test results
- Suite: `tests/base_tests/codex/HumanEval_002_BaseTest.java`
- Result: **5/5 pass**

## Coverage (JaCoCo)
| Suite    | Tests | Instr | Branch | Line | CC  | Method |
|----------|-------|-------|--------|------|-----|--------|
| base     | 5     | 9/9   | 0/0    | 2/2  | 2/2 | 2/2   |
| improved | 10    | 9/9   | 0/0    | 2/2  | 2/2 | 2/2   |
| manual   | 9     | 9/9   | 0/0    | 2/2  | 2/2 | 2/2   |

Full coverage on all suites. No branches — single return statement.

## Improved test rationale
Smells: assertion roulette (all assertions in one test would be roulette). Separated
into individual named tests. Added range-invariant test confirming result ∈ [0, 1)
across multiple inputs.

## Manual black-box
See `tests/manual_tests/codex/HumanEval_002_blackbox.md`. V1–V8 cover exact integer,
fraction-only, and mixed inputs. Boundary: fractional part → 0 (exact integer),
fractional part → 1 (0.999), integer part = 0 (sub-one input).

## Defects
None.

## Refactor loop
Not triggered.
