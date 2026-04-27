import java.util.*;
import java.lang.*;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

class Solution {
    /**
    Return the largest prime factor of n. Assume n > 1 and is not a prime.
    >>> largestPrimeFactor(13195)
    29
    >>> largestPrimeFactor(2048)
    2
     */
    public int largestPrimeFactor(int n) {
        int largestFactor = 1;

        while (n % 2 == 0) {
            largestFactor = 2;
            n /= 2;
        }

        for (int factor = 3; factor <= n / factor; factor += 2) {
            while (n % factor == 0) {
                largestFactor = factor;
                n /= factor;
            }
        }

        if (n > 1) {
            largestFactor = n;
        }

        return largestFactor;
    }
}
