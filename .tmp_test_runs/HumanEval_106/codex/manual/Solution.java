import java.lang.*;
import java.util.ArrayList;
import java.util.List;

class Solution {
    /**
    Implement the function f that takes n as a parameter,
    and returns a list of size n, such that the value of the element at index i is the factorial of i if i is even
    or the sum of numbers from 1 to i otherwise.
    i starts from 1.
    the factorial of i is the multiplication of the numbers from 1 to i (1 * 2 * ... * i).
    Example:
    f(5) == [1, 2, 6, 24, 15]
     */

    public List<Integer> f(int n) {
        List<Integer> result = new ArrayList<>();

        if (n <= 0) {
            return result;
        }

        int factorial = 1;
        int sum = 0;

        for (int i = 1; i <= n; i++) {
            sum += i;
            factorial *= i;

            if (i % 2 == 0) {
                result.add(factorial);
            } else {
                result.add(sum);
            }
        }

        return result;
    }
}