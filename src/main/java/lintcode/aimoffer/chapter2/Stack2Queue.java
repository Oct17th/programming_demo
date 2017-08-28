package lintcode.aimoffer.chapter2;

import java.util.Stack;

/**
 * 用两个栈实现队列
 *
 * @author YiJie 2017/8/25.
 */
public class Stack2Queue {
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    public static void main(String[] args) {
        Stack2Queue s = new Stack2Queue();
        s.push(1);
        s.pop();
        s.push(2);
        s.push(3);
        s.top();
        s.pop();
    }

    public Stack2Queue() {
        stack1 = new Stack<Integer>();
        stack2 = new Stack<Integer>();
    }

    public void push(int element) {
        while (!stack2.empty()) {
            stack1.push(stack2.pop());
        }
        stack1.push(element);
    }

    /**
     * 弹出队列中的第一个(最前面的)元素
     * @return
     */
    public int pop() {
        while (!stack1.empty()) {
            stack2.push(stack1.pop());
        }
        return stack2.pop();
    }

    /**
     * 返回队列中的第一个(最前面的)元素
     * @return
     */
    public int top() {
        while (!stack1.empty()) {
            stack2.push(stack1.pop());
        }
        int first = stack2.pop();
        stack2.push(first);
        return first;
    }
}
