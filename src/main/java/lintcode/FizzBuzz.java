package lintcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author YiJie 2017/8/18.
 */
public class FizzBuzz {
    /*
     * @param n: An integer
     * @return: A list of strings.
     */
    public List<String> fizzBuzz(int n) {
        List<String> result = new ArrayList<String>();
        for (Integer i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                result.add("fizz buzz");
                continue;
            }
            if (i % 3 == 0) {
                result.add("fizz");
                continue;
            }
            if (i % 5 == 0) {
                result.add("buzz");
                continue;
            }
            result.add(i.toString());
        }
        return result;
    }
}