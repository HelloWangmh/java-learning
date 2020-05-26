package wang.mh.designPatterns.observer;

import lombok.Getter;

import java.util.Observable;

/**
 * Java 内置的观察者模式
 *
 * ProductInventoryNotifier,当产品的数量变化时候,通知所有的采购商
 */
@Getter
public class ProductInventoryNotifier extends Observable {


    private String productCode; //产品Code

    private int inventory; //库存
    
    
    public void updateInventory(String productCode, int inventory) {
        this.productCode = productCode;
        this.inventory = inventory;
        inventoryChange();
    }
    
    private void inventoryChange() {
        setChanged();
        notifyObservers(); //通知所有观察者
    }

}
