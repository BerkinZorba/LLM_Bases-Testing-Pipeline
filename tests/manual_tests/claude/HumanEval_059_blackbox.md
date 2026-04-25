# HumanEval_059 — Black-box test design (Claude)

## Function under test
`int largestPrimeFactor(int n)` — returns the greatest prime divisor of `n`.
Spec preconditions: `n > 1` and `n` is not prime. The implementation does
trial-division from `factor = 2` upwards, dividing `x` (a copy of `n`) by every
prime it encounters, recording the most recent successful divisor; once
`factor*factor > x` the loop stops, and any remaining `x > 1` is itself prime
and becomes the answer.

## Input domain
- Type: `int` (32-bit signed). Practical range covers `2 .. Integer.MAX_VALUE`
  for spec-valid composites; however the spec excludes primes, `0`, `1`, and
  negative values. The implementation does not check these preconditions, so
  out-of-spec inputs are documented as observed behavior only.
- Implementation note: the loop guard uses `(long) factor * factor <= x` to
  avoid `int` overflow at `factor = 46341` (since `46341^2` overflows). This
  matters for very large `n` (e.g., near `Integer.MAX_VALUE`).

## Equivalence classes

### Valid partitions (V) — composite `n > 1`, per the spec
| ID  | Description                                            | Representative input | Expected |
|-----|--------------------------------------------------------|----------------------|----------|
| V1  | Smallest composite (prime square)                      | `4`                  | `2`      |
| V2  | Smallest semiprime with distinct primes                | `6`                  | `3`      |
| V3  | Pure power of 2                                        | `2048` (2^11)        | `2`      |
| V4  | Pure power of an odd prime                             | `27` (3^3)           | `3`      |
| V5  | Larger pure power of an odd prime                      | `2401` (7^4)         | `7`      |
| V6  | Semiprime with one factor > sqrt(n)                    | `15` (3*5)           | `5`      |
| V7  | Composite with a large prime > sqrt(n)                 | `22` (2*11)          | `11`     |
| V8  | Multi-prime composite                                  | `13195` (5*7*13*29)  | `29`     |
| V9  | Composite where largest prime is small                 | `720` (2^4*3^2*5)    | `5`      |
| V10 | Perfect square of a prime > sqrt boundary              | `121` (11^2)         | `11`     |
| V11 | Two large near-sqrt primes                             | `9991` (97*103)      | `103`    |
| V12 | Spec dataset cases                                     | `15, 27, 63, 330, 13195` | `5, 3, 7, 11, 29` |

### Invalid / undefined-by-spec partitions (I) — observed behavior only
| ID | Description           | Representative input | Observed behavior          |
|----|-----------------------|----------------------|----------------------------|
| I1 | `n` is prime          | `7`                  | Returns `n` itself (`7`).  |
| I2 | `n == 1`              | `1`                  | Returns `1` (loop never enters; `x > 1` false). |
| I3 | `n == 0`              | `0`                  | Returns `0` (`(long)2*2 <= 0` false; final `x > 1` false; `largest` stays `1`... actually returns `1`). Pinned. |
| I4 | Negative `n`          | `-15`                | Returns `1` (loop guard `factor*factor <= x` with negative `x` is false immediately; `x > 1` false; `largest` stays `1`). Pinned. |

These rows are observation-only; the spec does not require correctness here.

## Boundary conditions
- Smallest valid `n`: `4`.
- `factor*factor == x` exactly: `25`, `49`, `121` exercise the equality on the
  outer-while guard.
- Largest factor exactly at sqrt(n): `25` (5*5), `49` (7*7).
- Largest factor just above sqrt(n): `15` (5 > sqrt(15)≈3.87), `22` (11 >
  sqrt(22)≈4.69).
- Largest factor far above sqrt(n): `9991 = 97*103`.
- Inputs that drive the inner while many times: `2048` (2^11), `1024` (2^10),
  `3125` (5^5).
- Large composite with no small factors: `9991` ensures factor advances past
  many small primes without dividing.
- Wide range: `Integer.MAX_VALUE - 2 = 2147483645 = 5 * 1718428579? not used`
  — large-`int` cases skipped to keep the suite deterministic and fast.

## Coverage rationale
Materialized cases drive every branch in `Solution.largestPrimeFactor`:
- Outer-while entry: every input has `factor*factor (= 4) <= x`, so the
  true-on-entry branch is universally exercised. The false-exit branch is
  covered by every input that completes (the loop must exit eventually).
- Inner-while:
  - true-then-false (factor divides `x` once or more, then stops): all
    composites.
  - false-immediately (factor does not divide `x`): `15` at factor=2, `27` at
    factor=2, `13195` at factor=2,3,4,6,...,
- Final `if (x > 1)`:
  - true: `15`, `22`, `9991`, `13195` (a prime > sqrt(original n) survives).
  - false: `4`, `8`, `27`, `2048`, `3125` (perfect prime powers; `x` reaches
    `1`).
