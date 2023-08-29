package juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {
    private static final int THREAD_COUNT = 5;
    private static final CyclicBarrier BARRIER = new CyclicBarrier(THREAD_COUNT, () -> {
        // 所有线程到达屏障后执行的任务
        System.out.println("All threads have reached the barrier.");
    });

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                // 模拟线程的任务
                System.out.println(Thread.currentThread().getName() + " is working.");
                try {
                    Thread.sleep((long) (Math.random() * 1000));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println(Thread.currentThread().getName() + " is done.");
                try {
                    BARRIER.await(); // 等待其他线程
                } catch (InterruptedException | BrokenBarrierException e) {
                    Thread.currentThread().interrupt();
                }
            }, "Thread-" + i);
            thread.start();
        }
    }
}