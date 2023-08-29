package juc;

import java.util.concurrent.locks.ReentrantLock;

public class AqsStudy {

}


class Counter {
    private int count = 0;
    private ReentrantLock lock = new ReentrantLock();

    public void increment() {
        lock.lock(); // 获取锁
        try {
            count++; // 进行资源访问和修改
        } finally {
            lock.unlock(); // 释放锁
        }
    }

    public int getCount() {
        return count;
    }
}

class IncrementThread extends Thread {
    private Counter counter;

    public IncrementThread(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            counter.increment(); // 调用Counter的increment方法增加计数
        }
    }
}

 class ReentrantLockDemo {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        IncrementThread thread1 = new IncrementThread(counter);
        IncrementThread thread2 = new IncrementThread(counter);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Final Count: " + counter.getCount());
    }
}
