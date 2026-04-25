# HumanEval_095 — Claude

## Generation
- Source: `generated_code/claude/HumanEval_095.java`
- Log: `llm_logs/claude/HumanEval_095_initial.md`
- Approach: `checkDictCase(Map<Object, Object>)` short-circuits to
  `false` for an empty map, then iterates the keys. Any non-`String`
  key short-circuits to `false`. For each `String` key two flags
  `allLower` and `allUpper` are kept current: a key that does not
  equal its `toLowerCase()` form clears `allLower`; a key that does
  not equal its `toUpperCase()` form clears `allUpper`. The function
  returns `allLower || allUpper`. Caseless keys (digits, punctuation,
  empty string) are equal to both their upper- and lower-cased forms,
  so they leave both flags untouched and never block a true result.

## Base test
- File: `tests/base_tests/claude/HumanEval_095_BaseTest.java` (JUnit 6 port
  of `tests/base_tests/original/HumanEval_095/Main.java`).
- Adjusted dataset harness:
  `tests/base_tests/adjusted/HumanEval_095/Main.java` (added
  `import java.util.*;` so the dataset `Main.java`, which references
  `Map`, `HashMap`, `List`, and `Arrays`, compiles standalone). See
  `tests/base_tests/adjustment_log.md`.
- Adjusted-harness run: exit `0` (`ADJUSTED_OK`).
- JUnit 6 run: 7/7 pass — the all-lower row, the mixed-case row, the
  non-string-key row, the title-case row, the all-upper row, the
  second all-lower row, and the empty-map row.
- No code defects observed against the spec at this stage; no refactor
  loop triggered.

## Improved suite
- File: `tests/improved_tests/claude/HumanEval_095_ImprovedTest.java` —
  24/24 pass.
- Smells addressed:
  - assertion roulette (the dataset bundled seven booleans into a
    `List<Boolean> correct` and asserted `!contains(false)` →
    one `@Test` per dataset row with `@DisplayName`),
  - magic literals (each map literal is named after its semantic role:
    dataset row, branch coverage edge, edge case, structural property),
  - eager test (split into nested classes by behavioral concern:
    dataset, branch coverage, edge cases, structural properties),
  - conditional logic in tests (no `if` in test bodies; per-input
    `assertTrue`/`assertFalse` replaces looped boolean assertions).
- Behavioral targets:
  - empty-map short-circuit,
  - non-`String` key short-circuit (first key and after a valid key),
  - single-key all-lower / all-upper / mixed-case paths,
  - two-key all-lower / all-upper / mixed paths,
  - caseless keys (digits, empty string, punctuation) alone and
    combined with all-lower or all-upper keys.

## Manual black-box
- Notes: `tests/manual_tests/claude/HumanEval_095_blackbox.md` —
  equivalence classes V1..V13 cover all-lower (single + multi),
  all-upper (single + multi), the four caseless-key variants
  (digits with lower, digits with upper, digits alone, empty string,
  punctuation), and the negative classes (mixed lower+upper, title
  case, single mixed-case key). Invalid partitions I1..I4 cover the
  empty map and three non-`String` key configurations.
- Tests: `tests/manual_tests/claude/HumanEval_095_ManualTest.java` —
  24/24 pass.
- Pinned undefined-by-spec behavior:
  - empty map -> `false` (per spec),
  - any non-`String` key short-circuits to `false`,
  - caseless string keys (digits / empty / `_`) alone or in
    combination with same-case letter keys yield `true`.

## Coverage (JaCoCo 0.8.12, `--release 21`)
- Raw outputs:
  `coverage_reports/HumanEval_095/claude/{base,improved,manual}/{jacoco.exec,jacoco.xml,jacoco.csv,html,junit.txt,jacoco.log}`.
- Base suite: 53/53 instructions, 14/14 branches, 15/15 lines,
  9/9 cyclomatic, 2/2 methods.
- Improved suite: 53/53 instructions, 14/14 branches, 15/15 lines,
  9/9 cyclomatic, 2/2 methods.
- Manual suite: 53/53 instructions, 14/14 branches, 15/15 lines,
  9/9 cyclomatic, 2/2 methods.
- All three suites already hit every branch in the implementation:
  the empty-map short-circuit, the non-`String` `instanceof` branch,
  both `equals(toLowerCase())` arms, both `equals(toUpperCase())`
  arms, the loop-continuation branch, and the final
  `allLower || allUpper` short-circuit.

## Defects vs. spec
- None observed. The empty-map case returns `false` per the prompt,
  the non-string key case returns `false` per the dataset row, the
  all-lower and all-upper rows return `true`, and title-/mixed-case
  inputs return `false`. No refactor loop triggered.
