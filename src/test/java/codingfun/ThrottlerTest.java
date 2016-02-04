package codingfun;

import org.junit.Test;

public class ThrottlerTest {
    @Test
    public void printOutTimeStamp() throws Exception {
        Integer ratePerSecond = 8;
        Throttler throttler = new Throttler(ratePerSecond);

        int requestSize = 40;
        for(int i = 0; i < requestSize; i++){
            throttler.acquire();

            System.out.println(System.currentTimeMillis());
        }

    }
}
