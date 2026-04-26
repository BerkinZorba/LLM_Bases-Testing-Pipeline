import java.util.*;

class Solution {
    public List<String> separateParenGroups(String paren_string) {
        List<String> result = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        int balance = 0;

        for (char c : paren_string.toCharArray()) {
            if (c == ' ') continue;

            current.append(c);

            if (c == '(') balance++;
            else if (c == ')') balance--;

            if (balance == 0 && current.length() > 0) {
                result.add(current.toString());
                current.setLength(0);
            }
        }

        return result;
    }
}
