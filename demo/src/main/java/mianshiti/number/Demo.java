package mianshiti.number;

import org.junit.Test;

public class Demo {
    public static void main(String[] args) {
        int i = Integer.parseInt("1024");
        int i1 = Integer.valueOf("1024").intValue();
        boolean b = i == i1;
        System.out.println("b = " + b);
    }

    @Test
    public void a() {
        Float s = new Float(0.1f);
        Float t = new Float(0.1f);
        Double u = new Double(0.1);
        boolean a = s == t;
        boolean b = s.equals(t);
        boolean c = u.equals(s);
        boolean d = t.equals(u);

        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("c = " + c);
        System.out.println("d = " + d);

    }

    @Test
    public void test2() {

        int a = 10;
        System.out.println(a++ + a--);
    }
}
