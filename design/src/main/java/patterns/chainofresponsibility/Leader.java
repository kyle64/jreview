package patterns.chainofresponsibility;

/**
 * Created by ziheng on 2019-09-18.
 */
//组长
public class Leader implements Handler {
    @Override
    public void doHandle(VocationRequest leaveRequest, VocationResponse leaveResponse, HandlerChain handlerChain) {
        if (!leaveResponse.isApproved()) {
            if (leaveRequest.getDays() < 2) {
                leaveResponse.setApproved(true);
                leaveResponse.setSuggestion("Leader's suggestion: approved \n");
            } else if (leaveRequest.getDays() > 90) {
                leaveResponse.setSuggestion("Leader's suggestion: not approved \n");
            } else {
                leaveResponse.setSuggestion("Leader's suggestion: pending \n");
            }
        }

        handlerChain.handle(leaveRequest, leaveResponse);
    }
}
