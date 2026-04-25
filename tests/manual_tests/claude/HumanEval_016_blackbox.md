# HumanEval_016 — Manual black-box assessment (Claude)

Function under test: `Solution.countDistinctCharacters(String string)`
Spec (verbatim from the prompt):
> Given a string, find out how many distinct characters (regardless of case) does it consist of.

The spec is short, so the manual analysis below intentionally extracts assumptions that the spec leaves implicit and pins them as test cases.

---

## 1. Input domain

The single input is a Java `String`. Treating the spec literally, the relevant input dimensions are:

| Dimension | Possible values |
|-----------|-----------------|
| Reference | `null`, non-`null` |
| Length | `0`, `1`, `n>1` |
| Character categories present | letters only, digits only, punctuation/symbols only, whitespace only, mixed |
| Letter case | all-lower, all-upper, mixed; cases that share a fold (e.g. `'A'`/`'a'`) vs cases that do not (e.g. `'1'`/`'1'`) |
| Repetition pattern | all-distinct, all-same, partial duplicates |
| Encoding shape | ASCII only; non-ASCII BMP letters with case (e.g. `Ä`/`ä`); supplementary code points encoded as a UTF-16 surrogate pair |

The output is `int`, the count of distinct characters under case-insensitive comparison.

---

## 2. Equivalence classes

### Valid classes (spec says "given a string", so any non-null string is valid)

| ID | Class | Example | Expected |
|----|-------|---------|----------|
| V1 | Empty string | `""` | `0` |
| V2 | Single character | `"a"` | `1` |
| V3 | All-distinct ASCII letters, single case | `"abcde"` | `5` |
| V4 | Same letter repeated, mixed case only | `"aaaaAAAAaaaa"` | `1` |
| V5 | Mixed-case ASCII letters with duplicates that fold together | `"abcdecadeCADE"` | `5` |
| V6 | Mixed letters + spaces, multiple casings | `"Jerry jERRY JeRRRY"` | `5` (incl. space) |
| V7 | Digits only (case-fold is a no-op) | `"0123456789"` | `10` |
| V8 | Punctuation/symbols only | `"!?.,;"` | `5` |
| V9 | Whitespace only | `"   "` | `1` |
| V10 | Letters + digits + whitespace mixed | `"Aa Bb 11 22"` | `5` |
| V11 | Non-ASCII BMP letters with case | `"ÄäÖöÜü"` | `3` |

### Invalid / undefined-by-spec classes

| ID | Class | Behavior | How we treat it |
|----|-------|----------|-----------------|
| I1 | `null` reference | Implementation calls `string.toLowerCase()` → `NullPointerException` | Pinned as expected behavior; manual test asserts NPE so a future refactor that silently returns `0` is caught. |
| I2 | Surrogate pair (one code point, two `char`s) | Implementation iterates over `char`s, so a single emoji counts as 2 | Pinned as expected behavior; documents UTF-16 char-level semantics. |
| I3 | Locale-sensitive case folding (Turkish dotted/dotless I) | `String.toLowerCase()` without a locale uses root mapping; `İ` (U+0130) decomposes to `i` + combining dot above | Pinned as expected behavior; surfaces if the impl ever switches to a Turkish locale. |

---

## 3. Boundary analysis

| Boundary | Just below | On boundary | Just above |
|----------|------------|-------------|------------|
| Length 0 | n/a | `""` → `0` | `"a"` → `1` |
| Length 1 | `""` → `0` | `"a"` → `1` | `"ab"` → `2` |
| All same vs one different | `"aaaa"` → `1` | `"aaab"` → `2` | `"aabc"` → `3` |
| ASCII alphabet upper bound | 25 distinct letters | `"abcdefghijklmnopqrstuvwxyz"` → `26` | `"abcdefghijklmnopqrstuvwxyzA"` → still `26` (case fold) |
| Digit alphabet | 9 distinct digits | `"0123456789"` → `10` | `"01234567890"` → still `10` |

Boundary rationale: the function's only real "size" boundary is `length 0` vs `length ≥ 1`; the more interesting boundaries are around saturation of the available case-folded character set (full alphabet, full digit set), where adding one more character either does or does not change the count.

---

## 4. Special-character / encoding cases

| Case | Input | Expected | Reason |
|------|-------|----------|--------|
| Tab vs space | `"a\tb a b"` | 4 (`a`, `\t`, `b`, ` `) | whitespace variants are distinct chars |
| Repeated punctuation | `"!!!"` | 1 | duplicates fold |
| Mixed BMP non-ASCII | `"ÄäÖöÜü"` | 3 | case-insensitive fold |
| Surrogate pair | `"😀"` (U+1F600) | 2 | char-level counting (documented) |
| Two different surrogate pairs | `"😀😃"` | 4 | both pairs share no UTF-16 code units pairwise; each pair contributes 2 distinct units |

---

## 5. Coverage of the manual cases against the implementation

The implementation has two branches:
1. The for-each loop's continue/exit branch (covered by any non-empty input AND by the empty-string case).
2. The `Set.add` insertion semantics (no observable branch in source, but exercised by inputs that contain at least one duplicate vs no duplicates).

The manual tests below intentionally exercise both:
- empty string (V1) — loop never enters,
- single-char (V2) — loop runs once,
- duplicates (V4, V5, V11) — `add` returns false at least once,
- distinct (V3, V7, V8) — `add` always returns true.

Plus the three pinned undefined-by-spec cases (I1, I2, I3).
