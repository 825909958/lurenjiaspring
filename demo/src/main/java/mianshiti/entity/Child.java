package mianshiti.entity;

public class Child extends Persion {
    //public  String grade;
    //public String name="aaa";
   // 默认构造器有和他所在类相同的访问
    public Child() {
    }

    public Child(String name) {
        // 要求父类有无参构造  否则调用有参构造
        super("a");
        System.out.println("D");
        //this.grade = grade;
    }

    @Override
    // 重写方法不能没有返回值
    protected Integer getAge() {
        return super.getAge();
    }

    //@Override
    //public String getName() {
    //    String s = "1221";
    //    int age = super.age;
    //    String name = super.getName();
    //    return this.name;
    //}

    //public Long getAge() {
    //    return 1L;
    //}

    public static void main(String[] args) {
        Persion p = new Child("a");
        System.out.println("p.getName() = " + p.getName());
        //System.out.println("p.getName() = " + p.getAge());
    }
}
