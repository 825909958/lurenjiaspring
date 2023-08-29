package juc;

import java.util.concurrent.*;

public class Restaurant {
    private static final int WAITER_COUNT = 5;
    private static final int TABLE_COUNT = 10;
    private static final int CUSTOMER_COUNT = 20;
    private static final CyclicBarrier BARRIER = new CyclicBarrier(WAITER_COUNT + CUSTOMER_COUNT);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(WAITER_COUNT + CUSTOMER_COUNT);
        for (int i = 0; i < WAITER_COUNT; i++) {
            executorService.execute(new Waiter(i));
        }
        for (int i = 0; i < CUSTOMER_COUNT; i++) {
            executorService.execute(new Customer(i));
        }
        executorService.shutdown();
    }

    static class Waiter implements Runnable {
        private final int id;

        Waiter(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            System.out.printf("Waiter %d is ready.\n", id);
            for (int i = id % TABLE_COUNT; i < TABLE_COUNT; i += WAITER_COUNT) {
                // 询问客人的需求
                System.out.printf("Waiter %d is asking customers at table %d.\n", id, i);
                try {
                    Thread.sleep((long) (Math.random() * 1000));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            try {
                BARRIER.await(); // 等待其他服务员完成
            } catch (InterruptedException | BrokenBarrierException e) {
                Thread.currentThread().interrupt();
            }
            // 所有服务员都完成任务，开始做菜
            System.out.printf("The chefs are starting to cook the dishes.\n");
            try {
                Thread.sleep((long) (Math.random() * 1000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.printf("The dishes are ready!\n");
            try {
                BARRIER.await(); // 等待所有顾客离开餐厅
            } catch (InterruptedException | BrokenBarrierException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    static class Customer implements Runnable {
        private final int id;

        Customer(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            System.out.printf("Customer %d is seated.\n", id);
            try {
                Thread.sleep((long) (Math.random() * 1000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.printf("Customer %d is ready to order.\n", id);
            try {
                BARRIER.await(); // 等待所有服务员询问客人的需求
            } catch (InterruptedException | BrokenBarrierException e) {
                Thread.currentThread().interrupt();
            }
            // 点菜
            System.out.printf("Customer %d is ordering.\n", id);
            try {
                Thread.sleep((long) (Math.random() * 1000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.printf("Customer %d has ordered.\n", id);
            try {
                BARRIER.await(); // 等待所有服务员做好菜
            } catch (InterruptedException | BrokenBarrierException e) {
                Thread.currentThread().interrupt();
            }
            // 吃饭
            System.out.printf("Customer %d is eating.\n", id);
            try {
                Thread.sleep((long) (Math.random() * 1000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.printf("Customer %d has finished eating.\n", id);
            try {
                BARRIER.await(); // 等待所有顾客离开餐厅
            } catch (InterruptedException | BrokenBarrierException e) {
                Thread.currentThread().interrupt();
            }
            System.out.printf("Customer %d is leaving.\n", id);
            //更新后的代码中，服务员和顾客都会在 `CyclicBarrier` 指定的栅栏处等待，直到所有线程都到达栅栏才会继续执行后面的操作。服务员会在所有服务员完成任务后等待，而顾客会在所有服务员做好菜后等待。最后，所有顾客都会在离开餐厅前等待，以确保所有顾客都已经离开了餐厅。
            //
            //这样，就可以保证服务员和顾客之间的同步，避免了多个服务员询问同一桌人的情况，并且在顾客完成就餐后，所有顾客都会离开餐厅。
        }
    }
}