import java.util.*;
import java.lang.*;

class Solution {
    public int countUpper(String s) {
        int count = 0;
        String vowels = "AEIOU";
        for (int i = 0; i < s.length(); i += 2) {
            if (vowels.indexOf(s.charAt(i)) != -1) count++;
        }
        return count;
    }
}
