package toutiao;

import java.util.Scanner;

/**
 * 今日头条 2017客户端工程师笔试题
 * 出专辑
 * <p>
 * 你作为一名出道的歌手终于要出自己的第一份专辑了，你计划收录 n 首歌而且每首歌的长度都是 s 秒，
 * 每首歌必须完整地收录于一张 CD 当中。每张 CD 的容量长度都是 L 秒，而且你至少得保证
 * 同一张 CD 内相邻两首歌中间至少要隔 1 秒。为了辟邪，你决定任意一张 CD 内的歌数不能被 13 这个数字整除，
 * 那么请问你出这张专辑至少需要多少张 CD ？
 * <p>
 * 输入描述:
 * 每组测试用例仅包含一组数据，每组数据第一行为三个正整数 n, s, L。 保证 n ≤ 100 , s ≤ L ≤ 10000
 * <p>
 * <p>
 * 输出描述:
 * 输出一个整数代表你至少需要的 CD 数量。
 * <p>
 * 输入例子1:
 * 7 2 6
 * <p>
 * 输出例子1:
 * 4
 *
 * @author YiJie 2017/8/22.
 */
public class Album {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int s = in.nextInt();
        int L = in.nextInt();
        int max = L / (s + 1);//唱片最大歌数
        max = L % (s + 1) == s ? max + 1 : max;
        int num = 0;//唱片数
        if (n < max) {//一个CD就能装下所有的歌
            num = n % 13 == 0 ? 2 : 1;
        } else {
            if (max % 13 == 0) max--;//唱片最大歌数不能被13整除
            num = n / max;
            int temp = n % max;//最后一张唱片歌数
            if (temp > 0) {
                num++;
                //最后一张唱片的歌数能否被13整除
                //能被整除后还无法从别的唱片挪一首歌过来
                //即余数为13的倍数，max为13的倍数+1
                if (temp % 13 == 0 && max == temp + 1) num++;
            }
        }
        System.out.println(num);
    }
}
