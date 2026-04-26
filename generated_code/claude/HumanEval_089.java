import java.util.*;
import java.lang.*;

class Solution {
    /**
     * Create a function encrypt that takes a string as an argument and
     * returns a string encrypted with the alphabet being rotated.
     * The alphabet should be rotated in a manner such that the letters
     * shift down by two multiplied to two places.
     * For example:
     * encrypt("hi") returns "lm"
     * encrypt("asdfghjkl") returns "ewhjklnop"
     * encrypt("gf") returns "kj"
     * encrypt("et") returns "ix"
     */
    public String encrypt(String s) {
        if (s == null || s.isEmpty()) return "";

        StringBuilder result = new StringBuilder();
        int shift = 2 * 2; // two multiplied by two = 4

        for (char c : s.toCharArray()) {
            if (Character.isLowerCase(c)) {
                result.append((char) ('a' + (c - 'a' + shift) % 26));
            } else if (Character.isUpperCase(c)) {
                result.append((char) ('A' + (c - 'A' + shift) % 26));
            } else {
                result.append(c); // non-alphabetic characters unchanged
            }
        }

        return result.toString();
    }
}