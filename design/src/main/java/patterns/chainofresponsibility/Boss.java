package patterns.chainofresponsibility;

/**
 * Created by ziheng on 2019-09-18.
 */
public class Boss implements Handler {
    @Override
    public void doHandle(VocationRequest leaveRequest, VocationResponse leaveResponse, HandlerChain handlerChain) {
        if (!leaveResponse.isApproved()) {
            if ("admin".equalsIgnoreCase(leaveRequest.getName())) {
                leaveResponse.setApproved(true);
                leaveResponse.setSuggestion(leaveResponse.getSuggestion() + "Boss's suggestion: approved \n");
            } else {
                leaveResponse.setSuggestion(leaveResponse.getSuggestion() + "Boss's suggestion: not approved \n");
            }
        } else {
            leaveResponse.setSuggestion(leaveResponse.getSuggestion() + "Boss's suggestion: approved \n");
        }

        handlerChain.handle(leaveRequest, leaveResponse);
    }
}
