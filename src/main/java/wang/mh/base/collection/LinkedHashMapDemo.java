package wang.mh.base.collection;

import java.util.LinkedHashMap;

/**
 * Created by 明辉 on 2017/6/14.
 *各个item通过链表连接,用来记住插入的顺序,如果构造的时候accessOrder = true,那么也会根据get()方法排序,
 * 若get(a),那么a会拍到链表的最后
 * 通过重写removeEldestEntry方法,可以在满足一定条件返回true/false 表示每添加一个元素,是否删除最老的元素
 */
public class LinkedHashMapDemo {

    public static void main(String[] args) {
        LinkedHashMap<Integer,Integer> map = new LinkedHashMap(16,0.75F,true);
        map.put(1,1);
        map.put(2,2);
        map.put(3,3);
        map.put(4,4);
        map.get(1);
        map.put(5,5);
        //2,3,4,1,5
        map.forEach((k,v) ->{
            System.out.println(k+"==="+v);
        });
    }
}
