package wang.mh.base.io;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.*;

/**
 * 复制有浅复制 和 深复制
 */
public class CloneDemo {

    public static void main(String[] args) throws CloneNotSupportedException {
        deepClone();
    }


    /**
     *需要 实现Serializable接口
     */
    public static void deepClone(){
        CloneUser user = new CloneUser();
        String name  = "wmh";
        user.setName(name);
        CloneSon son = new CloneSon();
        user.setSon(son);

        CloneUser cloneUser = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            try (ObjectOutputStream out = new ObjectOutputStream(bos)){
                out.writeObject(user);
            }
            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            try (ObjectInputStream in = new ObjectInputStream(bis)){
                 cloneUser = (CloneUser) in.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(user);
        System.out.println(cloneUser);
        System.out.println(user == cloneUser); //false
        System.out.println(user.getSon() == cloneUser.getSon());//false

        son.setAge(100);
        System.out.println(cloneUser.getSon());//新对象的son的age不会改变

    }

    /**
     * 浅复制   需要实现Cloneable
     * 复制出来一个新的对象,但是对象的属性还是以前对象的
     * @throws CloneNotSupportedException
     */
    public static void shallowClone() throws CloneNotSupportedException {
        CloneUser user = new CloneUser();
        String name  = "wmh";
        user.setName(name);
        CloneSon son = new CloneSon();
        son.setAge(10);
        user.setSon(son);
        CloneUser cloneUser = (CloneUser) user.clone();
        System.out.println(user);
        System.out.println(cloneUser);
        System.out.println(user == cloneUser); //false
        System.out.println(user.getSon() == cloneUser.getSon());//true

        son.setAge(100);
        System.out.println(cloneUser.getSon());//新对象的son的age也会改变

    }
}
@Getter
@Setter
@ToString
class CloneUser implements Cloneable,Serializable{
    private String name;
    private CloneSon son;
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
@Getter
@Setter
@ToString
class CloneSon implements Serializable{
    private Integer age;
}
