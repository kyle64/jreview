package patterns.chainofresponsibility;

/**
 * Created by ziheng on 2019-09-18.
 */
public interface Handler {
    void doHandle(VocationRequest leaveRequest, VocationResponse leaveResponse, HandlerChain handlerChain);
}
