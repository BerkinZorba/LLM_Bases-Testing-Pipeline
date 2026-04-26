import java.util.*;

public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        List<Boolean> correct = Arrays.asList(
                s.countNums(Arrays.asList()) == 0,
                s.countNums(Arrays.asList(-1, 11, -11)) == 1,
                s.countNums(Arrays.asList(1, 1, 2)) == 3,
                s.countNums(Arrays.asList(-123)) == 1,
                s.countNums(Arrays.asList(0)) == 0
        );
        if (correct.contains(false)) {
            throw new AssertionError();
        }
    }
}
