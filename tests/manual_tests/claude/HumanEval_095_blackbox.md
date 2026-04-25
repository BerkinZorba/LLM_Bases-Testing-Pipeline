# HumanEval_095 — Manual black-box assessment (Claude)

## Specification recap
`checkDictCase(Map<Object, Object> dict)` returns:
- `false` if the map is empty (per the prompt's explicit rule),
- `false` if any key is not a `String` (per the dataset row
  `{"a", 8, "a"} -> false`),
- otherwise, `true` iff every string key is equal to its
  lower-cased form **or** every string key is equal to its
  upper-cased form,
- `false` for any other case (e.g. mixed-case keys, title-case
  keys, mixed lower- and upper-case keys).

Notes from the dataset:
- `{"a", "b"} -> true` and `{"fruit", "taste"} -> true` show
  all-lower keys map to `true`.
- `{"STATE", "ZIP"} -> true` shows that a key with no cased
  letters (`"12345"` is not a key here, but `"ZIP"`/`"STATE"`
  are upper) does not break the all-upper path. By the same
  logic, a key that is equal to **both** its lower- and
  upper-cased form (digits, empty string, punctuation) is
  caseless and does not flip either flag.
- `{"Name", "Age", "City"} -> false` shows title-case keys
  are neither all-lower nor all-upper.

## Input domain
A `Map<Object, Object>`. Keys are `Object`. Valid spec inputs
are non-empty maps whose keys are all `String`. Out-of-spec
inputs include the empty map and maps containing non-`String`
keys.

## Equivalence classes

### Valid partitions (spec defines behavior)
| ID  | Class                                              | Sample                                  | Expected | Why it matters                                  |
| --- | -------------------------------------------------- | --------------------------------------- | -------- | ----------------------------------------------- |
| V1  | all-lower keys (single key)                        | `{"a"}`                                  | true     | minimal `allLower` path                         |
| V2  | all-lower keys (multiple keys)                     | `{"p","b"}`                              | true     | dataset row 1                                   |
| V3  | all-lower keys with digits in value only           | `{"fruit","taste"}`                      | true     | dataset row 6                                   |
| V4  | all-upper keys (single key)                        | `{"A"}`                                  | true     | minimal `allUpper` path                         |
| V5  | all-upper keys (multiple keys)                     | `{"STATE","ZIP"}`                        | true     | dataset row 5                                   |
| V6  | all-lower keys + caseless key (digits)             | `{"zip","12345"}`                        | true     | digits leave both flags true; allLower wins     |
| V7  | all-upper keys + caseless key (digits)             | `{"ZIP","12345"}`                        | true     | digits leave both flags true; allUpper wins     |
| V8  | caseless key alone (digits)                        | `{"123"}`                                | true     | both flags stay true; `allLower || allUpper`    |
| V9  | caseless key alone (empty string)                  | `{""}`                                   | true     | `"" == "".toLowerCase()` and equal to upper too |
| V10 | caseless key alone (punctuation)                   | `{"_"}`                                  | true     | symbols are case-equal to themselves            |
| V11 | mixed lower and upper keys                         | `{"p","A","B"}`                          | false    | dataset row 2; clears both flags                |
| V12 | title-case keys                                    | `{"Name","Age","City"}`                  | false    | dataset row 4                                   |
| V13 | single mixed-case key                              | `{"aB"}`                                 | false    | one key alone clears both flags                 |

### Invalid partitions (spec says false, or behavior pinned)
| ID  | Class                              | Sample                       | Expected |
| --- | ---------------------------------- | ---------------------------- | -------- |
| I1  | empty map                          | `{}`                         | false    |
| I2  | only a non-string key              | `{1: "a"}`                   | false    |
| I3  | non-string key after lower keys    | `{"a":..., 7:...}`           | false    |
| I4  | non-string key with mixed valid    | `{"p":..., 5:..., "a":...}`  | false    |

## Boundary table — variable-by-variable
| Variable                | Just below          | At edge               | Just above             |
| ----------------------- | ------------------- | --------------------- | ---------------------- |
| map size                | empty (`{}`)         | 1-key map             | 2+ key map             |
| key type                | non-`String` (Int)  | `String`               | `String`                |
| case (per key)          | not all-lower       | all-lower              | mixed -> false         |
| case (per key)          | not all-upper       | all-upper              | mixed -> false         |
| caseless content        | mixed-case key      | digits / empty / `_`   | letter key             |

## Coverage of partitions in `HumanEval_095_ManualTest.java`
- V1..V13 -> one explicit JUnit test each (`valid_*`).
- I1..I4 -> one explicit JUnit test each (`invalid_*`).
- Boundary variables are exercised by the size variants
  (`empty`, `singleKey`, `manyKeys`), the type variant
  (`firstKeyInteger`, `intKeyAfterStringKey`), and the case
  variants (`allLower`, `allUpper`, `mixedLowerUpper`,
  `titleCase`, `mixedCaseSingle`, plus the caseless variants
  `digitsOnly`, `emptyString`, `underscoreOnly`).
- Order-of-iteration concerns are pinned by using
  `LinkedHashMap` so that inputs which interleave a non-string
  key after a string key still trigger the `false` branch.
