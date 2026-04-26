## 📄 PROGRESS.md

### 📌 Project: LLM-Based Code and Test Generation

Course: BLG 475E - Software Quality and Testing

## 1. Project Initialization

- Created GitHub repository
- Defined project scope based on assignment requirements:
  - Code generation using LLMs
  - Unit and integration test generation
  - Coverage and quality analysis

- Reviewed project document and extracted key requirements

## 2. LLM Selection

### Goal

Select two publicly available LLMs suitable for:

- Java code generation
- Unit test generation (JUnit)

### Approach

- Decided to select:
  - One **general-purpose high-performance model**
  - One **code-specialized model**

### Rationale

- Enables meaningful comparison in:
  - Code correctness
  - Test quality
  - Handling of edge cases
  - Behavior in iterative workflows

### Candidate Models

- 
- 

## 3. Approach Design

### Two Approaches Considered

#### Semi-Agentic Approach

- Manual step-by-step process:
  - Generate code
  - Analyze output
  - Create next prompt manually

- Provides:
  - Higher control
  - Better interpretability

#### Agentic Approach

- Automated loop:
  - Code → Test → Feedback → Fix

- Provides:
  - Faster iteration
  - Reduced manual intervention

### Plan

- Use both approaches for comparison
- Evaluate strengths and limitations of each

## 4. Dataset Familiarization

- Reviewed HumanEval Java dataset
- Understood structure:
  - Problem descriptions
  - Base test cases

### Observations

- Problems vary in:
  - Complexity
  - Required logic depth
  - Edge-case sensitivity

## 5. Prompt Selection Strategy (Planned)

### Goal

Select 30 prompts with balanced difficulty

### Planned Distribution

- 10 Easy
- 10 Moderate
- 10 Hard

### Selection Criteria

- Diversity in problem types:
  - String manipulation
  - Arrays / Lists
  - Mathematical logic
  - Conditional logic

- Inclusion of edge-case-heavy problems
- Avoid redundancy in similar problem types

### Difficulty Classification Basis

- Number of logical steps
- Algorithmic complexity
- Presence of edge cases

## 6. Development Workflow (Planned)

- Each step will be committed separately with descriptive messages
- LLM interactions will be logged:
  - Prompt
  - Response
  - Modifications and reasoning

- Testing and improvements will follow iterative cycles

## 7. Next Steps

- Finalize LLM selection
- Select and categorize 30 prompts
- Begin code generation phase
- Establish prompt templates for consistency

## Notes

- All decisions are made to align with evaluation criteria:
  - Coverage
  - Correctness
  - Test effectiveness

- Progress will be updated after each major step and commit

## 2026-04-25

- Started Phase 1 implementation for `HumanEval_016` under the `codex` workflow.
- Read the prompt text from `prompts/selected_prompts.md` and saved the initial Java solution to `generated_code/codex/HumanEval_016.java`.
- Logged the initial interaction in `llm_logs/codex/HumanEval_016_initial.md`.
- Executed the adjusted dataset base harness for `HumanEval_016` against the Codex solution using Homebrew OpenJDK; the run completed successfully with exit status 0.
- Added Codex-side base, improved, and manual test artifacts for `HumanEval_016`.
- Corrected a methodology error: the initial Codex improved/manual suites had mirrored the Claude-side design and were replaced with independent Codex-authored versions plus separate Codex test-generation logs.
- Executed JUnit 6 Codex suites with `.tools/junit-platform-console-standalone.jar`: base 5/5, improved 17/17, manual 17/17.
- Exported JaCoCo coverage to `coverage_reports/HumanEval_016/codex/{base,improved,manual}/`; `Solution` reached 33/33 instructions and 2/2 branches in all three suites.

## 8. Phase 1 prompt runs

### 2026-04-25 — HumanEval_036 (Codex)

- Started Phase 1 implementation for `HumanEval_036` under the `codex` workflow.
- Read the prompt text from `prompts/selected_prompts.md` and saved the initial Java solution to `generated_code/codex/HumanEval_036.java`.
- Logged the initial interaction in `llm_logs/codex/HumanEval_036_initial.md`.
- Added Codex-side base, improved, and manual test artifacts for `HumanEval_036`, plus separate Codex test-generation logs for improved/manual work.
- Executed the adjusted dataset base harness for `HumanEval_036` with Homebrew OpenJDK; compile succeeded and `Main` exited with status 0.
- JUnit 6 Codex suites with `.tools/junit-platform-console-standalone.jar`: base 8/8, improved 25/25, manual 14/14.
- Exported JaCoCo coverage to `coverage_reports/HumanEval_036/codex/{base,improved,manual}/`; `Solution` reached 37/37 instructions, 10/10 branches, 10/10 lines, 7/7 complexity, and 2/2 methods in all three suites.
- Added per-prompt summary findings to `analysis/HumanEval_036_codex.md`.
- No defects against the prompt specification were observed, so no refactor loop was triggered. HumanEval_036 (Codex side) is complete for Phase 1.

### 2026-04-25 — HumanEval_016 (Claude)

- Generated `generated_code/claude/HumanEval_016.java` from the verbatim Java/16 prompt; logged at `llm_logs/claude/HumanEval_016_initial.md`.
- Tooling bootstrap: `.tools/junit-platform-console-standalone.jar` (JUnit 6.0.0) and `.tools/jacoco/` (JaCoCo 0.8.12) added; `.gitignore` updated to exclude both. Sources compiled with `--release 21` (JaCoCo 0.8.12 does not support Java 25 bytecode).
- Base test: dataset `Main.java` needed `import java.util.*;` to compile standalone — adjusted copy at `tests/base_tests/adjusted/HumanEval_016/Main.java`, documented in `tests/base_tests/adjustment_log.md`. Generated code unchanged. Adjusted harness exits 0.
- JUnit 6 base port: `tests/base_tests/claude/HumanEval_016_BaseTest.java` — 5/5 pass.
- Improved JUnit 6 suite: `tests/improved_tests/claude/HumanEval_016_ImprovedTest.java` — 18/18 pass. Targets test smells (assertion roulette, magic numbers, eager test) and adds non-ASCII / surrogate-pair / Turkish-I edge cases.
- Manual black-box: `tests/manual_tests/claude/HumanEval_016_blackbox.md` (V1–V11, I1–I3, boundaries) + `HumanEval_016_ManualTest.java` — 21/21 pass.
- Coverage (all three suites): 33/33 instr, 2/2 branch, 5/5 line, 3/3 cyclomatic, 2/2 method. Raw outputs in `coverage_reports/HumanEval_016/claude/{base,improved,manual}/`.
- No defects against the spec → no refactor loop. Three undefined-by-spec behaviors pinned: NPE on `null`, surrogate pair counted as 2 `char`s, Turkish dotted/dotless I under root-locale folding.
- Per-prompt summary: `analysis/HumanEval_016_claude.md`. Codex side of HumanEval_016 still pending.

### 2026-04-25 — HumanEval_036 (Claude)

- Started Phase 1 implementation for `HumanEval_036` (Claude side).
- Generated `generated_code/claude/HumanEval_036.java` from the verbatim Java/36 prompt (count digit-7 occurrences in integers <n divisible by 11 or 13).
- Logged the initial interaction in `llm_logs/claude/HumanEval_036_initial.md`.
- Next: port base test, run with JUnit 6, then improved + manual suites and coverage.
- Adjusted dataset `Main.java` at `tests/base_tests/adjusted/HumanEval_036/Main.java` (added `import java.util.*;`); logged in `tests/base_tests/adjustment_log.md`. Adjusted harness exits 0.
- JUnit 6 base port at `tests/base_tests/claude/HumanEval_036_BaseTest.java` — 8/8 pass against the verbatim Claude solution.
- Per-prompt analysis seeded at `analysis/HumanEval_036_claude.md`.
- Improved JUnit 6 suite at `tests/improved_tests/claude/HumanEval_036_ImprovedTest.java` — 24/24 pass. Targets test smells (assertion roulette, magic numbers, eager test) and branch coverage of divisor `||` overlap and inner-while digit positions.
- Manual black-box notes + suite at `tests/manual_tests/claude/HumanEval_036_blackbox.md` and `HumanEval_036_ManualTest.java` — 20/20 pass. Pinned that negative `n` and `Integer.MIN_VALUE` return 0 (undefined by spec).
- JaCoCo coverage exported to `coverage_reports/HumanEval_036/claude/{base,improved,manual}/`. All three suites: 37/37 instr, 10/10 branch, 10/10 line, 7/7 CC, 2/2 method on `Solution`.
- No defects against spec → no refactor loop triggered. HumanEval_036 (Claude side) complete; Codex side now also complete.

### 2026-04-25 — HumanEval_007 (Codex)

- Logged existing codex generation at `llm_logs/codex/HumanEval_007_initial.md`; generated code remains `generated_code/codex/HumanEval_007.java`.
- Adjusted dataset `Main.java` at `tests/base_tests/adjusted/HumanEval_007/Main.java` by adding `import java.util.*;`; assertion logic and generated code unchanged. Adjusted harness compiles with `javac --release 21` and exits 0.
- JUnit 6 base port at `tests/base_tests/codex/HumanEval_007_BaseTest.java` — 4/4 pass.
- Improved suite at `tests/improved_tests/codex/HumanEval_007_ImprovedTest.java` — 11/11 pass. Targets assertion roulette, magic expected values, eager test shape, and missing edge cases.
- Manual black-box notes + suite at `tests/manual_tests/codex/HumanEval_007_blackbox.md` and `HumanEval_007_ManualTest.java` — 18/18 pass. Pinned undefined-by-spec NPE behavior for null list, null substring, and null list element.
- JaCoCo coverage exported to `coverage_reports/HumanEval_007/codex/{base,improved,manual}/`. All three suites: 28/28 instr, 4/4 branch, 7/7 line, 4/4 CC, 2/2 method on `Solution`.
- Per-prompt analysis added at `analysis/HumanEval_007/HumanEval_007_codex.md`. No defects against the prompt spec; no refactor loop triggered.

### 2026-04-25 — HumanEval_007 (Claude)

