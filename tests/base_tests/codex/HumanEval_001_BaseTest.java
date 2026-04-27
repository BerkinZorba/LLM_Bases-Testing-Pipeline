import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.*;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

public class HumanEval_001_BaseTest {

    private final Solution s = new Solution();

    @Test
    void docstringExample_threeGroups() {
        assertEquals(Arrays.asList("()", "(())", "(()())"),
                s.separateParenGroups("( ) (( )) (( )( ))"));
    }

    @Test
    void emptyInput_returnsEmptyList() {
        assertEquals(Arrays.asList(), s.separateParenGroups(""));
    }

    @Test
    void singleGroup_parentheses() {
        assertEquals(Arrays.asList("()"), s.separateParenGroups("()"));
    }

    @Test
    void fourGroups_complexNesting() {
        assertEquals(Arrays.asList("(()())", "((()))", "()", "((())(()))"),
                s.separateParenGroups("(()()) ((())) () ((())(()))"));
    }
}
