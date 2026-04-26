# HumanEval_093 - Manual black-box assessment (Codex)

Function under test: `Solution.encode(String message)`

Prompt requirement:
> Write a function that takes a message, and encodes in such a way that it swaps case of all letters, replaces all vowels in the message with the letter that appears 2 places ahead of that vowel in the english alphabet. Assume only letters.

The examples show two transformations: letters swap case, and vowels are shifted forward by two positions before the output case is chosen.

## 1. Observable input dimensions

| Dimension | Values worth partitioning |
|-----------|----------------------------|
| Length | null, empty, one character, multiple characters |
| Character class | vowel, consonant, non-letter |
| Original case | uppercase, lowercase, mixed case |
| Vowel replacement | lowercase vowels, uppercase vowels, no vowels |
| Prompt validity | letters only vs spaces/digits/punctuation |

The output preserves position count for non-null inputs. Letters swap case; vowels also change to the letter two positions ahead.

## 2. Equivalence classes

### Valid classes

| ID | Description | Example | Expected |
|----|-------------|---------|----------|
| V1 | Lowercase consonants only | `"bcdfg"` | `"BCDFG"` |
| V2 | Uppercase consonants only | `"BCDFG"` | `"bcdfg"` |
| V3 | Lowercase vowels only | `"aeiou"` | `"CGKQW"` |
| V4 | Uppercase vowels only | `"AEIOU"` | `"cgkqw"` |
| V5 | Mixed vowel/consonant lowercase | `"test"` | `"TGST"` |
| V6 | Mixed case word | `"Mudasir"` | `"mWDCSKR"` |
| V7 | Adjacent same vowel in both cases | `"aA"` | `"Cc"` |
| V8 | No vowel case swap only | `"zZ"` | `"Zz"` |

### Invalid or underspecified classes

| ID | Description | Current implementation behavior |
|----|-------------|---------------------------------|
| I1 | `null` input | returns `""` |
| I2 | Empty string | returns `""` |
| I3 | Spaces | preserved unchanged |
| I4 | Digits and punctuation | preserved unchanged |

## 3. Boundary cases

| Boundary | Input | Expected |
|----------|-------|----------|
| Null reference | `null` | `""` |
| Length 0 | `""` | `""` |
| Single lowercase vowel | `"a"` | `"C"` |
| Single uppercase vowel | `"A"` | `"c"` |
| Single lowercase consonant | `"b"` | `"B"` |
| Single uppercase consonant | `"B"` | `"b"` |

## 4. Black-box expectations to execute

- consonants only swap case;
- vowels shift two alphabet positions and then follow the swapped-case output style;
- mixed-case inputs must apply rules per character;
- spaces and other non-letters are outside the prompt's "Assume only letters" condition, so tests pin current behavior rather than treating it as a prompt defect.
