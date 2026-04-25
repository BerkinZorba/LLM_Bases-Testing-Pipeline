# CLAUDE.md — Phase 1 Operating Guide

## 1. Purpose of this file

This file is the operating contract for any AI agent working in this repository **during Phase 1 only**. It defines where to read inputs, where to write outputs, what must never change, the workflow order, and the logging format. Phase 2 is explicitly out of scope for this document.

If an instruction in a user message conflicts with this file, ask before deviating.

---

## 2. Phase 1 objective

Phase 1 compares **two LLMs** (one general-purpose, one code-specialized) on **30 selected Java prompts** from the HumanEval Java dataset. For each selected prompt the agent must:

1. Generate Java code from the **original, unmodified** HumanEval prompt with each of the two LLMs.
2. Execute the dataset's **base tests** (JUnit 6) against the generated code.
3. Improve the test suites using **test-smell** and **branch-coverage** evidence.
4. Perform a **manual black-box assessment** using equivalence classes, valid/invalid partitions, and boundary conditions.
5. Collect coverage and failure evidence and store it for analysis.
6. Iterate: if tests reveal real code defects, re-prompt the LLM with a controlled refactor request and log the loop.
7. Keep the repository history traceable: every LLM interaction is logged in full.

Language and tools: **Java + JUnit 6**. No other language/framework should be introduced in Phase 1.

---

## 3. Directory map

### `analysis/`
- **For**: written summaries, comparison tables, smell analyses, coverage roll-ups, defect/iteration notes.
- **Files**: `.md` summaries, `.csv`/`.tsv` tables, small `.json` aggregates.
- **Never**: raw coverage HTML dumps, generated `.java` files, raw LLM transcripts, final-report prose unless explicitly requested.

### `coverage_reports/`
- **For**: raw coverage outputs (e.g., JaCoCo XML/HTML), per-prompt coverage artifacts.
- **Files**: `HumanEval_XXX/<model>/jacoco/...`, `HumanEval_XXX_<model>_coverage.xml`.
- **Never**: source code, tests, prose analysis. Summarized findings live in `analysis/`.

### `generated_code/`
- **For**: Java source files produced by an LLM, organized per model.
- Subfolders: `claude/`, `codex/`. (Folder names are the canonical model labels for Phase 1.)
- **Files**: `HumanEval_XXX.java`, `HumanEval_XXX_v2.java` (after a refactor loop).
- **Never**: tests, logs, coverage data, or files from the other model.

### `llm_logs/`
- **For**: complete records of every LLM interaction, organized per model.
- Subfolders: `claude/`, `codex/`.
- **Files**: one markdown file per (prompt, step, iteration) — see Section 7.
- **Never**: edited or "cleaned up" transcripts. Logs are append-only and immutable once committed.

### `prompts/`
- **For**: the canonical, frozen input set.
  - `selected_prompts.md` — canonical selected prompts for Phase 1 prompt reading.
  - `selected_prompts.jsonl` — auxiliary structured file; do not use it as the prompt-reading source for agent work.
  - `selection_rationale.md` — why these 30 were chosen.
- **Never**: rewritten prompt text, additional prompts mid-experiment, or per-model variants. The agent must not edit these files unless the user explicitly asks.

### `tests/`
- **For**: all JUnit 6 tests. See Section 4 for sub-folder rules.

### `progress.md`
- **For**: chronological project log (decisions, milestones, blockers).
- **Update rule**: append a dated entry whenever a significant action is taken (prompt set frozen, model run completed, iteration triggered, etc.). Do not rewrite history.

### `README.md`
- **For**: project description for humans landing on the repo.
- The agent should keep it current at a high level but should not turn it into an analysis document.

---

## 4. Detailed test folder rules

All tests are JUnit 6 and are organized **per model** under each subfolder (one suite per LLM per prompt).

### `tests/base_tests/`
- Contains the **original HumanEval dataset tests**, ported to JUnit 6 where required.
- If a base test has an **incompatibility in expected values or signatures**, only **minor test-side adjustments** are allowed at this stage. Adjustments must be documented inline and logged in `analysis/base_test_adjustments.md`.
- **Do not modify the generated code** to make a base test pass at this stage. Base-test results are diagnostic.
- Recommended layout:
  ```
  tests/base_tests/<model>/HumanEval_XXX_BaseTest.java
  ```

### `tests/improved_tests/`
- Contains stronger suites built **after** base testing, informed by:
  - test-smell analysis (e.g., assertion roulette, eager test, magic numbers, conditional logic in tests),
  - branch-coverage evidence from `coverage_reports/`.
