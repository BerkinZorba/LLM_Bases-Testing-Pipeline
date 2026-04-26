---
prompt_id: HumanEval_087
model: codex
step: manual_assist
iteration: 1
timestamp: 2026-04-26T00:00:00 (local)
output_files:
  - tests/manual_tests/codex/HumanEval_087_blackbox.md
  - tests/manual_tests/codex/HumanEval_087_ManualTest.java
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

Black-box analysis identified 8 valid partitions (V1–V8) and 1 invalid/undefined one (I1).

Valid partitions cover: empty outer list, all rows empty, x absent, single cell match, one match per row across multiple rows, two matches in same row (reverseOrder sort), mixed rows with/without x, jagged rows.

Boundary conditions: empty outer list (outer loop not entered), empty row (inner loop not entered for that row), cols empty after inner loop (for-each over cols skipped), single-element row with match (cols=[0], sorted still [0]), two matches in row (reverseOrder reorders cols).

The ManualTest materializes all 8 valid partitions plus 2 boundary cases as 10 isolated JUnit tests.

## Decision note
- Output saved as-is: HumanEval_087_blackbox.md and HumanEval_087_ManualTest.java under tests/manual_tests/codex/.
- All 10 tests pass; 13/13 branches covered.
- Design is independent from implementation (black-box only).
