import java.util.*;
import java.lang.*;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

class Solution {
    public int countNums(List<Integer> arr) {
        int count = 0;

        for (int num : arr) {
            int sum = 0;
            int temp = Math.abs(num);

            while (temp > 0) {
                sum += temp % 10;
                temp /= 10;
            }

            if (num < 0) {
                int firstDigit = Math.abs(num);
                while (firstDigit >= 10) firstDigit /= 10;
                sum -= 2 * firstDigit;
            }

            if (sum > 0) count++;
        }

        return count;
    }
}
