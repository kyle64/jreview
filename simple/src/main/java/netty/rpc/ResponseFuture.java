package netty.rpc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by ziheng on 2020/8/29.
 */
public class ResponseFuture {
    private String requestId;
    private long timeoutMillis;
    private long beginTimestamp = System.currentTimeMillis();
    private CountDownLatch countDownLatch = new CountDownLatch(1);
    private Response responseContent;


    public Response waitForResponse(final long timeoutMillis) throws InterruptedException {
        this.countDownLatch.await(timeoutMillis, TimeUnit.MILLISECONDS);
        return this.responseContent;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public long getTimeoutMillis() {
        return timeoutMillis;
    }

    public void setTimeoutMillis(long timeoutMillis) {
        this.timeoutMillis = timeoutMillis;
    }

    public Response getResponseContent() {
        return responseContent;
    }

    public void setResponseContent(Response responseContent) {
        this.responseContent = responseContent;
    }
}
