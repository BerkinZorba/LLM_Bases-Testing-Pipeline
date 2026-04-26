# HumanEval_081 manual black-box assessment (Codex)

## Prompt contract

`numericalLetterGrade(List<Double> grades)` converts each GPA to a letter grade using the prompt’s grading table and returns the resulting list in the same order.

## Equivalence classes

### Valid classes

- V1: exact `4.0` maximum value
  - Example: `4.0` -> `"A+"`
- V2: GPA strictly inside a higher band
  - Example: `3.8` -> `"A"`
- V3: GPA exactly on a non-terminal threshold that must fall to the lower bucket
  - Example: `3.7` -> `"A-"`
- V4: GPA strictly inside a middle band
  - Example: `2.5` -> `"B-"`
- V5: GPA strictly inside a lower band
  - Example: `0.8` -> `"D"`
- V6: exact minimum value
  - Example: `0.0` -> `"E"`
- V7: multi-student list preserving order
  - Example: `[4.0, 3.0, 1.7, 2.0, 3.5]` -> `["A+", "B", "C-", "C", "A-"]`

### Invalid / out-of-scope classes

- I1: negative GPA values
  - The prompt table starts at `0.0`, so negatives are out of scope. The current implementation classifies them as `"E"` by fall-through.
- I2: GPA values above `4.0`
  - The prompt only defines the maximum exact value `4.0`. The current implementation classifies values above `4.0` as `"A"`.

## Boundary conditions

- B1: exact `4.0` special case versus just below it
  - `4.0` -> `"A+"`, `3.9` -> `"A"`
- B2: first strict-cut boundary
  - `3.7` -> `"A-"`, `3.7001` would move into `"A"`
- B3: lower strict-cut boundary
  - `0.7` -> `"D-"`, `0.8` -> `"D"`
- B4: minimum boundary
  - `0.0` -> `"E"`
- B5: empty list boundary
  - No GPAs should produce an empty list

## Expected black-box observations

- Every output element depends only on the matching input GPA band.
- Exact threshold values except `4.0` and `0.0` belong to the lower bucket because the prompt uses strict `>` comparisons.
- Output order should match input order exactly.
