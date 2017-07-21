package wang.mh.base.generic;

import java.util.Collections;

/**
 * Created by 明辉 on 2017/5/14.
 */
public class Demo03 {

    public static void main(String[] args) {
        getP(new Pair<Son>());


        Pair<Son> son = new Pair<Son>();
        Pair<? extends Parent> p = son;
        //p.setFirst(new Parent());//  编译报错
        Parent first = p.getFirst();
    }

    public static void getP(Pair<? extends Parent> p){
        System.out.println(1);

    }
}

class Pair<T>{
    T first;
    T second;

    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public T getSecond() {
        return second;
    }

    public void setSecond(T second) {
        this.second = second;
    }
}

class Parent{

}
class Son extends Parent{

}