- Each improved suite must be paired with a short note in `analysis/` explaining what smells were addressed and which branches it now exercises.
- Recommended layout:
  ```
  tests/improved_tests/<model>/HumanEval_XXX_ImprovedTest.java
  ```

### `tests/manual_tests/`
- Contains **human-designed black-box artifacts** plus the executable JUnit tests derived from them.
- Required artifacts per prompt:
  - `HumanEval_XXX_blackbox.md` — equivalence classes, valid/invalid partitions, boundary table.
  - `HumanEval_XXX_ManualTest.java` — JUnit 6 cases that materialize those classes.
- Recommended layout:
  ```
  tests/manual_tests/<model>/HumanEval_XXX_blackbox.md
  tests/manual_tests/<model>/HumanEval_XXX_ManualTest.java
  ```

**Recommended convention**: per-prompt files live under `<model>/` inside each test folder so that the same prompt-ID can be compared across models without collision.

---

## 5. Source of truth for inputs

Read inputs only from these locations:

- **Selected prompts**: `prompts/selected_prompts.md` (authoritative for Phase 1 prompt reading).
- **Selection rationale**: `prompts/selection_rationale.md`.
- **Existing generated code**: `generated_code/<model>/`.
- **Existing logs**: `llm_logs/<model>/`.
- **Existing tests**: `tests/base_tests/<model>/`, `tests/improved_tests/<model>/`, `tests/manual_tests/<model>/`.
- **Coverage evidence**: `coverage_reports/<prompt>/<model>/`.

Model-isolation rules:
- If you are producing `claude` artifacts, do not read `generated_code/codex/`, `llm_logs/codex/`, `tests/.../codex/`, or `coverage_reports/<prompt>/codex/`.
- If you are producing `codex` artifacts, do not read `generated_code/claude/`, `llm_logs/claude/`, `tests/.../claude/`, or `coverage_reports/<prompt>/claude/`.
- This isolation applies to initial generation, base-test execution, improved-test authoring, manual black-box design, and refactor prompting.
- Cross-model reading is allowed only for explicit comparison work in `analysis/` after both models' per-prompt artifacts exist, or when the user explicitly asks for cross-model comparison.

Hard rules:
- **Do not invent prompt IDs.** Only use IDs that appear in `selected_prompts.md`.
- **Do not silently replace selected prompts.** If a prompt is unworkable, surface it to the user and update `selection_rationale.md` only after approval.
- **Do not read initial prompt text from `selected_prompts.jsonl`.** Use `selected_prompts.md` so prompt reading stays aligned with the research intent.
- **Do not rewrite the original prompt text** when performing the *initial* code-generation step. The prompt must be sent to the LLM verbatim.
- Refactor-loop prompts (Section 8, step 9) are separate prompts and must be logged as such — they do **not** replace the original prompt.

---

## 6. Output placement rules

| Artifact                          | Path                                                                 |
| --------------------------------- | -------------------------------------------------------------------- |
| Initial generated Java            | `generated_code/<model>/HumanEval_XXX.java`                          |
| Revised Java after refactor loop  | `generated_code/<model>/HumanEval_XXX_v2.java` (increment as needed) |
| Base tests (and minor fixes)      | `tests/base_tests/<model>/HumanEval_XXX_BaseTest.java`               |
| Improved tests                    | `tests/improved_tests/<model>/HumanEval_XXX_ImprovedTest.java`       |
| Manual black-box notes            | `tests/manual_tests/<model>/HumanEval_XXX_blackbox.md`               |
| Manual JUnit 6 tests              | `tests/manual_tests/<model>/HumanEval_XXX_ManualTest.java`           |
| Raw coverage output               | `coverage_reports/HumanEval_XXX/<model>/...`                         |
| Coverage roll-up summary          | `analysis/coverage_summary.md`                                       |
| Per-prompt analysis note          | `analysis/HumanEval_XXX_<model>.md`                                  |
| LLM interaction log               | `llm_logs/<model>/HumanEval_XXX_<step>.md`                           |
| Project progress                  | `progress.md` (append entries)                                       |

Concrete examples:

- `generated_code/claude/HumanEval_016.java`
- `generated_code/codex/HumanEval_016_v2.java`
- `llm_logs/codex/HumanEval_016_initial.md`
- `llm_logs/claude/HumanEval_016_refactor_01.md`
- `tests/base_tests/claude/HumanEval_016_BaseTest.java`
- `tests/improved_tests/claude/HumanEval_016_ImprovedTest.java`
- `tests/manual_tests/codex/HumanEval_016_blackbox.md`
- `coverage_reports/HumanEval_016/claude/jacoco.xml`
- `analysis/HumanEval_016_claude.md`
- `analysis/coverage_summary.md`

