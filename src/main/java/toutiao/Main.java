package toutiao;

import java.util.Scanner;

/**
 * @author YiJie 2017/8/22.
 */
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }
        int maxindex = 0;
        for (int i = 0; i < n; i++) {
            if(nums[i]>maxindex){
                maxindex = i;
            }
        }
        System.out.println(nums[maxindex]*nums[maxindex]);
    }
}
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int n = in.nextInt();
//        int[][] points = new int[n][2];
//
//        for (int i = 0; i < n; i++) {
//            points[i][0] = in.nextInt();
//            points[i][1] = in.nextInt();
//        }
//        int[][] temp = new int[n][2];
//        for (int index, i = 0; i < n; i++) {
//            index = i;
//            for (int j = i; j < n; j++) {
//                if (points[index][0] > points[j][0]) {
//                    index = j;
//                }
//                int x = points[index][0];
//                int y = points[index][1];
//                points[index][0] = points[i][0];
//                points[index][1] = points[i][1];
//                points[i][0] = x;
//                points[i][1] = y;
//            }
//        }
//        for (int i = 0; i < n; i++) {
//            boolean flag = true;
//            for (int j = i + 1; j < n; j++) {
//                if (points[i][1] < points[j][1]) {
//                    flag = false;
//                }
//            }
//            if (flag) {
//                System.out.println(points[i][0] + " " + points[i][1]);
//            }
//
//        }
//
//    }


//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int n = in.nextInt();
//        int m = in.nextInt();
//        int[] A = new int[n];
//        for (int i = 0; i < n; i++) {
//            A[i] = in.nextInt();
//        }
//        int count = 0;
//        for (int i = 0; i < n; i++) {
//            for (int j = i; j < n; j++) {
//                if ((A[i] ^ A[j]) > m) count++;
//            }
//        }
//        System.out.println(count);
//    }

//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int n = in.nextInt();
//        int[] d = new int[n];//先排序
//        System.out.println(3-n%3);
//    }

//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int x = in.nextInt();
//        int k = in.nextInt();
//        int count = 0;
//        int y = 1;
//        while (true) {
//            if (x + y == (x | y)) count++;
//            if(count==k){
//                System.out.println(y);
//                return;
//            }else {
//                y++;
//            }
//        }
//    }




