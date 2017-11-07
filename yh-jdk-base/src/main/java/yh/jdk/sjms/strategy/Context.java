package yh.jdk.sjms.strategy;

import java.math.BigDecimal;

/**
 * Created by yango on 2017/3/23.
 */
public class Context {

    private  Strategy strategy;

    public Context(Strategy strategy){
        this.strategy = strategy;
    }

    public BigDecimal quote(BigDecimal price){
        return strategy.calcPrice(price);
    }


}
