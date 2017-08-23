package java.jdk.sjms.strategy;

import java.math.BigDecimal;

/**
 * Created by yango on 2017/3/23.
 */
public class Client {
    public static void main(String[] args){
        Strategy strategy = new PrimaryMemberStrategy();
        Context context = new Context(strategy);
        System.out.println(context.quote(new BigDecimal(5)).setScale(2,BigDecimal.ROUND_HALF_UP));
    }
}
