package alibaba.day_20170313;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * 17.3.13
 * 内推在线编程测试
 * <p>
 * 计算以每个结点为根节点的树的子节点数n*该节点v
 * 有一个二叉树，记为三位数十进制数组。百位为深度L，十位为位置P，个位为值V，求给定节点全部子路径下的叶子节点数值(三位数LPV)的和
 * 百位十位为递增数，百位1~4，十位0~8
 * <p>
 * 百位十位数值示意图： 2^(n-1) -1+currentNode_P
 * <p>
 * currentNode_L              currentNode_P
 * <p>
 * 1              0                                 10 0
 * /    \
 * 2         0       1                          20 1   21 2
 * /  \    /  \
 * 3      0   1   2   3                       30 3  31 4  32 5  33 6
 * / \ / \ / \ / \
 * 4     0 1 2 3 4 5 6 7                         40 7 |41 8 |42 9 |43 10 |44 11 |45 12 |46 13 |47 14
 *
 * @author YiJie
 * @date May 9, 2017
 */
public class BinaryTreePath {


    private List<Node> tree;
    private int currentNode_L;
    private int currentNode_P;
    private int treesize;
    private List<Node> leaves;
    private List<Node> markleaves;
    private List<Integer> marks;

    public BinaryTreePath() {
        tree = new ArrayList<Node>(16 - 1);//题目规定深度不大于4
        currentNode_L = -1;
        currentNode_P = -1;
        treesize = tree.size();
    }

    public BinaryTreePath(int[] n) {
        this();
        for (int i = 0; i < n.length; i++) {
            addNode(n[i]);
        }
    }

    public void addNode(int V) {
        if (currentNode_L == -1 && currentNode_P == -1) {
            tree.add(new Node(currentNode_L = 0, currentNode_P = 0, V));
            treesize++;
            return;
        }
        int L, P;
        int lastIndexoflineL = (int) Math.pow(2, this.currentNode_L)-1;
        if (this.currentNode_P == lastIndexoflineL) {
            L = ++this.currentNode_L;
            P = this.currentNode_P = 0;
        } else {
            L = this.currentNode_L;
            P = ++this.currentNode_P;
        }
        tree.add(new Node(L, P, V));
        treesize++;
    }


    private int getIndex(int L, int P) {
        int temp = (int) (Math.pow(2, L));//前L-1行的节点个数
        return temp - 1 + P;//加上改行的index
    }

    private int getLeft(int L, int P) {
        return getIndex(L + 1, 2 * P);
    }

    private int getRight(int L, int P) {
        return getIndex(L + 1, 2 * P + 1);
    }


    public List<Node> getLeaves(int L, int P) {
        leaves = new ArrayList<Node>();
        setLeaves(L, P);
        return leaves.size() == 0 ? null : leaves;
    }


    private void markLeaves(int L,int P){
        Integer maxindex = treesize - 1;
        Integer leftindex0 = getLeft(L, P);
        Integer leftindex = leftindex0 <= maxindex ? leftindex0 : null;
        Integer rightindex0 = getRight(L, P);
        Integer rightindex = rightindex0 <= maxindex ? rightindex0 : null;

        if (leftindex == null && rightindex == null) {
//            markleaves.get(getIndex(L, P)).markLeaf();
            marks.add(getIndex(L, P));
            return;
        }
        if (leftindex != null) {
            markLeaves(L + 1, 2 * P);
        }
        if (rightindex != null) {
            markLeaves(L + 1, 2 * P + 1);
        }

    }

    private void setLeaves(int L, int P) {
        Integer maxindex = treesize - 1;
        Integer leftindex0 = getLeft(L, P);
        Integer leftindex = leftindex0 <= maxindex ? leftindex0 : null;
        Integer rightindex0 = getRight(L, P);
        Integer rightindex = rightindex0 <= maxindex ? rightindex0 : null;

        if (leftindex == null && rightindex == null) {
            leaves.add(tree.get(getIndex(L, P)));
            return;
        }
        if (leftindex != null) {
            setLeaves(L + 1, 2 * P);
        }
        if (rightindex != null) {
            setLeaves(L + 1, 2 * P + 1);
        }

    }
    public void printLeaves(int L,int P){
        marks =new ArrayList<Integer>();
        markLeaves(L, P);
        printTree0(tree);
        marks=null;
    }
    public void printLeaves0(int L,int P){
        markleaves = new ArrayList<Node>(tree);
//        Collections.copy(markleaves,tree);
        markLeaves(L,P);
        printTree0(markleaves);
    }
    public void printTree(){
        printTree0(tree);
    }

    private void printTree0(List<Node> tree) {
        if (tree.size() == 0) {
            System.out.println("二叉树为空！");
            return;
        }
        int flag = tree.get(0).L;
        for (Node node : tree) {
            if(node.L>flag){
                System.out.println("\n");
                flag++;
            }
            if(marks!=null&&marks.contains(tree.indexOf(node))){
                System.out.print("["+node.L+node.P+":"+node.V+"]");
                continue;
            }
            System.out.print(node.value+"\t");
        }
        System.out.println();
    }

    class Node {
        int L;
        int P;
        int V;
        String value;

        Node(int L, int P, int V) {
            this.L = L;
            this.P = P;
            this.V = V;
            this.value = String.valueOf(""+L +P +":" + V);
        }

        void markLeaf(){
            this.value = String.valueOf("["+L+P+":"+V+"]");
        }
        @Override
        public String toString() {
            return "Node [L=" + L + ", P=" + P + ", V=" + V + ", value=" + value + "]";
        }
    }
}
