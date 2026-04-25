import java.util.*;
import java.lang.*;

class Solution {
    public int countNums(List<Integer> arr) {
        int count = 0;
        for (int n : arr) {
            if (signedDigitSum(n) > 0) count++;
        }
        return count;
    }

    private int signedDigitSum(int n) {
        if (n == 0) return 0;
        String s = String.valueOf(Math.abs((long) n));
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            int d = s.charAt(i) - '0';
            sum += (i == 0 && n < 0) ? -d : d;
        }
        return sum;
    }
}
