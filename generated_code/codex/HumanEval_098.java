import java.util.*;
import java.lang.*;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

class Solution {
    public int countUpper(String s) {
        int count = 0;

        for (int i = 0; i < s.length(); i += 2) {
            char c = s.charAt(i);
            if (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
                count++;
            }
        }

        return count;
    }
}
