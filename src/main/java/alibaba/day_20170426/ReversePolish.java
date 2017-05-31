package alibaba.day_20170426;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * 2017阿里巴巴（第2次投递）网申（ 04.26）在线笔试编程题 1/2
 * 
一个对于一个单行的逆波兰表达式，由如下元素构成：
数字：十进制数字字符构成的正整数，比如 223
运算符：支持三种运算符^+和*，分别代表自增，加法和乘法
分隔符：一个或者多个空格

例如下面的字符串就是个逆波兰表达式
2  3  4 * ^ 5 +
  
逆波兰表达式在一个基于栈的虚拟机中求解，虚拟机的栈能保存16个整数，虚拟机从左向右扫描表达式，遇到整数就压栈，遇到表达式则从栈顶弹出若干个整数进行计算，计算结果重新压回栈中。
其中运算符^从栈顶弹出一个整数，增加1之后压栈；运算符+和*从栈顶弹出两个整数，分别做相加和相乘运算后压栈。
如果遇到运算符的时候，栈内没有足够的整数，称为下溢出，返回-1；把整数压栈的时候，如果栈没有足够的空间，称为上溢出，返回-2；
如果整个计算过程中没有发生溢出，在整个表达式求解完成后，返回栈顶的整数。

时间限制: 1S (C/C++以外的语言为: 3 S)   内存限制: 64M (C/C++以外的语言为: 576 M)

输入:
        输入数据是一个单行的合法逆波兰表达式（可能存在上溢出或者下溢出，但是不会出现语法不合法的情形，
        比如空字符串或者存在不支持的运算符）
输出:
        一个整数
输入范例:
        1 1 + 2 ^ *
输出范例:
        6
 * 
 * @author YiJie
 * @date May 4, 2017
 */
public class ReversePolish {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String[] input = in.nextLine().split("[ ]+");
		
		ReversePolish main = new ReversePolish();
		int result = main.work(input);
		System.out.println(result);
	}

	/*
	 * 1.循环写入数据
	 * 2.判断写入字符或数字，写入数字判断是否上溢出，写入字符判断是否下溢出，取数字
	 * 3.写入数字，判断是否上溢出，是则返回-2，否则入栈
	 * 4.写入字符，出栈两个数，数不够下溢出，返回-1，否则运算后，入栈
	 * 
	 */
	public int work(String[] input) {
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
//			System.out.println(express);
//			flag = Character.isDigit(c) ? push(express, c) : pop(express, c);
			if (flag != 1)
				return flag;
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
				result = num+1;//这里不能写成num++，必须把自增后的值赋值给result，不然没有意义。
			} else if (operator.equals("+")) {
				int num1 = express.pop();
				int num2 = express.pop();
				result = num1 + num2;
			} else if (operator.equals("*")) {
				int num1 = express.pop();
				int num2 = express.pop();
				result = num1 * num2;
			}
		} catch (NoSuchElementException e) {
			return -1;
		}
		express.push(result);;//出栈运算后将结果压栈，运算结果肯定不会上溢出，不需要调用本地push方法进行溢出检测
		return 1;
	}
}
