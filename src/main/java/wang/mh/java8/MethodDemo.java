package wang.mh.java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * Created by 明辉 on 2017/6/26.
 * 方法引用
 */
public class MethodDemo {

    public static void main(String[] args) {
        //构造器引用
        Car car = Car.create(Car::new);
        //静态方法引用
        List<Car> cars = Arrays.asList(car);
        cars.forEach(Car::collide);

        //特定类的任意对象的方法引用
        cars.forEach( Car::repair );
        //特定对象的方法引用
        Car newCar = Car.create(Car::new);

        cars.forEach(newCar :: follow);
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
