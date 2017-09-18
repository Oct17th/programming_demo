package iflytek;

import java.util.Scanner;

/**
 * 科大讯飞笔试题
 * 争吵
 * <p>
 * 思路：从左向右读，遇到LR说明有争吵，
 * 留下L，若没有R，则找下一个LR
 *
 * @author YiJie 2017/9/16.
 */
public class Quarrel {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        StringBuffer sb = new StringBuffer(s);
        int mark = 0;
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == 'R') {
                mark = i;//记录第一个R
                break;
            }
        }
        for (int i = sb.length() - 1; i > 0 && i <= sb.length(); i--) {
            if (sb.charAt(i - 1) == 'R' && sb.charAt(i) == 'L') {
                if (i - 1 == mark) {
                    sb.replace(i, i + 1, "");//remove L
                    if (i < sb.length()) i++;
                } else {
                    sb.replace(i - 1, i, "");//remove R
                }
                System.out.println(sb.toString());
            }
        }

        System.out.println(sb.length());
    }
}
