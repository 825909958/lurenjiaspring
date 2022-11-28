package jichen;

public class Parent {
    //static Parent a = new Parent();

    public Parent() {
        System.out.println("parent构造函数");
    }

    static {
        System.out.println("parent静态块");

    }

    {
        System.out.println("parent构造块");

    }

    public static void main(String[] args) {
        new Parent();
    }
}
