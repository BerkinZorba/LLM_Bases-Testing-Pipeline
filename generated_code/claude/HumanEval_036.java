import java.util.*;
import java.lang.*;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

class Solution {
    /**
    Return the number of times the digit 7 appears in integers less than n which are divisible by 11 or 13.
    >>> fizzBuzz(50)
    0
    >>> fizzBuzz(78)
    2
    >>> fizzBuzz(79)
    3
     */
    public int fizzBuzz(int n) {
        int count = 0;
        for (int i = 1; i < n; i++) {
            if (i % 11 == 0 || i % 13 == 0) {
                int num = i;
                while (num > 0) {
                    if (num % 10 == 7) {
                        count++;
                    }
                    num /= 10;
                }
            }
        }
        return count;
    }
}
