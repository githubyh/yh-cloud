package com.yh.drools.util;

import com.yh.drools.Base;
import com.yh.drools.RuleEngine;
import com.yh.drools.RuleImpl;
import com.yh.drools.domain.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DroolsUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(DroolsUtil.class);

    // drl规则名称
    private String kSessionName = "call1-order-session";


    public DroolsUtil(String kSessionName) {
        this.kSessionName = kSessionName;
        pointRuleEngine = new RuleImpl(kSessionName);
        pointRuleEngine.initEngine();
    }

    private RuleEngine pointRuleEngine = null;

    public void executeRuleEngine(Base model) {
        LOGGER.info("要提交到规则引擎的数据为:" + model);
        pointRuleEngine.executeRuleEngine(model);
    }

    public void refreshEnginRule() {
        pointRuleEngine.refreshEnginRule();
    }

    public static void main(String[] args) throws Exception {
        DroolsUtil drools = new DroolsUtil("call1-order-session");

        Order order = new Order();
        order.setSumprice(159);
        System.out.println("DISCOUNT IS: " + order.getDiscountPercent());

        drools.executeRuleEngine(order);
        System.out.println("drools IS: " + order.getDiscountPercent());
    }
}
