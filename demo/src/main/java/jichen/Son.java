package jichen;

public class Son extends Parent{
    //static Son a = new Son();

    public Son() {
        System.out.println("Son构造函数");
    }

    static {
        System.out.println("Son静态块");

    }

    {
        System.out.println("Son构造块");

    }

    public static void main(String[] args) {
        new Son();
    }
}
