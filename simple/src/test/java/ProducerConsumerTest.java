import concurrent.MyConsumer;
import concurrent.MyProducer;
import org.junit.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by ziheng on 2019-08-15.
 */
public class ProducerConsumerTest {

    @Test
    public void producerConsumerTest() throws InterruptedException {
        int producerNum = 4;
        int consumerNum = 3;

        BlockingQueue<Object> blockingQueue = new LinkedBlockingQueue<>(5);

        for (int i = 0; i < producerNum; i++) {
            new Thread(new MyProducer(blockingQueue)).start();
        }

        for (int i = 0; i < consumerNum; i++) {
            new Thread(new MyConsumer(blockingQueue)).start();
        }

        Thread.sleep(1000);
        System.exit(0);
    }
}
