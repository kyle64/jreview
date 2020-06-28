package patterns.chainofresponsibility;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by ziheng on 2019-09-18.
 */
@Getter
@Setter
public class VocationResponse {
    private boolean approved = false;
    private String suggestion;

    @Override
    public String toString() {
        return "VocationResponse{" +
                "approved=" + approved +
                ", suggestion='" + suggestion + '\'' +
                '}';
    }
}
