package classorder;

public class Parent {
    static {
        System.out.println("静态父");
    }

    {
        System.out.println("构造块父");
    }

    public Parent() {
        System.out.println("构造函数父");
    }
}
