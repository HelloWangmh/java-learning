package wang.mh.base.annotation;

import lombok.Data;

import java.lang.annotation.*;
import java.lang.reflect.Field;

/**
 * 注解demo
 *
 * @Target 描述注解的修饰的对象范围
 * @Retention 定义了该Annotation被保留的时间长短
 * @Documented 用于描述其它类型的annotation应该被作为被标注的程序成员的公共API，因此可以被例如javadoc此类的工具文档化
 * @Inherited  阐述了某个被标注的类型是被继承的。
 */
public class AnnotationDemo {

    public static void main(String[] args) {
        //Person person = new Person();
        //Field[] fields = person.getClass().getDeclaredFields();
        Son son = new Son();

        Field[] fields = son.getClass().getFields();  //获取自身和父类的所有public实例
        System.out.println(fields.length);
        for (Field field : fields) {
            if(field.isAnnotationPresent(PersonColor.class)){
                PersonColor personColor = field.getAnnotation(PersonColor.class);
                System.out.println("color:"+personColor.value());
            }else if(field.isAnnotationPresent(PersonName.class)){
                PersonName personName = field.getAnnotation(PersonName.class);
                System.out.println("name:"+personName.firstName()+personName.secondName());

            }
        }

        //注意变量注解与类注解
        System.out.println("Son上的PersonColor注解:"+Son.class.isAnnotationPresent(PersonColor.class));
        System.out.println("Son上的PersonName注解:"+Son.class.isAnnotationPresent(PersonName.class));


        //类注解
        System.out.println("Son上的PersonSex注解:"+Son.class.isAnnotationPresent(PersonSex.class));  //false
        System.out.println("Person上的PersonSex注解:"+Person.class.isAnnotationPresent(PersonSex.class));//true

        //PersonFisrtName有@Inherited修饰  所以会被继承下去
        System.out.println("PersonFisrtName上的PersonSex注解:"+Son.class.isAnnotationPresent(PersonFisrtName.class));//true
        System.out.println("PersonFisrtName上的PersonSex注解:"+Person.class.isAnnotationPresent(PersonFisrtName.class));//true

    }
}


@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@interface PersonColor{
    String value();
}


@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@interface PersonName{
    String firstName() default "wang";

    String secondName();
}

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@interface PersonSex{
    String sex() default "";
}

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@interface PersonFisrtName{
    String firstName() default "";
}





@Data
@PersonSex
@PersonFisrtName
class Person{

    @PersonName(secondName = "mh")
    public String name;

    @PersonColor("yellow")
    public String color;

}
@Data
class Son extends Person{

}
