# HumanEval_100 — Claude

## Generation
- Source: `generated_code/claude/HumanEval_100.java`
- Log: `llm_logs/claude/HumanEval_100_initial.md`
- Approach: arithmetic progression `pile[i] = n + 2*i` for `i ∈ [0, n)`,
  built with a single `for` loop into an `ArrayList<Integer>`. The
  spec's "next odd / next even" rule collapses to "+2 per level"
  because the parity of `n` is preserved by adding 2; no explicit
  parity branch is needed.

## Base test
- File: `tests/base_tests/claude/HumanEval_100_BaseTest.java` (JUnit 6 port of
  `tests/base_tests/original/HumanEval_100/Main.java`).
- Adjusted dataset harness: `tests/base_tests/adjusted/HumanEval_100/Main.java`
  (added `import java.util.*;`). See `tests/base_tests/adjustment_log.md`.
- Adjusted-harness run: exit 0 (`ADJUSTED_OK`).
- JUnit 6 run: 5/5 pass — dataset cases `n=3, 4, 5, 6, 8` produce the
  expected piles.
- No code defects observed against the spec at this stage; no refactor loop triggered.

## Improved suite
- File: `tests/improved_tests/claude/HumanEval_100_ImprovedTest.java` — 15/15 pass.
- Smells addressed:
  - assertion roulette (dataset bundled five booleans into a single
    `List<Boolean> correct` then asserted `!contains(false)` →
    per-case `@Test` with descriptive `@DisplayName`),
  - magic numbers (each input `n` is paired with an intent name:
    odd-start, even-start, boundary, large-n),
  - eager test (split into nested classes by behavioral concern: dataset
    odd-start, dataset even-start, boundary, structural invariants,
    branch coverage),
  - conditional logic in tests (no `if` in test bodies; `assertAll`
    is used for invariant batches, and the closed-form check unrolls
    every index instead of looping with assertions inside).
- Behavioral targets:
  - `for`-loop guard taken (n ≥ 1) and not-taken paths,
  - `size(pile) == n` invariant under varying `n`,
  - `pile[0] == n`,
  - `pile[n-1] == 3n - 2`,
  - consecutive-element step is exactly +2 (arithmetic-progression invariant),
  - parity preservation (odd `n` → all odd; even `n` → all even),
  - closed form `pile[i] == n + 2*i` spot-checked at every index for `n=9`.

## Manual black-box
- Notes: `tests/manual_tests/claude/HumanEval_100_blackbox.md` — equivalence
  classes V1–V11 covering smallest-valid `n`, smallest even `n`, dataset
  odd/even cases, mid-range non-dataset values, and larger `n` values
  (50, 100). Boundary table covers `n ∈ {0, 1, 2, 100}`. Invalid /
  undefined-by-spec partitions U1 (`n=0`) and U2 (`n=-3`) are pinned
  as observed behavior (loop never enters → empty list); these are
  documented as observed-behavior pins rather than spec contracts.
- Tests: `tests/manual_tests/claude/HumanEval_100_ManualTest.java` — 19/19 pass.
- Pinned undefined-by-spec behavior: `n=0` and `n<0` return an empty list
  because the `for (int i = 0; i < n; i++)` guard is false on entry.

## Coverage (JaCoCo 0.8.12, `--release 21`)
- Raw outputs: `coverage_reports/HumanEval_100/claude/{base,improved,manual}/{jacoco.exec,jacoco.xml,jacoco.csv,html,junit.txt,jacoco.log}`.
- All three suites cover `Solution` at 25/25 instructions, 2/2 branches,
  5/5 lines, 3/3 cyclomatic, 2/2 methods. The two branches are the
  for-loop guard's taken / not-taken transitions; both are exercised
  in every suite by the dataset cases plus the boundary cases.

## Defects vs. spec
- None observed. No refactor loop triggered.
