import java.util.*;
import java.lang.*;

class Solution {
    /**
     * Implement the function f that takes n as a parameter,
     * and returns a list of size n, such that the value of the element at index i is the factorial of i if i is even
     * or the sum of numbers from 1 to i otherwise.
     * i starts from 1.
     * the factorial of i is the multiplication of the numbers from 1 to i (1 * 2 * ... * i).
     * Example:
     * f(5) == [1, 2, 6, 24, 15]
     */
    public List<Integer> f(int n) {
        List<Integer> result = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            if (i % 2 == 0) {
                // factorial of i
                int factorial = 1;
                for (int j = 1; j <= i; j++) {
                    factorial *= j;
                }
                result.add(factorial);
            } else {
                // sum of 1 to i
                int sum = i * (i + 1) / 2;
                result.add(sum);
            }
        }

        return result;
    }
}