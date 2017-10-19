package wang.mh.base.generic;

import com.google.common.collect.Lists;
import wang.mh.common.Father;
import wang.mh.common.Son;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.IntFunction;

public class SomeLimit {

    public static void main(String[] args) {
        /**
         * error   类型参数不能用基本类型  原因是类型擦除
         */
        //Pair<int> pair = new Pair<int>();

        /**
         *error   运行时类型查询只适用原始类型,因为虚拟机中的对象总有一个特定的非泛型类型
         */

        //Pair<String> stringPair = new Pair<>();
        //if(stringPair instanceof Pair<String>)
        //Pair<Employee> employeePair = new Pair<>();
        //System.out.println(stringPair.getClass() == employeePair.getClass());//true  都是Pair


        /**
         * warn   不能创建参数化类型的数组
         */
        //Pair<String>[] table = new Pair<String>[10];
//
//        ArrayList<Pair<String>> list = Lists.newArrayList();
//        addAll(list,new Pair<String>(),new Pair<String>());
//
//        Pair<String>[] array = array(new Pair<String>(), new Pair<String>());
//        Object[] obj = array;
//        //不会发生异常 因为数组存储只检查擦除的类型  但是在处理的时候有可能有异常
//        obj[0] = new Pair<Employee>();


        /**
         * 不能实例化类型变量     不能使用new T(...),new T[...],T.class
         * 因为这样会擦书为Object   无意义
         * 以下两种写法分别是通过java8的函数式接口和反射
         */
        //Pair.makePair(String::new);
        //Pair.makePair(java.lang.String.class);

        /**
         * 不能构造泛型数组
         */

        //wrong
        //minmax(new String());
        //true
        IntFunction<String> i = Integer::toString;

        /**
         * 泛型上下文中类型变量无效
         */


        /**
         * 不能抛出或捕获泛型类的实例   但是可以在异常规范中使用类型变量
         */



        SomeLimit.throwAs(new Throwable());

    }


    public static <T extends Throwable> void throwAs(Throwable t) throws T {
        throw (T)t;
    }



    public static <T extends Comparable> T[] minmaxRight(IntFunction<T[]> comstr,T...a){
        T[] ts = comstr.apply(2);
        ts[0] = a[0];
        return ts;
    }

    /**
     * 编译可以通过  但是运行时候会报错ClassCastException
     * @param ts
     * @param <T>
     * @return
     */
    public static <T extends Comparable> T[] minmax(T...ts){
        return (T[]) new Object[2];
    }


    public static <T> void addAll(Collection<T> collection,T...ts){
        for(T t : ts){
            collection.add(t);
        }
    }

    public static <T> T[] array(T...ts){
        return ts;
    }


}

class Employee{

}


class Singleton<T>{
//    private static T t ;  //error
//
//
//
//    public static T getSingleton(){  //error
//        //逻辑
//
//        return t;
//    }
}