- Logged initial generation in `llm_logs/claude/HumanEval_007_initial.md`; generated code is `generated_code/claude/HumanEval_007.java` (ArrayList + enhanced for-loop + `String.contains`).
- Adjusted dataset `Main.java` already present at `tests/base_tests/adjusted/HumanEval_007/Main.java` (adds `import java.util.*;`). Generated code was not modified.
- JUnit 6 base port at `tests/base_tests/claude/HumanEval_007_BaseTest.java` — 4/4 pass against the verbatim Claude solution.
- Improved suite at `tests/improved_tests/claude/HumanEval_007_ImprovedTest.java` — 11/11 pass. Targets assertion roulette, magic expected values, eager test shape, and missing edge cases (order, duplicates, case-sensitivity, empty-substring, result identity, state isolation).
- Manual black-box notes + suite at `tests/manual_tests/claude/HumanEval_007_blackbox.md` and `HumanEval_007_ManualTest.java` — 18/18 pass. Pinned undefined-by-spec NPE behavior for null list, null substring, and null list element.
- JaCoCo coverage exported to `coverage_reports/HumanEval_007/claude/{base,improved,manual}/`. All three suites: 28/28 instr, 4/4 branch, 7/7 line, 4/4 CC, 2/2 method on `Solution`.
- Per-prompt analysis added at `analysis/HumanEval_007/HumanEval_007_claude.md`. No defects against the prompt spec; no refactor loop triggered. Both models now complete for HumanEval_007.

### 2026-04-25 — HumanEval_018 (Claude)

- Logged initial generation in `llm_logs/claude/HumanEval_018_initial.md`; generated code is `generated_code/claude/HumanEval_018.java` (guard clause + for-loop using `String.startsWith` for overlapping-count).
- Adjusted dataset `Main.java` at `tests/base_tests/adjusted/HumanEval_018/Main.java` (added `import java.util.*;` and `import java.lang.*;`); assertion logic and generated code unchanged. Adjusted harness compiles with `javac --release 21` and exits 0.
- JUnit 6 base port at `tests/base_tests/claude/HumanEval_018_BaseTest.java` — 4/4 pass.
- Base coverage on `Solution`: 37/37 instr, 8/10 branch, 8/8 line, 5/7 CC, 2/2 method. Two guard-condition true branches (`substring.isEmpty()` and `substring.length() > string.length()`) not exercised by the base suite.
- Improved suite at `tests/improved_tests/claude/HumanEval_018_ImprovedTest.java` — 16/16 pass. Closes the two missing guard branches; adds prompt overlap examples, no-match, single-char, prefix/suffix, and non-overlapping-multiple cases.
- Manual black-box notes + suite at `tests/manual_tests/claude/HumanEval_018_blackbox.md` and `HumanEval_018_ManualTest.java` — 22/22 pass. Pinned undefined-by-spec NPE for null string and null substring.
- JaCoCo coverage exported to `coverage_reports/HumanEval_018/claude/{base,improved,manual}/`. Improved and manual suites: 37/37 instr, 10/10 branch, 8/8 line, 7/7 CC, 2/2 method on `Solution`.
- No defects against spec → no refactor loop triggered. Per-prompt summary: `analysis/HumanEval_018_claude.md`.

### 2026-04-25 — HumanEval_020 (Claude)

- Logged initial generation in `llm_logs/claude/HumanEval_020_initial.md`; generated code is `generated_code/claude/HumanEval_020.java` (sort + adjacent-pair scan using `Collections.sort` and `String.startsWith`-style diff check).
- Adjusted dataset `Main.java` at `tests/base_tests/adjusted/HumanEval_020/Main.java` (added `import java.util.*;` and `import java.lang.*;`). Adjusted harness compiles with `javac --release 21` and exits 0.
- JUnit 6 base port at `tests/base_tests/claude/HumanEval_020_BaseTest.java` — 5/5 pass.
- Base coverage on `Solution`: 74/74 instr, 4/4 branch, 12/12 line, 4/4 CC, 2/2 method — full coverage from the dataset suite alone.
- Improved suite at `tests/improved_tests/claude/HumanEval_020_ImprovedTest.java` — 16/16 pass. Adds 2-element boundary, pair-position coverage, negative/mixed-sign numbers, tie-breaking, and input-list immutability.
- Manual black-box notes + suite at `tests/manual_tests/claude/HumanEval_020_blackbox.md` and `HumanEval_020_ManualTest.java` — 19/19 pass. Pinned null list as NPE (undefined by spec).
- JaCoCo coverage exported to `coverage_reports/HumanEval_020/claude/{base,improved,manual}/`. All three suites: 74/74 instr, 4/4 branch, 12/12 line, 4/4 CC, 2/2 method on `Solution`.
- No defects against spec → no refactor loop triggered. Per-prompt summary: `analysis/HumanEval_020_claude.md`.

### 2026-04-25 — HumanEval_040 (Claude)

- Initial generation (`generated_code/claude/HumanEval_040.java`) was **truncated** — file ends mid-method at `while (left < right)` with no loop body or closing braces; `javac` reports `reached end of file while parsing`. Logged in `llm_logs/claude/HumanEval_040_initial.md`.
- Refactor loop triggered (iteration 1): complete two-pointer implementation generated and saved as `generated_code/claude/HumanEval_040_v2.java`; logged in `llm_logs/claude/HumanEval_040_refactor_01.md`.
- Adjusted dataset `Main.java` at `tests/base_tests/adjusted/HumanEval_040/Main.java` (added `import java.util.*;` and `import java.lang.*;`). Adjusted harness with v2 exits 0.
- JUnit 6 base port at `tests/base_tests/claude/HumanEval_040_BaseTest.java` — 9/9 pass against v2.
- Base coverage on `Solution` (v2): 68/68 instr, 10/10 branch, 17/17 line, 7/7 CC, 2/2 method — full coverage from the dataset suite.
- Improved suite at `tests/improved_tests/claude/HumanEval_040_ImprovedTest.java` — 21/21 pass. Adds empty/two-element guard branches, three-element boundary, all-positive/duplicate/immutability cases.
- Manual black-box notes + suite at `tests/manual_tests/claude/HumanEval_040_blackbox.md` and `HumanEval_040_ManualTest.java` — 21/21 pass. Pinned null list as NPE.
- JaCoCo coverage exported to `coverage_reports/HumanEval_040/claude/{base,improved,manual}/`. All three suites (run against v2): 68/68 instr, 10/10 branch, 17/17 line, 7/7 CC, 2/2 method.
- Per-prompt summary: `analysis/HumanEval_040_claude.md`.

### 2026-04-25 — HumanEval_019 (Claude)

- Logged initial generation in `llm_logs/claude/HumanEval_019_initial.md`; generated code is `generated_code/claude/HumanEval_019.java` (null/empty guard + HashMap word-to-int + `Arrays.sort` + `Comparator.comparingInt`). Minor dead code: `numToWord` array declared but unused.
- Adjusted dataset `Main.java` at `tests/base_tests/adjusted/HumanEval_019/Main.java` (added `import java.util.*;` and `import java.lang.*;`). Adjusted harness exits 0.
- JUnit 6 base port at `tests/base_tests/claude/HumanEval_019_BaseTest.java` — 4/4 pass.
- Base coverage on `Solution`: 135/135 instr, **3/4 branch**, 17/17 line, 3/4 CC, 2/2 method. The `numbers == null` true branch is not exercised by the dataset harness.
- Improved suite at `tests/improved_tests/claude/HumanEval_019_ImprovedTest.java` — 12/12 pass. Closes the null-guard branch; adds whitespace-only, all-ten-words, duplicates, and two-equal-word cases.
- Manual black-box notes + suite at `tests/manual_tests/claude/HumanEval_019_blackbox.md` and `HumanEval_019_ManualTest.java` — 16/16 pass. Pinned: null → "", unknown word → NPE.
- JaCoCo coverage exported to `coverage_reports/HumanEval_019/claude/{base,improved,manual}/`. Improved and manual: 135/135 instr, 4/4 branch, 17/17 line, 4/4 CC, 2/2 method.
- No spec defects → no refactor loop triggered. Per-prompt summary: `analysis/HumanEval_019_claude.md`.

### 2026-04-25 — HumanEval_018 (Codex)

- Applied the Phase 1 Codex workflow to existing `generated_code/codex/HumanEval_018.java`; generated code was not modified before base testing.
- Logged the existing Codex generation at `llm_logs/codex/HumanEval_018_initial.md`.
- Adjusted dataset harness at `tests/base_tests/adjusted/HumanEval_018/Main.java` compiled with `javac --release 21` and exited 0 against the Codex solution.
- JUnit 6 base port at `tests/base_tests/codex/HumanEval_018_BaseTest.java` — 4/4 pass.
- Improved suite at `tests/improved_tests/codex/HumanEval_018_ImprovedTest.java` — 17/17 pass after correcting one test authoring expectation for `"mississippi"` / `"issi"`.
- Manual black-box notes + suite at `tests/manual_tests/codex/HumanEval_018_blackbox.md` and `HumanEval_018_ManualTest.java` — 19/19 pass. Pinned undefined-by-spec behavior for null source, null substring, and empty substring.
- JaCoCo coverage exported to `coverage_reports/HumanEval_018/codex/{base,improved,manual}/`: base 36/38 instr and 7/10 branch; improved 38/38 instr and 8/10 branch; manual 38/38 instr and 10/10 branch.
- Per-prompt analysis added at `analysis/HumanEval_018/HumanEval_018_codex.md`. No defects against the prompt spec; no refactor loop triggered. Both models now complete for HumanEval_018.

### 2026-04-25 — HumanEval_020 (Codex)

