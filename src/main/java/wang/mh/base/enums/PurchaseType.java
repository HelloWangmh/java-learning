package wang.mh.base.enums;

/**
 * Created by 明辉 on 2017/7/4.
 */
public enum PurchaseType {

    money(1),
    zfb(2),
    wxzf(3);


    private int type;

    PurchaseType(int type){
        this.type = type;
    }

    public int getType(){
        return this.type;
    }
}

class MyTest{
    public static void main(String[] args) {
        System.out.println(PurchaseType.money.getType());
    }
}
