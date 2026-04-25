# AGENTS.md

## 1. Purpose of this file

This file tells an AI agent how to operate safely and consistently inside this repository for **Phase 1 only**. It defines where to read inputs, where to write outputs, what must never be overwritten, the required workflow order, and how every action must be documented. Phase 2 is out of scope for this file.

## 2. Phase 1 objective

The Phase 1 objective is to compare **two LLMs** on **30 selected prompts** from the **Java HumanEval dataset**.

For each selected prompt, the agent must:
- generate **Java** code from the original prompt text,
- use **JUnit 6** tests,
- run dataset base tests first,
- improve tests later using test smell analysis and branch coverage analysis,
- perform manual black-box assessment using equivalence classes, valid/invalid classes, and boundary conditions,
- collect failures, coverage evidence, and iteration notes,
- store artifacts in a way that supports later analysis and reporting.

## 3. Directory map

### `analysis/`
- Purpose: working analysis artifacts for Phase 1.
- Put here: per-prompt notes, coverage summaries, defect notes, test smell findings, base-test compatibility notes, comparison tables.
- Never put here: generated `.java` source files, raw LLM transcripts, raw coverage dumps, or unrelated final-report prose unless explicitly requested.

### `coverage_reports/`
- Purpose: raw and exported coverage evidence.
- Put here: per-prompt coverage folders, XML/HTML coverage outputs, branch coverage artifacts.
- Never put here: tests, generated code, or general prose notes.

### `generated_code/`
- Purpose: model outputs saved as Java source files.
- Put here: generated Java files under the correct model folder only.
- Never put here: logs, tests, coverage outputs, or analysis notes.

### `llm_logs/`
- Purpose: full records of every LLM interaction.
- Put here: markdown log files containing the exact prompt, exact response, usage note, and affected outputs.
- Never put here: generated Java code, rewritten summaries instead of transcripts, or mixed-model files.

### `prompts/`
- Purpose: the fixed input set for Phase 1.
- Put here: selected prompt lists and prompt selection rationale only.
- Never put here: rewritten prompt text for initial generation, replacement prompt sets, or ad hoc prompt IDs.

### `tests/`
- Purpose: all test artifacts for Phase 1.
- Put here: dataset base tests, stronger improved tests, and manual black-box artifacts in the correct subfolder.
- Never put here: coverage reports, generated production code, or LLM logs.

### `progress.md`
- Purpose: chronological project progress record.
- Put here: dated status entries, completed prompt milestones, blockers, iteration notes, and major decisions.
- Never put here: raw transcripts, full coverage exports, or large code blocks.

### `README.md`
- Purpose: high-level repository description.
- Put here: concise project-level context if needed.
- Never put here: per-prompt operational notes, raw experiment logs, or detailed working artifacts.

## 4. Detailed test folder rules

### `tests/base_tests/`
- Holds the original dataset tests for the Java HumanEval prompts.
- Also allowed: **minimal compatibility-adjusted variants** when the dataset base test has an expected-value or compatibility issue.
- Any such adjustment must stay on the test side only and must be documented in `analysis/`.
- During this step, **do not modify generated code** to satisfy the base tests.

### `tests/improved_tests/`
- Holds stronger JUnit 6 suites created **after** base testing.
- These tests must be informed by:
- test smell analysis,
- branch coverage analysis,
- observed gaps in the base tests.
- Improved tests are where stronger assertions, missing branches, and cleaner test design belong.

### `tests/manual_tests/`
- Holds human-designed black-box artifacts and executable tests derived from them.
- Required content should cover:
- equivalence classes,
- valid classes,
- invalid classes,
- boundary conditions.
- This folder may contain both markdown design notes and executable JUnit 6 tests derived from those notes.

Recommended per-prompt organization:
- `tests/base_tests/claude/HumanEval_016_BaseTest.java`
- `tests/base_tests/codex/HumanEval_016_BaseTest.java`
- `tests/improved_tests/claude/HumanEval_016_ImprovedTest.java`
- `tests/manual_tests/codex/HumanEval_016_blackbox.md`
- `tests/manual_tests/codex/HumanEval_016_ManualTest.java`

Recommended convention: keep prompt files grouped by model inside each test subfolder so Claude and Codex artifacts never collide.

