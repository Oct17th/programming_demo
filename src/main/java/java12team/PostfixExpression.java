package java12team;

import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * 后缀表达式<br/>
 * 输入一个后缀表达式，计算得到结果；
 * 如果遇到运算符的时候，栈内没有足够的整数，称为下溢出，返回-1；把整数压栈的时候，如果栈没有足够的空间，称为上溢出，返回-2；
 * <p/>
 * 说明：后缀表达式即运算式不包含括号，运算符放在两个运算对象的后面，
 * 所有的计算按运算符出现的顺序，严格从左向右进行
 * （不再考虑运算符的优先规则，如：(2 + 1) * 3 ， 即2 1 + 3 *
 * <p>
 * 输入:
 * 输入数据是一个单行的合法逆波兰表达式（可能存在上溢出或者下溢出，但是不会出现语法不合法的情形，
 * 比如空字符串或者存在不支持的运算符）
 * 输出:
 * 一个整数
 * 输入范例:
 * 1 1 + 2 ^ *
 * 输出范例:
 * 6
 **/
public class PostfixExpression {
    /*
     * 1.循环写入数据
     * 2.判断写入字符或数字，写入数字判断是否上溢出，写入字符判断是否下溢出，取数字
     * 3.写入数字，判断是否上溢出，是则返回-2，否则入栈
     * 4.写入字符，出栈两个数，数不够下溢出，返回-1，否则运算后，入栈
     */
    public int work(String[] input) throws OverFlowException {
        LinkedList<Integer> express = new LinkedList<>();
        int flag;
        String str;
        for (int i = 0; i < input.length; i++) {
            str = input[i];
            try {
                flag = push(express, Integer.parseInt(str));//若str不能转为int，说明是字符，进行pop并运算
            } catch (NumberFormatException e) {
                flag = pop(express, str);
            }
            if (flag != 1)
                throw new OverFlowException(flag==-1?"运算符输入错误！":"栈没有足够的空间");
        }
        return express.pop();

    }

    private int push(LinkedList<Integer> express, int num) {
        if (express.size() == 16) {
            return -2;
        }
        express.push(num);
        return 1;
    }

    private int pop(LinkedList<Integer> express, String operator) {
        int result = 0;
        try {
            if (operator.equals("^")) {
                int num = express.pop();
                result = ++num;//这里不能写成num++，必须把自增后的值赋值给result，不然没有意义。
            } else {
                int num1 = express.pop();
                int num2 = express.pop();
                switch (operator) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        result = num1 / num2;
                        break;
                }
            }
        } catch (NoSuchElementException e) {
            return -1;
        }
        express.push(result);//出栈运算后将结果压栈，运算结果肯定不会上溢出，不需要调用本地push方法进行溢出检测
        return 1;
    }
}
class OverFlowException extends Exception{
    public OverFlowException(String message) {
        super(message);
    }
}
