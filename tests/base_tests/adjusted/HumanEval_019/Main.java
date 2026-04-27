import java.util.*;
import java.lang.*;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        List<Boolean> correct = Arrays.asList(
                s.sortNumbers("").equals(""),
                s.sortNumbers("three").equals("three"),
                s.sortNumbers("three five nine").equals("three five nine"),
                s.sortNumbers("five zero four seven nine eight").equals("zero four five seven eight nine")
        );
        if (correct.contains(false)) {
            throw new AssertionError();
        }
    }
}