- Applied the Phase 1 Codex workflow to existing `generated_code/codex/HumanEval_020.java`; generated code was not modified before base testing.
- Added adjusted dataset harness at `tests/base_tests/adjusted/HumanEval_020/Main.java` by adding `import java.util.*;` only; assertion logic and generated code unchanged. Adjusted harness compiled with `javac --release 21` and exited 0.
- Logged the existing Codex generation at `llm_logs/codex/HumanEval_020_initial.md`.
- JUnit 6 base port at `tests/base_tests/codex/HumanEval_020_BaseTest.java` — 5/5 pass.
- Improved suite at `tests/improved_tests/codex/HumanEval_020_ImprovedTest.java` — 14/14 pass. Covers sorted-copy behavior, two-element input, duplicates, negatives, mixed signs, ties, null, and singleton inputs.
- Manual black-box notes + suite at `tests/manual_tests/codex/HumanEval_020_blackbox.md` and `HumanEval_020_ManualTest.java` — 15/15 pass. Pinned undefined-by-spec behavior for null, empty, singleton, and tied closest-pair inputs.
- JaCoCo coverage exported to `coverage_reports/HumanEval_020/codex/{base,improved,manual}/`: base 86/90 instr and 6/8 branch; improved/manual 90/90 instr and 8/8 branch.
- Per-prompt analysis added at `analysis/HumanEval_020/HumanEval_020_codex.md`. No defects against the prompt spec; no refactor loop triggered.

### 2026-04-25 — HumanEval_040 (Codex)

- Applied the Phase 1 Codex workflow to existing `generated_code/codex/HumanEval_040.java`; generated code was not modified before base testing.
- Added adjusted dataset harness at `tests/base_tests/adjusted/HumanEval_040/Main.java` by adding `import java.util.*;` only; assertion logic and generated code unchanged. Adjusted harness compiled with `javac --release 21` and exited 0.
- Logged the existing Codex generation at `llm_logs/codex/HumanEval_040_initial.md`.
- JUnit 6 base port at `tests/base_tests/codex/HumanEval_040_BaseTest.java` — 9/9 pass.
- Improved suite at `tests/improved_tests/codex/HumanEval_040_ImprovedTest.java` — 23/23 pass after correcting one test authoring case that did not actually sum to zero.
- Manual black-box notes + suite at `tests/manual_tests/codex/HumanEval_040_blackbox.md` and `HumanEval_040_ManualTest.java` — 17/17 pass. Pinned undefined-by-spec behavior for null, empty, singleton, and length-two inputs.
- JaCoCo coverage exported to `coverage_reports/HumanEval_040/codex/{base,improved,manual}/`: base 70/70 instr and 11/12 branch; improved/manual 70/70 instr and 12/12 branch.
- Per-prompt analysis added at `analysis/HumanEval_040/HumanEval_040_codex.md`. No defects against the prompt spec; no refactor loop triggered.

### 2026-04-25 — HumanEval_019 (Codex)

- Applied the Phase 1 Codex workflow to existing `generated_code/codex/HumanEval_019.java`; generated code was not modified before base testing.
- Added adjusted dataset harness at `tests/base_tests/adjusted/HumanEval_019/Main.java` by adding `import java.util.*;` only; assertion logic and generated code unchanged. Adjusted harness compiled with `javac --release 21` and exited 0.
- Logged the existing Codex generation at `llm_logs/codex/HumanEval_019_initial.md`.
- JUnit 6 base port at `tests/base_tests/codex/HumanEval_019_BaseTest.java` — 4/4 pass.
- Improved suite at `tests/improved_tests/codex/HumanEval_019_ImprovedTest.java` — 13/13 pass. Covers full vocabulary ordering, duplicates, repeated values, null input, invalid tokens, and delimiter edge cases.
- Manual black-box notes + suite at `tests/manual_tests/codex/HumanEval_019_blackbox.md` and `HumanEval_019_ManualTest.java` — 16/16 pass. Pinned undefined-by-spec behavior for null, invalid tokens, leading/extra spaces, and single invalid token without comparison.
- JaCoCo coverage exported to `coverage_reports/HumanEval_019/codex/{base,improved,manual}/`: base 90/90 instr and 3/4 branch; improved/manual 90/90 instr and 4/4 branch.
- Per-prompt analysis added at `analysis/HumanEval_019/HumanEval_019_codex.md`. No defects against the prompt spec; no refactor loop triggered.

### 2026-04-25 — HumanEval_089 (Codex)

- Applied the Phase 1 Codex workflow to existing `generated_code/codex/HumanEval_089.java`; generated code was not modified before base testing.
- Added adjusted dataset harness at `tests/base_tests/adjusted/HumanEval_089/Main.java` by adding `import java.util.*;` only; assertion logic and generated code unchanged. Adjusted harness compiled with `javac --release 21` and exited 0.
- Logged the existing Codex generation at `llm_logs/codex/HumanEval_089_initial.md`.
- JUnit 6 base port at `tests/base_tests/codex/HumanEval_089_BaseTest.java` — 8/8 pass.
- Improved suite at `tests/improved_tests/codex/HumanEval_089_ImprovedTest.java` — 17/17 pass after correcting one pinned uppercase expectation for Java remainder behavior.
- Manual black-box notes + suite at `tests/manual_tests/codex/HumanEval_089_blackbox.md` and `HumanEval_089_ManualTest.java` — 14/14 pass. Pinned undefined-by-spec behavior for null, uppercase, space, and digit inputs.
- JaCoCo coverage exported to `coverage_reports/HumanEval_089/codex/{base,improved,manual}/`: base 47/49 instr and 4/6 branch; improved/manual 49/49 instr and 6/6 branch.
- Per-prompt analysis added at `analysis/HumanEval_089/HumanEval_089_codex.md`. No defects against the lowercase prompt examples; no refactor loop triggered.

### 2026-04-25 — HumanEval_089 (Claude)

- Applied the Phase 1 Claude workflow to existing `generated_code/claude/HumanEval_089.java`; generated code was not modified before base testing.
- Verified adjusted dataset harness at `tests/base_tests/adjusted/HumanEval_089/Main.java`; harness compiled with `javac --release 21` and exited 0.
- Logged the existing Claude generation at `llm_logs/claude/HumanEval_089_initial.md`. Generation was complete and correct on the first attempt; no refactor loop triggered.
- JUnit 6 base port at `tests/base_tests/claude/HumanEval_089_BaseTest.java` — 8/8 pass. Base coverage: 51/74 instr, 5/10 branch. Missed branches: null guard, empty guard, uppercase branch, non-alphabetic else branch.
- Improved suite at `tests/improved_tests/claude/HumanEval_089_ImprovedTest.java` — 18/18 pass. One test-authoring error (wrong expected value for `mixedCase`) caught and corrected before finalising. Improved coverage: 74/74 instr, 10/10 branch.
- Manual black-box notes at `tests/manual_tests/claude/HumanEval_089_blackbox.md`. Defined 13 valid/extended equivalence classes (V1–V13) and 1 invalid class (I1: null). Pinned spec-undefined behaviors: uppercase rotation, non-alphabetic pass-through, null returns `""`.
- Manual suite at `tests/manual_tests/claude/HumanEval_089_ManualTest.java` — 18/18 pass. Manual coverage: 74/74 instr, 10/10 branch.
- JaCoCo coverage exported to `coverage_reports/HumanEval_089/claude/{base,improved,manual}/`.
- Per-prompt analysis added at `analysis/HumanEval_089_claude.md`. Coverage rows appended to `analysis/coverage_summary.md`.

### 2026-04-25 — HumanEval_093 (Claude)

- Applied the Phase 1 Claude workflow to existing `generated_code/claude/HumanEval_093.java`; generated code was not modified before base testing.
- Added adjusted dataset harness at `tests/base_tests/adjusted/HumanEval_093/Main.java` by adding `import java.util.*;` and `import java.lang.*;`; assertion logic and generated code unchanged. Adjusted harness compiled with `javac --release 21` and exited 0.
- Logged the existing Claude generation at `llm_logs/claude/HumanEval_093_initial.md`. Generation was complete and correct on the first attempt; no refactor loop triggered.
- JUnit 6 base port at `tests/base_tests/claude/HumanEval_093_BaseTest.java` — 5/5 pass. Base coverage: 120/122 instr, 10/12 branch. Missed branches: null guard true, empty-string guard true.
- Improved suite at `tests/improved_tests/claude/HumanEval_093_ImprovedTest.java` — 22/22 pass. Adds null/empty guards, per-vowel parameterized rows for all 10 vowels, isolated consonant tests, y/Y non-vowel verification, space pass-through. Improved coverage: 122/122 instr, 12/12 branch.
- Manual black-box notes at `tests/manual_tests/claude/HumanEval_093_blackbox.md`. Defined 14 equivalence classes (V1–V14) and 1 invalid class (I1: null). Pinned spec-undefined behaviors: space pass-through, null returns "".
- Manual suite at `tests/manual_tests/claude/HumanEval_093_ManualTest.java` — 19/19 pass. Manual coverage: 122/122 instr, 12/12 branch.
- JaCoCo coverage exported to `coverage_reports/HumanEval_093/claude/{base,improved,manual}/`.
- Per-prompt analysis added at `analysis/HumanEval_093_claude.md`. Coverage rows appended to `analysis/coverage_summary.md`.

### 2026-04-25 — HumanEval_093 (Codex)

- Applied the Phase 1 Codex workflow to existing `generated_code/codex/HumanEval_093.java`; generated code was not modified before base testing.
- Added adjusted dataset harness at `tests/base_tests/adjusted/HumanEval_093/Main.java` by adding `import java.util.*;` only; assertion logic and generated code unchanged. Adjusted harness compiled with `javac --release 21` and exited 0.
- Logged the existing Codex generation at `llm_logs/codex/HumanEval_093_initial.md`.
- JUnit 6 base port at `tests/base_tests/codex/HumanEval_093_BaseTest.java` — 5/5 pass.
- Improved suite at `tests/improved_tests/codex/HumanEval_093_ImprovedTest.java` — 15/15 pass after correcting one non-letter expected output where the leading vowel still transforms.
- Manual black-box notes + suite at `tests/manual_tests/codex/HumanEval_093_blackbox.md` and `HumanEval_093_ManualTest.java` — 15/15 pass. Pinned undefined-by-spec behavior for null, empty, spaces, digits, and punctuation.
- JaCoCo coverage exported to `coverage_reports/HumanEval_093/codex/{base,improved,manual}/`: base 91/93 instr and 20/22 branch; improved/manual 93/93 instr and 22/22 branch.
- Per-prompt analysis added at `analysis/HumanEval_093/HumanEval_093_codex.md`. No defects against the prompt spec; no refactor loop triggered.

### 2026-04-25 — HumanEval_099 (Codex)

