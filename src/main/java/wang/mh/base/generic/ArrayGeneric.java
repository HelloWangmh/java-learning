package wang.mh.base.generic;

import java.util.ArrayList;
import java.util.List;

public class ArrayGeneric {
    public static void main(String[] args) {
        Student[] arr = new Student [1];
        arr[0] = new Student();
        loopArr(arr); //编译,运行正常

        List<Student> list = new ArrayList<>();
        list.add(new Student());
        arrCovariant();
//        loopList(list);//编译错误  集合不是协变得,可以通过通配符解决
    }

    /**
     *  Java 中数组是类型兼容的,称为协变数组类型.
     *  但是数组明确了可以存入的类型,类型不同会throw ArrayStoreException
     */
    private static void arrCovariant() {
        List<Student> list = new ArrayList<>();
//        list.add(new Person());  //编译错误
        list.add(new Student());
        Person[] arr = new Student [2];
        arr[0] = new Student();
        arr[1] = new Person(); //可编译通过,但是运行时有  ArrayStoreException
        arr[2] = new Teacher(); //可编译通过,但是运行时有  ArrayStoreException
    }

    private static void loopArr(Person[] arr) {
        for (Person person : arr) {
            person.eat();
        }
    }

    private static void loopList(List<Person> list) {
        for (Person person : list) {
            person.eat();
        }
    }
}

class Person {

    public void eat() {
        System.out.println(this.getClass() + " --> eat");
    }
}

class Student extends Person {

}

class Teacher extends Person {

}


