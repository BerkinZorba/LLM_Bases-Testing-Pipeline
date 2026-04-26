import java.util.*;

public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        List<Boolean> correct = Arrays.asList(
                s.strlen("") == 0,
                s.strlen("abc") == 3,
                s.strlen("x") == 1,
                s.strlen("hello world") == 11
        );
        if (correct.contains(false)) {
            throw new AssertionError();
        }
    }
}
