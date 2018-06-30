package wang.mh.designPatterns.observer;

public class ObserverMain {
    public static void main(String[] args) {
        ProductInventoryNotifier notifier = new ProductInventoryNotifier();
        notifier.addObserver(new CtripPurchaser(notifier)); //增加观察者
        notifier.addObserver(new FliggyPurchaser(notifier));
        notifier.updateInventory("P1234", 10); //更新库存
    }
}
