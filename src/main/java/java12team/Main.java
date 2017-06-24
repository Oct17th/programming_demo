package java12team;

import java.util.Scanner;

/**
 * 主类进行测试计算结果
 **/
public class Main {
    public static void main(String[] args) {
		Main main = new Main();
		main.testConversion();
		main.testPostfixExpression();
    }
	
	public void testConversion(){
		System.out.print("输入十进制数：");
		Scanner in = new Scanner(System.in);
		int input = in.nextInt();
		Conversion conversion = new Conversion();
        System.out.println("转换为36进制数："+conversion.conversion(input));
	}
	
	public void testPostfixExpression(){
		System.out.println("输入后缀表达式（每个数字或运算符用空格分隔）：");
		Scanner in = new Scanner(System.in);
        String[] input = in.nextLine().split("[ ]+");

        PostfixExpression postfixExpression = new PostfixExpression();
		int result = 0;
		try {
			result = postfixExpression.work(input);
		} catch (OverFlowException e) {
			System.out.println(e.getMessage());
			return;
		}
		System.out.println("计算结果为："+result);
	}
}
