# HumanEval_095 manual black-box assessment (Codex)

## Prompt contract

`checkDictCase(Map<Object, Object> dict)` should return `true` only when:

- the map is not empty,
- every key is a `String`,
- every key belongs to the same case class,
- that shared class is either lowercase-only or uppercase-only.

If any key is non-string, mixed-case, or the map is empty, the observable result should be `false`.

## Equivalence classes

### Valid classes

- V1: all keys are lowercase strings
  - Example: `{"a": 1, "b": 2}` -> `true`
- V2: all keys are uppercase strings
  - Example: `{"A": 1, "B": 2}` -> `true`
- V3: lowercase words with separators or digits but no uppercase letters
  - Example: `{"zip_code1": 1, "state2": 2}` -> `true`
- V4: uppercase words with separators or digits but no lowercase letters
  - Example: `{"ZIP_CODE1": 1, "STATE2": 2}` -> `true`

### Invalid / out-of-scope classes

- I1: mixed lowercase and uppercase keys across the map
  - Example: `{"a": 1, "B": 2}` -> `false`
- I2: a mixed-case string key
  - Example: `{"Name": "John"}` -> `false`
- I3: a non-string key
  - Example: `{"a": 1, 8: 2}` -> `false`
- I4: empty map
  - Example: `{}` -> `false`
- I5: string key with no cased letters
  - Current Codex implementation treats keys like `""` or `"123"` as invalid and returns `false`.

## Boundary conditions

- B1: smallest non-empty lowercase map
  - `{"a": 1}` -> `true`
- B2: smallest non-empty uppercase map
  - `{"A": 1}` -> `true`
- B3: first cross-case conflict
  - `{"a": 1, "A": 2}` -> `false`
- B4: first type conflict
  - `{"a": 1, 1: 2}` -> `false`
- B5: empty-input boundary
  - `{}` -> `false`

## Expected black-box observations

- Values do not affect the result; only key type and key casing matter.
- Once the first key establishes lowercase or uppercase expectation, every later key must match it.
- Mixed-case words such as `"Name"` are rejected because they are neither fully lowercase nor fully uppercase.
- The current implementation also rejects strings without any letters because they do not belong to either cased class.
