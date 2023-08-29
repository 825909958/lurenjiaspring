package juc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

public class Print01Num {
    private static final Logger log = LoggerFactory.getLogger(Print01Num.class);


    private int n;
        private static Semaphore odd = new Semaphore(1);
        private static Semaphore even = new Semaphore(0);

    public Print01Num(int n) {

        this.n = n;
    }

    public void printOdd() {
        for (int i=1; i<=n; i+=2) {
            try {
                odd.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
            even.release();
        }

    }

    public void printEven() {
        for(int i=2; i<=n; i+=2) {
            try {
                even.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
            odd.release();
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //PrintNum printNum = new PrintNum(10);
        //ExecutorService executorService = Executors.newFixedThreadPool(2);
        //executorService.submit(printNum::printOdd);
        //executorService.submit(printNum::printEven);
        ForkJoinPool.commonPool();
        //executorService.shutdown();
        CompletableFuture<Void> a = CompletableFuture.runAsync(() -> {
            for (int i = 0; i < 25; i++) {
                try {
                    odd.acquire();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("A");
                even.release();
            }

        });

        CompletableFuture<Void> b = CompletableFuture.runAsync(() -> {
            for (int i = 0; i < 25; i++) {
                try {
                    even.acquire();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("B");
                odd.release();
            }

        });
        CompletableFuture<Void> all = CompletableFuture.allOf(a, b);
        Thread.sleep(1000);
        try {
            all.get(1, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            log.error(e.toString());
        }
        //all.join();

    }
}

