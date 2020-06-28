package patterns.chainofresponsibility;

/**
 * Created by ziheng on 2019-09-18.
 */
//经理
public class Manager implements Handler {
    @Override
    public void doHandle(VocationRequest leaveRequest, VocationResponse leaveResponse, HandlerChain handlerChain) {
        if (!leaveResponse.isApproved()) {
            if (leaveRequest.getDays() < 7) {
                leaveResponse.setApproved(true);
                leaveResponse.setSuggestion(leaveResponse.getSuggestion() + "Manager's suggestion: approved \n");
            } else if (leaveRequest.getDays() > 60) {
                leaveResponse.setSuggestion(leaveResponse.getSuggestion() + "Manager's suggestion: not approved \n");
            } else {
                leaveResponse.setSuggestion(leaveResponse.getSuggestion() + "Manager's suggestion: pending \n");
            }
        } else {
            leaveResponse.setSuggestion(leaveResponse.getSuggestion() + "Manager's suggestion: approved \n");
        }

        handlerChain.handle(leaveRequest, leaveResponse);
    }
}
