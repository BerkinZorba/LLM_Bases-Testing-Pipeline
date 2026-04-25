# HumanEval_106 – Claude analysis

## 1. Code generation

**Prompt**: `f(int n)` — return a list of `n` elements where element at 1-based index `i`
is `factorial(i)` if `i` is even, or `sum(1..i)` if `i` is odd.

**Generated file**: `generated_code/claude/HumanEval_106.java`

The generation was correct and complete on the first attempt. No refactor loop was needed.

**Implementation notes:**
- Outer loop: `for (int i = 1; i <= n; i++)` — correctly 1-based.
- Even branch: inner loop `for (int j = 1; j <= i; j++) { factorial *= j; }` — correct iterative factorial.
- Odd branch: closed-form `int sum = i * (i + 1) / 2` — efficient and correct.
- For `n = 0` or negative `n`, the loop condition is false immediately → returns `[]`.

---

## 2. Base test results

**Suite**: `tests/base_tests/claude/HumanEval_106_BaseTest.java` (4 tests)
**Log**: `llm_logs/claude/HumanEval_106_initial.md`

| Result | Count |
|--------|-------|
| Passed | 4 / 4 |
| Failed | 0 |

**Coverage on `Solution` (JaCoCo):**

| Metric | Covered / Total |
|--------|-----------------|
| INSTRUCTION | 52 / 52 |
| BRANCH | 6 / 6 |
| LINE | 12 / 12 |
| COMPLEXITY | 5 / 5 |
| METHOD | 2 / 2 |

The base dataset achieves 100% branch coverage immediately because the test cases include
inputs that exercise all branches: odd indices (sum), even indices (factorial), the inner
factorial loop running multiple iterations, and the outer loop terminating normally.

---

## 3. Test smell analysis

| Smell | Base test | Fix in improved suite |
|-------|-----------|----------------------|
| Assertion roulette | Original harness wraps 4 asserts in one `List<Boolean>` | Split into 4 named `@Test` methods |
| No n=0 case | Empty-list path not tested | Added `f0_returnsEmptyList` |
| No n=2 minimal case | Smallest case combining both branches not tested | Added `f2_minimalBothBranches` |
| No element-level isolation | Only full-list equality; hard to pinpoint which element fails | Added per-element parameterized suite for i=1..8 |
| No size invariant test | List size is implicit only | Added `listSizeEqualsN` loop test |

---

## 4. Improved test suite

**Suite**: `tests/improved_tests/claude/HumanEval_106_ImprovedTest.java` (17 tests)

| Result | Count |
|--------|-------|
| Passed | 17 / 17 |
| Failed | 0 |

**Coverage on `Solution`:** 52/52 instruction, 6/6 branch, 12/12 line (100% across all metrics).

---

## 5. Manual black-box assessment

**Design doc**: `tests/manual_tests/claude/HumanEval_106_blackbox.md`
**Suite**: `tests/manual_tests/claude/HumanEval_106_ManualTest.java` (14 tests)

### Equivalence classes

| ID | Class | Input | Expected |
|----|-------|-------|----------|
| V1 | n = 0 (empty) | `0` | `[]` |
| V2 | n = 1 (single odd) | `1` | `[1]` |
| V3 | n = 2 (odd + even) | `2` | `[1, 2]` |
| V4 | n = 3 (ends on odd) | `3` | `[1, 2, 6]` |
| V5 | n = 4 (ends on even) | `4` | `[1, 2, 6, 24]` |
| V6 | n = 5 (spec example) | `5` | `[1, 2, 6, 24, 15]` |
| V7 | n = 7 (dataset) | `7` | `[1, 2, 6, 24, 15, 720, 28]` |
| V8 | n = 8 (ends on even, 8!) | `8` | `[1, 2, 6, 24, 15, 720, 28, 40320]` |
| I1 | n < 0 (pinned) | `-1` | `[]` |

### Key boundary values

| i | Rule | Expected element |
|---|------|-----------------|
| 1 | odd sum | 1 |
| 2 | even factorial | 2 |
| 3 | odd sum | 6 |
| 4 | even factorial | 24 |
| 5 | odd sum | 15 |
| 6 | even factorial | 720 |
| 7 | odd sum | 28 |
| 8 | even factorial | 40320 |

**Coverage on `Solution`:** 52/52 instruction, 6/6 branch, 12/12 line.

---

## 6. Defects found

**No code defects found.** All 35 tests (4 base + 17 improved + 14 manual) pass against the
initial generation. No refactor loop was required.

---

## 7. Coverage roll-up

| Suite | Tests | Pass | Fail | INSTRUCTION | BRANCH | LINE |
|-------|-------|------|------|-------------|--------|------|
| Base | 4 | 4 | 0 | 52/52 (100%) | 6/6 (100%) | 12/12 (100%) |
| Improved | 17 | 17 | 0 | 52/52 (100%) | 6/6 (100%) | 12/12 (100%) |
| Manual | 14 | 14 | 0 | 52/52 (100%) | 6/6 (100%) | 12/12 (100%) |

Coverage reports stored at: `coverage_reports/HumanEval_106/claude/{base,improved,manual}/`.
