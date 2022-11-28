package queue;

import lombok.Data;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
@Data
public class DelayedImpl implements Delayed {
    public String name = "tht";
    @Override
     public long getDelay(TimeUnit unit) {
        return  1L;
    }

    @Override
    public int compareTo(Delayed o) {
        return 0;
    }
}
