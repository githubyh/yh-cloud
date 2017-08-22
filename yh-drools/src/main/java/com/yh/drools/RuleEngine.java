package com.yh.drools;

public interface RuleEngine {

    /**
     * 初始化规则引擎
     */
    public void initEngine();

    /**
     * 刷新规则引擎中的规则
     */
    public void refreshEnginRule();

    /**
     * 执行规则引擎
     * @param objectFace 对接 Fact
     */
    public void executeRuleEngine(final Object objectFace);

}
