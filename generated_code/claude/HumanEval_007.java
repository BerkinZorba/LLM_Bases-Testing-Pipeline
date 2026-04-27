import java.lang.*;
import java.util.*;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

class Solution {
    /**
     * Filter an input list of strings only for ones that contain given substring
     * >>> filterBySubstring(List.of(), "a")
     * []
     * >>> filterBySubstring(Arrays.asList("abc", "bacd", "cde", "array"), "a")
     * ["abc", "bacd", "array"]
     */
    public List<String> filterBySubstring(List<String> strings, String substring) {
        List<String> result = new ArrayList<>();
        for (String s : strings) {
            if (s.contains(substring)) {
                result.add(s);
            }
        }
        return result;
    }
}