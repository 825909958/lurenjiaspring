import java.util.concurrent.ArrayBlockingQueue;

public class ProducerConsumerDemo1 {

    /**
     * 缓冲队列
     */
    private final ArrayBlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(10);

    /**
     * 生产者
     */
    class Producer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                blockingQueue.offer(i);
            }
        }
    }

    /**
     * 消费者
     */
    class Consumer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Integer poll = blockingQueue.poll();
                System.out.println(poll);
            }
        }
    }

    public Producer getProducer() {
        return new Producer();
    }

    public Consumer getConsumer() {
        return new Consumer();
    }


    public static void main(String[] args) throws InterruptedException {
        ProducerConsumerDemo1 producerConsumerDemo1 = new ProducerConsumerDemo1();
        new Thread(producerConsumerDemo1.getProducer()).start();
        new Thread(producerConsumerDemo1.getConsumer()).start();
        Thread.currentThread().join();

    }
}
