import lombok.SneakyThrows;

public class CustomThreadUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler{
    @SneakyThrows
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("aaaaa");
        throw new Exception(e.getMessage());
    }
}