- Applied the Phase 1 Codex workflow to existing `generated_code/codex/HumanEval_099.java`; generated code was not modified before base testing.
- Added adjusted dataset harness at `tests/base_tests/adjusted/HumanEval_099/Main.java` by adding `import java.util.*;` and changing the incompatible method calls from `countUpper` to the selected-prompt API `closest_integer`; assertion inputs and expected values unchanged. Adjusted harness compiled with `javac --release 21` and exited 0.
- Logged the existing Codex generation at `llm_logs/codex/HumanEval_099_initial.md`.
- JUnit 6 base port at `tests/base_tests/codex/HumanEval_099_BaseTest.java` — 5/5 pass.
- Improved suite at `tests/improved_tests/codex/HumanEval_099_ImprovedTest.java` — 18/18 pass. Covers positive/negative rounding below, at, and above `.5`, near-zero boundaries, leading zeros, null, empty, and invalid numeric strings.
- Manual black-box notes + suite at `tests/manual_tests/codex/HumanEval_099_blackbox.md` and `HumanEval_099_ManualTest.java` — 16/16 pass. Pinned undefined-by-spec behavior for null, empty, and invalid numeric strings.
- JaCoCo coverage exported to `coverage_reports/HumanEval_099/codex/{base,improved,manual}/`: base 27/29 instr and 4/6 branch; improved/manual 29/29 instr and 6/6 branch.
- Per-prompt analysis added at `analysis/HumanEval_099/HumanEval_099_codex.md`. No defects against the prompt spec; no refactor loop triggered.

### 2026-04-25 — HumanEval_099 (Claude)

- Applied the Phase 1 Claude workflow to existing `generated_code/claude/HumanEval_099.java`; generated code was not modified before base testing.
- Discovered the original dataset harness (`tests/base_tests/original/HumanEval_099/Main.java`) calls `s.countUpper(...)`, which is the wrong method name (copy-paste error from a different problem). Fixed to `s.closestInteger(...)` in the adjusted harness. Documented in `analysis/base_test_adjustments.md` (file created this step).
- Logged the existing Claude generation at `llm_logs/claude/HumanEval_099_initial.md`. Generation was complete and correct on the first attempt; no refactor loop triggered.
- JUnit 6 base port at `tests/base_tests/claude/HumanEval_099_BaseTest.java` — 5/5 pass. Base coverage: 28/28 instr, 4/4 branch (100%) — the dataset examples include both positive and negative halfway cases, so all branches are covered immediately.
- Improved suite at `tests/improved_tests/claude/HumanEval_099_ImprovedTest.java` — 36/36 pass. Adds the -14.5 prompt note example, full grid of halfway/non-halfway/exact-integer parameterized cases, and invalid-input exception pinning.
- Manual black-box notes at `tests/manual_tests/claude/HumanEval_099_blackbox.md`. Defined 13 valid equivalence classes (V1–V13) and 3 invalid classes (I1–I3: null→NPE, empty/non-numeric→NFE). Documented boundary values around the x.5 decision point.
- Manual suite at `tests/manual_tests/claude/HumanEval_099_ManualTest.java` — 26/26 pass. Manual coverage: 28/28 instr, 4/4 branch.
- JaCoCo coverage exported to `coverage_reports/HumanEval_099/claude/{base,improved,manual}/`.
- Per-prompt analysis added at `analysis/HumanEval_099_claude.md`. Coverage rows appended to `analysis/coverage_summary.md`.

### 2026-04-26 — HumanEval_106 (Codex)

- Applied the Phase 1 Codex workflow to existing `generated_code/codex/HumanEval_106.java`; generated code was not modified before base testing.
- Added adjusted dataset harness at `tests/base_tests/adjusted/HumanEval_106/Main.java` by adding `import java.util.*;` only; assertion logic and generated code unchanged. Adjusted harness compiled with `javac --release 21` and exited 0.
- Logged the existing Codex generation at `llm_logs/codex/HumanEval_106_initial.md`.
- JUnit 6 base port at `tests/base_tests/codex/HumanEval_106_BaseTest.java` — 4/4 pass.
- Improved suite at `tests/improved_tests/codex/HumanEval_106_ImprovedTest.java` — 11/11 pass. Covers zero, negative `n`, first even boundary, larger factorial/triangular positions, and repeated-call stability.
- Manual black-box notes + suite at `tests/manual_tests/codex/HumanEval_106_blackbox.md` and `HumanEval_106_ManualTest.java` — 11/11 pass. Pinned undefined-by-spec behavior for zero and negative `n`.
- JaCoCo coverage exported to `coverage_reports/HumanEval_106/codex/{base,improved,manual}/`: base 45/47 instr and 5/6 branch; improved/manual 47/47 instr and 6/6 branch.
- Per-prompt analysis added at `analysis/HumanEval_106/HumanEval_106_codex.md`. No defects against the prompt spec; no refactor loop triggered.

### 2026-04-25 — HumanEval_106 (Claude)

- Applied the Phase 1 Claude workflow to existing `generated_code/claude/HumanEval_106.java`; generated code was not modified before base testing.
- Added adjusted dataset harness at `tests/base_tests/adjusted/HumanEval_106/Main.java` by adding `import java.util.*;` and `import java.lang.*;`. Adjusted harness compiled and exited 0.
- Logged the existing Claude generation at `llm_logs/claude/HumanEval_106_initial.md`. Generation was complete and correct on the first attempt; no refactor loop triggered.
- JUnit 6 base port at `tests/base_tests/claude/HumanEval_106_BaseTest.java` — 4/4 pass. Base coverage: 52/52 instr, 6/6 branch (100%) — dataset inputs already exercise both parity branches and all loop conditions.
- Improved suite at `tests/improved_tests/claude/HumanEval_106_ImprovedTest.java` — 17/17 pass. Adds n=0 (empty list), n=2 (minimal both-branch), n=6, per-element parameterized suite for i=1..8, list-size invariant test, and repeated-call independence check.
- Manual black-box notes at `tests/manual_tests/claude/HumanEval_106_blackbox.md`. Defined 8 valid classes (V1–V8) and 1 invalid class (I1: n<0 → empty list). Documented element-level boundary values for i=1..8 and list-level boundaries.
- Manual suite at `tests/manual_tests/claude/HumanEval_106_ManualTest.java` — 14/14 pass. Manual coverage: 52/52 instr, 6/6 branch.
- JaCoCo coverage exported to `coverage_reports/HumanEval_106/claude/{base,improved,manual}/`.
- Per-prompt analysis added at `analysis/HumanEval_106_claude.md`. Coverage rows appended to `analysis/coverage_summary.md`.

### 2026-04-25 — HumanEval_129 (Claude)

- Applied the Phase 1 Claude workflow to existing `generated_code/claude/HumanEval_129.java`; generated code was not modified before base testing.
- Added adjusted dataset harness at `tests/base_tests/adjusted/HumanEval_129/Main.java` by adding `import java.util.*;` and `import java.lang.*;`. Adjusted harness compiled and exited 0.
- Logged the existing Claude generation at `llm_logs/claude/HumanEval_129_initial.md`. Generation was complete and correct on the first attempt; no refactor loop triggered. Minor dead-code note: `int minVal = n * n + 1` is declared but never read.
- JUnit 6 base port at `tests/base_tests/claude/HumanEval_129_BaseTest.java` — 11/11 pass. Base coverage: 178/178 instr, 20/20 branch (100%) — the unusually comprehensive dataset covers 1 at corners, edges, and interior across 2x2, 3x3, and 4x4 grids with k from 1 to 12.
- Improved suite at `tests/improved_tests/claude/HumanEval_129_ImprovedTest.java` — 18/18 pass. Adds structural grouping (k-boundary, position-of-1, grid-size nested suites) and explicit all-four-corner 2x2 coverage.
- Manual black-box notes at `tests/manual_tests/claude/HumanEval_129_blackbox.md`. Defined 14 valid classes (V1–V14) and 2 invalid classes (I1: null grid→NPE, I2: k=0→empty list). Documented algorithmic insight: optimal path bounces between cell-with-1 and its minimum neighbour.
- Manual suite at `tests/manual_tests/claude/HumanEval_129_ManualTest.java` — 16/16 pass. Manual coverage: 178/178 instr, 20/20 branch.
- JaCoCo coverage exported to `coverage_reports/HumanEval_129/claude/{base,improved,manual}/`.
- Per-prompt analysis added at `analysis/HumanEval_129_claude.md`. Coverage rows appended to `analysis/coverage_summary.md`.

### 2026-04-26 — HumanEval_129 (Codex)

- Applied the Phase 1 Codex workflow to existing `generated_code/codex/HumanEval_129.java`; generated code was not modified before base testing.
- Added adjusted dataset harness at `tests/base_tests/adjusted/HumanEval_129/Main.java` by adding `import java.util.*;` only; assertion logic and generated code unchanged. Adjusted harness compiled with `javac --release 21` and exited 0.
- Logged the existing Codex generation at `llm_logs/codex/HumanEval_129_initial.md`.
- JUnit 6 base port at `tests/base_tests/codex/HumanEval_129_BaseTest.java` — 10/10 pass.
- Improved suite at `tests/improved_tests/codex/HumanEval_129_ImprovedTest.java` — 14/14 pass. Covers `k` boundaries, null/empty grid behavior, and cases where cell `1` is at a corner, edge, and interior.
- Manual black-box notes + suite at `tests/manual_tests/codex/HumanEval_129_blackbox.md` and `HumanEval_129_ManualTest.java` — 12/12 pass. Pinned undefined-by-spec behavior for null grid, empty grid, and non-positive `k`.
- JaCoCo coverage exported to `coverage_reports/HumanEval_129/codex/{base,improved,manual}/`: base 170/172 instr and 24/28 branch; improved/manual 172/172 instr and 27/28 branch.
- Per-prompt analysis added at `analysis/HumanEval_129/HumanEval_129_codex.md`. No defects against the prompt spec; no refactor loop triggered.
### 2026-04-25 — HumanEval_057 (Claude)