---

## 7. Required logging format

Every LLM interaction (initial generation, base-test fix request, refactor loop, test-improvement prompt, etc.) must produce one markdown log file in `llm_logs/<model>/`. Logs are immutable; a new step gets a new file.

**Filename pattern**: `HumanEval_XXX_<step>[_NN].md`
where `<step>` ∈ {`initial`, `base_fix`, `refactor`, `test_improve`, `manual_assist`} and `NN` is a zero-padded iteration counter when the same step repeats.

**Required template** (copy verbatim and fill in):

```markdown
---
prompt_id: HumanEval_XXX
model: claude | codex
step: initial | base_fix | refactor | test_improve | manual_assist
iteration: 1
timestamp: YYYY-MM-DDTHH:MM:SS (local or UTC; mark which)
output_files:
  - generated_code/<model>/HumanEval_XXX.java
---

## Prompt (verbatim)
<paste the EXACT prompt text sent to the LLM, no edits>

## Response (verbatim)
<paste the EXACT response from the LLM, no edits>

## Decision note
- How was this output used? (saved as-is / minor formatting / rejected / triggered refactor)
- If changed before saving, what changed and why?
- What downstream artifacts did this produce or invalidate?
```

If `timestamp` is not available, write `timestamp: unknown` rather than guessing.

---

## 8. Expected workflow order

For each prompt, in order:

1. **Read the selected prompt** from `prompts/selected_prompts.md`.
2. **Generate code** by sending the prompt **verbatim** to the target LLM.
3. **Save code** to `generated_code/<model>/HumanEval_XXX.java`.
4. **Log the interaction** to `llm_logs/<model>/HumanEval_XXX_initial.md` using the template in Section 7.
5. **Run base tests** from `tests/base_tests/<model>/HumanEval_XXX_BaseTest.java`. If the dataset's base test has an incompatibility, apply a **minimal test-side fix only** and document it in `analysis/base_test_adjustments.md`. Do not edit the generated code at this step.
6. **Record failures and incompatibilities** in `analysis/HumanEval_XXX_<model>.md` (failing cases, error messages, suspected root cause, whether it looks like a code defect or a test mismatch).
7. **Improve tests** using test-smell findings and branch-coverage evidence from the current model's own artifacts only. Save to `tests/improved_tests/<model>/HumanEval_XXX_ImprovedTest.java` and note rationale in the per-prompt analysis file.
8. **Document manual black-box assessment**: equivalence classes, valid/invalid partitions, boundary cases in `tests/manual_tests/<model>/HumanEval_XXX_blackbox.md`, plus the executable JUnit tests in `HumanEval_XXX_ManualTest.java`. Do not reuse the other model's black-box design.
9. **If tests reveal real code defects**, issue a **controlled refactor prompt** to the LLM, save the new code as `HumanEval_XXX_v2.java` (increment as needed), and log it as `llm_logs/<model>/HumanEval_XXX_refactor_01.md`. Re-run tests. Loop until pass or until the user calls a stop.
10. **Save all outputs** and update `analysis/HumanEval_XXX_<model>.md` and append a dated entry to `progress.md`.
11. **Only after both models are complete for the prompt**, write any cross-model comparison to `analysis/coverage_summary.md` or other comparison notes.

---

## 9. Guardrails — things the agent must not do

- **Do not change original prompt wording** during the initial generation step. Send it verbatim.
- **Do not silently edit generated code** before base testing. Base-test results must reflect the LLM's raw output.
- **Do not overwrite existing logs.** Logs are append-only; new iterations get new files.
- **Do not mix Claude and Codex outputs** across folders. `claude/` content stays under `claude/`; same for `codex/`.
- **Do not read the other model's prompt-specific artifacts** while generating or refining the current model's artifacts unless the user explicitly instructs you to compare.
- **Do not mirror or normalize test designs across models** during model-specific generation. Shared benchmark suites are allowed only if the user explicitly asks for a common suite.
- **Do not store coverage reports inside test folders.** Coverage lives in `coverage_reports/` only.
- **Do not write final-report prose** into `analysis/` unless the user explicitly asks. `analysis/` is for working notes and structured evidence.
- **Do not claim a test passed without evidence.** Cite the run output, the coverage file, or the JUnit report.
- **Do not fabricate data** for missing results. If a step did not run, write `not_run` and explain why.
- **Do not introduce non-Java tooling** (Python harnesses, shell glue beyond build/test) without user approval.
- **Do not modify `prompts/`** files mid-experiment.
- **Do not delete** anything in `llm_logs/`, `generated_code/`, `tests/`, or `coverage_reports/`.

