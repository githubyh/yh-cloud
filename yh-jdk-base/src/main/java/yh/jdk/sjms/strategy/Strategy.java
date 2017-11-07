package yh.jdk.sjms.strategy;

import java.math.BigDecimal;

/**
 * Created by yango on 2017/3/23.
 */
public interface Strategy {
    public BigDecimal calcPrice(BigDecimal price);
}
