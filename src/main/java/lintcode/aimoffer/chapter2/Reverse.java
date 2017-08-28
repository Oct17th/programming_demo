package lintcode.aimoffer.chapter2;

/**
 * @author YiJie 2017/8/18.
 */
public class Reverse {
    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        ListNode curr = head;
        for (int i = 1; i < 2; i++) {
            curr.next = new ListNode(i);
            curr = curr.next;
        }
        reverse(head);
    }

    static ListNode head;

    /*
     * @param head: n
     * @return: The new head of reversed linked list.
     */
    public static ListNode reverse(ListNode head) {
        if (head == null) return null;
        reverseNext(head);
        return head;
    }

    public static void reverseNext(ListNode node) {
        if (node.next == null) {
            head = node;
            return;
        }
        reverseNext(node.next);
        node.next.next = node;
        node.next = null;
    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}

