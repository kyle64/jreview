package netty.rpc;


/**
 * Created by ziheng on 2020/8/29.
 */
public class Response {
    private String requestId;
    private String content;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
