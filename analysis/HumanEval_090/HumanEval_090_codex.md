# HumanEval_090 — Per-prompt analysis (Codex / GPT)

## Function
`Optional<Integer> nextSmallest(List<Integer> lst)` — return the 2nd smallest
distinct element, or `Optional.empty()` if none exists.

## Generated code summary
Single-pass two-variable tracking (min, second). Guards: `lst == null || lst.size() < 2`
→ empty. Tracks minimum via `num < min`, promoting old min to second when a new minimum
is found, or updating second directly for values between min and second.

## Base test results
- Suite: `tests/base_tests/codex/HumanEval_090_BaseTest.java`
- Result: **5/5 pass**

## Coverage (JaCoCo)
| Suite    | Tests | Instr  | Branch | Line  | CC    | Method |
|----------|-------|--------|--------|-------|-------|--------|
| base     | 5     | 64/64  | 20/22  | 13/13 | 11/13 | 2/2   |
| improved | 11    | 64/64  | 20/22  | 13/13 | 11/13 | 2/2   |
| manual   | 12    | 64/64  | 20/22  | 13/13 | 11/13 | 2/2   |

**Missed branches (2)**: The `lst == null` true side of the `||` short-circuit.
Passing null is undefined by spec; not tested. Pinned as spec-boundary.

## Improved test rationale
Smells: assertion roulette, eager test. Added descending list, single element,
two-same, duplicate-min, and negative-number cases. All branches reachable under
valid spec are covered.

## Manual black-box
See `tests/manual_tests/codex/HumanEval_090_blackbox.md`. V_E1–V_E4 (empty-result
paths), V_R1–V_R7 (value paths). Boundaries: minimum-at-end traversal, exactly-two-distinct.

## Defects
None against the spec.

## Refactor loop
Not triggered.
