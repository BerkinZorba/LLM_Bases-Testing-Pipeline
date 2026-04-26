# HumanEval_089 - Manual black-box assessment (Codex)

Function under test: `Solution.encrypt(String s)`

Prompt requirement:
> Create a function encrypt that takes a string as an argument and returns a string encrypted with the alphabet being rotated. The alphabet should be rotated in a manner such that the letters shift down by two multiplied to two places.

The examples show a lowercase Caesar shift by 4: `a -> e`, `h -> l`, and wraparound such as `z -> d`.

## 1. Observable input dimensions

| Dimension | Values worth partitioning |
|-----------|----------------------------|
| Length | null, empty, one character, multiple characters |
| Alphabet position | no wraparound, wraparound near `z`, full alphabet |
| Character repetition | unique letters, repeated same letter |
| Input validity | lowercase alphabetic vs non-lowercase characters |
| Reference validity | non-null vs null |

The output is the input string with each lowercase letter shifted forward by 4 positions modulo 26.

## 2. Equivalence classes

### Valid classes

| ID | Description | Example | Expected |
|----|-------------|---------|----------|
| V1 | Empty string | `""` | `""` |
| V2 | Single non-wrapping letter | `"a"` | `"e"` |
| V3 | Single wrapping letter | `"z"` | `"d"` |
| V4 | Multiple non-wrapping letters | `"hi"` | `"lm"` |
| V5 | Mixed no-wrap and wrap letters | `"abcxyz"` | `"efgbcd"` |
| V6 | Full alphabet | `"abcdefghijklmnopqrstuvwxyz"` | `"efghijklmnopqrstuvwxyzabcd"` |
| V7 | Repeated wrapping letter | `"zzzz"` | `"dddd"` |
| V8 | Longer dataset word | `"hellomyfriend"` | `"lippsqcjvmirh"` |

### Invalid or underspecified classes

| ID | Description | Current implementation behavior |
|----|-------------|---------------------------------|
| I1 | `null` input | returns `""` |
| I2 | Uppercase letter | shifted using lowercase arithmetic with Java remainder behavior, e.g. `"A" -> "_"` |
| I3 | Space character | shifted using lowercase arithmetic, e.g. `" " -> "X"` |
| I4 | Digit | shifted using lowercase arithmetic with Java remainder behavior, e.g. `"1" -> "O"` |

## 3. Boundary cases

| Boundary | Input | Expected |
|----------|-------|----------|
| Null reference | `null` | `""` |
| Length 0 | `""` | `""` |
| First alphabet letter | `"a"` | `"e"` |
| Last alphabet letter | `"z"` | `"d"` |
| First wrap block | `"wxyz"` | `"abcd"` |
| Full alphabet | `"abcdefghijklmnopqrstuvwxyz"` | `"efghijklmnopqrstuvwxyzabcd"` |

## 4. Black-box expectations to execute

- valid lowercase letters must be shifted by 4 positions;
- letters near the end of the alphabet must wrap around to the beginning;
- output length should match input length for non-null inputs;
- null and non-lowercase characters are outside the examples' clear valid domain, so tests pin current behavior rather than treating it as a prompt defect.
