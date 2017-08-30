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
        bubbleSort(array, k);
    }

    /**
     * 冒泡排序
     * 时间复杂度 O(kn)
     *
     * @param array
     * @param k
     */
    private static void bubbleSort(ArrayList<Integer> array, int k) {
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

    /**
     * 利用快排的分治思想，在每趟划分出的更小区段里找，时间复杂度更低
     * 时间复杂度 O(n)=O(n+n/2+n/4+...)
     *
     * @param array
     * @param k
     */
    private static void partitionSort(ArrayList<Integer> array, int k) {
        /*
        从array[0]起，把比array[0]小的放左边，比array[0]大的放右边
        若array[0]的位置比k大，则在0~array[0]的位置之间找第k大的数...
        以此类推
         */

    }
}
