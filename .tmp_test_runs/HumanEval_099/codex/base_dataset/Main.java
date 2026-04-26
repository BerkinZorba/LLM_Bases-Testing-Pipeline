import java.util.*;

public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        List<Boolean> correct = Arrays.asList(
                s.closest_integer("10") == 10,
                s.closest_integer("14.5") == 15,
                s.closest_integer("-15.5") == -16,
                s.closest_integer("15.3") == 15,
                s.closest_integer("0") == 0
        );
        if (correct.contains(false)) {
            throw new AssertionError();
        }
    }
}
