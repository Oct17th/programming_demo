package didi;

/**
 * 最大子序列和
 *
 * @author YiJie 2017/8/28.
 */
public class MaxSubsequenceSum {

    public static int maxSubSum(int[] array) {
        int sum = 0, max = array[0];
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
            if (sum > max)
                max = sum;
            if (sum < 0)  //如果 sum < 0, 将 sum 重新置 0
                sum = 0;
        }
        return max;
    }
}
