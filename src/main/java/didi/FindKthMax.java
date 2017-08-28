package didi;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 滴滴笔试08.26
 * 找出一组序列中第K大的值
 *
 * @author YiJie 2017/8/26.
 */
public class FindKthMax {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        ArrayList<Integer> array = new ArrayList<Integer>();
        while (in.hasNext()) {
            array.add(in.nextInt());
        }
        int k = array.remove(array.size() - 1);
        int max, tmp;
        for (int i = 0; i < array.size(); i++) {
            max = array.get(i);
            for (int j = i; j < array.size(); j++) {
                tmp = array.get(j);
                if (tmp > max) {
                    array.set(j, max);
                    array.set(i, tmp);
                    max = tmp;
                }
            }
            k--;
            if (k == 0) {
                System.out.println(max);
                return;
            }
        }
    }
}
