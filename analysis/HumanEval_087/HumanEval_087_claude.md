# HumanEval_087 — Analysis (Claude)

## Function under test

`List<List<Integer>> getRow(List<List<Integer>> lst, int x)`
Find all `[row, col]` coordinates where `lst[row][col] == x`.
Sort by row ascending; within each row sort by column descending.

---

## Generated code summary

Claude's implementation iterates rows forward, then columns **in reverse** (`col = r.size()-1` down to `0`). Because columns are visited highest-first, matching coordinates are added in descending column order directly — no intermediate `cols` list or explicit sort needed.

```java
for (int row = 0; row < lst.size(); row++) {
    List<Integer> r = lst.get(row);
    for (int col = r.size() - 1; col >= 0; col--) {
        if (r.get(col) == x) {
            result.add(Arrays.asList(row, col));
        }
    }
}
```

**Approach**: reverse iteration — elegant, no extra allocation.

---

## Base test results

Suite: `tests/base_tests/claude/HumanEval_087_BaseTest.java` (6 tests)

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

JaCoCo identifies **6 branches** in `Solution.getRow`:

| Branch | Description | Covered by |
|--------|-------------|------------|
| B1 | Outer loop: `row < lst.size()` false (0 iterations) | emptyList |
| B2 | Outer loop: `row < lst.size()` true (≥1 iteration) | any non-empty list |
| B3 | Inner loop: `col >= 0` false (empty row) | allRowsEmpty / jaggedWithEmptyRow |
| B4 | Inner loop: `col >= 0` true (≥1 iteration) | any non-empty row |
| B5 | if: `r.get(col) == x` false | xAbsent / singleCellNoMatch |
| B6 | if: `r.get(col) == x` true | any match test |

| Suite | Branches missed | Branches covered | Coverage |
|-------|----------------|-----------------|----------|
| base | 0 | 6 | 100% |
| improved | 0 | 6 | 100% |
| manual | 0 | 6 | 100% |

**All three suites achieve 100% branch coverage.**

---

## Test smell analysis (improved suite)

Original base suite smells addressed in improved suite:
- **Assertion roulette**: base `diagonal7rows` test checks 14 coordinates in one call → split into isolated cases.
- **Magic numbers**: numbers like `7`, `14`, `6` replaced with `@DisplayName` descriptions.
- **Eager test**: base tests mix empty-list, no-match, and multi-match concerns → separated into `@Nested` groups.

Improved suite structure (13 tests):
- `EmptyInputs`: empty outer list, all rows empty, single empty row.
- `NoMatches`: x absent, single cell no match.
- `SingleMatch`: first row only, last row only.
- `MultipleMatchesInRow`: two matches, three matches, first+last column.
- `MultiRow`: one per row (6 rows), docstring example, jagged.

---

## Manual black-box assessment

9 valid partitions (V1–V9) + 2 invalid/undefined (I1, I2). 11 tests.
See `tests/manual_tests/claude/HumanEval_087_blackbox.md` for full EC table.

All 11 manual tests pass; 6/6 branches covered.

---

## Defects found

None. Claude's reverse-iteration approach is correct and produces the required descending column order without an explicit sort step.

---

## Refactor loop

Not triggered — no defects found.
