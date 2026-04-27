import java.lang.*;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

class Solution {

    /**
     Find how many times a given substring can be found in the original string. Count overlapping cases.
     >>> howManyTimes("", "a")
     0
     >>> howManyTimes("aaa", "a")
     3
     >>> howManyTimes("aaaa", "aa")
     3
     */
    public int howManyTimes(String string, String substring) {
        if (string == null || substring == null || substring.length() == 0) {
            return 0;
        }

        int count = 0;

        for (int i = 0; i <= string.length() - substring.length(); i++) {
            if (string.substring(i, i + substring.length()).equals(substring)) {
                count++;
            }
        }

        return count;
    }
}