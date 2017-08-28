package lintcode.aimoffer.chapter2;

import java.util.Scanner;

/**
 * @author YiJie 2017/8/18.
 */
public class Fibonacci {
    /*
     * @param n: an integer
     * @return: an integer f(n)
     */
    public static int fibonacci(int n) {
        int[] fibo = new int[n+1];
        for (int i = 0; i < n+1; i++) {
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
        return fibo[n];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int r = fibonacci(n);
        System.out.println(r);
    }
}