- Started Phase 1 implementation for `HumanEval_057` (Claude side) — `monotonic(List<Integer>)`: list is non-decreasing OR non-increasing.
- Generated `generated_code/claude/HumanEval_057.java` from the verbatim Java/57 prompt (two-flag pass: clears `nonDecreasing` on a strict drop, `nonIncreasing` on a strict rise; returns the OR).
- Logged the initial interaction in `llm_logs/claude/HumanEval_057_initial.md`.
- Adjusted dataset `Main.java` at `tests/base_tests/adjusted/HumanEval_057/Main.java` (added `import java.util.*;`); logged in `tests/base_tests/adjustment_log.md`. Adjusted harness exits 0.
- JUnit 6 base port at `tests/base_tests/claude/HumanEval_057_BaseTest.java` — 8/8 pass against the verbatim Claude solution.
- Improved JUnit 6 suite at `tests/improved_tests/claude/HumanEval_057_ImprovedTest.java` — 21/21 pass. Targets test smells (assertion roulette, magic numbers, eager test) and branch coverage of the two inner ifs and the final OR (left-only / right-only / both true via all-equal / both false via spike or dip).
- Manual black-box notes + suite at `tests/manual_tests/claude/HumanEval_057_blackbox.md` and `HumanEval_057_ManualTest.java` — 18/18 pass. Pinned that empty/singleton lists return `true` (loop never enters) and that `Integer.MIN_VALUE`/`Integer.MAX_VALUE` pairs are handled correctly (`<`/`>` only, no overflow risk).
- JaCoCo coverage exported to `coverage_reports/HumanEval_057/claude/{base,improved,manual}/`. All three suites: 53/53 instr, 10/10 branch, 9/9 line, 7/7 CC, 2/2 method on `Solution`.
- Per-prompt summary at `analysis/HumanEval_057/HumanEval_057_claude.md`; row added to `analysis/coverage_summary.md`.
- No defects against spec → no refactor loop triggered. HumanEval_057 (Claude side) complete; Codex side still pending.

### 2026-04-25 — HumanEval_057 (Codex)

- Started Phase 1 implementation for `HumanEval_057` under the `codex` workflow.
- Read the prompt text from `prompts/selected_prompts.md` and saved the initial Java solution to `generated_code/codex/HumanEval_057.java`.
- Logged the initial interaction in `llm_logs/codex/HumanEval_057_initial.md`.
- Added Codex-side base, improved, and manual test artifacts for `HumanEval_057`, plus separate Codex test-generation logs for improved/manual work.
- Executed the adjusted dataset base harness for `HumanEval_057` with Homebrew OpenJDK; compile succeeded and `Main` exited with status 0.
- JUnit 6 Codex suites with `.tools/junit-platform-console-standalone.jar`: base 8/8, improved 18/18, manual 14/14.
- Exported JaCoCo coverage to `coverage_reports/HumanEval_057/codex/{base,improved,manual}/`; `Solution` reached 53/53 instructions, 10/10 branches, 9/9 lines, 7/7 complexity, and 2/2 methods in all three suites.
- Added per-prompt summary findings to `analysis/HumanEval_057/HumanEval_057_codex.md` and rows to `analysis/coverage_summary.md`.
- No defects against the prompt specification were observed, so no refactor loop was triggered. HumanEval_057 (Codex side) is complete for Phase 1.

### 2026-04-25 — HumanEval_097 (Codex)

- Started Phase 1 implementation for `HumanEval_097` under the `codex` workflow.
- Read the prompt text from `prompts/selected_prompts.md` and saved the initial Java solution to `generated_code/codex/HumanEval_097.java`.
- Logged the initial interaction in `llm_logs/codex/HumanEval_097_initial.md`.
- Adjusted dataset `Main.java` at `tests/base_tests/adjusted/HumanEval_097/Main.java` by adding `import java.util.*;`; documented in `tests/base_tests/adjustment_log.md`. Generated code unchanged.
- Executed the adjusted dataset base harness for `HumanEval_097` with Homebrew OpenJDK; compile succeeded and `Main` exited with status 0.
- Added Codex-side base, improved, and manual test artifacts for `HumanEval_097`, plus separate Codex test-generation logs for improved/manual work.
- JUnit 6 Codex suites with `.tools/junit-platform-console-standalone.jar`: base 8/8, improved 12/12, manual 10/10.
- Exported JaCoCo coverage to `coverage_reports/HumanEval_097/codex/{base,improved,manual}/`; `Solution` reached 13/13 instructions, 0/0 branches, 2/2 lines, 2/2 complexity, and 2/2 methods in all three suites.
- Added per-prompt summary findings to `analysis/HumanEval_097/HumanEval_097_codex.md` and rows to `analysis/coverage_summary.md`.
- No defects against the prompt specification were observed, so no refactor loop was triggered. HumanEval_097 (Codex side) is complete for Phase 1.

### 2026-04-25 — HumanEval_097 (Claude)

- Started Phase 1 implementation for `HumanEval_097` (Claude side) — `multiply(int a, int b)`: product of the unit digits, sign-insensitive.
- Generated `generated_code/claude/HumanEval_097.java` from the verbatim Java/97 prompt (single-expression `Math.abs(a % 10) * Math.abs(b % 10)`).
- Logged the initial interaction in `llm_logs/claude/HumanEval_097_initial.md`.
- Adjusted dataset `Main.java` at `tests/base_tests/adjusted/HumanEval_097/Main.java` (added `import java.util.*;`); logged in `tests/base_tests/adjustment_log.md`. Adjusted harness exits 0.
- JUnit 6 base port at `tests/base_tests/claude/HumanEval_097_BaseTest.java` — 8/8 pass.
- Improved JUnit 6 suite at `tests/improved_tests/claude/HumanEval_097_ImprovedTest.java` — 22/22 pass. Targets test smells (assertion roulette, magic numbers, eager test) and behavioral path coverage of the sign-cross matrix `(±,±)`, the units-zero short-circuit (operand 0 or multiple of 10), and invariants (commutativity, sign-insensitivity, units-only, non-negativity, bound 81).
- Manual black-box notes + suite at `tests/manual_tests/claude/HumanEval_097_blackbox.md` and `HumanEval_097_ManualTest.java` — 19/19 pass. Pinned that `Integer.MIN_VALUE` operand is well-defined for this implementation (`MIN_VALUE % 10 == -8`, `Math.abs(-8) == 8`) and that no overflow path exists because the raw inputs are never multiplied.
- JaCoCo coverage exported to `coverage_reports/HumanEval_097/claude/{base,improved,manual}/`. All three suites: 13/13 instr, 0/0 branch, 2/2 line, 2/2 CC, 2/2 method on `Solution`. The implementation has no in-method conditionals so JaCoCo reports zero branch counters; behavioral path coverage is documented in the suite rationales.
- Per-prompt summary at `analysis/HumanEval_097/HumanEval_097_claude.md`; rows added to `analysis/coverage_summary.md`.
- No defects against spec → no refactor loop triggered. HumanEval_097 (Claude side) complete; Codex side still pending.

### 2026-04-25 — HumanEval_100 (Codex)

- Started Phase 1 implementation for `HumanEval_100` under the `codex` workflow.
- Read the prompt text from `prompts/selected_prompts.md` and saved the initial Java solution to `generated_code/codex/HumanEval_100.java`.
- Logged the initial interaction in `llm_logs/codex/HumanEval_100_initial.md`.
- Adjusted dataset `Main.java` at `tests/base_tests/adjusted/HumanEval_100/Main.java` by adding `import java.util.*;`; documented in `tests/base_tests/adjustment_log.md`. Generated code unchanged.
- Executed the adjusted dataset base harness for `HumanEval_100` with Homebrew OpenJDK; compile succeeded and `Main` exited with status 0.
- Added Codex-side base, improved, and manual test artifacts for `HumanEval_100`, plus separate Codex test-generation logs for improved/manual work.
- JUnit 6 Codex suites with `.tools/junit-platform-console-standalone.jar`: base 5/5, improved 12/12, manual 9/9.
- Exported JaCoCo coverage to `coverage_reports/HumanEval_100/codex/{base,improved,manual}/`; `Solution` reached 25/25 instructions, 2/2 branches, 5/5 lines, 3/3 complexity, and 2/2 methods in all three suites.
- Added per-prompt summary findings to `analysis/HumanEval_100/HumanEval_100_codex.md` and rows to `analysis/coverage_summary.md`.
- No defects against the prompt specification were observed, so no refactor loop was triggered. HumanEval_100 (Codex side) is complete for Phase 1.

### 2026-04-25 — HumanEval_100 (Claude)

- Started Phase 1 implementation for `HumanEval_100` (Claude side) — `makeAPile(int n)`: build a list of `n` levels where the first level has `n` stones and each subsequent level adds 2 (parity-preserving "next odd / next even").
- Generated `generated_code/claude/HumanEval_100.java` from the verbatim Java/100 prompt (closed-form arithmetic progression `n + 2*i` over a single `for` loop).
- Logged the initial interaction in `llm_logs/claude/HumanEval_100_initial.md`.
- Adjusted dataset `Main.java` at `tests/base_tests/adjusted/HumanEval_100/Main.java` (added `import java.util.*;`); logged in `tests/base_tests/adjustment_log.md`. Adjusted harness exits 0.
- JUnit 6 base port at `tests/base_tests/claude/HumanEval_100_BaseTest.java` — 5/5 pass.
- Improved JUnit 6 suite at `tests/improved_tests/claude/HumanEval_100_ImprovedTest.java` — 15/15 pass. Targets test smells (assertion roulette via per-case `@Test`s, magic numbers via intent-named display names, eager test via nested classes) and the for-loop-guard branch (taken / not-taken) plus invariants (size==n, first==n, last==3n-2, step==2, parity preserved, closed-form `n+2*i`).
- Manual black-box notes + suite at `tests/manual_tests/claude/HumanEval_100_blackbox.md` and `HumanEval_100_ManualTest.java` — 19/19 pass. Pinned that `n=0` and `n<0` return an empty list (loop never enters); these are observed-behavior pins, not spec contracts.
- JaCoCo coverage exported to `coverage_reports/HumanEval_100/claude/{base,improved,manual}/`. All three suites: 25/25 instr, 2/2 branch, 5/5 line, 3/3 CC, 2/2 method on `Solution`.
- Per-prompt summary at `analysis/HumanEval_100/HumanEval_100_claude.md`; rows added to `analysis/coverage_summary.md`.
- No defects against spec → no refactor loop triggered. HumanEval_100 (Claude side) complete.

