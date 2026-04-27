import java.util.*;
import java.lang.*;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

class Solution {
    /**
    Given a map, return True if all keys are strings in lower
    case or all keys are strings in upper case, else return False.
    The function should return False is the given map is empty.
    Examples:
    checkDictCase({"a":"apple", "b":"banana"}) should return True.
    checkDictCase({"a":"apple", "A":"banana", "B":"banana"}) should return False.
    checkDictCase({"a":"apple", 8:"banana", "a":"apple"}) should return False.
    checkDictCase({"Name":"John", "Age":"36", "City":"Houston"}) should return False.
    checkDictCase({"STATE":"NC", "ZIP":"12345" }) should return True.
     */
    public boolean checkDictCase(Map<Object, Object> dict) {
        if (dict.isEmpty()) {
            return false;
        }

        Boolean expectLower = null;
        for (Object key : dict.keySet()) {
            if (!(key instanceof String stringKey)) {
                return false;
            }

            Boolean currentCase = getUniformCase(stringKey);
            if (currentCase == null) {
                return false;
            }

            if (expectLower == null) {
                expectLower = currentCase;
            } else if (!expectLower.equals(currentCase)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns true for lowercase-only letter content, false for uppercase-only
     * letter content, and null when the string has no letters or mixes cases.
     */
    private Boolean getUniformCase(String value) {
        boolean sawLower = false;
        boolean sawUpper = false;
        boolean sawLetter = false;

        for (int i = 0; i < value.length(); i++) {
            char current = value.charAt(i);
            if (!Character.isLetter(current)) {
                continue;
            }

            sawLetter = true;
            if (Character.isLowerCase(current)) {
                sawLower = true;
            } else if (Character.isUpperCase(current)) {
                sawUpper = true;
            }

            if (sawLower && sawUpper) {
                return null;
            }
        }

        if (!sawLetter || (!sawLower && !sawUpper)) {
            return null;
        }

        return sawLower;
    }
}
