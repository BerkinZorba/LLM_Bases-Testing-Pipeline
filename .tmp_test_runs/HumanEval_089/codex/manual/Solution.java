import java.lang.*;

class Solution {

    /**
     Encrypt string by shifting each character forward by 4 positions in the alphabet.
     */
    public String encrypt(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }

        StringBuilder result = new StringBuilder();

        for (char c : s.toCharArray()) {
            // assuming lowercase a-z only
            char shifted = (char) ('a' + (c - 'a' + 4) % 26);
            result.append(shifted);
        }

        return result.toString();
    }
}