## 2026-04-26 — HumanEval_001, 002, 023, 028, 039, 075, 090, 098, 108 (Claude)

- Generated Java solutions for 9 prompts under `generated_code/claude/` and logged initial interactions under `llm_logs/claude/`.
- Added adjusted dataset harnesses under `tests/base_tests/adjusted/` for all 9 prompts.
- JUnit 6 base suites (all pass): 001:4/4, 002:5/5, 023:4/4, 028:4/4, 039:5/5, 075:6/6, 090:5/5, 098:5/5, 108:5/5.
  - HumanEval_075 base harness corrected: 12=2²·3, 18=2·3², 50=2·5² are products of 3 primes; wrong expected values fixed before commit.
- JUnit 6 improved suites (all pass): 001:11, 002:11, 023:8, 028:9, 039:10, 075:16, 090:12, 098:19, 108:19.
- JUnit 6 manual suites (all pass): 001:13, 002:10, 023:9, 028:10, 039:10, 075:15, 090:12, 098:13, 108:16.
- JaCoCo coverage exported to `coverage_reports/HumanEval_XXX/claude/{base,improved,manual}/` for all 9 prompts.
- Notable coverage findings:
  - HumanEval_001: 9/10 branch — missed path is non-paren/non-space char (unreachable under valid spec input).
  - HumanEval_039: 13/14 branch — `isPrime(n<2)` true path unreachable (Fibonacci sequence starts at 2).
  - HumanEval_075: base misses count>3 early-return branch; improved/manual achieve 8/8.
  - HumanEval_090: 5/6 branch — null-list path untested (spec guarantees non-null); pinned.
  - HumanEval_002, 023, 028, 098, 108: full branch/instruction coverage on all suites.
- Per-prompt analysis files created under `analysis/HumanEval_XXX/HumanEval_XXX_claude.md` for all 9 prompts.
- 27 rows added to `analysis/coverage_summary.md`.
- No defects found against spec in any of the 9 prompts → no refactor loops triggered.
- All 9 Claude-side prompts (001, 002, 023, 028, 039, 075, 090, 098, 108) complete for Phase 1.

## 2026-04-26 — HumanEval_001 (Codex / GPT) — Codex pipeline started

- Code generated by GPT (model label: codex), saved to `generated_code/codex/HumanEval_001.java`.
- Initial interaction logged: `llm_logs/codex/HumanEval_001_initial.md`.
- Base tests (4/4 pass): `tests/base_tests/codex/HumanEval_001_BaseTest.java`.
- Improved tests (12/12 pass): `tests/improved_tests/codex/HumanEval_001_ImprovedTest.java`.
- Manual black-box design: `tests/manual_tests/codex/HumanEval_001_blackbox.md`.
- Manual tests (10/10 pass): `tests/manual_tests/codex/HumanEval_001_ManualTest.java`.
- JaCoCo: 62/62 instr, 10/12 branch (2 missed = unreachable for valid spec input), 13/13 line on all suites.
- Coverage saved to `coverage_reports/HumanEval_001/codex/{base,improved,manual}/`.
- Per-prompt analysis: `analysis/HumanEval_001/HumanEval_001_codex.md`.
- No defects — refactor loop not triggered.

## 2026-04-26 — HumanEval_002 (Codex / GPT)

- Code generated by GPT, saved to `generated_code/codex/HumanEval_002.java`.
- Initial interaction logged: `llm_logs/codex/HumanEval_002_initial.md`.
- Base tests (5/5 pass), improved (10/10 pass), manual (9/9 pass).
- JaCoCo: 9/9 instr, 0/0 branch (no branches), 2/2 line on all suites.
- Coverage saved to `coverage_reports/HumanEval_002/codex/{base,improved,manual}/`.
- Per-prompt analysis: `analysis/HumanEval_002/HumanEval_002_codex.md`.
- No defects — refactor loop not triggered.

## 2026-04-26 — HumanEval_023 (Codex / GPT)

- Code generated by GPT, saved to `generated_code/codex/HumanEval_023.java`.
- Initial interaction logged: `llm_logs/codex/HumanEval_023_initial.md`.
- Base (4/4 pass), improved (8/8 pass), manual (9/9 pass).
- JaCoCo: 6/6 instr, 0/0 branch, 2/2 line on all suites.
- Coverage saved to `coverage_reports/HumanEval_023/codex/{base,improved,manual}/`.
- Per-prompt analysis: `analysis/HumanEval_023/HumanEval_023_codex.md`.
- No defects — refactor loop not triggered.

## 2026-04-26 — HumanEval_028 (Codex / GPT)

- Code generated by GPT, saved to `generated_code/codex/HumanEval_028.java`.
- Initial interaction logged: `llm_logs/codex/HumanEval_028_initial.md`.
- Base (4/4 pass), improved (9/9 pass), manual (10/10 pass).
- JaCoCo: 25/25 instr, 2/2 branch, 6/6 line on all suites.
- Coverage saved to `coverage_reports/HumanEval_028/codex/{base,improved,manual}/`.
- Per-prompt analysis: `analysis/HumanEval_028/HumanEval_028_codex.md`.
- No defects — refactor loop not triggered.

## 2026-04-26 — HumanEval_039 (Codex / GPT)

- Code generated by GPT, saved to `generated_code/codex/HumanEval_039.java`.
- Initial interaction logged: `llm_logs/codex/HumanEval_039_initial.md`.
- Base (5/5 pass), improved (9/9 pass), manual (9/9 pass).
- JaCoCo: 57/57 instr, 16/16 branch (full!), 14/14 line on all suites.
- Notable: codex inline implementation achieves 16/16 branch vs claude 13/14 (no helper method).
- Coverage saved to `coverage_reports/HumanEval_039/codex/{base,improved,manual}/`.
- Per-prompt analysis: `analysis/HumanEval_039/HumanEval_039_codex.md`.
- No defects — refactor loop not triggered.

## 2026-04-26 — HumanEval_075 (Codex / GPT)

- Code generated by GPT, saved to `generated_code/codex/HumanEval_075.java`.
- Initial interaction logged: `llm_logs/codex/HumanEval_075_initial.md`.
- Base (6/6 pass), improved (12/12 pass), manual (13/13 pass).
- JaCoCo: 65/73 instr, 17/24 branch (base) → 19/24 (improved/manual); 5 remaining missed are structurally unreachable defensive guards.
- Coverage saved to `coverage_reports/HumanEval_075/codex/{base,improved,manual}/`.
- Per-prompt analysis: `analysis/HumanEval_075/HumanEval_075_codex.md`.
- No defects — refactor loop not triggered.

## 2026-04-26 — HumanEval_090 (Codex / GPT)

- Code generated by GPT, saved to `generated_code/codex/HumanEval_090.java`.
- Initial interaction logged: `llm_logs/codex/HumanEval_090_initial.md`.
- Base (5/5 pass), improved (11/11 pass), manual (12/12 pass).
- JaCoCo: 64/64 instr, 20/22 branch; 2 missed = lst==null spec-boundary.
- Coverage saved to `coverage_reports/HumanEval_090/codex/{base,improved,manual}/`.
- Per-prompt analysis: `analysis/HumanEval_090/HumanEval_090_codex.md`.
- No defects — refactor loop not triggered.

## 2026-04-26 — HumanEval_098 (Codex / GPT)

- Code generated by GPT (explicit `||` chain for vowel check), saved to `generated_code/codex/HumanEval_098.java`.
- Initial interaction logged: `llm_logs/codex/HumanEval_098_initial.md`.
- Base (5/5 pass): 35/35 instr, 9/12 branch (I/O/U true-paths not in docstring examples).
- Improved (14/14 pass), manual (10/10 pass): 35/35 instr, 12/12 branch (full).
- JaCoCo coverage saved to `coverage_reports/HumanEval_098/codex/{base,improved,manual}/`.
- Test logs: `llm_logs/codex/HumanEval_098_test_improvement.md`, `HumanEval_098_manual_assistance.md`.
- Per-prompt analysis: `analysis/HumanEval_098/HumanEval_098_codex.md`.
- No defects against spec — refactor loop not triggered. HumanEval_098 fully complete (both models).

## 2026-04-26 — HumanEval_108 (Codex / GPT)

- Code generated by GPT (integer-arithmetic digit extraction with `sum -= 2 * firstDigit` sign correction), saved to `generated_code/codex/HumanEval_108.java`.
- Initial interaction logged: `llm_logs/codex/HumanEval_108_initial.md`.
- Known edge-case defect: `Math.abs(Integer.MIN_VALUE)` overflows → `countNums([-2147483648])` returns 0 instead of 1. Spec silent on this value; no refactor triggered. Differs from Claude's `Math.abs((long) n)` approach.
- Base (5/5 pass), improved (14/14 pass), manual (10/10 pass): 59/59 instr, 10/10 branch, 15/15 line (full coverage on all suites).
- JaCoCo coverage saved to `coverage_reports/HumanEval_108/codex/{base,improved,manual}/`.
- Test logs: `llm_logs/codex/HumanEval_108_test_improvement.md`, `HumanEval_108_manual_assistance.md`.
- Per-prompt analysis: `analysis/HumanEval_108/HumanEval_108_codex.md`.
- No spec defects — refactor loop not triggered. HumanEval_108 fully complete (both models).

## 2026-04-26 — HumanEval_087 (getRow) — Both models complete

### Claude
- GPT response obtained; Claude code generated (reverse column iteration — no explicit sort needed), saved to `generated_code/claude/HumanEval_087.java`.
- Initial interaction logged: `llm_logs/claude/HumanEval_087_initial.md`.
- Base (6/6 pass), improved (13/13 pass), manual (11/11 pass): 54/54 instr, 6/6 branch, 8/8 line (full coverage on all suites).
- JaCoCo coverage saved to `coverage_reports/HumanEval_087/claude/{base,improved,manual}/`.
- Test logs: `llm_logs/claude/HumanEval_087_test_improve.md`, `HumanEval_087_manual_assist.md`.
- Per-prompt analysis: `analysis/HumanEval_087/HumanEval_087_claude.md`.
- No defects — refactor loop not triggered.

