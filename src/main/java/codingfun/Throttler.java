package codingfun;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Throttler {
    private static final int PERMIT_POOL_SIZE = 1;
    private final long milliSecondPerRequest;
    private final BlockingQueue<Permit> permitPool = new ArrayBlockingQueue(PERMIT_POOL_SIZE);

    public Throttler(Integer ratePerSecond) {
        milliSecondPerRequest = TimeUnit.SECONDS.toMillis(1) / ratePerSecond;

        System.out.println("interval:" + milliSecondPerRequest );

        new Timer().schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        permitPool.offer(new Permit());
                    }
                }, 0, milliSecondPerRequest);
    }

    public void acquire() {
        try {
            permitPool.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private class Permit {
    }
}
