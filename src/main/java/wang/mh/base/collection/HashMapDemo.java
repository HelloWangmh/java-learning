package wang.mh.base.collection;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by 明辉 on 2017/6/14.
 *
 */
public class HashMapDemo {

    public static void main(String[] args) {
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
