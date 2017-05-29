package alibaba;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 2017阿里巴巴（第2次投递）网申（ 04.26）在线笔试编程题 1/2
 * 
阿里的消息中间件，负责淘宝天猫支付宝等各个系统的消息中转，削峰填谷及架构的解耦。在每年的双11中承载了数万亿的消息。
消息中间件中有Pub/Sub两个角色，Pub方发送消息到消息中间件，消息中间件再根据订阅关系投递给订阅方。
例如用户成功购买了一个物品，交易平台（Pub）会发送一条交易完成（trade-done）的消息，购物车平台（Sub）订阅到这个消息后，会将用户的购物车物品删除掉。
这里涉及一个问题，交易平台会发送各种类型的消息，消息中间件是如何准确的将相应的消息投递给购物车平台的？
所使用的就是消息中间件的过滤功能，过滤的方式有很多种，表达式判断，正则匹配等。

假设让你来实现一个过滤功能，来匹配订阅关系是否符合，给定如下条件：
‘?’ 匹配一个字符
‘*’ 匹配任意连串的字符
如上面的例子中，购物车平台订阅方式是pattern=*trade-done，那么
filter(100-trade-done, pattern) = 1,
filter(200-trade-done, pattern) = 1,
filter(200-paid-done, pattern) = 0
 
如果pattern=200-?*-done :
filter(100-trade-done, pattern) = 0, 
filter(200-trade-done, pattern) = 1,
filter(200-paid-done, pattern) = 1
 
如果pattern=1*trade*done :
filter(100-trade-done, pattern) = 1,
filter(200-trade-done, pattern) = 0,
filter(200-paid-done, pattern) = 0

时间限制: 1S (C/C++以外的语言为: 3 S) 内存限制: 64M (C/C++以外的语言为: 576 M) 

输入: 
	第一行，需要过滤的字符串 第二行，匹配模式 
输出: 
	匹配结果，匹配成功返回1，匹配失败返回0 
输入范例: 
	100-trade-done 
	1*trade*done 
输出范例: 
	1 
 * 
 * @author YiJie
 * @date May 4, 2017
 */
public class Main2_1 {
	public static void main(String[] args) {
		// read test
//        String test = "100-trade-done";
		Scanner in = new Scanner(System.in);
		String test = in.nextLine();
		// read pattern
//        String pattern = "1*trade?done";
		String pattern = in.nextLine();
		Main2_1 m = new Main2_1();
		if (m.filter(test, pattern)) {
			System.out.println(1);
		} else {
			System.out.println(0);
		}
	}

	public boolean filter(String test, String stand) {
		String regex = getRegex(stand);
//        Pattern p=Pattern.compile(regex);
//        Matcher m = p.matcher(test);
//        return m.matches();
		return test.matches(regex);

	}

	private String getRegex(String stand) {
		StringBuffer temp = new StringBuffer("^");
		char c;
		for (int i = 0; i < stand.length(); i++) {
			c = stand.charAt(i);
			if ('?' == c) {
				temp.append(".");
				continue;
			}
			if ('*' == c) {
				temp.append(".+");
				continue;
			}
			temp.append("[" + c + "]");
		}
		temp.append("$");
		return new String(temp);
	}
}