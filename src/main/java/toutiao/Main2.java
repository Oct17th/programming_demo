package toutiao;

import java.util.Scanner;

/**
 * 今日头条模拟测试
 * 序列平方根和
 *
 * @author YiJie 2017/8/22.
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m;
        float n, sum;
        while (in.hasNext()) {
            n = in.nextInt();
            m = in.nextInt();
            sum = n;
            for (int i = 1; i < m; i++) {
                n = (float) Math.sqrt(n);
                sum += n;
            }
            System.out.println(String.format("%.2f",sum));
        }
    }

}
