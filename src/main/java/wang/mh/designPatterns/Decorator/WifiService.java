package wang.mh.designPatterns.Decorator;

import java.math.BigDecimal;

/**
 *  含Wifi
 */
public class WifiService implements Service {

    private Service service;

    public WifiService(Service service) {
        this.service = service;
    }

    @Override
    public BigDecimal getPrice() {
        return service.getPrice().add(BigDecimal.valueOf(5));
    }

    @Override
    public String getDescription() {
        return service.getDescription() + " Wifi";
    }
}
