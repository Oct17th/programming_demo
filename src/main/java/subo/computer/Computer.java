package subo.computer;

import java.math.BigDecimal;

/**
 * 基于swing的计算器
 *
 * @author YiJie  2017/7/11
 **/
public class Computer {
    Computer() {
        Computer c = new Computer();//构造方法内调用当前构造方法，在类加载的链接->准备阶段会报错 at packageName.className.<init> (xxxline)，抛出栈溢出异常。
        System.out.println("------");
    }

    public static void main(String[] args) {
//        new Computer();
        ComputerFrame computerFrame = new ComputerFrame(new OperatorHandler() {
            @Override
            protected Double add(Double x, Double y) {
                return Double.valueOf(BigDecimal.valueOf(x).add(BigDecimal.valueOf(y)).toString());
            }

            @Override
            protected Double subtract(Double x, Double y) {
                return Double.valueOf(BigDecimal.valueOf(x).subtract(BigDecimal.valueOf(y)).toString());
            }

            @Override
            protected Double multiply(Double x, Double y) {
                return Double.valueOf(BigDecimal.valueOf(x).multiply(BigDecimal.valueOf(y)).toString());
            }

            @Override
            protected Double divide(Double x, Double y) {
                return Double.valueOf(BigDecimal.valueOf(x).divide(BigDecimal.valueOf(y)).toString());
            }
        });
    }
}
