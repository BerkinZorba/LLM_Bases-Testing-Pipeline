import java.util.*;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        List<Boolean> correct = Arrays.asList(
                s.isMultiplyPrime(30) == true,
                s.isMultiplyPrime(22) == false,
                s.isMultiplyPrime(4)  == false,
                s.isMultiplyPrime(8)  == true,
                s.isMultiplyPrime(3)  == false,
                s.isMultiplyPrime(5)  == false,
                s.isMultiplyPrime(12) == true,
                s.isMultiplyPrime(18) == true,
                s.isMultiplyPrime(50) == true
        );
        if (correct.contains(false)) {
            throw new AssertionError();
        }
    }
}
