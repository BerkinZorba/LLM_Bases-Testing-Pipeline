import java.util.*;
import java.lang.*;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

class Solution {

    /**
     Input is a space-delimited string of numerals from 'zero' to 'nine'.
     Return the string with numbers sorted from smallest to largest.
     */
    public String sortNumbers(String numbers) {
        if (numbers == null || numbers.isEmpty()) {
            return "";
        }

        Map<String, Integer> map = new HashMap<>();
        map.put("zero", 0);
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        map.put("four", 4);
        map.put("five", 5);
        map.put("six", 6);
        map.put("seven", 7);
        map.put("eight", 8);
        map.put("nine", 9);

        String[] parts = numbers.split(" ");

        Arrays.sort(parts, Comparator.comparingInt(map::get));

        return String.join(" ", parts);
    }
}