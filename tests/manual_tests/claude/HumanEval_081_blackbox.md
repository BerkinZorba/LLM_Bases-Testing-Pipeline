# HumanEval_081 — Manual black-box assessment (Claude)

## Specification recap
`numericalLetterGrade(List<Double> grades)` returns a `List<String>` of
letter grades, one per element of `grades`, preserving order. The
mapping is read directly from the prompt's table:

| GPA condition | Letter |
| ------------- | ------ |
| `gpa == 4.0` | `A+` |
| `gpa > 3.7` | `A` |
| `gpa > 3.3` | `A-` |
| `gpa > 3.0` | `B+` |
| `gpa > 2.7` | `B` |
| `gpa > 2.3` | `B-` |
| `gpa > 2.0` | `C+` |
| `gpa > 1.7` | `C` |
| `gpa > 1.3` | `C-` |
| `gpa > 1.0` | `D+` |
| `gpa > 0.7` | `D` |
| `gpa > 0.0` | `D-` |
| `gpa == 0.0` | `E` |

Strict-`>` semantics for the 12 mid bands are confirmed by the dataset
example: `3.0 -> B`, `1.7 -> C-`, `2.0 -> C`. The top band uses exact
equality (`4.0 -> A+`); the bottom band catches `0.0 -> E` and any
positive GPA strictly less than `0.7` falls into `D-`.

## Input domain
A `List<Double>`. The spec implicitly assumes valid GPAs in `[0.0, 4.0]`.
Values outside that range, an empty list, and signed-zero floats are
out-of-spec but observable; selected cases are pinned below.

## Equivalence classes

### Valid partitions (spec defines behavior)
| ID  | Class                                     | Sample                                | Why it matters                                |
| --- | ----------------------------------------- | ------------------------------------- | --------------------------------------------- |
| V1  | exact top (`4.0`)                         | `[4.0]` -> `[A+]`                      | exercises the `==` branch (only path to A+)   |
| V2  | strictly inside `A` band                  | `[3.8]` -> `[A]`                       | mid-band, predicate `> 3.7` true              |
| V3  | strictly inside `A-` band                 | `[3.4]` -> `[A-]`                      | mid-band                                      |
| V4  | strictly inside `B+` band                 | `[3.1]` -> `[B+]`                      | mid-band                                      |
| V5  | strictly inside `B` band                  | `[2.8]` -> `[B]`                       | dataset row also covers this                  |
| V6  | strictly inside `B-` band                 | `[2.4]` -> `[B-]`                      | mid-band                                      |
| V7  | strictly inside `C+` band                 | `[2.1]` -> `[C+]`                      | mid-band                                      |
| V8  | strictly inside `C` band                  | `[1.8]` -> `[C]`                       | mid-band                                      |
| V9  | strictly inside `C-` band                 | `[1.4]` -> `[C-]`                      | mid-band                                      |
| V10 | strictly inside `D+` band                 | `[1.2]` -> `[D+]` (dataset)            | mid-band                                      |
| V11 | strictly inside `D` band                  | `[0.8]` -> `[D]`                       | mid-band                                      |
| V12 | strictly inside `D-` band                 | `[0.5]` -> `[D-]` (dataset)            | mid-band                                      |
| V13 | exact bottom (`0.0`)                      | `[0.0]` -> `[E]` (dataset)             | only path to `E`                              |
| V14 | dataset multi-element example             | `[4.0, 3.0, 1.7, 2.0, 3.5]`            | per-index mapping, mixed bands                |
| V15 | order preservation under repeats          | `[4.0, 0.0, 4.0, 0.0]` -> alternating  | implementation must not sort or dedupe        |
| V16 | every-band batch                           | one-element-per-band call              | every band is reachable from one call         |

