package lintcode.aimoffer.chapter2;

/**
 * 写出一个高效的算法来搜索m×n矩阵中的值，返回这个值出现的次数。
 * <p>
 * 这个矩阵具有以下特性：
 * <p>
 * 每行中的整数从左到右是排序的。
 * 每一列的整数从上到下是排序的。
 * 在每一行或每一列中没有重复的整数。
 *
 * @author YiJie 2017/8/25.
 */
public class SearchMatrix {
    public static void main(String[] args) {
        SearchMatrix sm = new SearchMatrix();
        sm.searchMatrix(new int[][]{{1, 3, 5, 7}, {3, 11, 16, 20}, {23, 30, 34, 50}}, 3);
    }

    /**
     * @param matrix: A list of lists of integers
     * @param: A number you want to search in the matrix
     * @return: An integer indicate the occurrence of target in the given matrix
     */
    public int searchMatrix(int[][] matrix, int target) {
        int count = 0;
        if (matrix.length == 0) return 0;
        int mark = matrix[0].length - 1;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = mark; j >= 0; j--) {
                if (matrix[i][j] == target) {
                    count++;
                    mark = j - 1;//优化1
                    break;//优化2
                }
            }
        }
        return count;
    }
}
