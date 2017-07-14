package subo.computer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 计算器界面UI
 * 通过调用构造方法实例化，即可打开界面
 * 需要传入一个OperatorHandler实现类对操作符进行处理
 *
 * @author YiJie  2017/7/12
 **/
public class ComputerFrame extends JFrame {
    protected static String SHOW_FONT = "Yu Gothic UI Semibold";
    protected static String KEYBOARD_FONT = "微软雅黑";
    /**
     * 显示计算过程
     */
    protected JLabel processLabel;
    /**
     * 显示计算结果
     */
    protected JLabel resultLabel;
    /**
     * 运算符计算
     */
    private OperatorHandler operatorHandler;

    public ComputerFrame(OperatorHandler operatorHandler) {
        this.operatorHandler = operatorHandler;


        this.setTitle("计算器");
        this.setBounds(76, 85, 417, 688);//仿照win10的尺寸
        this.setLayout(new GridLayout(2, 1));

        //设置显示面板
        JPanel displayPanel = new JPanel(new GridLayout(3, 1));
        {//设置processLabel
            processLabel = new JLabel("", SwingConstants.RIGHT);
            processLabel.setFont(new Font("Malgun Gothic", Font.PLAIN, 18));
            processLabel.setBorder(new EmptyBorder(0, 17, 0, 17));

            //利用JScrollPane添加水平滚动条需要时自动出现
            JScrollPane js = new JScrollPane(processLabel);
            js.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            displayPanel.add(js);
        }
        {//设置resultLabel
            resultLabel = new JLabel("0", SwingConstants.RIGHT);
            resultLabel.setFont(new Font(SHOW_FONT, Font.BOLD, 58));
            resultLabel.setBorder(new EmptyBorder(0, 15, 0, 15));
            displayPanel.add(resultLabel);
        }
        this.add(displayPanel);//添加显示面板

        this.add(getKeyboardPanel());//添加键盘面板
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);//TODO 记录上次关闭位置
        this.setVisible(true);
    }

    /**
     * 获取键盘面板
     *
     * @return
     */
    private JPanel getKeyboardPanel() {
        final String[] keyboard = {"%", "√", "x²", "1/x", "CE", "C", "DEL", "÷", "7", "8", "9", "×", "4", "5", "6", "-", "1", "2", "3", "+", "±", "0", ".", "="};
        String currentKey;
        JPanel keyboardPanel = new JPanel(new GridLayout(5, 4));

        for (int i = 4; i < keyboard.length; i++) {
            currentKey = keyboard[i];
            JButton jButton = new JButton(keyboard[i]);
            //添加事件监听
            switch (currentKey) {
                case "CE":
                    jButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            resultLabel.setText("0");
                            resultLabel.setFont(new Font(SHOW_FONT, Font.BOLD, 58));
                        }
                    });
                    break;
                case "C":
                    jButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            processLabel.setText("");
                            processLabel.setFont(new Font(SHOW_FONT, Font.PLAIN, 17));
                            resultLabel.setText("0");
                            resultLabel.setFont(new Font(SHOW_FONT, Font.BOLD, 58));
                            operatorHandler.registerResult = null;//存储的计算过程结果置空
                            operatorHandler.currentOperator = null;//当前操作符置空
                            operatorHandler.isLockResult = false;//标记未进行equals操作
                        }
                    });
                    break;
                case "DEL":
                    jButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (operatorHandler.isLockResult) {//进行过运算后result显示的是计算结果，不可删除
                                return;
                            }
                            String originalText = resultLabel.getText();
                            originalText = originalText.substring(0, originalText.length() - 1);//删一位

                            boolean isNegative = originalText.startsWith("-");//是否为负数
                            originalText = isNegative ? originalText.substring(1) : originalText;//去负号处理

                            //若result面板数据为 （±）0.  或（±）一位数的情况
                            if (originalText.length() == 0 || originalText.equals("0")) {//若删除一位后originalText长度为0，则直接显示0（不再进行补负号操作）
                                resultLabel.setText("0");
                                return;
                            }

                            originalText = isNegative ? "-" + originalText : originalText;//补充负号
                            OperandFormatUtil.setResultText(resultLabel, OperandFormatUtil.resetSeparate(originalText));
                        }
                    });
                    break;
                case "±":
                    jButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String originalText = resultLabel.getText();
                            OperandFormatUtil.setResultText(resultLabel, originalText.equals("0") ? "0" : originalText.startsWith("-") ? originalText.substring(1) : "-" + originalText);
                        }
                    });
                    break;
                case ".":
                    jButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (resultLabel.getText().contains(".")) return;
                            if (operatorHandler.isLockResult) {
                                resultLabel.setText("0.");
                            } else {
                                OperandFormatUtil.setResultText(resultLabel, resultLabel.getText() + ".");
                            }
                            operatorHandler.isLockResult = false;
                        }
                    });
                    break;
                case "+"://TODO process超过长度限制不处理
                case "-":
                case "×":
                case "÷":
                    jButton.addActionListener(operatorHandler.getOperatorActionListener(resultLabel, processLabel, currentKey.charAt(0)));
                    break;
                case "=":
                    jButton.addActionListener(operatorHandler.getEqualActionListener(resultLabel, processLabel));
                    break;
                default://按数字键
                    final String finalCurrentKey = currentKey;
                    jButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (operatorHandler.isLockResult) {
                                resultLabel.setText(finalCurrentKey);
                            } else {
                                String originalText = resultLabel.getText();
                                originalText = originalText + finalCurrentKey;
                                OperandFormatUtil.setResultText(resultLabel, OperandFormatUtil.resetSeparate(originalText));
                            }
                            operatorHandler.isLockResult = false;

                        }
                    });
//                    jButton.addActionListener(new NumberActionListener(keyboard[i], resultLabel,operatorHandler.isResetResult));
            }
            //设置键盘字体
            if (keyboard[i].matches("\\d")) {
                jButton.setFont(new Font(KEYBOARD_FONT, Font.BOLD, 25));//0~9加粗
            } else {
                if (keyboard[i].matches("[A-Z]+")) {
                    jButton.setFont(new Font(KEYBOARD_FONT, Font.PLAIN, 19));//字母size偏大
                } else {
                    jButton.setFont(new Font(KEYBOARD_FONT, Font.PLAIN, 25));
                }
            }
            keyboardPanel.add(jButton);
        }
        return keyboardPanel;
    }
}







