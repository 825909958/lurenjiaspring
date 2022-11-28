package classorder;

public class Demo extends Parent {
    static {
        System.out.println("静态子");
    }

    {
        System.out.println("构造块子");
    }

    public Demo() {
        System.out.println("构造函数子");
    }

    public static void main(String[] args) {
        Demo demo = new Demo();
    }
}
