---
prompt_id: HumanEval_087
model: claude
step: manual_assist
iteration: 1
timestamp: 2026-04-26T00:00:00 (local)
output_files:
  - tests/manual_tests/claude/HumanEval_087_blackbox.md
  - tests/manual_tests/claude/HumanEval_087_ManualTest.java
---

## Prompt (verbatim)

Design a black-box test suite for HumanEval_087 (getRow) from the specification only:

`List<List<Integer>> getRow(List<List<Integer>> lst, int x)` —
Find all [row, col] coordinates where lst[row][col] == x.
Sort results by row ascending; within each row sort by column descending.

Produce:
1. A blackbox.md with equivalence classes (valid + invalid), boundary conditions, and coverage rationale.
2. A JUnit 5 ManualTest.java materializing those classes as executable tests.

Do not look at the implementation; base the design purely on the specification.

## Response (verbatim)

Black-box analysis identified 9 valid partitions (V1–V9) and 2 invalid/undefined ones (I1–I2).

Valid partitions cover: empty outer list, all rows empty, x absent, single cell match, one match per row, multiple matches same row, jagged rows with x in longer row, multi-row multi-match (docstring), x at first and last column.

Boundary conditions: empty outer list (loop not entered), empty row (inner loop not entered), single cell match/no-match, column order (highest matching index first within row), row order (lower index before higher).

The ManualTest materializes all 9 valid partitions plus 2 boundary cases as 11 isolated JUnit tests.

## Decision note
- Output saved as-is: HumanEval_087_blackbox.md and HumanEval_087_ManualTest.java under tests/manual_tests/claude/.
- All 11 tests pass; 6/6 branches covered.
- Design is independent from implementation (black-box only).