### Boundary partitions (predicate edges)
| ID  | Boundary               | Just below (lower band) | At edge (lower band) | Just above (upper band) |
| --- | ---------------------- | ----------------------- | -------------------- | ----------------------- |
| B1  | 4.0 / `==` for A+      | n/a                     | `4.0` -> `A+`         | (out of valid range)    |
| B2  | 3.7                    | `3.69` -> `A-`          | `3.7` -> `A-`         | `3.71` -> `A`           |
| B3  | 3.3                    | `3.29` -> `B+`          | `3.3` -> `B+`         | `3.31` -> `A-`          |
| B4  | 3.0                    | `2.99` -> `B`           | `3.0` -> `B`          | `3.01` -> `B+`          |
| B5  | 2.7                    | `2.69` -> `B-`          | `2.7` -> `B-`         | `2.71` -> `B`           |
| B6  | 2.3                    | `2.29` -> `C+`          | `2.3` -> `C+`         | `2.31` -> `B-`          |
| B7  | 2.0                    | `1.99` -> `C`           | `2.0` -> `C`          | `2.01` -> `C+`          |
| B8  | 1.7                    | `1.69` -> `C-`          | `1.7` -> `C-`         | `1.71` -> `C`           |
| B9  | 1.3                    | `1.29` -> `D+`          | `1.3` -> `D+`         | `1.31` -> `C-`          |
| B10 | 1.0                    | `0.99` -> `D`           | `1.0` -> `D`          | `1.01` -> `D+`          |
| B11 | 0.7                    | `0.69` -> `D-`          | `0.7` -> `D-`         | `0.71` -> `D`           |
| B12 | 0.0                    | n/a                     | `0.0` -> `E`          | `0.01` -> `D-`          |

### Invalid / undefined-by-spec partitions
| ID  | Class                                | Sample                  | Observed behavior                                  |
| --- | ------------------------------------ | ----------------------- | -------------------------------------------------- |
| U1  | empty input list                     | `[]`                    | returns empty list (loop never enters)             |
| U2  | GPA above 4.0 (out of range)         | `[5.0]`                 | `gpa > 3.7` true, returns `[A]` (pinned)           |
| U3  | negative GPA (out of range)          | `[-0.5]`                | falls through to `else`, returns `[E]` (pinned)    |
| U4  | signed negative zero                 | `[-0.0]`                | `-0.0 == 0.0` is true in Java IEEE-754, returns `[E]` |

## Boundary table — variable-by-variable
| Variable             | Just below     | At edge        | Just above     |
| -------------------- | -------------- | -------------- | -------------- |
| top boundary 4.0     | 3.99 -> A      | 4.0 -> A+      | (out of range) |
| 3.7                  | 3.69 -> A-     | 3.7 -> A-      | 3.71 -> A      |
| 3.3                  | 3.29 -> B+     | 3.3 -> B+      | 3.31 -> A-     |
| 3.0                  | 2.99 -> B      | 3.0 -> B       | 3.01 -> B+     |
| 2.7                  | 2.69 -> B-     | 2.7 -> B-      | 2.71 -> B      |
| 2.3                  | 2.29 -> C+     | 2.3 -> C+      | 2.31 -> B-     |
| 2.0                  | 1.99 -> C      | 2.0 -> C       | 2.01 -> C+     |
| 1.7                  | 1.69 -> C-     | 1.7 -> C-      | 1.71 -> C      |
| 1.3                  | 1.29 -> D+     | 1.3 -> D+      | 1.31 -> C-     |
| 1.0                  | 0.99 -> D      | 1.0 -> D       | 1.01 -> D+     |
| 0.7                  | 0.69 -> D-     | 0.7 -> D-      | 0.71 -> D      |
| 0.0                  | (out of range) | 0.0 -> E       | 0.01 -> D-     |
| input length         | empty list     | 1-element list | many-element list |

## Coverage of partitions in `HumanEval_081_ManualTest.java`
- V1..V16 -> one explicit assertion each (the four dataset rows are
  also retained as `dataset_*` cases for direct traceability).
- B1..B12 -> covered by `boundary_*` tests for "at edge" and
  "just above" inputs; "just below" the top band tests both `3.99`
  (still `A`) and `4.0` (the `==` path).
- U1..U4 -> pinned in `pinned_undefined_*` tests with comments noting
  this is observed-behavior, not a spec contract. U4 documents the
  IEEE-754 quirk where Java treats `-0.0 == 0.0` as `true` so the
  signed-zero input still maps to the `E` band.
