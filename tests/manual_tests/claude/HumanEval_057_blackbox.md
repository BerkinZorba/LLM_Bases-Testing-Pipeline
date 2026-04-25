# HumanEval_057 — Black-box test design (Claude)

## Function under test
`boolean monotonic(List<Integer> l)` — returns `true` iff the elements of `l`
are monotonically non-decreasing OR monotonically non-increasing. The dataset
examples and the trivial-true case `[9,9,9,9]` confirm that "monotonic" here
includes the non-strict case (equal adjacent elements are allowed).

## Input domain
- Element type: `Integer` (32-bit signed). Practical range covers
  `Integer.MIN_VALUE` and `Integer.MAX_VALUE`.
- Length: `0 .. n` where `n` is bounded by `int`. The spec gives no lower-size
  precondition. The implementation reads `l.size()` and indexes `l.get(i)` and
  `l.get(i-1)` only inside the `for (i=1; i<l.size(); i++)` loop, so empty and
  singleton lists never enter the loop — the function returns `true` for them
  (vacuously monotonic). Pinned as observed behavior.
- Element nullability: not specified. The implementation auto-unboxes elements;
  a `null` element would NPE. Treated here as undefined-by-spec; not exercised.

## Equivalence classes

### Valid partitions (V)
| ID | Description | Representative input | Expected |
|----|-------------|----------------------|----------|
| V1 | Empty list (loop never enters) | `[]` | `true` |
| V2 | Single-element list (loop never enters) | `[42]` | `true` |
| V3 | Two-element strictly increasing pair | `[1,2]` | `true` |
| V4 | Two-element strictly decreasing pair | `[2,1]` | `true` |
| V5 | Two-element equal pair | `[7,7]` | `true` |
| V6 | Strictly increasing, length >= 3 | `[1,2,4,10]`, `[1,2,4,20]` | `true` |
| V7 | Strictly decreasing, length >= 3 | `[4,1,0,-10]` | `true` |
| V8 | Non-decreasing with plateau | `[1,2,2,3]`, `[4,1,1,0]` (non-increasing plateau) | `true` |
| V9 | All equal (degenerate plateau) | `[9,9,9,9]` | `true` |
| V10 | Up then down (single direction violation) | `[1,3,2]`, `[1,20,4,10]` | `false` |
| V11 | Down then up (single direction violation) | `[3,1,2]`, `[1,2,3,2,5,60]` | `false` |
| V12 | Wide ranges incl. extremes | `[Integer.MIN_VALUE, Integer.MAX_VALUE]` | `true` |

### Invalid / undefined-by-spec partitions (I)
| ID | Description | Representative input | Observed behavior |
|----|-------------|----------------------|--------------------|
| I1 | `null` list | `null` | NPE on `l.size()` (not exercised) |
| I2 | List containing `null` element | `[1, null, 2]` | NPE on auto-unbox (not exercised) |

## Boundary conditions
- Size boundaries: 0, 1, 2 (smallest size where the loop enters and a single
  comparison decides everything), 3 (smallest size that can be non-monotonic).
- Element-equality boundary: pure plateau (all equal) and mixed plateau
  (`[1,2,2,3]`, `[4,1,1,0]`) — exercises the "neither inner if fires" path.
- Sign boundary: lists straddling zero (`[-5,-1,0,3,100]`, `[4,1,0,-10]`).
- Range boundary: `[Integer.MIN_VALUE, Integer.MAX_VALUE]` confirms no overflow
  in the comparison (the implementation uses `<` and `>` directly, no subtraction).

## Coverage rationale
Materialized cases drive every branch in `Solution.monotonic`:
- Outer-loop entry: V1, V2 (no entry) vs V3..V11 (entry).
- First inner if (`l.get(i) < l.get(i-1)`):
  - true (clears `nonDecreasing`): V4, V7, V10, V11.
  - false: V3, V5, V6, V8, V9.
- Second inner if (`l.get(i) > l.get(i-1)`):
  - true (clears `nonIncreasing`): V3, V6, V8 (`[1,2,2,3]` step 1->2),
    V10, V11.
  - false: V4, V5, V7, V9.
- Final `||`:
  - left-only true (non-decreasing): V3, V6, V8 `[1,2,2,3]`.
  - right-only true (non-increasing): V4, V7, V8 `[4,1,1,0]`.
  - both true (no rises and no drops): V1, V2, V5, V9.
  - both false (a rise AND a drop appear): V10, V11.
