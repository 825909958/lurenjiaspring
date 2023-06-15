package juc;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CasDemo {
    AtomicInteger count = new AtomicInteger();

    @Test
    public void a() {
        CasDemo casDemo = new CasDemo();

        List<Thread> threads = new ArrayList<Thread>();

        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(m(casDemo), "thread-" + i));
        }

        threads.forEach(Thread::start);

        threads.forEach((o) -> {
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(casDemo.count);
    }


    private Runnable m(CasDemo casDemo) {
        return ()-> {
            for (int i = 0; i < 10000; i++) {
                casDemo.count.incrementAndGet(); // count++
            }
        };
    }

    @Test
    public void b() {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        System.out.println(atomicInteger.compareAndSet(5, 2020)+"\t"+atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5, 1024)+"\t"+atomicInteger.get());

    }
}
