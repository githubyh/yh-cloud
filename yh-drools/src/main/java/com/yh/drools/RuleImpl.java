package com.yh.drools;

import org.drools.core.marshalling.impl.RuleBaseNodes;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class RuleImpl implements RuleEngine {
    private KieServices ks;
    private String kSessionName;

    public RuleImpl(String kSessionName) {
        this.kSessionName = kSessionName;
    }

    @Override
    public void initEngine() {
        // 设置时间格式
        System.setProperty("drools.dateformat", "yyyy-MM-dd HH:mm:ss");
        // KieServices is the factory for all KIE services

        ks = KieServices.Factory.get();

    }

    @Override
    public void refreshEnginRule() {
//        ks = new KieServicesImpl();

    }

    @Override
    public void executeRuleEngine(Object objectFace) {
        KieContainer kc = ks.getKieClasspathContainer();
        KieSession ksession = kc.newKieSession(kSessionName);
        ksession.insert(objectFace);
        int count = ksession.fireAllRules();
        System.out.println("总执行了"+count+"条规则");
        ksession.dispose();
    }
}