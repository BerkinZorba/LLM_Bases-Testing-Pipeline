import java.util.*;
import java.lang.*;

class Solution {
    public List<String> separateParenGroups(String paren_string) {
        List<String> result = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        int depth = 0;
        for (char c : paren_string.toCharArray()) {
            if (c == ' ') continue;
            current.append(c);
            if (c == '(') depth++;
            else if (c == ')') {
                depth--;
                if (depth == 0) {
                    result.add(current.toString());
                    current = new StringBuilder();
                }
            }
        }
        return result;
    }
}
