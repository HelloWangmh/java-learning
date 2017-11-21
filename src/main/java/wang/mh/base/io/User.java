package wang.mh.base.io;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class User implements Serializable,Cloneable {


    private static final long serialVersionUID = 4128517001742899878L;
    private String name;
    private Integer age;
    private double money;
    private Son son;
    private transient String car;

    public User(String name, Integer age, double money) {
        this.name = name;
        this.age = age;
        this.money = money;
    }

    public static final int NAME_SIZE = 20;
    //num of byte
    public static final int TOTAL_SIZE = 2 * NAME_SIZE + 4 + 8;

}
class Son extends User{

    public Son(String name, Integer age, double money) {
        super(name, age, money);
    }

}
