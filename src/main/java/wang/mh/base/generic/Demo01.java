package wang.mh.base.generic;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

/**
 * Created by 明辉 on 2017/5/14.
 */
public class Demo01 {
    public static void main(String[] args) {
        Person<String> p = new Person(new ArrayList(),1);
        //编译可以通过 但是执行下面语句报错
        //java.lang.ClassCastException: java.util.ArrayList cannot be cast to java.lang.String
        System.out.println(p.getT1());
        System.out.println(p.getT2());

    }

}

@Setter
@Getter
class Person<T>{
    private T t1;
    private T t2;
    public Person() {

    }

    public Person(T t1, T t2){
        this.t1 = t1;
        this.t2 = t2;
    }

}

