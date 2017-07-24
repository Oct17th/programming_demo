package subo.computer;

import javax.swing.*;
import java.awt.*;

/**
 * 计算器显示文本格式化工具类
 * <p>
 * 文本转换有三种形式
 * resetSeparate
 * text：在result上显示的人文本格式，即number带","分隔
 * number：可计算的数字。若为Double类型则正整数以".0"结尾，若为String类型则正整数去尾部".0"
 * process：(String)number + operator
 *
 * @author YiJie  2017/7/13
 **/
public class OperandFormatUtil {


    /**
     * 格式化操作数按与其匹配的字体大小在operandLabel上的显示
     *
     * @param operandLabel    装入操作数文本的的Label
     * @param separateOperand 带正确分隔符的操作数
     * @return
     */
    protected static void setResultText(JLabel operandLabel, String separateOperand) {//TODO 不完善！！待优化！！

        int numcount = removeSeparator(separateOperand).length();

        //长度超出范围直接返回
        if (numcount > 10) {//TODO 暂时隔绝数值过大Double转型失败问题
            return;
        }

        //设置字体大小根据length进行匹配
        int size = 58;
        if (numcount > 9) {
            size = 56;
        }
        if (numcount > 10) {
            size = 52;
        }
        if (numcount > 11) {
            size = 47;//全1 size:52
        }
        if (numcount > 12) {
            size = 43;
        }
        if (numcount > 13) {
            size = 39;
        }
        operandLabel.setText(separateOperand);
        operandLabel.setFont(new Font(operandLabel.getFont().getName(), Font.BOLD, size));
    }

    protected static void setResultText(JLabel resultLabel, Double doubleNumber) {//TODO doubleNumber没有格式化成text
        setResultText(resultLabel, String.valueOf(doubleNumber));
    }


    /**
     * 重置操作数分隔符
     * <p>
     * 1.移除分隔符
     * 2.标记是否为负数，为负则去掉负号进行处理
     * 3取小数点位置（默认为末端）
     * 4.以小数点切分小数部分和整数部分
     * 5.整数部分添加分隔符
     * 6.重新拼接小数部分和带分隔符的整数部分
     * 7.判断是否为负数，为负则补回之前去掉的负号
     *
     * @param wrongSeparateOperand 以错误方式分隔的操作数，可能为不合法操作数
     * @return 带正确分隔符的操作数
     */
    public static String resetSeparate(String wrongSeparateOperand) {
        //先把分隔符全部移除。选用此方法而非removeSeparator，考虑到原操作数可能为不合法法操作数，用此方法做一个String->Double->String的转型
        String unseparateOperand = separateOperand2unseparateOperand(wrongSeparateOperand);
        boolean isNegative = isNegative(unseparateOperand);//是否为负数
        String separateOperand = isNegative ? negate(unseparateOperand) : unseparateOperand;//若为负数先取反去除负号处理
        {//以小数点切分小数部分和整数部分
            int decimalPointIndex = separateOperand.length();//确定小数点位置decimalPointIndex
            if (separateOperand.contains(".")) {
                decimalPointIndex = separateOperand.indexOf(".");
            }
            String decimalOperand = separateOperand.substring(decimalPointIndex);//小数部分
            String integerOperand = separateOperand.substring(0, decimalPointIndex);//整数部分
            {//整数部分添加","分隔符
                StringBuffer separateIntegerOperand = new StringBuffer();//已处理的整数部分

                for (int i = integerOperand.length() - 1; i >= 0; i--) {
                    separateIntegerOperand = separateIntegerOperand.append(integerOperand.charAt(i));
                    separateIntegerOperand = separateIntegerOperand.length() % (3 + 1) == 3 ? separateIntegerOperand.append(",") : separateIntegerOperand;//每隔3位添加一个逗号分隔
                }
                separateIntegerOperand = separateIntegerOperand.reverse();
                integerOperand = separateIntegerOperand.charAt(0) == ',' ? separateIntegerOperand.substring(1) : new String(separateIntegerOperand);
            }
            //拼接带分隔符的整数部分和不处理小数部分
            separateOperand = new String(integerOperand + decimalOperand);
        }
        separateOperand = isNegative ? negate(separateOperand) : separateOperand;//若为负数处理结束后取反加上负号
        return separateOperand;
    }

    /**
     * 判断是否为负数
     *
     * @param operand
     * @return
     */
    public static boolean isNegative(String operand) {
        return operand.startsWith("-");
    }

    /**
     * 判断是否为负数
     *
     * @param operand
     * @return
     */
    public static boolean isNegative(Double operand) {
        return operand.compareTo(0.0) == -1;//operand<0则为负数
    }

    /**
     * 取反。<br/>
     * //TODO 若操作数为 0，取反仍为 0
     *
     * @param operand 操作数 <b>传入数据不能为0<b/>
     * @return 相反数
     */
    public static String negate(String operand) {
        return isNegative(operand) ? operand.substring(1) : "-" + operand;
    }


    /**
     * 移除分隔符
     *
     * @param separateOperand 包含","的文本
     * @return 返回除去","的文本
     */
    private static String removeSeparator(String separateOperand) {
        return separateOperand.replace(",", "");
    }

    /**
     * 格式化separateOperand成Double型的操作数operand<br/>
     * <p>
     * 1.去除分隔符","<br/>
     * 2.String->Double处理不合法的情况（以"."结尾 或 开头结尾有多余的"0"）<br/>
     *
     * @param separateOperand 带分隔符的操作数
     * @return 可计算的Double型的操作数
     */
    public static Double separateOperand2operand(String separateOperand) {
        Double operand = Double.valueOf(removeSeparator(separateOperand));//TODO 这里double做转型，超过7位数会转成Infinity
        return operand;
    }

    /**
     * 格式化Double型的操作数operand成unseparateOperand<br/>
     * 即 Double转String，去除整数末端".0"
     *
     * @param operand 可计算的Double型的操作数，<b>一定为合法的操作数</b>
     * @return 不带分隔符的操作数
     */
    public static String operand2unseparateOperand(Double operand) {
        String unseparateOperand = String.valueOf(operand);
        if (unseparateOperand.endsWith(".0"))
            return unseparateOperand.substring(0, unseparateOperand.length() - 2);
        return unseparateOperand;
    }

    /**
     * 格式化separateOperand成unseparateOperand<br/>
     * <p>
     * 1.去除分隔符","<br/>
     * 2.String->Double->String处理不合法的情况（以"."结尾 或 开头结尾有多余的"0"）<br/>
     * 3.去除整数末端".0"<br/>
     *
     * @param separateOperand 带分隔符的操作数
     * @return 不带分隔符的操作数
     */
    public static String separateOperand2unseparateOperand(String separateOperand) {
        //去除","，转为Double类型，可自动处理不合法text句首句尾多余的0，以及"."结尾的情况
        Double operand = separateOperand2operand(separateOperand);
        //去整数去末端".0"
        String unseparateOperand = operand2unseparateOperand(operand);
        return unseparateOperand;
    }
}