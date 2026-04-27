import java.util.*;
import java.lang.*;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

class Solution {
    /**
     * From a supplied list of numbers (of length at least two) select and return two that are the closest to each
     * other and return them in order (smaller number, larger number).
     * >>> findClosestElements(Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0, 2.2))
     * [2.0, 2.2]
     * >>> findClosestElements(Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0, 2.0))
     * [2.0, 2.0]
     */
    public List<Double> findClosestElements(List<Double> numbers) {
        List<Double> sorted = new ArrayList<>(numbers);
        Collections.sort(sorted);

        double minDiff = Double.MAX_VALUE;
        double first = 0, second = 0;

        for (int i = 0; i < sorted.size() - 1; i++) {
            double diff = sorted.get(i + 1) - sorted.get(i);
            if (diff < minDiff) {
                minDiff = diff;
                first = sorted.get(i);
                second = sorted.get(i + 1);
            }
        }

        return Arrays.asList(first, second);
    }
}