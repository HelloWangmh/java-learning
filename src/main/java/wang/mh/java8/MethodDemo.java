package wang.mh.java8;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * Created by 明辉 on 2017/6/26.
 * 方法引用
 * object::instanceMethod      方法引用等价于提供方法参数的lambda表达式
 * Class::staticMethor          方法引用等价于提供方法参数的lambda表达式
 * Class::instanceMethod        特殊***第一个参数会成为方法的目标
 */
public class MethodDemo {

    public static void main(String[] args) {
        //构造器引用
        Car car = Car.create(Car::new);

        List<Car> cars = Arrays.asList(car);
        //静态方法引用Class::staticMethor
        cars.forEach(Car::collide);
        cars.forEach(c -> Car.collide(c));


        //特定类的任意对象的方法引用 Class::instanceMethod
        cars.forEach( Car::repair );
        cars.forEach(c -> c.repair());


        Car newCar = Car.create(Car::new);

        //特定对象的方法引用   object::instanceMethod    打印出来的是cars里的对象 不是newCar
        cars.forEach(newCar :: follow);
        cars.forEach(c -> c.follow(c));
        cars.forEach(c -> c.follow(newCar));

    }

    public static class Car {
        public static Car create( final Supplier< Car > supplier ) {
            return supplier.get();
        }

        public static void collide( final Car car ) {
            System.out.println( "Collided " + car.toString() );
        }

        public void follow( final Car another ) {
            System.out.println( "Following the " + another.toString() );
        }

        public void repair() {
            System.out.println( "Repaired " + this.toString() );
        }
    }
}

class Greeter{


    public void greet(){
        System.out.println("Hello world");
    }
}
class SonGreeter extends  Greeter{

    public void greet() {
//        Timer timer = new Timer(1000,super::greet);
    }
}


