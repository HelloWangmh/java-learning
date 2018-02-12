package wang.mh.base.collection;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Assert;

import java.util.*;

/**
 * Created by 明辉 on 2017/6/14.
 *
 */
public class HashMapDemo {

    public static void main(String[] args) {
        testGetOrDefault();



    }

    /**
     * WeakHashMap   当key不存在别的引用的时候,垃圾回收器会自动删除map中对应的条目
     */
    private static void   testWeakMap(){
        Map<Person,String> weakMap = new WeakHashMap<>();
        Person p = new Person("wmh");

        weakMap.put(p,"wmh");
        p = null;
        //通知垃圾回收器
        System.gc();

        try {
            //等待垃圾回收器去工作
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("size:"+weakMap.size());

    }

    private static void testEntrySet(){

        HashMap<String, Integer> map = Maps.newHashMap();
        map.put("wang",1);
        map.put("ming",2);
        map.put("hui",3);

        Set<Map.Entry<String, Integer>> entries = map.entrySet();

        Iterator<Map.Entry<String, Integer>> iterator = entries.iterator();
        Map.Entry<String, Integer> next = iterator.next();
        entries.remove(next);
        //UnsupportedOperationException    可以删除,但是不能添加元素
        entries.add(next);
        System.out.println("size:"+map.size());

    }




    /**
     * getOrDefault   computeIfAbsent
     */
    private static void testGetOrDefault(){

        //getOrDefault
        HashMap<String, String> map = Maps.newHashMap();
        System.out.println("init map size : " + map.size());
        System.out.println(map.getOrDefault("data", "100"));
        System.out.println("after getOrDefault  map size : " + map.size());

        //computeIfAbsent
        map.clear();
        System.out.println(map.computeIfAbsent("data", k -> k + "100"));
        System.out.println("after computeIfAbsent  map size : " + map.size());

    }


    private static void testSetAndMap(){
        Map<Person,Integer> map = new HashMap<Person, Integer>();
        Person p1 = new Person("11");
        Person p2 = new Person("11");
        Person p3 = new Person("33");
        System.out.println(p1.hashCode()+"==="+p2.hashCode()+"=="+p3.hashCode());
        System.out.println(p1.equals(p2));
        System.out.println(p1.equals(p3));
        map.put(p1,1);
        map.put(p2,2);
        map.put(p3,3);

        for (Person person : map.keySet()) {
            System.out.println(map.get(person));
        }


        HashSet<Person> set = new HashSet<Person>();
        set.add(p1);
        set.add(p2);
        set.add(p3);
        for (Person person : set) {
            System.out.println(person.getName());
        }

    }
}
class Person{

    public Person(String name){
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return name.length();
    }

    @Override
    public boolean equals(Object obj) {
        Person p = (Person)obj;
        return p.getName().equals(this.name);
    }
}
