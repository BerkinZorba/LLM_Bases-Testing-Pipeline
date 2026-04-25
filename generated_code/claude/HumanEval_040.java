import java.util.*;
import java.lang.*;

class Solution {
    /**
     * triplesSumToZero takes a list of integers as an input.
     * it returns True if there are three distinct elements in the list that
     * sum to zero, and False otherwise.
     *
     * >>> triplesSumToZero(Arrays.asList(1, 3, 5, 0))
     * false
     * >>> triplesSumToZero(Arrays.asList(1, 3, -2, 1))
     * true
     * >>> triplesSumToZero(Arrays.asList(1, 2, 3, 7))
     * false
     * >>> triplesSumToZero(Arrays.asList(2, 4, -5, 3, 9, 7))
     * true
     * >>> triplesSumToZero(Arrays.asList(1))
     * false
     */
    public boolean triplesSumToZero(List<Integer> l) {
        int n = l.size();
        if (n < 3) return false;

        List<Integer> sorted = new ArrayList<>(l);
        Collections.sort(sorted);

        for (int i = 0; i < n - 2; i++) {
            int left = i + 1;
            int right = n - 1;

            while (left < right)