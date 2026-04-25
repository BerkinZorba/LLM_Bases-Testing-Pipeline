# HumanEval_081 — Claude

## Generation
- Source: `generated_code/claude/HumanEval_081.java`
- Log: `llm_logs/claude/HumanEval_081_initial.md`
- Approach: `numericalLetterGrade(List<Double>)` builds an `ArrayList<String>`
  and walks the input in order. Each `gpa` is mapped via a 13-arm
  if/else cascade that mirrors the prompt's table verbatim: a single
  exact-equality arm `gpa == 4.0 -> "A+"`, eleven strict-`>` arms for
  the mid bands (`> 3.7`, `> 3.3`, ... `> 0.0`), and a final `else` that
  catches `0.0` and produces `"E"`. Strict `>` is required by the
  dataset example (`3.0 -> B`, `1.7 -> C-`, `2.0 -> C`).

## Base test
- File: `tests/base_tests/claude/HumanEval_081_BaseTest.java` (JUnit 6 port
  of `tests/base_tests/original/HumanEval_081/Main.java`).
- Adjusted dataset harness:
  `tests/base_tests/adjusted/HumanEval_081/Main.java` (added
  `import java.util.*;` so the dataset `Main.java`, which references
  `List`, `ArrayList`, and `Arrays`, compiles standalone). See
  `tests/base_tests/adjustment_log.md`.
- Adjusted-harness run: exit `0` (`ADJUSTED_OK`).
- JUnit 6 run: 6/6 pass — the dataset 5-element example, the three
  singletons (`[1.2] -> [D+]`, `[0.5] -> [D-]`, `[0.0] -> [E]`), the
  five-element low/mid mix (`[1.0, 0.3, 1.5, 2.8, 3.3] -> [D, D-, C-, B, B+]`),
  and the `[0.0, 0.7] -> [E, D-]` two-element case.
- No code defects observed against the spec at this stage; no refactor
  loop triggered.

## Improved suite
- File: `tests/improved_tests/claude/HumanEval_081_ImprovedTest.java` —
  23/23 pass.
- Smells addressed:
  - assertion roulette (the dataset bundled six booleans into a
    `List<Boolean> correct` and asserted `!contains(false)` →
    one `@Test` per dataset row with `@DisplayName`),
  - magic numbers (each `(input, expected)` pair is named after its
    semantic role: dataset row, band edge, structural property,
    every-band batch),
  - eager test (split into nested classes by behavioral concern:
    dataset, band-edge branch coverage, structural properties,
    every-band reachability),
  - conditional logic in tests (no `if` in test bodies; per-input
    `assertEquals` replaces looped boolean assertions).
- Behavioral targets:
  - exact-equality top arm (`4.0 -> A+`) and the strict-`>` semantics
    of every mid band (12 edges: at-edge stays in lower band, +0.01
    promotes to upper band),
  - `else` arm catching `0.0` (`E`) and the `> 0.0` arm catching `0.01`
    (`D-`),
  - per-index ordering (no sort/dedupe), output-length parity, and
    handling of the empty list.

## Manual black-box
- Notes: `tests/manual_tests/claude/HumanEval_081_blackbox.md` —
  equivalence classes V1..V16 cover one mid-band representative per
  letter (V1..V13), the dataset 5-element call (V14), order
  preservation under repeats (V15), and an every-band 13-element batch
  (V16). Boundary table B1..B12 covers all 12 numeric thresholds
  (top-equality `4.0`, the eleven strict-`>` cuts, and the bottom
  `0.0`), each with "just below", "at edge", and "just above" rows
  where applicable. Invalid / undefined-by-spec partitions U1..U4 are
  pinned: empty list, GPA above 4.0, negative GPA, and the IEEE-754
  signed-zero quirk where `-0.0 == 0.0` is true so `-0.0` still maps to
  `E`.
- Tests: `tests/manual_tests/claude/HumanEval_081_ManualTest.java` —
  32/32 pass.
- Pinned undefined-by-spec behavior:
  - `[]` -> `[]` (loop never executes),
  - `[5.0]` -> `[A]` because `5.0 > 3.7` is the first true arm,
  - `[-0.5]` -> `[E]` because all strict-`>` arms fail and the `else`
    catches it,
  - `[-0.0]` -> `[E]` because Java `==` treats `-0.0` and `0.0` as
    equal and the top arm short-circuits.

## Coverage (JaCoCo 0.8.12, `--release 21`)
- Raw outputs:
  `coverage_reports/HumanEval_081/claude/{base,improved,manual}/{jacoco.exec,jacoco.xml,jacoco.csv,html,junit.txt,jacoco.log}`.
- Base suite: 104/113 instructions, 23/26 branches, 29/32 lines,
  12/15 cyclomatic, 2/2 methods. The three missing branches are the
  `> 3.7` (`A`), `> 2.3` (`B-`), and `> 2.0` (`C+`) arms — none of the
  dataset rows place a GPA strictly inside those bands.
- Improved suite: 113/113 instructions, 26/26 branches, 32/32 lines,
  15/15 cyclomatic, 2/2 methods. The band-edge tests promote `3.71 -> A`,
  `2.31 -> B-`, and `2.01 -> C+`, which closes the gap.
- Manual suite: 113/113 instructions, 26/26 branches, 32/32 lines,
  15/15 cyclomatic, 2/2 methods. The V2/V6/V7 mid-band representatives
  (`3.8 -> A`, `2.4 -> B-`, `2.1 -> C+`) and the every-band batch
  (V16) collectively reach the same arms.

## Defects vs. spec
- None observed. The exact-equality top arm matches the table's `4.0`
  row, every strict-`>` mid arm is consistent with the dataset
  examples, and the trailing `else` correctly catches `0.0 -> E`. No
  refactor loop triggered.
