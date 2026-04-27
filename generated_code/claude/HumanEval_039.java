import java.util.*;
import java.lang.*;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

class Solution {
    public int primeFib(int n) {
        int count = 0;
        int a = 1, b = 2;
        while (true) {
            if (isPrime(b)) {
                count++;
                if (count == n) return b;
            }
            int c = a + b;
            a = b;
            b = c;
        }
    }

    private boolean isPrime(int n) {
        if (n < 2) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        for (int i = 3; (long) i * i <= n; i += 2)
            if (n % i == 0) return false;
        return true;
    }
}
