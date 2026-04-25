# HumanEval_057 — Claude

## Generation
- Source: `generated_code/claude/HumanEval_057.java`
- Log: `llm_logs/claude/HumanEval_057_initial.md`
- Approach: single pass over adjacent pairs maintaining two boolean flags
  (`nonDecreasing`, `nonIncreasing`); clear `nonDecreasing` on a strict drop and
  `nonIncreasing` on a strict rise; return `nonDecreasing || nonIncreasing`.
  Equal adjacent elements clear neither flag, so plateaus and all-equal lists
  are reported as monotonic.

## Base test
- File: `tests/base_tests/claude/HumanEval_057_BaseTest.java` (JUnit 6 port of `tests/base_tests/original/HumanEval_057/Main.java`).
- Adjusted dataset harness: `tests/base_tests/adjusted/HumanEval_057/Main.java` (added `import java.util.*;`). See `tests/base_tests/adjustment_log.md`.
- Adjusted-harness run: exit 0.
- JUnit 6 run: 8/8 pass — cases mirror dataset values
  (`[1,2,4,10]→true`, `[1,2,4,20]→true`, `[1,20,4,10]→false`, `[4,1,0,-10]→true`,
   `[4,1,1,0]→true`, `[1,2,3,2,5,60]→false`, `[1,2,3,4,5,60]→true`, `[9,9,9,9]→true`).
- No code defects observed against the spec at this stage; no refactor loop triggered.

## Improved suite
- File: `tests/improved_tests/claude/HumanEval_057_ImprovedTest.java` — 21/21 pass.
- Smells addressed: assertion roulette (single-bag `List<Boolean>` in dataset → per-case `@Test` with descriptive `@DisplayName`), magic numbers (each list literal is paired with a behavioral-intent name), eager test (split into nested classes by behavioral concern: degenerate sizes, strictly monotonic, plateaus, non-monotonic, dataset parity, invariants), conditional logic in tests (no loops/ifs in test bodies).
- Branch targets: outer-loop entry (size 0/1 vs >=2), first inner if (`<` true on strict drop, false on equal/up), second inner if (`>` true on strict rise, false on equal/down), final OR (left-only true / right-only true / both true via all-equal / both false via spike or dip), invariants (reversal, constant offset, two-element universality).

## Manual black-box
- Notes: `tests/manual_tests/claude/HumanEval_057_blackbox.md` — equivalence classes V1–V12, invalid I1–I2 (null list / null element, undefined-by-spec, not exercised), boundary table.
- Tests: `tests/manual_tests/claude/HumanEval_057_ManualTest.java` — 18/18 pass.
- Pinned undefined-by-spec behaviors: empty and singleton lists return `true` (loop never enters); extreme `Integer.MIN_VALUE`/`Integer.MAX_VALUE` pairs are handled correctly because the implementation uses `<`/`>` directly with no subtraction (no overflow).

## Coverage (JaCoCo 0.8.x, `--release 21`)
- Raw outputs: `coverage_reports/HumanEval_057/claude/{base,improved,manual}/{jacoco.exec,jacoco.xml,jacoco.csv,html,junit.txt,jacoco.log}`.
- All three suites cover `Solution` at 53/53 instructions, 10/10 branches, 9/9 lines, 7/7 cyclomatic, 2/2 methods.

## Defects vs. spec
- None observed. No refactor loop triggered.
