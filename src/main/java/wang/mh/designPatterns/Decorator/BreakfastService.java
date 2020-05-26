package wang.mh.designPatterns.Decorator;

import java.math.BigDecimal;

/**
 *  含早
 */
public class BreakfastService implements Service {

    private Service service;

    public BreakfastService(Service service) {
        this.service = service;
    }

    @Override
    public BigDecimal getPrice() {
        return service.getPrice().add(BigDecimal.valueOf(10));
    }

    @Override
    public String getDescription() {
        return service.getDescription() + " Breakfast";
    }
}
