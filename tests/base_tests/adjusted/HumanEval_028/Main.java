import java.util.*;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        List<Boolean> correct = Arrays.asList(
                s.concatenate(List.of()).equals(""),
                s.concatenate(Arrays.asList("a", "b", "c")).equals("abc"),
                s.concatenate(Arrays.asList("hello", " ", "world")).equals("hello world"),
                s.concatenate(Arrays.asList("x")).equals("x")
        );
        if (correct.contains(false)) {
            throw new AssertionError();
        }
    }
}
