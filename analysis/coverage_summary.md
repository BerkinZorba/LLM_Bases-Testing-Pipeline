# Coverage roll-up — Phase 1

Source: per-suite JaCoCo CSV under `coverage_reports/<HumanEval_XXX>/<model>/<suite>/jacoco.csv`. Compiled with `javac --release 21` (JaCoCo 0.8.12 does not support Java 25 bytecode).

| Prompt | Model | Suite | Tests | Pass | Instr | Branch | Line | CC | Method |
|--------|-------|-------|-------|------|-------|--------|------|----|--------|
| HumanEval_016 | claude | base     | 5  | 5  | 33/33 | 2/2 | 5/5 | 3/3 | 2/2 |
| HumanEval_016 | claude | improved | 18 | 18 | 33/33 | 2/2 | 5/5 | 3/3 | 2/2 |
| HumanEval_016 | claude | manual   | 21 | 21 | 33/33 | 2/2 | 5/5 | 3/3 | 2/2 |
| HumanEval_016 | codex  | base     | 5  | 5  | 33/33 | 2/2 | 5/5 | 3/3 | 2/2 |
| HumanEval_016 | codex  | improved | 17 | 17 | 33/33 | 2/2 | 5/5 | 3/3 | 2/2 |
| HumanEval_016 | codex  | manual   | 17 | 17 | 33/33 | 2/2 | 5/5 | 3/3 | 2/2 |
| HumanEval_036 | claude | base     | 8  | 8  | 37/37 | 10/10 | 10/10 | 7/7 | 2/2 |
| HumanEval_036 | claude | improved | 24 | 24 | 37/37 | 10/10 | 10/10 | 7/7 | 2/2 |
| HumanEval_036 | claude | manual   | 20 | 20 | 37/37 | 10/10 | 10/10 | 7/7 | 2/2 |
| HumanEval_036 | codex  | base     | 8  | 8  | 37/37 | 10/10 | 10/10 | 7/7 | 2/2 |
| HumanEval_036 | codex  | improved | 25 | 25 | 37/37 | 10/10 | 10/10 | 7/7 | 2/2 |
| HumanEval_036 | codex  | manual   | 14 | 14 | 37/37 | 10/10 | 10/10 | 7/7 | 2/2 |
| HumanEval_007 | codex | base     | 4  | 4  | 28/28 | 4/4 | 7/7 | 4/4 | 2/2 |
| HumanEval_007 | codex | improved | 11 | 11 | 28/28 | 4/4 | 7/7 | 4/4 | 2/2 |
| HumanEval_007 | codex | manual   | 18 | 18 | 28/28 | 4/4 | 7/7 | 4/4 | 2/2 |
