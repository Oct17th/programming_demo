package hangseng.day_20170513;

/**
 * 17.05.13恒生电子（南昌大学）现场笔试编程题
 * <p>
 * 36进制与十进制转换
 *
 * @author YiJie
 * @date May 13, 2017
 */

public class Conversion {
    /**
     * 36进制
     */
    private static final int RADIX = 36;
    /**
     * 表示高进制数的所有字符
     */
    private static final char[] DIGITS = {
            '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b',
            'c', 'd', 'e', 'f', 'g', 'h',
            'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z'};

    /**
     * 十进制转36进制
     *
     * @param num
     * @return
     */
    public String conversion(int num) {
        StringBuffer sb = new StringBuffer();
        int length = RADIX;
        int divisor = length;
        while (num > 0) {
            sb.append(DIGITS[num % divisor / (divisor / length)]);
            num -= num % divisor;
            divisor *= length;
        }
        return new String(sb.reverse());
    }

    public static void main(String[] args) {
        Conversion conversion = new Conversion();
        System.out.println(conversion.conversion(12345));
    }
}
