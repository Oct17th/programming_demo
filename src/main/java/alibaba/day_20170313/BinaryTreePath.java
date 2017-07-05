package alibaba.day_20170313;

import java.io.File;

/**
 * 17.3.13
 * 内推在线编程测试
 *
 计算以每个结点为根节点的树的子节点数n*该节点v
 有一个二叉树，记为三位数十进制数组，设深度不大于4
 百位为深度L，十位为位置P，个位为值V，求各路径数值和
 百位十位为递增数，百位0~4，十位0~8
 *
 * @author YiJie
 * @date May 9, 2017
 */
public class BinaryTreePath {



}

class Node{
    int L;
    int P;
    int V;
    int num;
    Node(int num){
        this.num=num;
        this.L=num/100;
        this.P=num%100/10;
        this.V=num%10;
    }
    @Override
    public String toString() {
        return "Node [L=" + L + ", P=" + P + ", V=" + V + ", num=" + num + "]";
    }
}
class Tree{
    int[] l = {0,1,2,3,4};
    int[] p = {0,1,2,3,4,5,6,7,8,9};
    Node n;
//	Integer.toBinaryString();
}
