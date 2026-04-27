# LLM-Based Testing Pipeline

**BLG 475E — Software Quality & Testing** | Istanbul Technical University, Spring 2026

A research pipeline that compares two large language models on their ability to produce correct Java solutions for algorithmic problems, then subjects each solution to a rigorous, multi-layer test regime.

---

## Overview

This project implements a structured **Phase 1** experiment:

1. Select 30 algorithmic prompts from the [HumanEval](https://github.com/openai/human-eval) dataset (Java port).
2. Send each prompt *verbatim* to **two LLMs** — **Claude** (general-purpose) and **Codex** (code-specialised).
3. For every (prompt, model) pair, apply a three-layer testing workflow:
   - **Base tests** — the original dataset harness, ported to JUnit 6.
   - **Improved tests** — test-smell-driven and branch-coverage-guided additions.
   - **Manual black-box tests** — equivalence classes, boundary analysis, and invalid-input coverage designed independently of the code.
4. Measure JaCoCo coverage (instruction, branch, line, cyclomatic complexity, method) for each layer.
5. Trigger a **refactor loop** when tests expose real code defects, log every LLM interaction, and keep the full history traceable.

---

## Repository Structure

```
.
├── generated_code/
│   ├── claude/          # Claude-generated Java solutions (HumanEval_XXX.java, _v2.java …)
│   └── codex/           # Codex-generated Java solutions
│
├── tests/
│   ├── base_tests/
│   │   ├── original/    # Unmodified dataset harnesses (Main.java per prompt)
│   │   ├── adjusted/    # Minimal import-only or method-name fixes (see adjustment_log.md)
│   │   ├── claude/      # JUnit 6 ports — HumanEval_XXX_BaseTest.java
│   │   └── codex/
│   ├── improved_tests/
│   │   ├── claude/      # Smell-fixed, coverage-guided suites
│   │   └── codex/
│   └── manual_tests/
│       ├── claude/      # HumanEval_XXX_blackbox.md + HumanEval_XXX_ManualTest.java
│       └── codex/
│
├── coverage_reports/
│   └── HumanEval_XXX/
│       ├── claude/
│       │   ├── base/    # jacoco.exec, jacoco.xml, jacoco.csv, HTML report, junit.txt
│       │   ├── improved/
│       │   └── manual/
│       └── codex/
│
├── llm_logs/
│   ├── claude/          # HumanEval_XXX_initial.md, _refactor_01.md, …
│   └── codex/
│
├── analysis/
│   ├── coverage_summary.md          # Roll-up coverage table (all prompts × models × suites)
│   ├── base_test_adjustments.md     # Documents every test-side harness fix
│   ├── HumanEval_XXX_claude.md      # Per-prompt findings (Claude)
│   └── HumanEval_XXX_codex.md       # Per-prompt findings (Codex)
│
├── prompts/
│   ├── selected_prompts.md          # Authoritative prompt text for Phase 1 (30 prompts)
│   └── selection_rationale.md       # Why each prompt was chosen; difficulty tiers
│
├── progress.md          # Chronological project log (append-only)
├── CLAUDE.md            # Phase 1 agent operating contract
└── AGENTS.md            # Agent guardrails and workflow rules
```

---

## Selected Prompts

30 problems spanning three difficulty tiers and five domain categories:

| Tier | Count | Domain spread |
|------|------:|---------------|
| Easy | 10 | String ops, basic math, list filtering |
| Medium | 10 | Encoding, parsing, numeric edge cases |
| Hard | 10 | Graph / grid algorithms, complex string reasoning |

**Full list (by HumanEval ID):**

`001` `002` `007` `016` `018` `019` `020` `023` `028` `036` `039` `040` `057` `059` `075` `081` `087` `089` `090` `093` `095` `097` `098` `099` `100` `103` `106` `108` `122` `129`

See [`prompts/selected_prompts.md`](prompts/selected_prompts.md) for verbatim problem statements and [`prompts/selection_rationale.md`](prompts/selection_rationale.md) for rationale.

---

## Workflow (per prompt, per model)

```
Read prompt (verbatim)
       │
       ▼
Generate code → generated_code/<model>/HumanEval_XXX.java
       │
       ▼
Log interaction → llm_logs/<model>/HumanEval_XXX_initial.md
       │
       ▼
Port dataset harness to JUnit 6 → tests/base_tests/<model>/
Run with JaCoCo → coverage_reports/HumanEval_XXX/<model>/base/
       │
       ├─ Defects found? ──► refactor prompt → _v2.java + refactor_01.md log → re-run
       │
       ▼
Analyse coverage gaps + test smells
Write improved tests → tests/improved_tests/<model>/
Run with JaCoCo → .../improved/
       │
       ▼
Manual black-box assessment (ECs, BCs, invalid inputs)
       → tests/manual_tests/<model>/HumanEval_XXX_blackbox.md
       → tests/manual_tests/<model>/HumanEval_XXX_ManualTest.java
Run with JaCoCo → .../manual/
       │
       ▼
Summarise → analysis/HumanEval_XXX_<model>.md
Update  → analysis/coverage_summary.md
Append  → progress.md
```

---

## Toolchain

| Tool | Version | Role |
|------|---------|------|
| Java | 21 (LTS) | Compilation target (`javac --release 21`) |
| JUnit Jupiter | 5.x (JUnit 6) | Test runner |
| JUnit Platform Console Standalone | 1.x | `ConsoleLauncher execute` runner |
| JaCoCo | 0.8.12 | Coverage instrumentation and reporting |

Tests are compiled and run from the command line — no build tool (Maven/Gradle) is required. Each coverage report folder contains the full JaCoCo HTML report alongside `jacoco.xml`, `jacoco.csv`, and the raw `jacoco.exec` binary.

---

## Coverage Summary (Phase 1 progress)

12 of the 30 selected prompts have been fully processed for both models. All tests pass (100% pass rate across every suite). Coverage highlights:

| Prompt | Domain | Claude base → manual (branch) | Codex base → manual (branch) |
|--------|--------|-------------------------------|-------------------------------|
| HumanEval_007 | String filtering | 4/4 → 4/4 | 4/4 → 4/4 |
| HumanEval_016 | Character counting | 2/2 → 2/2 | 2/2 → 2/2 |
| HumanEval_018 | Substring search | **8/10 → 10/10** | **7/10 → 10/10** |
| HumanEval_019 | Sort by digit sum | **3/4 → 4/4** | **3/4 → 4/4** |
| HumanEval_020 | Closest pair | 4/4 → 4/4 | **6/8 → 8/8** |
| HumanEval_036 | FizzBuzz variant | 10/10 → 10/10 | 10/10 → 10/10 |
| HumanEval_040 | Triples sum to zero | 10/10 → 10/10 | **11/12 → 12/12** |
| HumanEval_089 | Caesar cipher | **5/10 → 10/10** | **4/6 → 6/6** |
| HumanEval_093 | Encode / case-swap | **10/12 → 12/12** | **20/22 → 22/22** |
| HumanEval_099 | Closest integer | 4/4 → 4/4 | **4/6 → 6/6** |
| HumanEval_106 | Factorial / sum list | 6/6 → 6/6 | **5/6 → 6/6** |
| HumanEval_129 | Min path in grid | 20/20 → 20/20 | **24/28 → 27/28** |

**Bold** entries = base tests did not achieve full branch coverage; improved/manual tests closed the gap. Full per-suite metrics are in [`analysis/coverage_summary.md`](analysis/coverage_summary.md).

---

## Key Conventions

- **Prompt IDs** are zero-padded three-digit numbers: `HumanEval_016`, not `HumanEval_16`.
- **Generated code is never modified** before base testing. Base-test results reflect raw LLM output.
- **Refactored code** is saved as `HumanEval_XXX_v2.java`, `_v3.java`, etc. The original file is never overwritten.
- **LLM logs are immutable.** Every new interaction (initial, refactor, test-improvement prompt) gets its own markdown file.
- **Model isolation:** Claude artifacts (`generated_code/claude/`, `tests/.../claude/`, etc.) and Codex artifacts are never mixed. Cross-model comparison is confined to `analysis/`.
- **Test-side compatibility fixes** (import additions, method-name corrections for dataset bugs) are documented in [`analysis/base_test_adjustments.md`](analysis/base_test_adjustments.md) and are never applied to the generated code.

---

## Known Issues / Limitations (Phase 1)

- 18 of the 30 selected prompts have not yet had code generated or tests run.
- `selection_rationale.md` contains naming mismatches for at least `Java/129` (describes *maxFill* instead of *minPath*) and `Java/95` (*checkDictKeys* vs *checkDictCase*); these are documentation inconsistencies only and do not affect the experiment.
- JaCoCo 0.8.12 does not instrument Java 25+ bytecode; all compilation uses `--release 21`.

---

## Repository Rules

The operating contracts for this experiment live in:

- [`CLAUDE.md`](CLAUDE.md) — Phase 1 workflow, directory map, logging format, guardrails.
- [`AGENTS.md`](AGENTS.md) — Complementary agent-operating rules (mirrors CLAUDE.md).

Any deviation from the workflow defined in those files must be approved and logged in `progress.md`.
