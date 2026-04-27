import java.util.*;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        List<Boolean> correct = Arrays.asList(
                s.f(5).equals(Arrays.asList(1, 2, 6, 24, 15)),
                s.f(7).equals(Arrays.asList(1, 2, 6, 24, 15, 720, 28)),
                s.f(1).equals(List.of(1)),
                s.f(3).equals(Arrays.asList(1, 2, 6))
        );
        if (correct.contains(false)) {
            throw new AssertionError();
        }
    }
}
