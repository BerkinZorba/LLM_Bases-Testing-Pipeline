# HumanEval_089 – Claude analysis

## 1. Code generation

**Prompt**: `encrypt(String s)` — Caesar-style cipher that shifts each letter down by
`2 × 2 = 4` positions in the alphabet (e.g. `"hi"` → `"lm"`).

**Generated file**: `generated_code/claude/HumanEval_089.java`

The generation was correct and complete on the first attempt. No refactor loop was needed.

**Implementation notes:**
- Guard: `if (s == null || s.isEmpty()) return ""` — handles null and empty input.
- Shift: `int shift = 2 * 2` — correctly computes 4.
- Lowercase branch: `'a' + (c - 'a' + shift) % 26` — correctly wraps.
- Uppercase branch (spec-undefined): same modular rotation for `'A'`-`'Z'`.
- Else branch (spec-undefined): non-alphabetic characters pass through unchanged.

The two spec-undefined behaviors (uppercase rotation and non-alpha pass-through) are
reasonable extensions; they are documented and pinned in the manual black-box suite.

---

## 2. Base test results

**Suite**: `tests/base_tests/claude/HumanEval_089_BaseTest.java` (8 tests)  
**Log**: `llm_logs/claude/HumanEval_089_initial.md`

| Result | Count |
|--------|-------|
| Passed | 8 / 8 |
| Failed | 0 |

**Coverage on `Solution` (JaCoCo):**

| Metric | Covered / Total |
|--------|-----------------|
| INSTRUCTION | 51 / 74 |
| BRANCH | 5 / 10 |
| LINE | 8 / 11 |
| COMPLEXITY | 3 / 7 |
| METHOD | 2 / 2 |

**Missed branches:**
1. `s == null` true — null not sent in dataset tests.
2. `s.isEmpty()` true — empty string not sent in dataset tests.
3. `Character.isUpperCase(c)` true — no uppercase chars in dataset inputs.
4. Else branch (non-alphabetic) — no digits/spaces/punctuation in dataset inputs.

---

## 3. Test smell analysis

| Smell | Base test | Fix in improved suite |
|-------|-----------|----------------------|
| Assertion roulette | Original harness puts 8 asserts in one `List<Boolean>` — failure reports only which index failed | Split into 8 individually named `@Test` methods |
| Magic expected values | Implicit from dataset examples | Comments retained for traceability |
| Missing branch coverage | 5/10 branches | Improved suite adds null, empty, uppercase, non-alpha |
| Eager test | Dataset harness mixes all cases in one block | Separate `@Nested` groups per behavioural dimension |

---

## 4. Improved test suite

**Suite**: `tests/improved_tests/claude/HumanEval_089_ImprovedTest.java` (18 tests)

| Result | Count |
|--------|-------|
| Passed | 18 / 18 |
| Failed | 0 |

Note: the initial version of the `mixedCase` test had the wrong expected value (`"eEiI"`
instead of `"eEfF"`). This was a test-authoring error (not a code defect); the test was
corrected before finalising the suite.

**Coverage on `Solution`:**

| Metric | Covered / Total |
|--------|-----------------|
| INSTRUCTION | 74 / 74 |
| BRANCH | 10 / 10 |
| LINE | 11 / 11 |
| COMPLEXITY | 7 / 7 |
| METHOD | 2 / 2 |

**Branches newly covered:**
- Null guard → true (null input).
- Empty guard → true (empty string).
- Uppercase branch → true (`"AB"`, `"WXYZ"`, `"aAbB"`).
- Else branch → true (`"a1e"`, `"hi no"`, `"a!e."`).

---

## 5. Manual black-box assessment

**Design doc**: `tests/manual_tests/claude/HumanEval_089_blackbox.md`  
**Suite**: `tests/manual_tests/claude/HumanEval_089_ManualTest.java` (18 tests)

### Equivalence classes

| ID | Class | Input | Expected |
|----|-------|-------|----------|
| V1 | Empty string | `""` | `""` |
| V2 | Single lowercase mid-alphabet | `"a"` | `"e"` |
| V3 | Single lowercase near end (wrap) | `"z"` | `"d"` |
| V4 | All lowercase no wrap | `"hi"` | `"lm"` |
| V5 | All lowercase with wrap | `"wxyz"` | `"abcd"` |
| V6 | Longer lowercase string | `"hellomyfriend"` | `"lippsqcjvmirh"` |
| V7 | Repeated chars | `"aaa"` | `"eee"` |
| V8 | Uppercase no wrap (pinned) | `"AB"` | `"EF"` |
| V9 | Uppercase wrap (pinned) | `"WXYZ"` | `"ABCD"` |
| V10 | Mixed lower + upper (pinned) | `"aAbB"` | `"eEfF"` |
| V11 | Digits pass through (pinned) | `"a1e"` | `"e1i"` |
| V12 | Spaces pass through (pinned) | `"hi no"` | `"lm rs"` |
| V13 | Punctuation pass through (pinned) | `"a!e."` | `"e!i."` |
| I1 | null input (pinned) | `null` | `""` |

### Boundary conditions

| Boundary | Just below | On boundary | Just above |
|----------|------------|-------------|------------|
| String length 0 | n/a | `""` → `""` | `"a"` → `"e"` |
| Lowercase wrap | `"v"` → `"z"` | `"w"` → `"a"` | `"x"` → `"b"` |
| Uppercase wrap | `"V"` → `"Z"` | `"W"` → `"A"` | n/a |

**Coverage on `Solution`:**

| Metric | Covered / Total |
|--------|-----------------|
| INSTRUCTION | 74 / 74 |
| BRANCH | 10 / 10 |
| LINE | 11 / 11 |
| COMPLEXITY | 7 / 7 |
| METHOD | 2 / 2 |

---

## 6. Defects found

**No code defects found.** All 44 tests (8 base + 18 improved + 18 manual) pass against
the initial generation (`HumanEval_089.java`). No refactor loop was required.

The one failure encountered (`mixedCase` wrong expected value) was a **test-authoring
error** and was corrected before finalising the improved suite.

---

## 7. Coverage roll-up

| Suite | Tests | Pass | Fail | INSTRUCTION | BRANCH | LINE |
|-------|-------|------|------|-------------|--------|------|
| Base | 8 | 8 | 0 | 51/74 (69 %) | 5/10 (50 %) | 8/11 (73 %) |
| Improved | 18 | 18 | 0 | 74/74 (100 %) | 10/10 (100 %) | 11/11 (100 %) |
| Manual | 18 | 18 | 0 | 74/74 (100 %) | 10/10 (100 %) | 11/11 (100 %) |

Coverage reports stored at: `coverage_reports/HumanEval_089/claude/{base,improved,manual}/`.
