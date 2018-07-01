package wang.mh.designPatterns.Decorator;

import java.math.BigDecimal;

/**
 *  大床房
 */
public class DoubleRoom implements Service {

    @Override
    public BigDecimal getPrice() {
        return BigDecimal.valueOf(200);
    }

    @Override
    public String getDescription() {
        return "Double Room";
    }
}
