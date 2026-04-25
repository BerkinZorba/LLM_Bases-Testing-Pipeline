# Selection Rationale – 30 Prompts

## Easy (10 prompts)

### Java/2 – truncateNumber
Tests floating-point modulo operation. A baseline for precision handling and decimal part extraction. Edge cases: integers, small decimals.

### Java/7 – filterBySubstring
A combination of list filtering and substring matching. Tests iteration together with conditional logic. Edge cases: empty list, no matches, case sensitivity.

### Java/16 – countDistinctCharacters
Requires case-insensitive character counting, normalization, and use of a Set. Tests core building blocks of string processing.

### Java/18 – howManyTimes
Overlapping substring detection. Critical for correct implementation of a naive string search algorithm. Edge cases: empty strings, overlaps.

### Java/20 – findClosestElements
Pairwise comparison and minimum distance finding. Tests awareness of O(n²) complexity and tuple return handling.

### Java/23 – strlen
The simplest string operation. A perfect baseline for testing Unicode handling, null safety, and boundary conditions.

### Java/28 – concatenate
List iteration and string joining. A basic test for use of the Java String API and handling of empty lists.

### Java/36 – fizzBuzz
Combines divisibility logic, digit counting, and string-to-int conversion. Requires more complex reasoning than classic FizzBuzz.

### Java/40 – triplesSumToZero
Triple nested loop and combination search. Tests awareness of O(n³) complexity and duplicate handling. The most challenging in the easy category.

### Java/57 – monotonic
Sequence pattern recognition. Detects increasing/decreasing order with a single-pass iteration. Edge cases: equal elements, single element.

## Medium (10 prompts)

### Java/87 – getRow
2D jagged array traversal and multi-criteria sorting. Requires handling nested structures and custom comparator logic. Edge cases: empty rows, multiple matches.

### Java/89 – encrypt
Caesar cipher with modular arithmetic. Tests alphabet wrap-around and character manipulation. Edge cases: boundary letters (x, y, z).

### Java/90 – nextSmallest
Sorting with distinct element handling. Combines duplicate removal and Optional/null handling. Edge cases: all duplicates, single element.

### Java/97 – multiply
A misleading function name; actually tests digit extraction logic. Combines last-digit manipulation and sign handling. Edge cases: negative numbers, trailing zeros.

### Java/98 – countUpper
Character classification with index-based filtering. Combines even-index traversal and uppercase vowel detection. Edge cases: off-by-one errors.

### Java/99 – closestInteger
String-to-number parsing and custom rounding rules (ties away from zero). Edge cases: negative .5 values, integer strings.

### Java/100 – makeAPile
Pattern inference and arithmetic sequence generation. Implements parity-based growth rules. Edge cases: n=1, odd vs. even.

### Java/103 – roundedAvg
A multi-step pipeline: range validation → average → binary conversion. Includes mixed return types (String vs int). Edge cases: n > m, binary conversion errors.

### Java/106 – f
Dual logic implementation (factorial vs summation) based on parity. Tests indexing and mathematical computation accuracy. Edge cases: n=1, factorial overflow.

### Java/122 – addElements
Combines array slicing and filtering. Digit-length calculation ignores sign. Order of operations is critical. Edge cases: k=1, no valid elements.

## Hard (10 prompts)

### Java/1 – separateParenGroups
Nested parentheses parsing with depth tracking. Tests stateful string processing and balanced bracket detection. Edge cases: deeply nested structures, spaces, malformed input.

### Java/19 – sortNumbers
Word-to-number mapping and custom sorting. Involves string tokenization, HashMap usage, and ordering logic. Canonical solution is 1277 characters—one of the longest. Edge cases: empty input, single word.

### Java/39 – primeFib
Combines Fibonacci generation with prime testing. Requires coordination of two algorithms and tracking the n-th element. Edge cases: large n, prime-checking efficiency.

### Java/59 – largestPrimeFactor
Prime factorization algorithm. Tests trial division optimization and composite decomposition. Edge cases: prime inputs, large numbers.

### Java/75 – isMultiplyPrime
Validator for multiplication of three primes. Uses triple nested loops (O(n³)), prime checking, and an exactly-three constraint. 871 characters—one of the longest solutions in the dataset. Edge cases: 8 = 2×2×2 (invalid, must be distinct primes).

### Java/81 – numericalLetterGrade
Letter grade mapping with complex GPA boundaries. Includes many conditional branches and floating-point precision concerns. Solution length: 1113 characters. Edge cases: boundary values (3.7, 3.3, etc.).

### Java/93 – encode
Vowel-specific rotation cipher. Preserves case, detects vowels, and applies a rotation offset (a→c, e→g). 699 characters. Edge cases: mixed case, non-letters, edge vowels.

### Java/95 – checkDictKeys
Nested Map validation with type checking. Requires deep structure traversal, null handling, and mixed types. 1199 characters—the most complex data structure task in the dataset. Edge cases: null values, nested nulls, empty maps.

### Java/108 – countNums
Digit extraction for negative numbers with sign-aware summing. The first digit keeps the sign, the others are added positively. 530 characters. Edge case: -123 → -1+2+3=4; also single-digit negatives.

### Java/129 – maxFill
2D grid optimization with capacity constraints. Involves matrix analysis, bucket capacity logic, and operation counting. 1045 characters. Edge cases: capacity=1, empty grid, single row.

## Summary of the Selection Strategy

### Difficulty Criteria
Easy: <300 chars, single concept, minimal branching
Medium: 300–600 chars, 2–3 concepts, moderate branching
Hard: >600 chars, multiple algorithms, complex state management

### Diversity
String manipulation (7 prompts)
Mathematical computation (6 prompts)
Data structure traversal (5 prompts)
Algorithm implementation (6 prompts)
Parsing/encoding (6 prompts)
