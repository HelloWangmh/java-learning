package wang.mh.designPatterns.Decorator;

import java.math.BigDecimal;

/**
 *  装饰者模式
 */
public interface Service {

    BigDecimal getPrice();

    String getDescription();

}
