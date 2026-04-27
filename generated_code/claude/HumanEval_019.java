import java.util.*;
import java.lang.*;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

class Solution {
    /**
     * Input is a space-delimited string of numerals from 'zero' to 'nine'.
     * Valid choices are 'zero', 'one', 'two', 'three', 'four', 'five', 'six', 'seven', 'eight' and 'nine'.
     * Return the string with numbers sorted from smallest to largest
     * >>> sortNumbers("three one five")
     * "one three five"
     */
    public String sortNumbers(String numbers) {
        if (numbers == null || numbers.trim().isEmpty()) return "";

        Map<String, Integer> wordToNum = new HashMap<>();
        wordToNum.put("zero",  0);
        wordToNum.put("one",   1);
        wordToNum.put("two",   2);
        wordToNum.put("three", 3);
        wordToNum.put("four",  4);
        wordToNum.put("five",  5);
        wordToNum.put("six",   6);
        wordToNum.put("seven", 7);
        wordToNum.put("eight", 8);
        wordToNum.put("nine",  9);

        String[] numToWord = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

        String[] words = numbers.trim().split(" ");
        Arrays.sort(words, Comparator.comparingInt(wordToNum::get));

        return String.join(" ", words);
    }
}