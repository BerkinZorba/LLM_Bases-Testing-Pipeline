import java.util.*;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        List<Boolean> correct = Arrays.asList(
                s.nextSmallest(Arrays.asList(1, 2, 3, 4, 5)).equals(Optional.of(2)),
                s.nextSmallest(Arrays.asList(5, 1, 4, 3, 2)).equals(Optional.of(2)),
                s.nextSmallest(Arrays.asList()).equals(Optional.empty()),
                s.nextSmallest(Arrays.asList(1, 1)).equals(Optional.empty()),
                s.nextSmallest(Arrays.asList(1, 1, 1, 1, 0)).equals(Optional.of(1))
        );
        if (correct.contains(false)) {
            throw new AssertionError();
        }
    }
}
