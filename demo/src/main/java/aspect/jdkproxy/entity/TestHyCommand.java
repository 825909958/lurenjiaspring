package aspect;


public class TestHyCommand implements ParentInterface {
    @Override
    //@HystrixCommand(fallbackMethod = "b")
    public void a() throws Exception {
        System.out.println("a");
        //throw new Exception("e");
    }



}
