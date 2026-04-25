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
