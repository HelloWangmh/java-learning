package wang.mh.base.grammer;

import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;

/**
 * Created by 明辉 on 2017/7/5.
 */
public class CloneDemo {
    public static void main(String[] args) {
        Person p = new Person("嘿嘿嘿",10);
        Person p1 = SerializationUtils.clone( p);
        System.out.println(p==p1);

    }

}
class Person implements Serializable{
    private String name;
    private Integer age;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
