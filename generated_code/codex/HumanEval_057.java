import java.util.*;
import java.lang.*;

class Solution {
    /**
    Return True is list elements are monotonically increasing or decreasing.
    >>> monotonic(Arrays.asList(1, 2, 4, 20))
    true
    >>> monotonic(Arrays.asList(1, 20, 4, 10))
    false
    >>> monotonic(Arrays.asList(4, 1, 0, -10))
    true
     */
    public boolean monotonic(List<Integer> l) {
        boolean nonDecreasing = true;
        boolean nonIncreasing = true;

        for (int i = 1; i < l.size(); i++) {
            if (l.get(i) < l.get(i - 1)) {
                nonDecreasing = false;
            }
            if (l.get(i) > l.get(i - 1)) {
                nonIncreasing = false;
            }
        }

        return nonDecreasing || nonIncreasing;
    }
}