## 5. Source of truth for inputs

Read inputs from these locations only:
- selected prompts: `prompts/selected_prompts.md`
- selection rationale: `prompts/selection_rationale.md`
- existing generated code: `generated_code/<model>/`
- existing logs: `llm_logs/<model>/`
- existing tests: `tests/base_tests/<model>/`, `tests/improved_tests/<model>/`, `tests/manual_tests/<model>/`

Model-isolation rules:
- When working on `claude`, read only `generated_code/claude/`, `llm_logs/claude/`, `tests/.../claude/`, and `coverage_reports/<prompt>/claude/`.
- When working on `codex`, read only `generated_code/codex/`, `llm_logs/codex/`, `tests/.../codex/`, and `coverage_reports/<prompt>/codex/`.
- Do not read the other model's generated code, improved tests, manual tests, logs, or coverage while producing code, improved tests, manual black-box artifacts, or refactor prompts for the current model.
- Cross-model reading is allowed only for explicit comparison work in `analysis/` after both models' per-prompt artifacts already exist, or when the user explicitly asks for comparison.

Rules:
- Do not invent prompt IDs.
- Do not silently replace selected prompts.
- Treat `prompts/selected_prompts.md` as the source of truth for prompt text used in Phase 1 work.
- Do not read initial prompt text from `prompts/selected_prompts.jsonl`.
- During initial code generation, do **not** rewrite the original prompt text before using it.

## 6. Output placement rules

Write outputs to these locations:

- New generated Java files: `generated_code/<model>/HumanEval_XXX.java`
- Revised Java files after a later iteration: `generated_code/<model>/HumanEval_XXX_v2.java`, `HumanEval_XXX_v3.java`, and so on
- Improved tests: `tests/improved_tests/<model>/HumanEval_XXX_ImprovedTest.java`
- Manual black-box artifacts: `tests/manual_tests/<model>/HumanEval_XXX_blackbox.md`
- Manual executable tests: `tests/manual_tests/<model>/HumanEval_XXX_ManualTest.java`
- Analysis notes: `analysis/HumanEval_XXX_<model>.md`
- Coverage outputs: `coverage_reports/HumanEval_XXX/<model>/...`
- LLM interaction logs: `llm_logs/<model>/HumanEval_XXX_<step>.md`
- Progress updates: append to `progress.md`

Concrete examples:
- `generated_code/claude/HumanEval_016.java`
- `generated_code/codex/HumanEval_016_v2.java`
- `llm_logs/codex/HumanEval_016_initial.md`
- `tests/improved_tests/claude/HumanEval_016_ImprovedTest.java`
- `tests/manual_tests/claude/HumanEval_016_blackbox.md`
- `analysis/HumanEval_016_claude.md`
- `analysis/coverage_summary.md`

Recommended convention: keep one per-prompt analysis note and update it incrementally instead of scattering findings across many small files.

## 7. Required logging format

Every LLM interaction must be logged in `llm_logs/<model>/`. Each log must include:
- prompt ID
- model name
- workflow step
- timestamp if available
- exact prompt
- exact response
- decision note
- output file(s) affected

Use this markdown template:

```markdown
# LLM Interaction Log

- Prompt ID: HumanEval_XXX
- Model: claude | codex
- Workflow Step: initial_generation | refactor | test_improvement | manual_assistance
- Timestamp: YYYY-MM-DD HH:MM:SS
- Output Files:
  - generated_code/<model>/HumanEval_XXX.java

## Exact Prompt
<paste full prompt exactly as sent>

## Exact Response
<paste full model response exactly as received>

## Decision Note
<short note explaining how the output was used, rejected, or changed>
```

Rule: do not replace or rewrite older logs. Create a new log file for every new interaction or iteration.

## 8. Expected workflow order

Follow this Phase 1 sequence for each prompt:

