package patterns.template;

/**
 * Created by ziheng on 2019-09-14.
 */
public abstract class RuleCheckpointTemplate {

    abstract void loadRule();

    abstract boolean isResultPersistent(); // 钩子方法

    protected void collectVariables() {
        System.out.println("收集变量");
    }

    protected void fireRules() {
        System.out.println("运行规则");
    }

    private void storeResult() {
        System.out.println("保存规则结果");
    }

    public void execute() {
        loadRule();
        collectVariables();
        fireRules();

        if (isResultPersistent()) {
            storeResult();
        }
    }
}
