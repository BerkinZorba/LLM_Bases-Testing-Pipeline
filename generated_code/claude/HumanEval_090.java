import java.util.*;
import java.lang.*;

class Solution {
    public Optional<Integer> nextSmallest(List<Integer> lst) {
        if (lst == null || lst.size() < 2) return Optional.empty();
        TreeSet<Integer> distinct = new TreeSet<>(lst);
        if (distinct.size() < 2) return Optional.empty();
        distinct.pollFirst();
        return Optional.of(distinct.first());
    }
}
