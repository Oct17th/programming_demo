package alibaba.day_20170313;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author YiJie  2017/7/11
 **/
public class Test {
    //    @org.junit.Test
    public static void main(String[] args) {
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//
//        OutputStream outputStream = new BufferedOutputStream(System.out);
//        int num = 0;
//        while (true) {
//
//            try {
//                num = Integer.parseInt(in.readLine());
//
//                System.out.println("*""*");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public void test() {
//        int[] n = {1, 2, 3, 4, 5, 6, 7,8,9,1,2,3,4,5,6,8,9,1,0,2};

        Scanner in = new Scanner(System.in);
//        System.out.println("输入数值以空格分隔：");
//        while (true) {
//            String i = in.next();
//            if (i.equals("#")) break;
//            num.add(Integer.valueOf(i));
//        }

        System.out.println("请输入结点个数：");
        ArrayList<Integer> num = new ArrayList<Integer>();
        int size = in.nextInt();
        int[] tmp = new int[size];
        for (int i = 0; i < size; i++) {
            tmp[i] = i;
        }

        BinaryTreePath binaryTreePath = new BinaryTreePath(tmp);
        System.out.println("--------- 打印二叉树，数据表示形式为 [列标][行标]:[数值] ---------");
        binaryTreePath.printTree();
        while (true) {
            System.out.println("请输入想查询的根结点下标：");
            int i = in.nextInt();
            if (i == -1) break;
//        System.out.println("\n"+binaryTreePath.getLeaves(2, 0));
            System.out.println("--------- 打印二叉树，该根节点的叶子结点用[]标识 ---------");
            binaryTreePath.printLeaves(i / 10, i % 10);
        }



        /*
        开3个线程，
        一个添加/\，标记是否换行，标记是左节点还是右节点
        一个移动根节点，每次加"  "，标记现在操作的叶子结点向上寻根的每个结点的index，用map存
        一个读取控制台输入数据

         */
        StringBuffer path = new StringBuffer();
        path.append("           1          \n" +
                "       /        \\       \n" +
                "     3           2       \n" +
                "   /   \\       /   \\   \n" +
                "  2     3     2     2     \n" +
                " / \\   / \\   /   \n" +
                "2   3 2   2 2    \n");
        path.append(
                "\t\t\t\t1         \n\n" +
                        "\t\t\t3\t\t\t2       \n\n" +
                        "\t\t2\t\t3\t2\t2\n" +
                        "\t  /\t  \\\t\t3\t2\t2\n" +
                        "\t2\t\t3\t2\t2\t2    \n\n");
        String left = "/";
        String right = "\\";
//        String append
//        System.out.println(path);

    }


}

class MoveRoot implements Runnable {


    Map<Integer, String> roots = new HashMap<Integer, String>();

    public void setRootMap(int L, int P, int index) {
        for (int i = 0; i < L; i++) {
            if (P % 2 == 0) {//能整除2为左节点
            }
        }
    }

    @Override
    public void run() {

    }
}

class PrintLimb implements Runnable {

    TYPE nextPositionType;

    public void setLastPositionType(TYPE type) {
        nextPositionType = type;
    }

    @Override
    public void run() {
        switch (nextPositionType) {
            case LEFT:
                System.out.print("   /");
                break;
            case LINEEND:
                System.out.print(" \\");
                break;
            case RIGHT:
                System.out.print("\n/");
                break;
        }
        //TODO 唤醒线程
    }

    enum TYPE {
        LEFT,
        RIGHT,
        LINEEND;
    }

}

