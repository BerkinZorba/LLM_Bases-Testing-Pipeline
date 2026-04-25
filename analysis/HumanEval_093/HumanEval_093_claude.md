# HumanEval_093 – Claude analysis

## 1. Code generation

**Prompt**: `encode(String message)` — swap case of every letter, then shift vowels +2 places in the alphabet.

**Generated file**: `generated_code/claude/HumanEval_093.java`

The generation was correct and complete on the first attempt. No refactor loop was needed.

**Implementation notes:**
- Guard: `if (message == null || message.isEmpty()) return ""` — handles null and empty input.
- Space pass-through: `if (c == ' ') { result.append(c); continue; }` — necessary because dataset examples include spaces, even though the spec says "Assume only letters."
- Two-step logic per character: (1) swap case, (2) if the *original* character was a vowel, shift the already-swapped character by +2.
- Vowel set: `{'a','e','i','o','u','A','E','I','O','U'}` — `y`/`Y` are correctly excluded.

---

## 2. Base test results

**Suite**: `tests/base_tests/claude/HumanEval_093_BaseTest.java` (5 tests)
**Log**: `llm_logs/claude/HumanEval_093_initial.md`

| Result | Count |
|--------|-------|
| Passed | 5 / 5 |
| Failed | 0 |

**Coverage on `Solution` (JaCoCo):**

| Metric | Covered / Total |
|--------|-----------------|
| INSTRUCTION | 120 / 122 |
| BRANCH | 10 / 12 |
| LINE | 13 / 13 |
| COMPLEXITY | 6 / 8 |
| METHOD | 2 / 2 |

**Missed branches:**
1. `message == null` true — null not sent in any dataset test.
2. `message.isEmpty()` true — empty string not sent in any dataset test.

All other branches (space pass-through, uppercase/lowercase consonant, uppercase/lowercase vowel, vowels.contains true/false) are exercised by the five dataset cases.

---

## 3. Test smell analysis

| Smell | Base test | Fix in improved suite |
|-------|-----------|----------------------|
| Assertion roulette | Original harness wraps 5 asserts in one `List<Boolean>` — failure only reports an index | Split into 5 individually named `@Test` methods |
| Missing guard coverage | Null and empty guards never exercised | Added `nullInput_returnsEmpty` and `emptyString_returnsEmpty` |
| Missing isolated vowel coverage | Only compound strings tested | Added per-vowel parameterized rows for all 10 vowels (5 lower + 5 upper) |
| Incomplete consonant coverage | Consonants only appear inside mixed strings | Added focused single-consonant tests (upper and lower) |

---

## 4. Improved test suite

**Suite**: `tests/improved_tests/claude/HumanEval_093_ImprovedTest.java` (22 tests)

| Result | Count |
|--------|-------|
| Passed | 22 / 22 |
| Failed | 0 |

**Coverage on `Solution`:**

| Metric | Covered / Total |
|--------|-----------------|
| INSTRUCTION | 122 / 122 |
| BRANCH | 12 / 12 |
| LINE | 13 / 13 |
| COMPLEXITY | 8 / 8 |
| METHOD | 2 / 2 |

**Branches newly covered:** null guard true, empty-string guard true.

---

## 5. Manual black-box assessment

**Design doc**: `tests/manual_tests/claude/HumanEval_093_blackbox.md`
**Suite**: `tests/manual_tests/claude/HumanEval_093_ManualTest.java` (19 tests)

### Equivalence classes

| ID | Class | Input | Expected |
|----|-------|-------|----------|
| V1 | Empty string | `""` | `""` |
| V2 | Single lowercase vowel `a` | `"a"` | `"C"` |
| V3 | Single uppercase vowel `E` | `"E"` | `"g"` |
| V4 | Single lowercase consonant | `"b"` | `"B"` |
| V5 | Single uppercase consonant | `"T"` | `"t"` |
| V6 | All 5 lowercase vowels | `"aeiou"` | `"CGKQW"` |
| V7 | All 5 uppercase vowels | `"AEIOU"` | `"cgkqw"` |
| V8 | Mixed consonants only | `"bTsT"` | `"BtSt"` |
| V9 | Lowercase vowels + consonants | `"message"` | `"MGSSCGG"` |
| V10 | Uppercase vowels + consonants | `"YES"` | `"ygs"` |
| V11 | Full mixed case + spaces | `"This is a message"` | `"tHKS KS C MGSSCGG"` |
| V12 | y/Y are not vowels | `"yYy"` | `"YyY"` |
| V13 | Single space (pinned) | `" "` | `" "` |
| V14 | Leading/trailing spaces (pinned) | `" a "` | `" C "` |
| I1 | null input (pinned) | `null` | `""` |

### Boundary conditions

| Boundary | Input | Expected |
|----------|-------|----------|
| Length 0 | `""` | `""` |
| Length 1 consonant | `"b"` | `"B"` |
| Length 1 vowel | `"a"` | `"C"` |
| Last vowel `u` | `"u"` | `"W"` (`U+2`) |
| Non-vowel `y`/`Y` | `"y"`, `"Y"` | `"Y"`, `"y"` |

**Coverage on `Solution`:**

| Metric | Covered / Total |
|--------|-----------------|
| INSTRUCTION | 122 / 122 |
| BRANCH | 12 / 12 |
| LINE | 13 / 13 |
| COMPLEXITY | 8 / 8 |
| METHOD | 2 / 2 |

---

## 6. Defects found

**No code defects found.** All 46 tests (5 base + 22 improved + 19 manual) pass against the initial generation (`HumanEval_093.java`). No refactor loop was required.

---

## 7. Coverage roll-up

| Suite | Tests | Pass | Fail | INSTRUCTION | BRANCH | LINE |
|-------|-------|------|------|-------------|--------|------|
| Base | 5 | 5 | 0 | 120/122 (98%) | 10/12 (83%) | 13/13 (100%) |
| Improved | 22 | 22 | 0 | 122/122 (100%) | 12/12 (100%) | 13/13 (100%) |
| Manual | 19 | 19 | 0 | 122/122 (100%) | 12/12 (100%) | 13/13 (100%) |

Coverage reports stored at: `coverage_reports/HumanEval_093/claude/{base,improved,manual}/`.