1. Read the selected prompt from `prompts/selected_prompts.md`.
2. Use the **original prompt text without changing it** for the initial generation step.
3. Save the generated Java code to `generated_code/<model>/HumanEval_XXX.java`.
4. Log that interaction in `llm_logs/<model>/HumanEval_XXX_initial.md`.
5. Run the dataset base tests first.
6. Record failures, incompatibilities, and evidence in `analysis/`.
7. If a base test has an expected-value incompatibility, make only a **minor test-side fix** and document it. Do not modify the generated code during this step.
8. Improve tests using test smell analysis and branch coverage analysis from the current model's own artifacts only, then save them under `tests/improved_tests/`.
9. Document the manual black-box assessment in `tests/manual_tests/`, including equivalence classes, valid/invalid classes, and boundary conditions, without reusing the other model's black-box design.
10. If the improved or manual tests reveal a real code problem, return to code generation/refinement with a controlled refactor prompt, save the revised file with a versioned name, and log the interaction separately.
11. Save all outputs, store coverage evidence in `coverage_reports/`, update same-model analysis notes, and append a progress entry to `progress.md`.
12. Only after both models have completed their own prompt workflow may you read across models to write comparative notes in `analysis/`.

## 9. Guardrails / things the agent must not do

- Do not change original prompt wording during initial code generation.
- Do not silently edit generated code before base testing.
- Do not overwrite old logs.
- Do not mix Claude and Codex outputs in the wrong folders.
- Do not let Claude-derived artifacts shape Codex generation steps, and do not let Codex-derived artifacts shape Claude generation steps.
- Do not store coverage reports inside test folders.
- Do not write final-report prose into raw analysis folders unless explicitly asked.
- Do not claim a test passed without evidence.
- Do not create fake data for missing results.
- Do not invent missing prompt text, IDs, coverage numbers, or execution outcomes.
- Do not overwrite existing prompt selection files unless explicitly instructed.

## 10. Recommended naming conventions

Use `HumanEval_XXX` with a zero-padded numeric ID when possible.

- Prompt references: `HumanEval_016`
- Generated code: `HumanEval_016.java`
- Revised code: `HumanEval_016_v2.java`
- Initial log: `HumanEval_016_initial.md`
- Refactor log: `HumanEval_016_refactor_01.md`
- Improved tests: `HumanEval_016_ImprovedTest.java`
- Manual black-box document: `HumanEval_016_blackbox.md`
- Manual executable tests: `HumanEval_016_ManualTest.java`
- Per-prompt analysis note: `HumanEval_016_claude.md`
- Coverage summary file: `analysis/coverage_summary.md`

Recommended convention:
- use `_initial` for first-generation logs,
- use `_refactor_01`, `_refactor_02`, and so on for later code iterations,
- keep model names in folder paths rather than in every filename unless needed for clarity.

## 11. Definition of done for a single prompt

A prompt is complete for Phase 1 only when all of the following exist and are documented:

- code generated and saved in `generated_code/<model>/`
- raw generation logged in `llm_logs/<model>/`
- base tests executed and results recorded
- any allowed base-test compatibility fix documented on the test side only
- improved tests added in `tests/improved_tests/<model>/`
- manual black-box assessment documented in `tests/manual_tests/<model>/`
- executable manual tests added if applicable
- coverage evidence stored in `coverage_reports/`
- refactor loop documented if used
- summary findings added to `analysis/`
- progress entry appended to `progress.md`

## 12. Short example walkthrough

Example for `HumanEval_016`:

- Read the prompt from `prompts/selected_prompts.md`.
- Save the first Claude output to `generated_code/claude/HumanEval_016.java`.
- Save the matching log to `llm_logs/claude/HumanEval_016_initial.md`.
- Run the base tests from `tests/base_tests/claude/HumanEval_016_BaseTest.java`.
- Write stronger follow-up tests to `tests/improved_tests/claude/HumanEval_016_ImprovedTest.java`.
- Write black-box notes to `tests/manual_tests/claude/HumanEval_016_blackbox.md`.
- Save executable manual tests to `tests/manual_tests/claude/HumanEval_016_ManualTest.java`.
- Store coverage output under `coverage_reports/HumanEval_016/claude/`.
- Record findings in `analysis/HumanEval_016_claude.md`.
- Add any cross-prompt roll-up note to `analysis/coverage_summary.md`.

Isolation reminder for the example:
- While producing `claude` artifacts for `HumanEval_016`, do not read `generated_code/codex/`, `llm_logs/codex/`, `tests/.../codex/`, or `coverage_reports/HumanEval_016/codex/`.
- Apply the same rule symmetrically when producing `codex` artifacts.
