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
        boolean allLower = true;
        boolean allUpper = true;
        for (Object key : dict.keySet()) {
            if (!(key instanceof String)) {
                return false;
            }
            String s = (String) key;
            if (!s.equals(s.toLowerCase())) {
                allLower = false;
            }
            if (!s.equals(s.toUpperCase())) {
                allUpper = false;
            }
        }
        return allLower || allUpper;
    }
}
