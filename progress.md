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
