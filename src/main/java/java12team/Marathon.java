package java12team;

import java.util.Scanner;

/**
 * 题目描述：
 * 搜狐进行了一次黑客马拉松大赛，全公司一共分为了N个组，每组一个房间排成一排开始比赛，
 * 比赛结束后没有公布成绩，但是每组能够看到自己相邻的两个组里比自己成绩低的组的成绩，
 * 比赛结束之后要发奖金，以1w为单位，每个组都至少会发1w的奖金，另外，如果一个组发现
 * 自己的奖金没有高于比自己奖金低的组发的奖金们，就会不满意，作为比赛的组织方，根据
 * 成绩计算出至少需要发多少奖金才能让所有的组满意
 *
 * 输入描述：
 * 每组数据先输入N，然后N行输入N个正整数，每个数表示每个组的比赛成绩
 *
 * 输出描述：
 * 输出至少需要多少w的奖金
 *
 * 输入例子：
 * 10
 * 20 1
 * 32 2
 * 12 1
 * 32 2
 * 45 3
 * 11 1
 * 21 2
 * 31 3
 * 41 4
 * 33 1
 *
 * 输出例子：
 * 20
 *
 * @author YiJie  2017/7/21
 **/
public class Marathon {//时间复杂度O(n)，空间复杂度O(5)

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int lastScore = in.nextInt();
        int lastAward = 1;//设第一张为最低奖金1w
        int totalAward = 0;
        for (int currentScore, i = 0; i < N - 1; i++) {//遍历读取得分
            currentScore = in.nextInt();
            if (currentScore > lastScore) {//若得分高于前组，则奖金为前组所得奖金+1w，否则设置为最低奖金1w元
                lastAward += 1;
            } else {
                lastAward = 1;
            }
            totalAward += lastAward;
            lastScore = currentScore;
        }
        System.out.println(totalAward);
    }

}
