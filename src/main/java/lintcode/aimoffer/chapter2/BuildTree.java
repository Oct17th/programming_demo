package lintcode.aimoffer.chapter2;

/**
 * 前序遍历和中序遍历构造二叉树
 *
 * @author YiJie 2017/8/25.
 */
public class BuildTree {
    public static void main(String[] args) {
        BuildTree b = new BuildTree();
        b.buildTree(new int[]{1, 2, 3}, new int[]{2, 1, 3});
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        pointer = 0;
        return buildBinaryTree(0, inorder.length, preorder, inorder);

    }

    int pointer;//前序序列读取光标指针，因为没办法return出去，所以只好用全局变量

    public TreeNode buildBinaryTree(int instart, int inend, int[] prelist, int[] inlist) {
        //前序序列已遍历完，或已经不存在右子树（instart == inend 一定是递归调用方法传入index + 1导致的）
        if (pointer == prelist.length || instart == inend) return null;
        //在指定某段中序序列中找当前光标指向的前序序列值，一定能找到
        int index = findIndex(pointer, instart, inend, prelist, inlist);
        //在此段中序序列中没有找到当前光标指向的前序序列值
        //出现这种情况主要是因为某个前序序列值，已经在中序序列里查左子节点的时候查到了，还去右子树中查
//        if (index == -1) return null;//pointer用了移动光标自增后，不会出现这种情况，一定能在指定范围的中序序列内，找到当前光标指向的前序序列值
        TreeNode treeNode = new TreeNode(prelist[pointer]);
        pointer++;//存下一个点之后光标指针右移一位
        //以index为分割点，继续寻找左子树和右子树
        treeNode.left = buildBinaryTree(instart, index, prelist, inlist);
        //找完左子树之后，pointer已经移动到了可以开始找右子树的位置
        treeNode.right = buildBinaryTree(index + 1, inend, prelist, inlist);
        return treeNode;
    }

    public int findIndex(int prestart, int instart, int inend, int[] prelist, int[] inlist) {
        for (int i = instart; i < inend; i++) {
            if (inlist[i] == prelist[prestart]) {
                return i;
            }
        }
        System.out.println("find:" + prelist[prestart] + " from" + instart + "to" + inend);
        return -1;
    }
}

class TreeNode {
    public int val;
    public TreeNode left, right;

    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}