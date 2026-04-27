import java.util.*;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        List<Boolean> correct = Arrays.asList(
                s.primeFib(1) == 2,
                s.primeFib(2) == 3,
                s.primeFib(3) == 5,
                s.primeFib(4) == 13,
                s.primeFib(5) == 89
        );
        if (correct.contains(false)) {
            throw new AssertionError();
        }
    }
}
