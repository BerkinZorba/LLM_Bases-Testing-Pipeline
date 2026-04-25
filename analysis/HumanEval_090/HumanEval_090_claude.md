# HumanEval_090 — Per-prompt analysis (Claude)

## Function
`Optional<Integer> nextSmallest(List<Integer> lst)` — return the 2nd smallest
distinct element, or `Optional.empty()` if none exists.

## Generated code summary
Guard: `lst == null || lst.size() < 2` → empty. Deduplication via `TreeSet<Integer>`.
Guard: `distinct.size() < 2` → empty. `pollFirst()` removes minimum; `first()` returns
new minimum (= 2nd distinct smallest).

## Base test results
- Suite: `tests/base_tests/claude/HumanEval_090_BaseTest.java`
- Result: **5/5 pass** (all docstring examples pass)

## Coverage (JaCoCo)
| Suite    | Tests | Instr  | Branch | Line | CC  | Method |
|----------|-------|--------|--------|------|-----|--------|
| base     | 5     | 30/30  | 5/6    | 6/6  | 4/5 | 2/2   |
| improved | 12    | 30/30  | 5/6    | 6/6  | 4/5 | 2/2   |
| manual   | 12    | 30/30  | 5/6    | 6/6  | 4/5 | 2/2   |

**Missed branch**: The `lst == null` side of the `||` short-circuit. Passing `null`
would throw a NullPointerException before reaching the guard in some JVM implementations,
and passing null is undefined by spec. Pinned as spec-boundary; not a defect.

## Improved test rationale
Smells: assertion roulette, eager test. Branch targets: size < 2 (null/single-element),
distinct.size() < 2 (all identical), normal path with negative numbers and large lists.

## Manual black-box
See `tests/manual_tests/claude/HumanEval_090_blackbox.md`. V_E1–V_E4 (empty paths),
V_R1–V_R7 (value paths), boundary: exactly two distinct values after dedup.

## Defects
None against the spec.

## Refactor loop
Not triggered.
