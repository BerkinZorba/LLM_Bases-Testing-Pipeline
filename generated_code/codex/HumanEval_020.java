import java.util.*;
import java.lang.*;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

class Solution {

    /**
     From a supplied list of numbers (of length at least two) select and return two that are the closest to each
     other and return them in order (smaller number, larger number).
     */
    public List<Double> findClosestElements(List<Double> numbers) {
        if (numbers == null || numbers.size() < 2) {
            return new ArrayList<>();
        }

        // Sort the list
        List<Double> sorted = new ArrayList<>(numbers);
        Collections.sort(sorted);

        double minDiff = Double.MAX_VALUE;
        double first = sorted.get(0);
        double second = sorted.get(1);

        for (int i = 1; i < sorted.size(); i++) {
            double diff = sorted.get(i) - sorted.get(i - 1);
            if (diff < minDiff) {
                minDiff = diff;
                first = sorted.get(i - 1);
                second = sorted.get(i);
            }
        }

        return Arrays.asList(first, second);
    }
}