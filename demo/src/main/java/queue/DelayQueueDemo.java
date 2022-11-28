package queue;

import java.util.concurrent.DelayQueue;

public class DelayQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        DelayQueue<DelayedImpl> delayeds = new DelayQueue<>();

        delayeds.add(new DelayedImpl());
        delayeds.add(new DelayedImpl());
        delayeds.add(new DelayedImpl());

        DelayedImpl take = delayeds.take();

        System.out.println("take = " + take.getName());
        System.out.println("take = " + take.getName());

    }
}
