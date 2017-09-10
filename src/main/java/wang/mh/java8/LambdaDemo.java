package wang.mh.java8;

import com.google.common.collect.Lists;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 明辉 on 2017/6/26.
 */
public class LambdaDemo {

    public static void main(String[] args) {
        Arrays.asList(1,2,3).forEach(i -> System.out.println(i));


        //复杂的可以用{}
        Arrays.asList(1,2,3).forEach((Integer e) -> {
            System.out.println(++e);
        });

        Integer max = 100;
        //max被隐含的转化为final
        Arrays.asList(1,2,3).forEach(i -> System.out.println(i+max));

        //根据返回值排序
        List<Integer> list = Arrays.asList(3, 2, 1);
        list.sort((e1,e2) -> e1.compareTo(e2));
        list.forEach(integer -> System.out.println(integer));

        new Thread(()-> System.out.println(1));

        //函数式接口
        TestFunctionalInterface testFunctionalInterface =new TestFunctionalInterface(() -> System.out.println("FunctionalInterface"));
        testFunctionalInterface.run();
        System.exit(0);
    }
}

class TestFunctionalInterface{
    MyFunctionalInterface functionalInterface;
    public TestFunctionalInterface(MyFunctionalInterface functionalInterface){
        this.functionalInterface = functionalInterface;

    }

    public void run(){
        functionalInterface.method();
    }
}

/**
 * @FunctionalInterface 该注解表明这个接口是一个函数式接口
 * 只可以有一个普通方法    默认方法与静态方法并不影响函数式接口的契约
 */
@FunctionalInterface
 interface MyFunctionalInterface{
    void method();
    //void method2();
}

class LambdaTimerDemo{
    public static void main(String[] args) {
        Timer t = new Timer(1000, event -> System.out.println(event.getClass()));
        t.start();
        JOptionPane.showMessageDialog(null,"Quite");
    }
}
class LambdaPredicateDemo{
    public static void main(String[] args) {
        ArrayList<Integer> list = Lists.newArrayList();

        list.add(1);
        list.add(2);
        list.add(3);

        list.removeIf( i -> i ==1);

        list.forEach(i -> System.out.println(i));
    }
}
