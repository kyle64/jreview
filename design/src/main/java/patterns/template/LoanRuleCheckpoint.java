package patterns.template;

/**
 * Created by ziheng on 2019-09-14.
 */
public class LoanRuleCheckpoint extends RuleCheckpointTemplate {
    @Override
    void loadRule() {
        System.out.println("从数据库加载贷款规则");
    }

    @Override
    boolean isResultPersistent() {
        //需要保存结果
        return true;
    }
}
