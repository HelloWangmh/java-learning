package wang.mh.base.generic;

import java.util.ArrayList;

/**
 * Created by 明辉 on 2017/5/14.
 */
public class Demo01 {
    public static void main(String[] args) {
        Person<String> p = new Person(new ArrayList(),"123");


        //编译可以通过 但是执行下面语句报错
        //java.lang.ClassCastException: java.util.ArrayList cannot be cast to java.lang.String
        System.out.println(p.getT1());

        System.out.println(p.getT2());



    }

}

class Person<T>{
    private T t1;
    private T t2;


    public Person() {
    }

    public Person(T t1, T t2){
        this.t1 = t1;
        this.t2 = t2;
    }

    public T getT1() {
        return t1;
    }

    public void setT1(T t1) {
        this.t1 = t1;
    }

    public T getT2() {
        return t2;
    }

    public void setT2(T t2) {
        this.t2 = t2;
    }
}

