import java.util.*;
import java.lang.*;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

class Solution {
    public boolean isMultiplyPrime(int a) {
        int count = 0;

        for (int i = 2; i <= a && count < 3; i++) {
            while (a % i == 0) {
                boolean prime = true;
                if (i < 2) prime = false;
                if (i % 2 == 0 && i != 2) prime = false;
                for (int j = 3; j * j <= i && prime; j += 2) {
                    if (i % j == 0) prime = false;
                }

                if (prime) {
                    count++;
                    a /= i;
                } else {
                    return false;
                }
            }
        }

        return count == 3 && a == 1;
    }
}
