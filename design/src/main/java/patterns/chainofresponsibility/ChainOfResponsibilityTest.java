package patterns.chainofresponsibility;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ziheng on 2019-09-18.
 */
public class ChainOfResponsibilityTest {
    public static void main(String[] args) {
        Handler[] handlers = {new Leader(), new Manager(), new DepartmentHead(), new Boss()};
        List<Handler> handlerList = Arrays.asList(handlers);
        HandlerChain handlerChain = new VocationApplicationHandlerChain(handlerList);

        VocationRequest request = new VocationRequest();
        request.setName("admin");
        request.setDays(150);
        request.setReason("illness");
        VocationResponse response = new VocationResponse();

        handlerChain.handle(request, response);

        System.out.println(response);
    }
}
