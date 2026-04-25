import java.util.*;
import java.lang.*;

class Solution {
    /**
     * Write a function that takes a message, and encodes in such a
     * way that it swaps case of all letters, replaces all vowels in
     * the message with the letter that appears 2 places ahead of that
     * vowel in the english alphabet.
     * Assume only letters.
     *
     * Examples:
     * >>> encode("test")
     * "TGST"
     * >>> encode("This is a message")
     * "tHKS KS C MGSSCGG"
     */
    public String encode(String message) {
        if (message == null || message.isEmpty()) return "";

        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
        StringBuilder result = new StringBuilder();

        for (char c : message.toCharArray()) {
            if (c == ' ') {
                result.append(c);
                continue;
            }

            // Step 1: swap case
            char swapped = Character.isUpperCase(c) ? Character.toLowerCase(c) : Character.toUpperCase(c);

            // Step 2: if original was a vowel, shift the swapped char by 2
            if (vowels.contains(c)) {
                swapped = (char) (swapped + 2);
            }

            result.append(swapped);
        }

        return result.toString();
    }
}