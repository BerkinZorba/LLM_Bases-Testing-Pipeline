# HumanEval_075 — Per-prompt analysis (Claude)

## Function
`boolean isMultiplyPrime(int a)` — true iff `a` (< 100) has exactly 3 prime factors
counting multiplicity.

## Generated code summary
Trial division from p=2 upward; inner while divides out each factor; increments count;
returns false early if count > 3; returns `count == 3` at end.

## Base test results
- Suite: `tests/base_tests/claude/HumanEval_075_BaseTest.java`
- Result: **6/6 pass**
- Note: initial harness had wrong expectations for 12/18/50 (they ARE products of 3
  primes); fixed before committing. Documented in adjustment_log.md.

## Coverage (JaCoCo)
| Suite    | Tests | Instr  | Branch | Line | CC  | Method |
|----------|-------|--------|--------|------|-----|--------|
| base     | 6     | 31/33  | 7/8    | 8/8  | 5/6 | 2/2   |
| improved | 16    | 33/33  | 8/8    | 8/8  | 6/6 | 2/2   |
| manual   | 15    | 33/33  | 8/8    | 8/8  | 6/6 | 2/2   |

Base test missed the `count > 3` early-return branch (no 4-factor number tested).
Improved and manual suites add 16 (2⁴) and 90 (2·3²·5), covering all branches.

## Improved test rationale
Smells: assertion roulette, magic numbers. Branch targets: count<3 (false), count==3
(true for distinct and repeated primes), count>3 early return.

## Manual black-box
See `tests/manual_tests/claude/HumanEval_075_blackbox.md`. V1–V6 cover 1-factor,
2-factor, 3-distinct, 3-repeated, 4+factor, near-100 boundary (98=2·7·7).

## Defects
None against the spec.

## Refactor loop
Not triggered.
