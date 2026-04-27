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
        int largest = 1;
        int x = n;
        int factor = 2;
        while ((long) factor * factor <= x) {
            while (x % factor == 0) {
                largest = factor;
                x /= factor;
            }
            factor++;
        }
        if (x > 1) {
            largest = x;
        }
        return largest;
    }
}
