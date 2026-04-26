# HumanEval_087 — Analysis (Codex)

## Function under test

`List<List<Integer>> getRow(List<List<Integer>> lst, int x)`
Find all `[row, col]` coordinates where `lst[row][col] == x`.
Sort by row ascending; within each row sort by column descending.

---

## Generated code summary

Codex's implementation collects matching column indices in a temporary `cols` list, then sorts them with `Collections.sort(cols, Collections.reverseOrder())` before appending to results.

```java
for (int i = 0; i < lst.size(); i++) {
    List<Integer> row = lst.get(i);
    List<Integer> cols = new ArrayList<>();
    for (int j = 0; j < row.size(); j++) {
        if (row.get(j) == x) { cols.add(j); }
    }
    Collections.sort(cols, Collections.reverseOrder());
    for (int col : cols) { result.add(Arrays.asList(i, col)); }
}
```

**Approach**: collect + explicit sort — explicit and correct.

---

## Base test results

Suite: `tests/base_tests/codex/HumanEval_087_BaseTest.java` (6 tests)

| Test | Result |
|------|--------|
| docstringExample_3rows_x1 | PASS |
| uniform6rows_x2_onePerRow | PASS |
| diagonal7rows_x1_twoPerRow | PASS |
| emptyList_returnsEmpty | PASS |
| singleCellNoMatch_returnsEmpty | PASS |
| jaggedWithEmptyRow_xInLastRow | PASS |

**Result: 6/6 PASS**

No test-side adjustments required.

---

## Branch coverage

JaCoCo identifies **13 branches** in `Solution.getRow` (3 for-loops + 1 if + for-each iterator internals):

| Branch group | Description | Covered by |
|---|---|---|
| Outer for-loop (2) | `i < lst.size()` true/false | emptyList (false), any match (true) |
| Inner for-loop (2) | `j < row.size()` true/false | emptyRow (false), any non-empty row (true) |
| if condition (2) | `row.get(j) == x` true/false | xAbsent (false), any match (true) |
| for-each cols (2) | cols empty/non-empty | xAbsent (empty), any match (non-empty) |
| remaining (5) | loop iteration/termination paths | covered by multi-match tests |

| Suite | Branches missed | Branches covered | Coverage |
|-------|----------------|-----------------|----------|
| base | 0 | 13 | 100% |
| improved | 0 | 13 | 100% |
| manual | 0 | 13 | 100% |

**All three suites achieve 100% branch coverage.**

---

## Test smell analysis (improved suite)

Original base suite smells addressed in improved suite:
- **Assertion roulette**: base `diagonal7rows` and `docstring` tests check many coordinates → split into focused cases.
- **Magic numbers**: grid sizes and x values given meaningful `@DisplayName` descriptions.
- **Eager test**: base tests conflate empty-input and multi-match concerns → separated into flat methods by concern.

Improved suite structure (11 tests with `@DisplayName`):
- Empty list, single empty row, x absent, single cell match/no-match.
- Two matches in row (reverseOrder sort verified), match at both ends.
- One per row (6 rows), docstring 3-row example.
- Jagged list, x in some rows only.

---

## Manual black-box assessment

8 valid partitions (V1–V8) + 1 invalid/undefined (I1). 10 tests.
See `tests/manual_tests/codex/HumanEval_087_blackbox.md` for full EC table.

All 10 manual tests pass; 13/13 branches covered.

---

## Defects found

None. Codex's collect-then-sort approach correctly implements the specification. `Collections.reverseOrder()` ensures descending column order within each row; outer loop preserves ascending row order.

---

## Refactor loop

Not triggered — no defects found.

---

## Claude vs Codex comparison note

Both implementations are correct. Key difference: Claude iterates columns in reverse (avoids a sort), Codex iterates forward then sorts. Both produce identical output. Codex has more branches (13 vs 6) because of the extra `for-each` loop over `cols`.
