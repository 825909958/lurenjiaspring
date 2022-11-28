import jdk.nashorn.internal.runtime.regexp.joni.Matcher;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;

import java.util.concurrent.*;

/**
 * @author THT
 */
public class ClassLoadDemo implements BeanNameAware, BeanFactoryAware {
    public String name = "1";

    public ClassLoadDemo(String paramName) {
        this.name = paramName;
    }

    static void thread(){
        new Thread(() -> {
            int a = 1 / 0;
        }).start();
    }

    public static void main(String[] args) throws InterruptedException {
//        new ClassLoadDemo("22");
        String s = "I'm a good boy,but not boys !";
        Regex re = new Regex("/boy/ig");  // 创建正则表达式模式
        Matcher matcher = re.matcher(s.toCharArray());// 尝试去匹配搜索字符串
        System.out.println("matcher = " + matcher);
//
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("src/main" + "/java/ClassLoadDemo.java");
//        context.setBeanName("ClassLoadDemo");
        CompletableFuture<String> stringCompletableFuture = new CompletableFuture<>();
//        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
//            int a = 1 / 0;
//        });
        // 还是得设置线程的未捕获异常处理器
        Thread.setDefaultUncaughtExceptionHandler(new CustomThreadUncaughtExceptionHandler());
        thread();

        System.out.println("tht");


//        voidCompletableFuture.join();


        System.out.println("stringCompletableFuture = " + stringCompletableFuture);

    }

    private static ThreadPoolExecutor executorPool = new ThreadPoolExecutor(4, 20,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(1),
            Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy()
    ) {
        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            super.afterExecute(r, t);
            if (t == null && r instanceof Future<?>) {
                try {
                    Object result = ((Future<?>) r).get();
                } catch (CancellationException ce) {
                    t = ce;
                } catch (ExecutionException ee) {
                    t = ee.getCause();
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt(); // ignore/reset
                }
            }
            if (t != null) {
                System.out.println("t is :" + t);
                t.printStackTrace();
            }
        }
    };

    @Override
    public void setBeanName(String s) {
        System.out.println(s);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {

        System.out.println(beanFactory);
    }


}
