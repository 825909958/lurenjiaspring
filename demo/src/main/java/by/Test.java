package by;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Test {
    public static void main(String[] args) {
        BigDecimal bigDecimal = new BigDecimal("1");
        bigDecimal=bigDecimal.add(BigDecimal.ONE);
        System.out.println("bigDecimal = " + bigDecimal);

        BigDecimal decimal = new BigDecimal("200000000");

        String bigDecimal1 = decimal.divide(new BigDecimal(100000000)).setScale(4, RoundingMode.HALF_UP).toPlainString();
        System.out.println("bigDecimal = " + bigDecimal1);

    }

    @org.junit.Test
    public void test1() {
        String s = "aa aa cc";
        if (s.indexOf(" ")>0) {
            String s1 = s.split(" ")[0];
            System.out.println("s1 = " + s1);
        }
    }

    @org.junit.Test
    public void test3() {
        BigDecimal bigDecimal = new BigDecimal(152111867.445900);
        BigDecimal bigDecimal2 = new BigDecimal(52931233.53);
        BigDecimal d = bigDecimal.divide(bigDecimal2, 4, RoundingMode.HALF_UP);
        System.out.println("d = " + d);


    }
}
