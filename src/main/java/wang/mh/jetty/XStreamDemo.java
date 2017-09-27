package wang.mh.jetty;

import com.google.common.collect.Lists;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class XStreamDemo {


    private static final XStream xStream = new XStream();

    public static void main(String[] args) {

        Child c1 = new Child("1");
        Child c2 = new Child("2");
        ArrayList<Child> list = Lists.newArrayList(c1, c2);

        //解析这个类的注解
        xStream.processAnnotations(new Class[]{PersonXs.class});
        PersonXs personXs = new PersonXs("wmh", 15,100000l);
        personXs.setList(list);
        String s = xStream.toXML(personXs);
        System.out.println(s);

        PersonXs personXs1 = (PersonXs) xStream.fromXML(s);
        System.out.println(personXs1);
    }


}

@XStreamAlias("person")     //配置别名等价xStream.alias("person",PersonXs.class);
@Data
class PersonXs {

    @XStreamAlias("myName")   //等价xStream.aliasField("myName",PersonXs.class,"name");
    private String name;

    @XStreamAlias("myAge")
    @XStreamAsAttribute()
    private Integer age;        // 作为头信息, <person myAge="15">   等价xStream.aliasAttribute(PersonXs.class,"age","myAge");

    @XStreamOmitField           //忽略这个属性
    private Long money;

    @XStreamImplicit    //隐式集合   忽略这个<child>标签外层的 <list></list>
    private List<Child> list;

    public PersonXs(String name, Integer age,Long money) {
        this.name = name;
        this.age = age;
        this.money = money;
    }
}

@XStreamAlias("child")
@Data
class Child {

    private String sex;

    public Child(String sex) {
        this.sex = sex;
    }
}
