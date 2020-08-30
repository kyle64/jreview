import netty.rpc.consumer.ConsumerProxy;
import netty.rpc.interfaces.ConvertService;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.*;

/**
 * Created by ziheng on 2020/8/28.
 */
public class MyRpcTest {
    @Test
    public void testConvert() {
//        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
//        String input = null;
//        try {
//            input = executor.submit(() -> {
//                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//                String line = reader.readLine();
//                return line;
//            }).get(2000, TimeUnit.MILLISECONDS);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (TimeoutException e) {
//            e.printStackTrace();
//        }
//
//        Assert.assertNotNull(input);

        ConvertService convertService = (ConvertService) ConsumerProxy.create(ConvertService.class);
        convertService.base64Encrypt("123");
    }
}