### Codex (GPT)
- GPT response obtained; Codex code generated (collect cols + Collections.sort reverseOrder), saved to `generated_code/codex/HumanEval_087.java`.
- Initial interaction logged: `llm_logs/codex/HumanEval_087_initial.md`.
- Base (6/6 pass), improved (11/11 pass), manual (10/10 pass): 77/77 instr, 13/13 branch, 8/8 line (full coverage on all suites).
- JaCoCo coverage saved to `coverage_reports/HumanEval_087/codex/{base,improved,manual}/`.
- Test logs: `llm_logs/codex/HumanEval_087_test_improve.md`, `HumanEval_087_manual_assist.md`.
- Per-prompt analysis: `analysis/HumanEval_087/HumanEval_087_codex.md`.
- No defects — refactor loop not triggered. HumanEval_087 fully complete (both models).
### 2026-04-26 — HumanEval_103 (Codex)

- Started Phase 1 implementation for `HumanEval_103` under the `codex` workflow.
- Read the prompt text from `prompts/selected_prompts.md` and saved the initial Java solution to `generated_code/codex/HumanEval_103.java`.
- Logged the initial interaction in `llm_logs/codex/HumanEval_103_initial.md`.
- Adjusted dataset `Main.java` at `tests/base_tests/adjusted/HumanEval_103/Main.java` by adding `import java.util.*;`; documented in `tests/base_tests/adjustment_log.md`. Generated code unchanged.
- Executed the adjusted dataset base harness for `HumanEval_103` with Homebrew OpenJDK; compile succeeded and `Main` exited with status 0.
- Added Codex-side base, improved, and manual test artifacts for `HumanEval_103`, plus separate Codex test-generation logs for improved/manual work.
- JUnit 6 Codex suites with `.tools/junit-platform-console-standalone.jar`: base 12/12, improved 12/12, manual 9/9.
- Exported JaCoCo coverage to `coverage_reports/HumanEval_103/codex/{base,improved,manual}/`; `Solution` reached 25/25 instructions, 2/2 branches, 6/6 lines, 3/3 complexity, and 2/2 methods in all three suites.
- Added per-prompt summary findings to `analysis/HumanEval_103/HumanEval_103_codex.md` and rows to `analysis/coverage_summary.md`.
- No defects against the prompt specification were observed, so no refactor loop was triggered. HumanEval_103 (Codex side) is complete for Phase 1.

### 2026-04-26 — HumanEval_103 (Claude)

- Started Phase 1 implementation for `HumanEval_103` (Claude side) — `roundedAvg(int n, int m)`: average of integers in `[n, m]`, rounded to nearest int (half-up), returned as a binary string; or `-1` when `n > m`.
- Generated `generated_code/claude/HumanEval_103.java` from the verbatim Java/103 prompt (guarded summation loop into a `long` accumulator, `Math.round` on the double quotient, `Long.toBinaryString` for the result; return type `Object`).
- Logged the initial interaction in `llm_logs/claude/HumanEval_103_initial.md`.
- Adjusted dataset `Main.java` at `tests/base_tests/adjusted/HumanEval_103/Main.java` (added `import java.util.*;` so the dataset harness, which references `List`, `Arrays`, and `Objects`, compiles standalone); logged in `tests/base_tests/adjustment_log.md`. Adjusted harness exits 0.
- JUnit 6 base port at `tests/base_tests/claude/HumanEval_103_BaseTest.java` — 12/12 pass against the verbatim Claude solution.
- Improved JUnit 6 suite at `tests/improved_tests/claude/HumanEval_103_ImprovedTest.java` — 19/19 pass. Targets test smells (assertion roulette via per-case `@Test`s, magic numbers via intent-named display names, eager test via nested classes by behavioral concern) and branch coverage of the `n > m` guard (taken / not-taken at edge `n == m` and `n == m + 1`) and the `for`-loop guard; exercises return-type contract (`String` on success, `Integer(-1)` on failure), exact integer averages, the half-up rounding rule (1.5/2.5/3.5 → up), singleton ranges, and larger ranges (1..100, 50..100).
- Manual black-box notes + suite at `tests/manual_tests/claude/HumanEval_103_blackbox.md` and `HumanEval_103_ManualTest.java` — 18/18 pass. Pinned that `(0, 5)` returns `"11"` and `(-2, 2)` returns `"0"` — the implementation does not check `n > 0`, so the summation/round/binary path runs unconditionally; these are observed-behavior pins, not spec contracts.
- JaCoCo coverage exported to `coverage_reports/HumanEval_103/claude/{base,improved,manual}/`. All three suites: 41/41 instr, 4/4 branch, 9/9 line, 4/4 CC, 2/2 method on `Solution`.
- Per-prompt summary at `analysis/HumanEval_103/HumanEval_103_claude.md`; rows added to `analysis/coverage_summary.md`.
- No defects against spec → no refactor loop triggered. HumanEval_103 (Claude side) complete for Phase 1.

### 2026-04-26 — HumanEval_122 (Codex)

- Started Phase 1 implementation for `HumanEval_122` under the `codex` workflow.
- Read the prompt text from `prompts/selected_prompts.md` and saved the initial Java solution to `generated_code/codex/HumanEval_122.java`.
- Logged the initial interaction in `llm_logs/codex/HumanEval_122_initial.md`.
- Adjusted dataset `Main.java` at `tests/base_tests/adjusted/HumanEval_122/Main.java` by adding `import java.util.*;`; documented in `tests/base_tests/adjustment_log.md`. Generated code unchanged.
- Executed the adjusted dataset base harness for `HumanEval_122` with Homebrew OpenJDK; compile succeeded and `Main` exited with status 0.
- Added Codex-side base, improved, and manual test artifacts for `HumanEval_122`, plus separate Codex logs for the initial test-generation steps and a later test-side expected-value correction.
- JUnit 6 Codex suites with `.tools/junit-platform-console-standalone.jar`: base 5/5, improved 10/10, manual 9/9.
- Exported JaCoCo coverage to `coverage_reports/HumanEval_122/codex/{base,improved,manual}/`; `Solution` reached 30/30 instructions, 4/4 branches, 7/7 lines, 4/4 complexity, and 2/2 methods in all three suites.
- Added per-prompt summary findings to `analysis/HumanEval_122/HumanEval_122_codex.md` and rows to `analysis/coverage_summary.md`.
- No defect against the prompt specification was observed in generated code, so no refactor loop was triggered. A single arithmetic mistake in the first Codex-authored improved/manual boundary case was corrected on the test side and logged separately.


### 2026-04-26 — HumanEval_122 (Claude)

- Started Phase 1 implementation for `HumanEval_122` (Claude side) — `addElements(List<Integer> arr, int k)`: sum of values from `arr[0..k-1]` whose decimal representation has at most two digits (interpreted as `|v| < 100`, sign-preserving).
- Generated `generated_code/claude/HumanEval_122.java` from the verbatim Java/122 prompt (single `for` loop bounded by `k`, `Math.abs(v) < 100` predicate, `int` accumulator); logged at `llm_logs/claude/HumanEval_122_initial.md`.
- Base test: dataset `Main.java` needed `import java.util.*;` to compile standalone — adjusted copy at `tests/base_tests/adjusted/HumanEval_122/Main.java`, documented in `tests/base_tests/adjustment_log.md`. Generated code unchanged. Adjusted harness exits 0.
- JUnit 6 base port at `tests/base_tests/claude/HumanEval_122_BaseTest.java` — 5/5 pass (all dataset cases including the negatives-count `-4`, the all-three-digit prefix `0`, the all-qualifying prefix `125`, the mixed example `24`, and the singleton `1`).
- Improved JUnit 6 suite at `tests/improved_tests/claude/HumanEval_122_ImprovedTest.java` — 21/21 pass. Targets test smells (assertion roulette via per-case `@Test`s, magic numbers via intent-named display names, eager test via nested classes by behavioral concern) and exercises both branches of the `Math.abs(v) < 100` predicate at the edges 99/100 and -99/-100, the loop bound at `k=1` / `k == arr.size()`, sign cancellation, all-out-of-range, and the constraint upper bound (length-100 list of 1s).
- Manual black-box notes + suite at `tests/manual_tests/claude/HumanEval_122_blackbox.md` and `HumanEval_122_ManualTest.java` — 21/21 pass. Pinned three undefined-by-spec behaviors: `k = 0` returns `0` (loop never runs), `Integer.MAX_VALUE` is dropped, and `Integer.MIN_VALUE` slips past the predicate because `Math.abs(Integer.MIN_VALUE) == Integer.MIN_VALUE` (overflow); these are observed-behavior pins, not spec contracts.
- JaCoCo coverage exported to `coverage_reports/HumanEval_122/claude/{base,improved,manual}/`. All three suites: 28/28 instr, 4/4 branch, 7/7 line, 4/4 CC, 2/2 method on `Solution`.
- Per-prompt summary at `analysis/HumanEval_122/HumanEval_122_claude.md`; rows added to `analysis/coverage_summary.md`.
- No defects against spec → no refactor loop triggered. HumanEval_122 (Claude side) complete for Phase 1.

### 2026-04-26 — HumanEval_059 (Codex)

- Started Phase 1 implementation for `HumanEval_059` under the `codex` workflow.
- Read the prompt text from `prompts/selected_prompts.md` and saved the initial Java solution to `generated_code/codex/HumanEval_059.java`.
- Logged the initial interaction in `llm_logs/codex/HumanEval_059_initial.md`.
- Adjusted dataset `Main.java` at `tests/base_tests/adjusted/HumanEval_059/Main.java` by adding `import java.util.*;`; documented in `tests/base_tests/adjustment_log.md`. Generated code unchanged.
- Executed the adjusted dataset base harness for `HumanEval_059` with Homebrew OpenJDK; compile succeeded and `Main` exited with status 0.
- Added Codex-side base, improved, and manual test artifacts for `HumanEval_059`, plus separate Codex test-generation logs for improved/manual work.
- JUnit 6 Codex suites with `.tools/junit-platform-console-standalone.jar`: base 5/5, improved 13/13, manual 13/13.
- Exported JaCoCo coverage to `coverage_reports/HumanEval_059/codex/{base,improved,manual}/`; `Solution` reached 43/43 instructions, 8/8 branches, 12/12 lines, 6/6 complexity, and 2/2 methods in all three suites.
- Added per-prompt summary findings to `analysis/HumanEval_059/HumanEval_059_codex.md` and rows to `analysis/coverage_summary.md`.
- No defects against the prompt specification were observed, so no refactor loop was triggered. HumanEval_059 (Codex side) is complete for Phase 1.

