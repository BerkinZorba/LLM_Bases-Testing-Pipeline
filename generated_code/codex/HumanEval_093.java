import java.lang.*;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

class Solution {

    public String encode(String message) {
        if (message == null || message.isEmpty()) {
            return "";
        }

        StringBuilder result = new StringBuilder();

        for (char c : message.toCharArray()) {
            if (Character.isLetter(c)) {
                char lower = Character.toLowerCase(c);

                // Step 1: replace vowels
                if (isVowel(lower)) {
                    lower = (char) ('a' + (lower - 'a' + 2) % 26);
                }

                // Step 2: swap case
                if (Character.isUpperCase(c)) {
                    result.append(Character.toLowerCase(lower));
                } else {
                    result.append(Character.toUpperCase(lower));
                }
            } else {
                // keep non-letters (like spaces)
                result.append(c);
            }
        }

        return result.toString();
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}