package java.jdk.sjms.strategy;

import java.math.BigDecimal;

/**
 * Created by yango on 2017/3/23.
 */
public class PrimaryMemberStrategy implements Strategy {

    @Override
    public BigDecimal calcPrice(BigDecimal price) {
        return price.multiply(new BigDecimal(0.9));
    }
}
