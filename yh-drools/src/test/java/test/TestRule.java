package test;

import com.yh.drools.util.DroolsUtil;
import com.yh.drools.domain.Order;
import com.yh.drools.domain.PointDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public class TestRule {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestRule.class);

    public static  void main(String[] args) throws Exception {
        DroolsUtil  drools = new DroolsUtil("call1-order-session");
        while(true) {
            InputStream is = System.in;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String input = br.readLine();

            if (null != input && "o".equals(input)) {
                TestOrder(drools);
            }else  if(null != input && "p".equals(input)) {
                TestPoint();
            }
        }

    }

    private static void TestPoint() {

        DroolsUtil drools = new DroolsUtil("call1-point-session");
        PointDomain pointDomain = new PointDomain();
        pointDomain.setBackNums(55);
//        ksession.execute( Arrays.asList( new Object[]{order} ) );


        pointDomain.setUserName("hello kity");
        pointDomain.setBackMondy(100d);
        pointDomain.setBuyMoney(500d);
        pointDomain.setBackNums(1);
        pointDomain.setBuyNums(5);
        pointDomain.setBillThisMonth(5);
        pointDomain.setBirthDay(true);
        pointDomain.setPoint(0l);

        drools.executeRuleEngine(pointDomain);

        System.out.println("执行完毕BillThisMonth："+pointDomain.getBillThisMonth());
        System.out.println("执行完毕BuyMoney："+pointDomain.getBuyMoney());
        System.out.println("执行完毕BuyNums："+pointDomain.getBuyNums());

        System.out.println("执行完毕规则引擎决定发送积分："+pointDomain.getPoint());


    }
    private static void TestOrder(DroolsUtil  drools) {


        Order order = new Order();
        order.setSumprice(159);
//        ksession.execute( Arrays.asList( new Object[]{order} ) );


        System.out.println( "DISCOUNT IS: " + order.getDiscountPercent() );

        drools.executeRuleEngine(order);
        System.out.println( "drools IS: " + order.getDiscountPercent() );
        drools.refreshEnginRule();
    }
}