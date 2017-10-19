package wang.mh.base.generic;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by 明辉 on 2017/5/14.
 * 泛型方法
 */
public class Demo02 {

    public static void main(String[] args) {
        //指定类型
        String abc = ArrayAlg.getMiddle("", "123", "abc");
        //不指定   同类型
        String abc1 = ArrayAlg.getMiddle("", "123", "abc");
        //不指定   不同类型  寻找共同的父类或接口
        Serializable middle = ArrayAlg.getMiddle(1,  new ArrayList(), 5);
        System.out.println(middle);

    }
}

/**
 * 泛型方法
 */
class ArrayAlg{

    public static <T> T getMiddle(T...a){
        return a[a.length/2];
    }
}
