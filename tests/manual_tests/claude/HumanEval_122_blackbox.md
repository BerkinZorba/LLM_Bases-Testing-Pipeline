# HumanEval_122 — Manual black-box assessment (Claude)

## Specification recap
`addElements(List<Integer> arr, int k)` returns the `int` sum of those
values among the first `k` elements of `arr` whose decimal representation
has at most two digits. The dataset case
`addElements([1, -2, -3, 41, 57, 76, 87, 88, 99], 3) == -4` shows that
negatives qualify by absolute magnitude (so the predicate is
`|v| < 100`, equivalently `v ∈ [-99, 99]`), and that they are added
with their sign preserved. Constraints: `1 <= len(arr) <= 100`,
`1 <= k <= len(arr)`.

## Input domain
Two parameters: a `List<Integer>` and an integer `k`. The spec restricts
both, but the implementation accepts any `int` for `k` and any list
contents (including `null`-free integers). Behavior outside the spec
domain is not contractually defined; selected undefined-by-spec cases
are pinned below as observed.

## Equivalence classes

### Valid partitions (spec defines behavior)
| ID  | Class                                              | Sample                                                | Why it matters                                              |
| --- | -------------------------------------------------- | ----------------------------------------------------- | ----------------------------------------------------------- |
| V1  | k == 1, single qualifying element                  | `([1], 1)`                                            | smallest valid input; loop body runs once with a hit        |
| V2  | k == 1, single non-qualifying element              | `([123], 1)`                                          | predicate false in the only iteration                       |
| V3  | every prefix element qualifies                     | `([11, 21, 3, 90, ...], 4)` -> 125                    | cumulative sum across multiple hits                         |
| V4  | none of the prefix qualifies                       | `([111, 121, 3, 4000, ...], 2)` -> 0                  | empty effective sum                                         |
| V5  | mixed: some qualify, some do not                   | `([111, 21, 3, 4000, ...], 4)` -> 24                  | dataset example, predicate alternates                       |
| V6  | negatives qualify and contribute with sign         | `([1, -2, -3, 41, ...], 3)` -> -4                     | sign preservation; dataset                                  |
| V7  | only negatives qualify                             | `([-10, -20, -1000], 2)` -> -30                       | result is negative                                          |
| V8  | mixed signs cancelling                             | `([50, -50, 25, -25], 4)` -> 0                        | result is zero by cancellation, not by exclusion            |
| V9  | tail elements ignored                              | `([7, 9999, 9999, 9999], 1)` -> 7                     | k truncates iteration                                       |
| V10 | k == arr.size()                                    | `([11, 22, 33, -4, 500, 600], 6)` -> 62               | full scan; out-of-range tail still ignored by predicate     |
| V11 | length-100 list (constraint upper bound)           | `nCopies(100, 1), 100`                                | exercises the `1 <= len(arr) <= 100` upper bound            |

### Boundary partitions
| ID  | Boundary                              | Sample                       | Reason                                                 |
| --- | ------------------------------------- | ---------------------------- | ------------------------------------------------------ |
| B1  | predicate true at upper edge          | `([99, 1000], 1)` -> 99      | `|99| < 100` is true                                   |
| B2  | predicate false just past upper edge  | `([100], 1)` -> 0            | `|100| < 100` is false                                 |
| B3  | predicate true at lower edge          | `([-99, -1000], 1)` -> -99   | `|-99| < 100` is true                                  |
| B4  | predicate false just past lower edge  | `([-100], 1)` -> 0           | `|-100| < 100` is false                                |
| B5  | symmetric edge mix                    | `([-99, -100, 99, 100], 4)`  | -> 0; covers all four edges in one assertion           |
| B6  | k at the loop boundary (k == len(arr))| `([10, 20, 1000], 3)` -> 30  | last index inspected is `arr.size() - 1`               |
| B7  | k=1 (smallest k allowed)              | `([1], 1)` -> 1              | loop runs exactly once                                 |

### Invalid / undefined-by-spec partitions
| ID  | Class                                | Sample                              | Observed behavior                                                 |
| --- | ------------------------------------ | ----------------------------------- | ----------------------------------------------------------------- |
| U1  | k == 0 (spec says k >= 1)            | `([1, 2, 3], 0)`                    | loop body never executes -> returns `0`                           |
| U2  | element value Integer.MAX_VALUE      | `([Integer.MAX_VALUE], 1)`          | `|MAX_VALUE| > 99` (treated as `MAX_VALUE` by `Math.abs`) -> `0`  |
| U3  | element value Integer.MIN_VALUE      | `([Integer.MIN_VALUE], 1)`          | `Math.abs(MIN_VALUE) == MIN_VALUE` (negative); `< 100` is true so |
|     |                                      |                                     | `MIN_VALUE` is summed -> `Integer.MIN_VALUE`. Documented quirk.   |

These are pinned to make any future regression visible. They are not
part of the spec contract.

## Boundary table
| Variable        | Just below       | At edge          | Just above       |
| --------------- | ---------------- | ---------------- | ---------------- |
| value (positive)| 99 (kept)        | 100 (dropped)    | 101 (dropped)    |
| value (negative)| -99 (kept)       | -100 (dropped)   | -101 (dropped)   |
| k vs arr.size() | k < size         | k == size        | (out of spec)    |
| k (lower)       | (out of spec, 0) | k == 1           | k > 1            |

## Coverage of partitions in `HumanEval_122_ManualTest.java`
- V1..V11 → one explicit assertion each (the four dataset rows are also
  covered as `dataset_*` cases for direct traceability).
- B1..B7 → covered by `boundary_*` tests.
- U1..U3 → pinned in `pinned_undefined_*` tests with observed-behavior
  comments documenting the quirks (notably `Math.abs(Integer.MIN_VALUE)`).
