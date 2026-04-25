# HumanEval_036 — Claude

## Generation
- Source: `generated_code/claude/HumanEval_036.java`
- Log: `llm_logs/claude/HumanEval_036_initial.md`
- Approach: iterate `i` in `[1, n)`, for each `i` divisible by 11 or 13 walk its decimal digits and increment counter on each `7`.

## Base test
- File: `tests/base_tests/claude/HumanEval_036_BaseTest.java` (JUnit 6 port of `tests/base_tests/original/HumanEval_036/Main.java`).
- Adjusted dataset harness: `tests/base_tests/adjusted/HumanEval_036/Main.java` (added `import java.util.*;`). See `tests/base_tests/adjustment_log.md`.
- Adjusted-harness run: exit 0.
- JUnit 6 run: 8/8 pass (cases mirror dataset values — `fizzBuzz(50)=0`, `78=2`, `79=3`, `100=3`, `200=6`, `4000=192`, `10000=639`, `100000=8026`).
- No code defects observed against the spec at this stage; no refactor loop triggered.

## Improved suite
- File: `tests/improved_tests/claude/HumanEval_036_ImprovedTest.java` — 24/24 pass.
- Smells addressed: assertion roulette (single-bag List<Boolean> in dataset → per-case `@Test`/`@ParameterizedTest`), magic numbers (`@DisplayName` documents intent), eager test (split into nested classes by behavioral concern), conditional logic in tests (no loops/ifs in test bodies).
- Branch targets: outer-loop entry (n<=1 vs n>1, negative n), divisor branches (only-11, only-13, both, neither — incl. 143 overlap), inner-while digit positions (zero, one, two '7' digits), monotonicity invariants across crossings of 77 and 78.

## Manual black-box
- Notes: `tests/manual_tests/claude/HumanEval_036_blackbox.md` — equivalence classes V1–V8, invalid I1–I3, boundary table.
- Tests: `tests/manual_tests/claude/HumanEval_036_ManualTest.java` — 20/20 pass.
- Pinned undefined-by-spec behaviors: negative `n` and `Integer.MIN_VALUE` both return `0` (the `for (i=1; i<n; i++)` loop never enters).

## Coverage (JaCoCo 0.8.12, `--release 21`)
- Raw outputs: `coverage_reports/HumanEval_036/claude/{base,improved,manual}/{jacoco.exec,jacoco.xml,jacoco.csv,html,junit.txt,jacoco.log}`.
- All three suites cover `Solution` at 37/37 instructions, 10/10 branches, 10/10 lines, 7/7 cyclomatic, 2/2 methods.

## Defects vs. spec
- None observed. No refactor loop triggered.
