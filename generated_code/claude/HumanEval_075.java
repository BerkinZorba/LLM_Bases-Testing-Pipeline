import java.util.*;
import java.lang.*;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

class Solution {
    public boolean isMultiplyPrime(int a) {
        int count = 0;
        for (int p = 2; p <= a; p++) {
            while (a % p == 0) {
                a /= p;
                count++;
                if (count > 3) return false;
            }
        }
        return count == 3;
    }
}
