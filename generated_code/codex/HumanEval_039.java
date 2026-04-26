import java.util.*;
import java.lang.*;

class Solution {
    public int primeFib(int n) {
        int count = 0, a = 0, b = 1;

        while (true) {
            int next = a + b;
            a = b;
            b = next;

            if (next >= 2) {
                boolean prime = true;
                if (next % 2 == 0 && next != 2) prime = false;
                for (int i = 3; i * i <= next && prime; i += 2) {
                    if (next % i == 0) prime = false;
                }
                if (prime) {
                    count++;
                    if (count == n) return next;
                }
            }
        }
    }
}
