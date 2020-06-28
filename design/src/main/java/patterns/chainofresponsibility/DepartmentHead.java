package patterns.chainofresponsibility;

/**
 * Created by ziheng on 2019-09-18.
 */
// 部门主管
public class DepartmentHead implements Handler {
    @Override
    public void doHandle(VocationRequest leaveRequest, VocationResponse leaveResponse, HandlerChain handlerChain) {
        if (!leaveResponse.isApproved()) {
            if (leaveRequest.getDays() < 30) {
                leaveResponse.setApproved(true);
                leaveResponse.setSuggestion(leaveResponse.getSuggestion() + "Department Head's suggestion: approved \n");
            } else {
                leaveResponse.setSuggestion(leaveResponse.getSuggestion() + "Department Head's suggestion: not approved \n");
            }
        } else {
            leaveResponse.setSuggestion(leaveResponse.getSuggestion() + "Department Head's suggestion: approved \n");
        }

        handlerChain.handle(leaveRequest, leaveResponse);
    }
}
