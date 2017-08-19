package lintcode;

/**
 * @author YiJie 2017/8/18.
 */
public class Fibonacci {
    /*
     * @param n: an integer
     * @return: an integer f(n)
     */
    public int fibonacci(int n) {
        int[] fibo = new int[n];
        for (int i = 2; i < n; i++) {
            if (i == 0) {
                fibo[i] = 0;
                continue;
            }
            if (i == 1) {
                fibo[i] = 1;
                continue;
            }
            fibo[i] = fibo[i - 1] + fibo[i - 2];
        }
        return fibo[n - 1];
    }
}
