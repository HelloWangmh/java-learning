package wang.mh.designPatterns.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * 携程采购商  -> 观察者
 */
public class CtripPurchaser implements Observer {


    private Observable observable;

    public CtripPurchaser(Observable observable) {
        this.observable = observable;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof ProductInventoryNotifier) {
            ProductInventoryNotifier notifier = (ProductInventoryNotifier) o;
            int inventory = notifier.getInventory();
            String productCode = notifier.getProductCode();
            System.out.println("CtripPurchaser productCode : " + productCode + "  inventory :  " + inventory);
        }
    }

}
