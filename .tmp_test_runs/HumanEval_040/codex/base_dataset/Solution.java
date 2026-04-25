import java.lang.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {

    /**
     triplesSumToZero takes a list of integers as an input.
     it returns True if there are three distinct elements in the list that sum to zero, and False otherwise.
     */
    public boolean triplesSumToZero(List<Integer> l) {
        if (l == null || l.size() < 3) {
            return false;
        }

        List<Integer> nums = new ArrayList<>(l);
        Collections.sort(nums);

        for (int i = 0; i < nums.size() - 2; i++) {
            int left = i + 1;
            int right = nums.size() - 1;

            while (left < right) {
                int sum = nums.get(i) + nums.get(left) + nums.get(right);

                if (sum == 0) {
                    return true;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return false;
    }
}