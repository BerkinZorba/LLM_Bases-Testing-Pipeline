import java.util.*;
import java.lang.*;

class Solution {
    /**
     * Create a function that takes a value (string) representing a number
     * and returns the closest integer to it. If the number is equidistant
     * from two integers, round it away from zero.
     *
     * Examples
     * >>> closest_integer("10")
     * 10
     * >>> closest_integer("15.3")
     * 15
     *
     * Note:
     * Rounding away from zero means that if the given number is equidistant
     * from two integers, the one you should return is the one that is the
     * farthest from zero. For example closest_integer("14.5") should
     * return 15 and closest_integer("-14.5") should return -15.
     */
    public int closest_integer(String value) {
        double num = Double.parseDouble(value);

        // Check if exactly halfway (x.5)
        if (num - Math.floor(num) == 0.5) {
            // Round away from zero
            return (int) (num > 0 ? Math.ceil(num) : Math.floor(num));
        }

        // Standard rounding for all other cases
        return (int) Math.round(num);
    }
}