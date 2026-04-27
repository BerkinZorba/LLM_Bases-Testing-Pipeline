import java.util.*;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        List<Boolean> correct = Arrays.asList(
                Math.abs(s.truncateNumber(3.5) - 0.5) < 1e-9,
                Math.abs(s.truncateNumber(1.33) - 0.33) < 1e-9,
                Math.abs(s.truncateNumber(123.456) - 0.456) < 1e-9,
                Math.abs(s.truncateNumber(1.0) - 0.0) < 1e-9,
                Math.abs(s.truncateNumber(0.5) - 0.5) < 1e-9
        );
        if (correct.contains(false)) {
            throw new AssertionError();
        }
    }
}
