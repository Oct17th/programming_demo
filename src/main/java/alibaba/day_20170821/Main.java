package alibaba.day_20170821;

/**
 * 阿里模拟编程测试 08.21
 *
 * 小猴子下山，沿着下山的路由一排桃树，每棵树都结了一些套子。小猴子想摘桃子，但是有一些条件需要遵守，
 * 小猴子只能沿着下山的方向走，不能回头，每棵树最多摘一个，而且一旦摘了一棵树的桃子，
 * 就不能再摘比这棵树结的桃子少的树上的桃子了，那么小猴子最多能摘几个桃子呢？
 * 举例说明，比如有5课树，分别结了10,4,5,12,8颗桃子，那么小猴子最多能摘3颗桃子，来自于结了4,5,8颗桃子的树。
 *
 * @author YiJie 2017/8/21.
 */

import java.util.Scanner;

public class Main {
    static int max(int[] counts, int start) {
        int maxindex = start;
        int max = counts[start];
        for (int i = start + 1; i < counts.length; i++) {
            if (counts[i] >= max) {
                maxindex = i;
                max = counts[i];
            }
        }
        return maxindex;
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int trees = Integer.parseInt(in.nextLine().trim());
        int[] peaches = new int[trees];
        int[] counts = new int[trees];
        for (int i = 0; i < peaches.length; i++) {
            peaches[i] = Integer.parseInt(in.nextLine().trim());
            counts[i] = 0;
        }
        //每捡的一颗桃子要使得后面比他大的桃子最多
        int max = peaches[0];
        for (int i = 0; i < peaches.length-1; i++) {
            for (int j = i + 1; j < peaches.length; j++) {
                if (peaches[j] > max) {
                    max = peaches[j];
                    counts[i]++;
                }
            }
            max = peaches[i+1];
        }

        int count = 0;
        for (int i = 0; i < counts.length - 1; ) {
            i = max(counts, i + 1);
            count++;
        }
        System.out.println(count);
    }
}