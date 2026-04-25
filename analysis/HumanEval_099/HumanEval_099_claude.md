# HumanEval_099 – Claude analysis

## 1. Code generation

**Prompt**: `closestInteger(String value)` — parse the string as a number and return the
closest integer, rounding away from zero when equidistant (x.5 cases).

**Generated file**: `generated_code/claude/HumanEval_099.java`

The generation was correct and complete on the first attempt. No refactor loop was needed.

**Implementation notes:**
- Parses via `Double.parseDouble(value)` — throws `NumberFormatException` for bad strings,
  `NullPointerException` for null (both spec-undefined; pinned in manual suite).
- Halfway detection: `num - Math.floor(num) == 0.5` — works correctly for both positive
  and negative numbers because `Math.floor` rounds toward negative infinity.
- Positive halfway: `Math.ceil(num)` → rounds up (away from zero for positive numbers).
- Negative halfway: `Math.floor(num)` → rounds down (away from zero for negative numbers).
- Non-halfway: delegates to `Math.round`, which uses "round half up" (i.e., 0.5 rounds up),
  but the halfway branch intercepts those cases first so there is no conflict.

---

## 2. Base test adjustment

**Original harness problem**: `tests/base_tests/original/HumanEval_099/Main.java` calls
`s.countUpper(...)`, which is an unrelated method name from a different HumanEval problem.
This is a dataset copy-paste error.

**Fix applied** (test-side only, documented in `analysis/base_test_adjustments.md`):
Corrected five call sites from `s.countUpper(...)` to `s.closestInteger(...)`.
All input strings and expected integer values are identical to the original harness.

---

## 3. Base test results

**Suite**: `tests/base_tests/claude/HumanEval_099_BaseTest.java` (5 tests)
**Log**: `llm_logs/claude/HumanEval_099_initial.md`

| Result | Count |
|--------|-------|
| Passed | 5 / 5 |
| Failed | 0 |

**Coverage on `Solution` (JaCoCo):**

| Metric | Covered / Total |
|--------|-----------------|
| INSTRUCTION | 28 / 28 |
| BRANCH | 4 / 4 |
| LINE | 5 / 5 |
| COMPLEXITY | 4 / 4 |
| METHOD | 2 / 2 |

The base test dataset happens to include both a positive halfway case (`"14.5"`) and a
negative halfway case (`"-15.5"`), so all four branches are covered immediately.

---

## 4. Test smell analysis

| Smell | Base test | Fix in improved suite |
|-------|-----------|----------------------|
| Assertion roulette | Original harness wraps 5 asserts in one `List<Boolean>` | Split into 5 named `@Test` methods |
| Missing prompt-note case | `-14.5 → -15` is in the prompt note but not the dataset | Added explicitly |
| No invalid-input pinning | `null`, empty, non-numeric not tested | Added `assertThrows` cases |
| Narrow value range | Only one positive/negative halfway each, one decimal | Added full parameterized grid of halfway, non-halfway, and boundary values |

---

## 5. Improved test suite

**Suite**: `tests/improved_tests/claude/HumanEval_099_ImprovedTest.java` (36 tests)

| Result | Count |
|--------|-------|
| Passed | 36 / 36 |
| Failed | 0 |

**Coverage on `Solution`:** 28/28 instruction, 4/4 branch, 5/5 line (unchanged from base; base already at 100%).

---

## 6. Manual black-box assessment

**Design doc**: `tests/manual_tests/claude/HumanEval_099_blackbox.md`
**Suite**: `tests/manual_tests/claude/HumanEval_099_ManualTest.java` (26 tests)

### Equivalence classes

| ID | Class | Input | Expected |
|----|-------|-------|----------|
| V1 | Exact integer, positive | `"10"` | `10` |
| V2 | Exact integer, zero | `"0"` | `0` |
| V3 | Exact integer, negative | `"-3"` | `-3` |
| V4 | Fractional < 0.5, positive | `"15.3"` | `15` |
| V5 | Fractional > 0.5, positive | `"1.6"` | `2` |
| V6 | Fractional < 0.5, negative | `"-1.3"` | `-1` |
| V7 | Fractional > 0.5, negative | `"-1.7"` | `-2` |
| V8 | Exactly 0.5, positive | `"14.5"` | `15` |
| V9 | Exactly 0.5, negative | `"-14.5"` | `-15` |
| V10 | Halfway near zero, positive | `"0.5"` | `1` |
| V11 | Halfway near zero, negative | `"-0.5"` | `-1` |
| V12 | Large positive | `"999"` | `999` |
| V13 | Decimal `x.0` | `"5.0"` | `5` |
| I1 | null (pinned) | `null` | `NullPointerException` |
| I2 | Empty string (pinned) | `""` | `NumberFormatException` |
| I3 | Non-numeric (pinned) | `"abc"` | `NumberFormatException` |

### Boundary conditions (around the x.5 decision point)

| Input | Expected | Notes |
|-------|----------|-------|
| `"14.4"` | `14` | just below halfway → rounds toward zero |
| `"14.5"` | `15` | exactly halfway, positive → away from zero |
| `"14.6"` | `15` | just above halfway → rounds up |
| `"-14.4"` | `-14` | just below halfway, negative → toward zero |
| `"-14.5"` | `-15` | exactly halfway, negative → away from zero |
| `"-14.6"` | `-15` | just above halfway, negative → rounds away |

**Coverage on `Solution`:** 28/28 instruction, 4/4 branch, 5/5 line.

---

## 7. Defects found

**No code defects found.** All 67 tests (5 base + 36 improved + 26 manual) pass against the
initial generation. No refactor loop was required.

---

## 8. Coverage roll-up

| Suite | Tests | Pass | Fail | INSTRUCTION | BRANCH | LINE |
|-------|-------|------|------|-------------|--------|------|
| Base | 5 | 5 | 0 | 28/28 (100%) | 4/4 (100%) | 5/5 (100%) |
| Improved | 36 | 36 | 0 | 28/28 (100%) | 4/4 (100%) | 5/5 (100%) |
| Manual | 26 | 26 | 0 | 28/28 (100%) | 4/4 (100%) | 5/5 (100%) |

Coverage reports stored at: `coverage_reports/HumanEval_099/claude/{base,improved,manual}/`.
