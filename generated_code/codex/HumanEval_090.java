import java.util.*;
import java.lang.*;

class Solution {
    public Optional<Integer> nextSmallest(List<Integer> lst) {
        if (lst == null || lst.size() < 2) return Optional.empty();

        Integer min = null;
        Integer second = null;

        for (Integer num : lst) {
            if (min == null || num < min) {
                if (min != null && !num.equals(min)) {
                    second = min;
                }
                min = num;
            } else if (!num.equals(min) && (second == null || num < second)) {
                second = num;
            }
        }

        return second == null ? Optional.empty() : Optional.of(second);
    }
}
