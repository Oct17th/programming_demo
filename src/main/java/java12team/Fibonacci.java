package java12team;

/**
 * 06.24
 * <p>
 * 编写一个Java方法输出k*k的数字矩阵，k作为方法参数
 * （1）	对角线上是斐波那契数列（数列中第n个数字是第n-1和第n-2两个数字的和）；
 * （2）非对角线上的输出0。下面是一个5*5的例子：
 * 输入：
 * 5
 * 输出：
 * 10000
 * 01000
 * 00200
 * 00030
 * 00005
 *
 * @author YiJie
 * @date Jun 24, 2017
 */
public class Fibonacci {
    public static void main(String[] args) {
        Fibonacci m = new Fibonacci();
        m.printFibonacci(0);
    }

    public void printFibonacci(int k) {
        if (k == 0)
            return;
        if (k == 1) {
            System.out.println(1);
            return;
        }
        //填充斐波那契列数组
        int[] n = getFibonacci(k);
        //对角线打印数组
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                if (j == i) {
                    System.out.print(n[i]);
                } else {
                    System.out.print(0);
                }
            }
            System.out.print("\n");
        }
    }

    private int[] getFibonacci(int k) {
        if (k == 0) return null;
        int[] n = new int[k];
        if (k == 1) {
            n[0] = 1;
        } else {
            n[0] = n[1] = 1;
            //填充斐波那契列数组
            for (int i = 2; i < k; i++) {
                n[i] = n[i - 1] + n[i - 2];
            }
        }
        String a = null;
        switch (a) {
            case "ABC":
            case "BCD":
        }
        return n;

    }

}
