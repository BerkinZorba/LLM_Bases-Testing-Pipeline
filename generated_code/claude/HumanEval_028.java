import java.util.*;
import java.lang.*;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

class Solution {
    public String concatenate(List<String> strings) {
        StringBuilder sb = new StringBuilder();
        for (String s : strings) sb.append(s);
        return sb.toString();
    }
}
