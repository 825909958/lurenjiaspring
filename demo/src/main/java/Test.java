import lombok.AllArgsConstructor;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * 方法\处理方式	抛出异常	  返回特殊值	一直阻塞	超时退出
 * 插入方法	    add(e)	  offer(e)	put(e)	offer(e,time,unit)
 * 移除方法	    remove()	poll()	take()	poll(time,unit)
 * 检查方法	    element()	peek()	不可用	不可用
 */
public class Test {
    public static void main(String[] args) {
        LinkedBlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<>(10);
        //Produce produce1 = new Produce(blockingQueue,10);
        Produce produce2 = new Produce(blockingQueue, 10);
        Custom custom1 = new Custom(blockingQueue);
        Custom custom2 = new Custom(blockingQueue);
        //produce1.start();
        produce2.start();
        custom1.start();
        custom2.start();

    }
}

@AllArgsConstructor
class Produce extends Thread {
    private LinkedBlockingQueue<Integer> blockingQueue;
    private Integer num;

    public void setBlockingQueue(LinkedBlockingQueue<Integer> blockingQueue, int num) {
        this.blockingQueue = blockingQueue;
        this.num = num;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                blockingQueue.put(i);
                System.out.println("put:   i: " + i + " threadId: " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

@AllArgsConstructor
class Custom extends Thread {
    private LinkedBlockingQueue<Integer> blockingQueue;

    public void setBlockingQueue(LinkedBlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        while (true) {
            Integer poll = null;
            try {
                poll = blockingQueue.take();
                System.out.println("poll:  i: " + poll + " threadId " + Thread.currentThread().getName());

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