---

## 10. Recommended naming conventions

Use the HumanEval ID as the stable key everywhere. Pad to three digits.

| Artifact            | Pattern                                                          |
| ------------------- | ---------------------------------------------------------------- |
| Prompt ID           | `HumanEval_016`                                                  |
| Generated code      | `HumanEval_016.java`                                             |
| Revised code        | `HumanEval_016_v2.java`, `HumanEval_016_v3.java`                 |
| Base test           | `HumanEval_016_BaseTest.java`                                    |
| Improved test       | `HumanEval_016_ImprovedTest.java`                                |
| Manual test (code)  | `HumanEval_016_ManualTest.java`                                  |
| Manual test (notes) | `HumanEval_016_blackbox.md`                                      |
| LLM log             | `HumanEval_016_initial.md`, `HumanEval_016_refactor_01.md`, etc. |
| Per-prompt analysis | `HumanEval_016_claude.md`                                        |
| Coverage roll-up    | `coverage_summary.md`                                            |

**Recommended convention**: Java class names mirror file names exactly (e.g., `HumanEval_016`, `HumanEval_016_BaseTest`) so that build tooling and test runners line up without per-file configuration.

---

## 11. Definition of done for a single prompt

A prompt is complete in Phase 1 when **all** of the following are true for **each** of the two models:

- [ ] Initial Java code generated from the verbatim prompt and saved under `generated_code/<model>/`.
- [ ] Initial generation logged in `llm_logs/<model>/HumanEval_XXX_initial.md` with full prompt and full response.
- [ ] Base tests executed; results (pass/fail, errors, any minor base-test fixes) recorded in `analysis/HumanEval_XXX_<model>.md`.
- [ ] Improved test suite added under `tests/improved_tests/<model>/`, with a smell/coverage rationale in the analysis file.
- [ ] Manual black-box assessment documented (`*_blackbox.md`) and materialized as JUnit tests (`*_ManualTest.java`).
- [ ] Coverage evidence stored under `coverage_reports/HumanEval_XXX/<model>/`.
- [ ] Refactor loop, if triggered, fully documented: each iteration has its own log file and its own `_vN.java`.
- [ ] Per-prompt summary in `analysis/HumanEval_XXX_<model>.md` and a row added to `analysis/coverage_summary.md`.
- [ ] Dated entry appended to `progress.md`.

If any item is `not_run`, that must be stated explicitly with a reason.

---

## 12. Short example walkthrough — `HumanEval_016` with Claude

1. **Read input**: locate `HumanEval_016` in `prompts/selected_prompts.md`.
2. **Generate**: send the prompt verbatim to Claude.
3. **Save code**: `generated_code/claude/HumanEval_016.java`.
4. **Log**: `llm_logs/claude/HumanEval_016_initial.md` (full prompt, full response, decision note).
5. **Base test**: run `tests/base_tests/claude/HumanEval_016_BaseTest.java`. Record pass/fail + any test-side compatibility fix in `analysis/HumanEval_016_claude.md` and `analysis/base_test_adjustments.md`.
6. **Coverage**: store JaCoCo (or equivalent) output at `coverage_reports/HumanEval_016/claude/`.
7. **Improve tests**: write `tests/improved_tests/claude/HumanEval_016_ImprovedTest.java`; note smells fixed and branches now covered in `analysis/HumanEval_016_claude.md`.
8. **Black-box**: write `tests/manual_tests/claude/HumanEval_016_blackbox.md` (equivalence classes, valid/invalid, boundaries) and the corresponding `HumanEval_016_ManualTest.java`.
9. **Refactor loop (if needed)**: send the refactor prompt to Claude; save the new code as `generated_code/claude/HumanEval_016_v2.java`; log it as `llm_logs/claude/HumanEval_016_refactor_01.md`; re-run tests and update coverage.
10. **Summary**: finalize `analysis/HumanEval_016_claude.md`, append a row to `analysis/coverage_summary.md`, append a dated entry to `progress.md`.

Repeat steps 1–10 with Codex, writing under `generated_code/codex/`, `llm_logs/codex/`, `tests/.../codex/`, and `coverage_reports/HumanEval_016/codex/`. Cross-model comparison belongs in `analysis/`, never inside `generated_code/` or `tests/`.

Isolation reminder for the example:
- While producing the Claude side of `HumanEval_016`, do not read Codex prompt-specific artifacts.
- While producing the Codex side of `HumanEval_016`, do not read Claude prompt-specific artifacts.
