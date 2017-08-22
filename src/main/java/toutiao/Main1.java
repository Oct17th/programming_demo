package toutiao;

import java.util.Scanner;

/**
 * 今日头条模拟测试
 * 水仙花数
 *
 * @author YiJie 2017/8/22.
 */
public class Main1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a, b, c, m, n;
        boolean flag;
        while (in.hasNext()) {
            m = in.nextInt();
            n = in.nextInt();
            flag = false;
            for (int i = m + 1; i <= n; i++) {
                a = i / 100;
                b = (i - a * 100) / 10;
                c = (i - a * 100 - b * 10);
                if (a * a * a + b * b * b + c * c * c == i) {
                    System.out.print(i + " ");
                    flag = true;
                }
            }
            if (!flag) {
                System.out.print("no ");
            }
        }
    }
}
