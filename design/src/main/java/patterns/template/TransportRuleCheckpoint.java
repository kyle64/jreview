package patterns.template;

/**
 * Created by ziheng on 2019-09-14.
 */
public class TransportRuleCheckpoint extends RuleCheckpointTemplate {
    @Override
    void loadRule() {
        System.out.println("从缓存加载运输规则");
    }

    @Override
    boolean isResultPersistent() {
        return false;
    }
}
