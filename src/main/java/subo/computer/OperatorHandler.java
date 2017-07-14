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
     * 寄存计算结果。一个结果数最多两个运算符（双目+单目）
     */
    //TODO 暂时不考虑单目运算符，registerResult就寄存一个double计算结果和一个操作符
//    static Stack<String> registerResult = new Stack<>();
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
     * <p>
     * 1.按下 = ，若操作符为空 ，return
     * //TODO 若操作符为单目运算符，操作当前数...暂时不考虑单目运算符
     * 取寄存结果{@link OperatorHandler#registerResult}、当前操作符{@link OperatorHandler#currentOperator}
     * 2.若寄存器不为空，计算 操作数 = 寄存器 [操作符] 操作数
     * 3.寄存器置空，操作符置空，操作面板显示操作数，isLockResult==true，操作数append到过程面板
     */
    void equal(final JLabel resultLabel, final JLabel processLabel) {
        String processText = processLabel.getText();
        String separateOperand = resultLabel.getText();
        String unseparateOperand = OperandFormatUtil.separateOperand2unseparateOperand(separateOperand);

        //针对反复按=的处理，&&右边是针对processText=一个操作数+一个操作符的情况下按下=的情况
        if (isLockResult && !processText.endsWith(String.valueOf(currentOperator))) {
            processText += currentOperator;
        }
        processLabel.setText(processText + unseparateOperand);

        Double operand = OperandFormatUtil.separateOperand2operand(separateOperand);
        switch (currentOperator.charValue()) {
            case OperatorHandler.ADD:
                operand = add(registerResult, operand);//调用add方法处理数据
                break;
            case OperatorHandler.SUBTRACT:
                operand = subtract(registerResult, operand);//调用subtract方法处理数据
                break;
            case OperatorHandler.MULTIPLY:
                operand = multiply(registerResult, operand);//调用multiply方法处理数据
                break;
            case OperatorHandler.DIVIDE:
                operand = divide(registerResult, operand);//调用divide方法处理数据
                break;
        }

        //resultLabel显示数据
        unseparateOperand = OperandFormatUtil.operand2unseparateOperand(operand);
        OperandFormatUtil.setResultText(resultLabel, OperandFormatUtil.resetSeparate(unseparateOperand));

        isLockResult = true;//标记已执行equals方法
        registerResult = operand;//存储计算结果
    }

    /**
     * 1.按下操作符，
     * 2.IF isLockResult==true 过程文本、{@link OperatorHandler#currentOperator}替换操作符
     * 3.将操作数+操作符写入过程文本
     * 4.IF 原始过程文本为空，寄存器存入操作数
     * 5.ELSE 调用equals
     * 6.最后将操作符存入{@link OperatorHandler#currentOperator}
     * <p>
     * {@link OperatorHandler#currentOperator}为processText最后一位操作符
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
                if(resultLabel.getText().length()>15||processLabel.getText().length()>270)return;//长度超过限制直接退出不处理
                String processText = processLabel.getText();
                String separateOperand = resultLabel.getText();
                String unseparateOperand = OperandFormatUtil.separateOperand2unseparateOperand(separateOperand);
                Double operand = OperandFormatUtil.separateOperand2operand(separateOperand);

                if (processText.equals("")) {
                    if (registerResult == null) {//初始化状态，process面板里的数据为""，即currentOperator==null
                        processLabel.setText(unseparateOperand + operator);
                        currentOperator = operator;
                        registerResult = operand;
                        isLockResult = true;
                        return;
                    }
                }

                if (isLockResult) {//锁定情况下只允许修改操作符
                    if (processText.endsWith(String.valueOf(OperatorHandler.ADD)) ||
                            processText.endsWith(String.valueOf(OperatorHandler.SUBTRACT)) ||
                            processText.endsWith(String.valueOf(OperatorHandler.MULTIPLY)) ||
                            processText.endsWith(String.valueOf(OperatorHandler.DIVIDE))) {
                        processLabel.setText(processText.substring(0, processText.length() - 1) + operator);
                    } else {
                        processLabel.setText(processText + operator);
                    }
                    currentOperator = operator;
                    return;//反复按操作符不进行运算
                }
//
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
                if(resultLabel.getText().length()>15||processLabel.getText().length()>270)return;//长度超过限制直接退出不处理
                if (currentOperator == null) return;//栈内没有数据与运算符
                equal(resultLabel, processLabel);
                processLabel.setText("");
                currentOperator = null;
                registerResult = null;
            }
        };
    }

}
