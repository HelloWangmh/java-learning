package wang.mh.java8;

import java.util.function.Supplier;

/**
 * Created by 明辉 on 2017/6/26.
 * 接口的默认方法与静态方法
 */
public class InterfaceDemo {


    public static void main(String[] args) {
        Defaulable defaulable = DefaulableFactory.create(DefaultableImpl :: new);
        System.out.println(defaulable.notRequired());

        defaulable = DefaulableFactory.create(OverridableImpl :: new);
        System.out.println(defaulable.notRequired());
    }


     private interface MyInterface{
        default void defaultMethod(){
            System.out.println("我是default方法");
        }

        static void staticMethod(){
            System.out.println("我是static方法");
        }
     }

    private interface Defaulable {
        default String notRequired() {
            return "Default implementation";
        }
    }

    private static class DefaultableImpl implements Defaulable {
    }

    private static class OverridableImpl implements Defaulable {
        @Override
        public String notRequired() {
            return "Overridden implementation";
        }
    }

    private interface DefaulableFactory {
        // Interfaces now allow static methods
        static Defaulable create( Supplier< Defaulable > supplier ) {
            return supplier.get();
        }
    }
}


