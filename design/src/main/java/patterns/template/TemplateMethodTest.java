package patterns.template;

/**
 * Created by ziheng on 2019-09-14.
 */
public class TemplateMethodTest {
    public static void main(String[] args) {
        LoanRuleCheckpoint loanRuleCheckpoint = new LoanRuleCheckpoint();
        loanRuleCheckpoint.execute();

        System.out.println("================================");

        TransportRuleCheckpoint transportRuleCheckpoint = new TransportRuleCheckpoint();
        transportRuleCheckpoint.execute();
    }
}
