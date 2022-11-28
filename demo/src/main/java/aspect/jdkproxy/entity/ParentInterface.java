package aspect.jdkproxy.entity;

public interface ParentInterface {
    @HystrixCommand(fallbackMethod = "b")
     void a() throws Exception;

    static void b() {
        System.out.println("异常熔断");
    }
}
