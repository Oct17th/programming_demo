package u51;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author YiJie 2017/9/18.
 */
public class ThreeThreadStringMatch {
    static Scanner in;
    static AtomicInteger count = new AtomicInteger(0);
    static AtomicInteger line = new AtomicInteger(2000);

    public static void main(String[] args) {
        in = new Scanner(System.in);
        new Thread(new KMP()).start();
        new Thread(new KMP()).start();
        new Thread(new KMP()).start();
        System.out.println(count);
    }

    static class KMP implements Runnable {

        public void match(String source, String target) {
            int[] next = createNext(target.toCharArray());
            for (int i = 0; i < source.length() - target.length() + 1; ) {
                boolean flag = true;//是否匹配出子串
                for (int j = 0; j < target.length(); j++) {
                    if (source.charAt(i + j) != target.charAt(j)) {
//                    i += j == 0 ? 1 : j - next[j];//借助next数组确定移动步长
                        i += j - next[j];
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    count.getAndIncrement();
                    i += target.length();//移动子串长度个数
                }
            }
        }

        /**
         * 寻找各字符的前缀最大公共长度
         * 失配时，模式串向右移动的位数为：失配字符所在位置 - 失配字符对应的next值
         * <p>
         *
         * @param target
         * @return
         */
        private int[] createNext(char[] target) {
            int[] next = new int[target.length];
            next[0] = 0;//-1
            for (int j = 0, i = 1; i < target.length; i++) {
                if (target[i] == target[j]) {
                    next[i] = next[i - 1] + 1;
                    j++;
                } else {
                    next[i] = 0;
                    j = 0;
                }
            }
            next = transferNext(next);
            return next;
        }

        private int[] transferNext(int[] next) {
            for (int i = next.length - 1; i > 0; i--) {
                next[i] = next[i - 1];
            }
            next[0] = -1;
            return next;
        }

        @Override
        public void run() {
            if (line.getAndDecrement() > 0) {
                match(in.nextLine(), "u51");
            }
        }
    }
}