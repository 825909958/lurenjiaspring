package mianshiti.entity;

public class Persion {

    public Persion() {
    }

    public Persion(String name) {
        this.name = name;
    }

    private String name = "Person";
    protected int age = 0;

    public String getName() {
        return name;
    }

    protected Integer getAge() {
        return age;
    }
}
