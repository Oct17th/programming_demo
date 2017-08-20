package lianjia;

//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.PrintStream;
//import java.util.Iterator;
//import java.util.Scanner;
//import java.util.TreeSet;
//
//public class Main {
//    public static void main(String[] args) {
//        try {
//            Scanner in = new Scanner(new FileInputStream("random.in"));
//            TreeSet<Integer> set = new TreeSet<Integer>();
//            int N = in.nextInt();
//            //in.nextLine();
//            for (int i = 0; i < N; i++) {
//                set.add(in.nextInt());
//            }
//            PrintStream out = new PrintStream(new FileOutputStream("random.out"));
//            out.println(set.size());
//            Iterator it = set.iterator();
//            while (it.hasNext()) {
//                out.print(it.next()+" ");
//            }
//            in.close();
//            out.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//    }
//}



import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] ai = new int[n];
        for (int i = 0; i < n; i++) {
            ai[i] = i == 0 ? in.nextInt() : ai[i - 1] + in.nextInt();
        }
        int Q = in.nextInt();
        int[] Qi = new int[n];
        for (int i = 0; i < Q; i++) {
            Qi[i] = in.nextInt();
            for (int j = 1; j < n; j++) {
                if (Qi[i] <= ai[0]) {
                    System.out.println(1);
                    break;
                } else if (Qi[i] > ai[j - 1] && Qi[i] <= ai[j]) {
                    System.out.println(j + 1);
                    break;
                }
            }
        }
    }
}

/**
 * 模拟 2
 * @author YiJie 2017/8/19.
 */
class Main2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int count = 0;
        boolean flag = true;
        //循环被除数，
        int i = 2;
        while (true) {
            //循环除数
            for (int k = 2; k < i; k++) {
                if (i % k == 0) {
                    flag = false;
                    break;
                }
            }
            //是素数
            if (flag) {
                if (count == n - 1) {
                    System.out.println(i);
                    return;
                } else {
                    count++;
                }
            }
            flag = true;
            i++;
        }
    }
}

/**
 * 模拟 1
 */
class Main1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            int a = in.nextInt();
            int b = in.nextInt();
            System.out.print(a + b);
        }
    }
}