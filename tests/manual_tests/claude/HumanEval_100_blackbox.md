# HumanEval_100 — Manual black-box assessment (Claude)

## Specification recap
`makeAPile(int n)` returns a list of `n` integers, where the first level has `n`
stones and each subsequent level adds 2 (the "next odd/even number" rule
collapses to "+2 per level" because parity is preserved).

Closed form: `pile[i] = n + 2*i` for `i ∈ [0, n)`.
Postconditions:
- `size(pile) == n`
- `pile[0] == n`
- `pile[n-1] == 3n - 2`
- consecutive elements differ by exactly 2
- all elements share `n`'s parity

## Input domain
The contract is "positive integer `n`". The implementation accepts any `int`,
but the spec only defines behavior for `n >= 1`.

## Equivalence classes

### Valid partitions (spec defines behavior)
| ID  | Class                              | Sample n | Why it matters                                              |
| --- | ---------------------------------- | -------- | ----------------------------------------------------------- |
| V1  | smallest valid n                   | 1        | loop body must execute exactly once; off-by-one canary       |
| V2  | smallest even n                    | 2        | smallest even-parity start                                   |
| V3  | small odd n (dataset)              | 3        | dataset case; first non-trivial odd start                    |
| V4  | small even n (dataset)             | 4        | dataset case                                                 |
| V5  | small odd n (dataset)              | 5        | dataset case                                                 |
| V6  | small even n (dataset)             | 6        | dataset case                                                 |
| V7  | small even n (dataset)             | 8        | dataset case                                                 |
| V8  | mid odd n                          | 9        | non-dataset odd; closed-form spot check                      |
| V9  | mid even n                         | 10       | non-dataset even                                             |
| V10 | larger n (size correctness)        | 50       | size invariant under non-trivial loop count                  |
| V11 | larger n (last-element identity)   | 100      | branch coverage: many iterations; checks `3n-2` last element |

### Boundary partitions
| ID  | Boundary                 | Sample n | Reason                                                       |
| --- | ------------------------ | -------- | ------------------------------------------------------------ |
| B1  | n = 1 (lower edge)       | 1        | loop guard `i < n` flips after one iteration                 |
| B2  | n = 2 (one above edge)   | 2        | first n that exercises both first-and-last as distinct cells |
| B3  | n = large                | 100      | sweeps many loop iterations                                  |

### Invalid / undefined-by-spec partitions
| ID  | Class                  | Sample n | Observed behavior of implementation                                            |
| --- | ---------------------- | -------- | ------------------------------------------------------------------------------ |
| U1  | n = 0                  | 0        | returns empty list (loop never enters); not a spec contract — pinned, not assumed |
| U2  | n < 0                  | -3       | returns empty list (loop never enters); pinned as observed behavior              |

The U1/U2 cases are documented to make any future regression visible.
They are not part of the spec contract.

## Boundary table
| Variable | Just below | At edge | Just above |
| -------- | ---------- | ------- | ---------- |
| n        | 0 (U1)     | 1 (B1)  | 2 (B2)     |

## Coverage of partitions in `HumanEval_100_ManualTest.java`
- V1, V2, V3, V4, V5, V6, V7, V8, V9, V10, V11 → one explicit assertion each.
- B1, B2, B3 → covered by `boundary_*` tests.
- U1, U2 → pinned in `pinned_undefined_*` tests with observed-behavior comments.
- Postcondition invariants (size == n, first == n, last == 3n-2, step == 2,
  parity preserved) are exercised across multiple `n` values.
