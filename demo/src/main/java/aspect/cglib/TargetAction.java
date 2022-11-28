package aspect.cglib;


public class TargetAction {
    private String info;
    public TargetAction(){

    }

    public TargetAction(String info){
        this.info=info;
    }


    public String doSomething(){
        System.out.println("aspect.cglib do proxy "+ info);

        return "proxy "+info;
    }
}



