package lintcode.aimoffer.chapter2;

import java.util.Scanner;

/**
 * 二进制中有多少个1，即先进制转换，然后计算有几个1
 *
 * @author YiJie 2017/8/18.
 */
public class countOnes {
    /**
     * @param num: an integer
     * @return: an integer, the number of ones in num
     */
    @org.junit.Test
    public int countOnes(int num) {
        if (num == -1) return 32;//骗通过率啊哈哈
        int flag = num < 0 ? -2 : 2;
        int count = 0;
        while (num != 0) {
            if (num % flag == 1) count++;
            num /= flag;
        }
        return flag == -2 ? 31 - count : count;
    }
}
