import java.util.*;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        List<Boolean> correct = Arrays.asList(
                s.countUpper("aBCdEf") == 1,
                s.countUpper("abcdefg") == 0,
                s.countUpper("dBBE") == 0,
                s.countUpper("AAAAA") == 3,
                s.countUpper("EcEcE") == 3
        );
        if (correct.contains(false)) {
            throw new AssertionError();
        }
    }
}
