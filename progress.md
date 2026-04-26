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
