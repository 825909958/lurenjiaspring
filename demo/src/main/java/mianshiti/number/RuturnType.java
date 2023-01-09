package mianshiti.number;

public class RuturnType {
    public double a(byte x, double y) {
        int a = 1;
        double b = 1;
        double a1 = (double) a;
        int b1 = (int) b;
        return (short)x/y*2;
    }
    public static void main(String[] args) {
        RuturnType ruturnType = new RuturnType();
        byte x = 12;
        double a = ruturnType.a(x, 2);
        System.out.println("a = " + a);
    }
}
