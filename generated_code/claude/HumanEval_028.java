import java.util.*;
import java.lang.*;

class Solution {
    public String concatenate(List<String> strings) {
        StringBuilder sb = new StringBuilder();
        for (String s : strings) sb.append(s);
        return sb.toString();
    }
}
