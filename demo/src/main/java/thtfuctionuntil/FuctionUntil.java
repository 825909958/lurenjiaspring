package thtfuctionuntil;

import java.util.function.Function;

/**
 * @author THT
 */
public class FuctionUntil {
    String name="tht";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public <T, R> R getValue(T t, Function<T,R> fuction){
        return fuction.apply(t);
    }

    public static void main(String[] args) {
        FuctionUntil fuctionUntil = new FuctionUntil();
        String value = fuctionUntil.getValue(fuctionUntil, FuctionUntil::getName);
        System.out.println(value);
    }

}
