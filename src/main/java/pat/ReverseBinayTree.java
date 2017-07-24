package pat;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一棵二叉树的中序遍历和前序遍历，请你先将树做个镜面反转，再输出反转后的层序遍历的序列。
 * 所谓镜面反转，是指将所有非叶结点的左右孩子对换。这里假设键值都是互不相等的正整数。
 * <p>
 * 输入格式：
 * 输入第一行给出一个正整数N（<=30），是二叉树中结点的个数。第二行给出其中序遍历序列。第三行给出其前序遍历序列。数字间以空格分隔。
 * <p>
 * 输出格式：
 * 在一行中输出该树反转后的层序遍历的序列。数字间以1个空格分隔，行首尾不得有多余空格。
 * <p>
 * 输入样例：
 * 7
 * 1 2 3 4 5 6 7
 * 4 1 3 2 6 5 7
 * <p>
 * 输出样例：
 * 4 6 1 7 5 3 2
 *
 * @author YiJie  2017/7/21
 **/
public class ReverseBinayTree {
    /*
    如果每层数据是用数组存的话，镜面反转就是把数据倒序输出
     */
    public static void main(String[] args) {

    }
    public void storeTree(){
        int[] preorder = {4, 1, 3, 2, 6, 5, 7};
        int[] inorder = {1, 2, 3, 4, 5, 6, 7};
        Map<Integer,Integer> node2preindex = new HashMap<Integer, Integer>(7);
        for (int i = 0; i < preorder.length; i++) {
            node2preindex.put(preorder[i],i);
        }
//        Node root = new Node(preorder[0]);
        for (int i = 1; i < preorder.length; i++) {

        }

    }

    public void preorderTraversal(Node node){

    }
    public void inorderTraversal(Node node){

    }
    class Node{
        int value;
        int inorderIndex;
        Node left;
        Node right;
        Node parent;
        Node(int value,int inorderIndex){
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }
    }
}
