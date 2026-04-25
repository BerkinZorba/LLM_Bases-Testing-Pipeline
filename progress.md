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
