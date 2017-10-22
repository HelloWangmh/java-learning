package wang.mh.base.io;

import java.io.*;
import java.util.Arrays;

/**
 * user  必须实现Serializable接口才能通过ObjectInputStream/ObjectOutputStream  读取,写入
 * 对象序列化写入文件后,每个对象引用都会在文件有一个序列号,在第一次写入的时候写入数据,以后不会再次写入
 * 序列号的时候每个class有一个单独的serialVersionUID,若不确定为一个固定值,那么当这个class改变时候,
 * serialVersionUID也会变化,重新读取变化之前内容时候会报错
 * serialVersionUID固定会后,增加或减少类的属性都不会报错
 */
public class ObjectInOutDemo {

    private static String path = IODemo.BASE_PATH + "io/object.txt";

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //testWrite();
        computeSize();
        testRead();
    }

    public static void testClone(){
        User user  = new User("wang mh", 8, 8888.0);


    }

    public static void testWrite() throws IOException {
        User wmh = new User("wang mh", 8, 8888.0);
        User hmw = new User("mh wang", 8, 8888.0);

        Son son = new Son("son", 5, 888.0);
        son.setCar("bmw");
        wmh.setSon(son);
        hmw.setSon(son);
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path))){
            out.writeObject(wmh);
            out.writeObject(hmw);
        }

    }

    public static void testRead() throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(path))){
            User wmh = (User) in.readObject();
            User hmw = (User) in.readObject();
            System.out.println(wmh.getSon() == hmw.getSon());  //true
            System.out.println(wmh);
            System.out.println(hmw);
        }

    }


    public static void computeSize(){
        File file = new File(path);
        System.out.println("file length : " + file.length());
    }
}
