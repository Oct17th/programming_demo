package subo.computer;

import javax.swing.*;
import java.awt.*;

/**
 * @author YiJie  2017/7/13
 **/
public class TextFormatUtil {


    /**
     * 包装resultLabel的text输出。根据字长设置text的字体大小
     *
     * @param resultLabel 装入text的label
     * @param text
     * @return
     */
    protected static void setResultText(JLabel resultLabel, String text) {//TODO 不完善！！待优化！！

        int numcount = spiltResultText(text).length();

        //长度超出范围直接返回
        if (numcount > 15) {
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
        resultLabel.setText(text);
        resultLabel.setFont(new Font(resultLabel.getFont().getName(), Font.BOLD, size));
    }

    protected static void setResultText(JLabel resultLabel, Double text) {
        setResultText(resultLabel,String.valueOf(text));
    }

    /**
     * 过滤","
     * @param text
     * @return
     */
    public static String spiltResultText(String text){
        return text.replace(",", "");
    }


    /**
     * 格式化resultText。为每三位正整数text添加","分隔符，控制负号"-"、小数点"."、和句首"0"的显示
     * <p>
     * 1.判断是否为负数，为负则去掉负号进行处理
     * 2.以"0."开头不处理
     * 3.若为小数，以小数点为分隔，截取处理前半段整数部分。小数点index默认为句末
     * 4.整数不等于0的情况下，去句首0
     * 5.整数部分加","分隔符操作
     *
     * @param resultText 仅仅追加了一个字符的文本
     * @return
     */
    public static String formatResultText(String resultText) {//TODO 设定输入的是一个标准的int，重写该方法
        String originalText = spiltResultText(resultText);

        boolean isNegative = originalText.startsWith("-");//是否为负数
        originalText = isNegative ? originalText.substring(1) : originalText;//若为负数先去除符号处理
        if (!originalText.startsWith("0.")) {
            //取出数字首位的0
            originalText = originalText.startsWith("0") ? originalText.substring(1, originalText.length()) : originalText;

            //重置","分割符
            int decimalpointIndex = originalText.length();//确定小数点位置
            if (originalText.contains(".")) {
                decimalpointIndex = originalText.indexOf(".");
            }
            StringBuffer stringBuffer = new StringBuffer();
//            StringBuffer stringBuffer = new StringBuffer(originalText.substring(decimalpointIndex, originalText.length()));
//            stringBuffer.reverse();
            for (int i = decimalpointIndex - 1; i >= 0; i--) {//小数点后面不再加","分隔符
                stringBuffer = stringBuffer.append(originalText.charAt(i));
                stringBuffer = stringBuffer.length() % (3 + 1) == 3 ? stringBuffer.append(",") : stringBuffer;
            }
            originalText = new String(stringBuffer.reverse().append(originalText.substring(decimalpointIndex, originalText.length())));
            originalText = originalText.startsWith(",") ? originalText.substring(1) : originalText;
        }
        originalText = isNegative ? "-" + originalText : originalText;//若为负数处理结束后加上符号
        return originalText;
    }

    public static String formatProcessText(String resultText){
        String temp = spiltResultText(resultText);
        if( temp.endsWith("."))
            return temp.substring(0,temp.length()-1);
        return temp;
    }
    public static String formatProcessText(Double resultText){
        String temp = spiltResultText(String.valueOf(resultText));
        if(temp.endsWith(".0"))
            return temp.substring(0,temp.length()-2);
        return temp;
    }
    /**
     * 去句尾0
     * @param resultText
     * @return
     */
    public static String formatResultText(Double resultText) {
        String originalText = String.valueOf(resultText);
        originalText = originalText.endsWith(".0") ? originalText.substring(0, originalText.length() - 2) : originalText;
        if (resultText < 0) {
            return "-" + formatResultText(originalText.substring(1));
        } else {
            return formatResultText(originalText);
        }
    }
    public static Double string2Double(String text){
        return Double.valueOf(formatProcessText(text));
    }

    /**
     * 处理首位带多余0的Double
     * @param number
     * @return
     */
    public static String double2String(Double number){
        return null;
    }


}