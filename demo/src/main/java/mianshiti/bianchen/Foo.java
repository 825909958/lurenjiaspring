package mianshiti.bianchen;

public class Foo implements Runnable{
    @Override
    public void run() {
        System.out.println("Running");
    }

    public static void main(String[] args) {
        new Thread(new Foo()).start();
    }
}