### 2026-04-26 — HumanEval_059 (Claude)

- Started Phase 1 implementation for `HumanEval_059` (Claude side) — `largestPrimeFactor(int n)`: returns the largest prime divisor of `n` (spec assumes `n > 1` and not prime).
- Generated `generated_code/claude/HumanEval_059.java` from the verbatim Java/59 prompt (trial division with the most-recent-divisor pattern; outer guard cast to `long` to avoid overflow at large factors; final `if (x > 1)` returns the residual prime).
- Logged the initial interaction in `llm_logs/claude/HumanEval_059_initial.md`.
- Adjusted dataset `Main.java` at `tests/base_tests/adjusted/HumanEval_059/Main.java` (added `import java.util.*;` so the dataset harness, which references `List` and `Arrays`, compiles standalone); logged in `tests/base_tests/adjustment_log.md`. Adjusted harness exits 0.
- JUnit 6 base port at `tests/base_tests/claude/HumanEval_059_BaseTest.java` — 5/5 pass against the verbatim Claude solution (`15→5`, `27→3`, `63→7`, `330→11`, `13195→29`).
- Improved JUnit 6 suite at `tests/improved_tests/claude/HumanEval_059_ImprovedTest.java` — 26/26 pass. Targets test smells (assertion roulette via per-case `@Test`s, magic numbers via factorisation-named display names, eager test via nested classes by behavioral concern) and exercises both branches of the inner-while divisibility check, the outer sqrt-loop boundary at `factor*factor == x` (`25`, `49`, `121`), pure prime powers (`2048`, `1024`, `2401`, `3125`) for the false branch of `if (x > 1)`, and large remainders (`22`, `9991`) for its true branch.
- Manual black-box notes + suite at `tests/manual_tests/claude/HumanEval_059_blackbox.md` and `HumanEval_059_ManualTest.java` — 20/20 pass. Pinned four undefined-by-spec behaviors: `n = 7` (prime) returns `7` itself; `n ∈ {0, 1, -15}` returns `1` because the loop guard fails on entry and the final `if (x > 1)` is also false. These are observation-only pins, not spec contracts.
- JaCoCo coverage exported to `coverage_reports/HumanEval_059/claude/{base,improved,manual}/`. All three suites: 38/38 instr, 6/6 branch, 12/12 line, 5/5 CC, 2/2 method on `Solution`.
- Per-prompt summary at `analysis/HumanEval_059/HumanEval_059_claude.md`; rows added to `analysis/coverage_summary.md`.
- No defects against spec → no refactor loop triggered. HumanEval_059 (Claude side) complete for Phase 1.

### 2026-04-26 — HumanEval_081 (Codex)

- Started Phase 1 implementation for `HumanEval_081` under the `codex` workflow.
- Read the prompt text from `prompts/selected_prompts.md` and saved the initial Java solution to `generated_code/codex/HumanEval_081.java`.
- Logged the initial interaction in `llm_logs/codex/HumanEval_081_initial.md`.
- Adjusted dataset `Main.java` at `tests/base_tests/adjusted/HumanEval_081/Main.java` by adding `import java.util.*;`; documented in `tests/base_tests/adjustment_log.md`. Generated code unchanged.
- Executed the adjusted dataset base harness for `HumanEval_081` with Homebrew OpenJDK; compile succeeded and `Main` exited with status 0.
- Added Codex-side base, improved, and manual test artifacts for `HumanEval_081`, plus separate Codex test-generation logs for improved/manual work.
- JUnit 6 Codex suites with `.tools/junit-platform-console-standalone.jar`: base 6/6, improved 26/26, manual 10/10.
- Exported JaCoCo coverage to `coverage_reports/HumanEval_081/codex/{base,improved,manual}/`; `Solution` reached 118/133 instructions, 23/26 branches, 27/30 lines, 12/15 complexity, and 2/2 methods in the base and manual suites, and 133/133 instructions, 26/26 branches, 30/30 lines, 15/15 complexity, and 2/2 methods in the improved suite.
- Added per-prompt summary findings to `analysis/HumanEval_081/HumanEval_081_codex.md` and rows to `analysis/coverage_summary.md`.
- No defects against the prompt specification were observed, so no refactor loop was triggered. HumanEval_081 (Codex side) is complete for Phase 1.

## 2026-04-26 — HumanEval_081 (Claude)

- Started Phase 1 implementation for `HumanEval_081` under the `claude` workflow.
- Read the prompt text from `prompts/selected_prompts.md` and saved the initial Java solution to `generated_code/claude/HumanEval_081.java` (13-arm if/else: exact equality at `4.0 -> A+`, eleven strict-`>` mid bands, `else -> E`).
- Logged the initial interaction in `llm_logs/claude/HumanEval_081_initial.md`.
- Confirmed the adjusted dataset `Main.java` at `tests/base_tests/adjusted/HumanEval_081/Main.java` (added `import java.util.*;`); appended a Claude-side entry to `tests/base_tests/adjustment_log.md`. Generated code unchanged.
- Executed the adjusted dataset base harness for `HumanEval_081` with Homebrew OpenJDK; compile succeeded and `Main` exited with status 0.
- Added Claude-side base, improved, and manual test artifacts for `HumanEval_081`.
- JUnit 6 Claude suites with `.tools/junit-platform-console-standalone.jar`: base 6/6, improved 23/23, manual 32/32.
- Exported JaCoCo coverage to `coverage_reports/HumanEval_081/claude/{base,improved,manual}/`; `Solution` reached 104/113 instructions, 23/26 branches, 29/32 lines, 12/15 complexity, and 2/2 methods in the base suite (the three missed branches are `> 3.7`, `> 2.3`, `> 2.0` — `A`, `B-`, `C+` are not represented in the dataset rows), and 113/113 instructions, 26/26 branches, 32/32 lines, 15/15 complexity, and 2/2 methods in both the improved and manual suites.
- Added per-prompt summary findings to `analysis/HumanEval_081/HumanEval_081_claude.md` and rows to `analysis/coverage_summary.md`.
- No defects against the prompt specification were observed, so no refactor loop was triggered. HumanEval_081 (Claude side) is complete for Phase 1.

## 2026-04-26 — HumanEval_095 (Codex)

- Started Phase 1 implementation for `HumanEval_095` under the `codex` workflow.
- Read the prompt text from `prompts/selected_prompts.md` and saved the initial Java solution to `generated_code/codex/HumanEval_095.java`.
- Logged the initial interaction in `llm_logs/codex/HumanEval_095_initial.md`.
- Adjusted dataset `Main.java` at `tests/base_tests/adjusted/HumanEval_095/Main.java` by adding `import java.util.*;`; documented in `tests/base_tests/adjustment_log.md`. Generated code unchanged during the base-test step.
- Executed the adjusted dataset base harness for `HumanEval_095` with Homebrew OpenJDK; compile succeeded and `Main` exited with status 0.
- Added Codex-side base, improved, and manual test artifacts for `HumanEval_095`, plus separate Codex test-generation logs for improved/manual work.
- Improved testing exposed a real bug in the initial solution: a key containing uncased letters was accepted even though it was neither lowercase nor uppercase. Logged and addressed with `generated_code/codex/HumanEval_095_v2.java` plus `llm_logs/codex/HumanEval_095_refactor_01.md`.
- Re-ran the dataset harness and all JUnit 6 Codex suites against `HumanEval_095_v2.java` with `.tools/junit-platform-console-standalone.jar`: base 7/7, improved 17/17, manual 13/13.
- Exported JaCoCo coverage to `coverage_reports/HumanEval_095/codex/{base,improved,manual}/`; `Solution` reached 100/103 instructions, 26/30 branches, 31/33 lines, 14/18 complexity, and 3/3 methods in the base suite, and 103/103 instructions, 30/30 branches, 33/33 lines, 18/18 complexity, and 3/3 methods in both the improved and manual suites.
- Added per-prompt summary findings to `analysis/HumanEval_095/HumanEval_095_codex.md` and rows to `analysis/coverage_summary.md`.
- HumanEval_095 (Codex side) is complete for Phase 1, including a documented refactor loop triggered by improved-test findings.

## 2026-04-26 — HumanEval_095 (Claude)

- Generated `generated_code/claude/HumanEval_095.java` from the verbatim prompt and logged the interaction in `llm_logs/claude/HumanEval_095_initial.md`.
- Authored the JUnit 6 base port at `tests/base_tests/claude/HumanEval_095_BaseTest.java` (7 cases) and the adjusted dataset harness at `tests/base_tests/adjusted/HumanEval_095/Main.java` (added `import java.util.*;` only, recorded in `tests/base_tests/adjustment_log.md`).
- Authored the improved suite at `tests/improved_tests/claude/HumanEval_095_ImprovedTest.java` (24 cases across dataset / branches / edge / structural nests) and the manual black-box notes plus tests at `tests/manual_tests/claude/HumanEval_095_blackbox.md` and `tests/manual_tests/claude/HumanEval_095_ManualTest.java` (24 cases across V1..V13 valid, I1..I4 invalid, and size/type/case boundary axes).
- Adjusted dataset harness compiles and exits `0` (`ADJUSTED_OK`); JUnit 6 runs report 7/7, 24/24, 24/24 for base/improved/manual respectively.
- Exported JaCoCo coverage to `coverage_reports/HumanEval_095/claude/{base,improved,manual}/`; `Solution` reached 53/53 instructions, 14/14 branches, 15/15 lines, 9/9 complexity, and 2/2 methods on every suite — every branch (empty short-circuit, non-string `instanceof`, both case-equality arms, the loop continuation, and the final disjunction) is hit.
- Added per-prompt summary findings to `analysis/HumanEval_095/HumanEval_095_claude.md` and rows to `analysis/coverage_summary.md`.
- No defects against the prompt specification were observed, so no refactor loop was triggered. HumanEval_095 (Claude side) is complete for Phase 1.
