package patterns.chainofresponsibility;

/**
 * Created by ziheng on 2019-09-18.
 */
public interface HandlerChain {
    void handle(VocationRequest vocationRequest, VocationResponse vocationResponse);
}
