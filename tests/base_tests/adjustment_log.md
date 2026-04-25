# Base-test adjustment log

This file records every minor, test-side compatibility fix made to dataset base tests under `tests/base_tests/original/`. Adjusted variants live in `tests/base_tests/adjusted/<HumanEval_XXX>/`. Per CLAUDE.md §4, no generated code is modified to satisfy a base test — fixes stay on the test side only.

## HumanEval_016

- Date: 2026-04-25
- Original: `tests/base_tests/original/HumanEval_016/Main.java`
- Adjusted: `tests/base_tests/adjusted/HumanEval_016/Main.java`
- Change: added `import java.util.*;` at the top of `Main.java`. The original references `List` and `Arrays` without importing them, so it does not compile as a standalone file.
- Why this is a "minor test-side fix": the assertion logic, expected values, and inputs are unchanged. Only an import line was added to make the dataset harness compilable.
- Generated code modified? No.
