package subo.computer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 建议实现类通过BigDecimal来实现计算
 *
 * @author YiJie  2017/7/12
 **/
abstract class OperatorHandler {
    private static final char ADD = '+';
    private static final char SUBTRACT = '-';
    private static final char MULTIPLY = '×';
    private static final char DIVIDE = '÷';
    /**
     * 寄存计算结果
     */
    Double registerResult = null;
    /**
     * 当前操作符
     */
    Character currentOperator = null;
    /**
     * 标识当前result面板显示数据状态。
     * 调用equals处理（=或操作符）后锁定
     * 点击数字或小数点后解除锁定
     */
    boolean isLockResult = false;//isLockResult

    boolean getIsResetResult() {
        return currentOperator == null;
    }

    /**
     * + 操作处理
     */
    protected abstract Double add(Double x, Double y);

    /**
     * - 操作处理
     *
     * @param x 被减数
     * @param y 减数
     */
    protected abstract Double subtract(Double x, Double y);

    /**
     * × 操作处理
     */
    protected abstract Double multiply(Double x, Double y);

    /**
     * ÷ 操作处理
     *
     * @param x 被除数
     * @param y 除数
     */
    protected abstract Double divide(Double x, Double y);

    /**
     * = 操作处理
     * 取processText末尾操作符，取寄存的processResult和resultText进行操作
     * TODO 若precess为空?      运算要自定义一个操作数栈
     * 结果append到processLabel并显示到resultLabel
     */
    void equal(final JLabel resultLabel, final JLabel processLabel) {
//        if (isAlreadyEquals) {
//            return;
//        }

        //处理数据String转Double
        String resultText = resultLabel.getText();
        if (resultText.endsWith(".")) {//TODO 处理不合法的输入数据
            resultText = resultText.substring(0, resultText.length() - 1);
        }

        //processLabel显示数据
//        String prefix = processLabel.getText().equals("0") ? "" : processLabel.getText();//processLabel的数据内容为0
        String prefix = processLabel.getText();
        Double result = TextFormatUtil.string2Double(resultText);

        if (isLockResult && !prefix.endsWith(String.valueOf(currentOperator))) {//针对重复按=的情况，补操作符
            processLabel.setText(prefix + currentOperator + (result.equals(0.0) ? "0" : TextFormatUtil.formatProcessText(result)));
        } else {
            processLabel.setText(prefix + (result.equals(0.0) ? "0" : TextFormatUtil.formatProcessText(result)));
        }

        switch (currentOperator.charValue()) {
            case OperatorHandler.ADD:
                result = add(registerResult, result);//调用add方法处理数据
                break;
            case OperatorHandler.SUBTRACT:
                result = subtract(registerResult, result);//调用subtract方法处理数据
                break;
            case OperatorHandler.MULTIPLY:
                result = multiply(registerResult, result);//调用multiply方法处理数据
                break;
            case OperatorHandler.DIVIDE:
                result = divide(registerResult, result);//调用divide方法处理数据
                break;
        }

        //resultLabel显示数据
        if (result.equals(0.0)) {
            resultLabel.setText("0");
        } else {
            TextFormatUtil.setResultText(resultLabel, TextFormatUtil.formatResultText(result));
        }

        isLockResult = true;//标记已执行equals方法
        registerResult = result;//存储计算结果
    }

    /**
     * 栈里没数
     * <p>
     * 没有运算符（进来该方法的都有运算符）
     *
     * @param resultLabel
     * @param processLabel
     * @param operator
     * @return
     */
    ActionListener getOperatorActionListener(final JLabel resultLabel, final JLabel processLabel, final Character operator) {

        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String prefix = processLabel.getText();
                if (isLockResult) {
                    if (prefix.endsWith(String.valueOf(OperatorHandler.ADD)) ||
                            prefix.endsWith(String.valueOf(OperatorHandler.SUBTRACT)) ||
                            prefix.endsWith(String.valueOf(OperatorHandler.MULTIPLY)) ||
                            prefix.endsWith(String.valueOf(OperatorHandler.DIVIDE))) {
                        processLabel.setText(prefix.substring(0, prefix.length() - 1) + operator);
                    } else {
                        processLabel.setText(prefix + operator);
                    }
                    currentOperator = operator;
                    return;////反复按操作符不进行运算
                }
//                if(prefix.equals("")){
                if (registerResult == null) {//初始化状态，process面板里的数据为""，即currentOperator==null
                    processLabel.setText(TextFormatUtil.formatProcessText(resultLabel.getText()) + operator);
                    currentOperator = operator;
                    registerResult = TextFormatUtil.string2Double(resultLabel.getText());
                    isLockResult = true;
                    return;
                }
                //到此位置 断言 currentOperator!=null && operator!=null
                equal(resultLabel, processLabel);//方法内已设置了registerResult和isLockResult
                currentOperator = operator;
                processLabel.setText(processLabel.getText() + operator);
            }
        };
    }

    /**
     * 取processText末尾操作符，取寄存的processResult和resultText进行操作
     * 计算完后更新registerResult，置空currentOperation，标记isLockResult
     *
     * @param resultLabel
     * @param processLabel
     * @return
     */
    ActionListener getEqualActionListener(final JLabel resultLabel, final JLabel processLabel) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentOperator == null) return;//栈内没有数据与运算符
                equal(resultLabel, processLabel);
//                currentOperator = null;
            }
        };
    }
}

