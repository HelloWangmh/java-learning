package wang.mh.designPatterns.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * 飞猪采购商 --> 观察者
 */
public class FliggyPurchaser implements Observer{

    private Observable observable;

    public FliggyPurchaser(Observable observable) {
        this.observable = observable;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof ProductInventoryNotifier) {
            ProductInventoryNotifier notifier = (ProductInventoryNotifier) o;
            int inventory = notifier.getInventory();
            String productCode = notifier.getProductCode();
            System.out.println("FliggyPurchaser productCode : " + productCode + "  inventory :  " + inventory);
        }
    }
}
