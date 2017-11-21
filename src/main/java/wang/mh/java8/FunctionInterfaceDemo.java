package wang.mh.java8;

import wang.mh.common.Father;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Created by 明辉 on 2017/9/9.
 * 测试常用函数式接口
 */
public class FunctionInterfaceDemo {
    public static void main(String[] args) {
        //Runnable
        repeat(10,()-> System.out.println("hello world"));

        //Supplier<T>
        Father father = get(() -> { return new Father();});
        System.out.println(father);




        //Consumer<T>  Predicate<Father>
        //toConsume(Father::new,f1 -> f1.getAge() ==1 ,f1 -> f1.setAge(1));


        //Function<T,U>
        System.out.println(getFatherName((father1) -> father1.getName(),new Father("wmh",2)));

    }



    public static void repeat(int n,Runnable runnable){
        for (int i = 0;i<n;i++){
            runnable.run();
        }
    }

    public static Father get(Supplier<Father> supplier){
        return supplier.get();
    }

    public static void toConsume(Supplier<Father> supplier, Predicate<Father> predicate, Consumer<Father> consumer){
        if(predicate.test(supplier.get())){
            consumer.accept(supplier.get());
        }

    }

    public static String getFatherName(Function<Father,String> function,Father father){
        return function.apply(father);
    }
}
