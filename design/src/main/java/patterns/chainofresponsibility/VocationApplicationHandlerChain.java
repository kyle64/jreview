package patterns.chainofresponsibility;

import java.util.List;

/**
 * Created by ziheng on 2019-09-18.
 */
public class VocationApplicationHandlerChain implements HandlerChain {
    private List<Handler> handlers;
    private int pos = 0; // current filter position

    public VocationApplicationHandlerChain(List<Handler> handlers) {
        this.handlers = handlers;
    }

    @Override
    public void handle(VocationRequest vocationRequest, VocationResponse vocationResponse) {
        if (pos < handlers.size()) {
            handlers.get(pos++).doHandle(vocationRequest, vocationResponse, this);
            return;
        }

        System.out.println("reached the end of chain!");
    }
}
