package subo.computer;

import java.math.BigDecimal;

/**
 * 基于swing的计算器测试类
 *
 * @author YiJie  2017/7/11
 **/
public class Computer {
    public static void main(String[] args) {
        new ComputerFrame(new OperatorHandler() {
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
