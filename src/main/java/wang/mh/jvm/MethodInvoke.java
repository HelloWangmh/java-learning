package wang.mh.jvm;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static java.lang.invoke.MethodHandles.lookup;

/**
 * 方法调用
 * 5条调用字节码指令 :
 *      1. invokestatic : 调用静态方法, 2. invokespecial : 调用实例构造器,私有方法,父类方法
 *      3. invokevirtual : 调用虚方法, 4. invokeinterface : 调用接口方法
 *      5. invokedynamic : 运行时动态解析出调用点限定符所引用的方法,与上面四个不同的是,分派逻辑由程序员决定
 *    解析 :
 *          1,2在解析阶段就可以确定唯一版本,在加载时候就会把符号引用转化为直接引用
 *          其他除了final方法外称为虚方法
 *    分派 :
 *          静态分派(编译阶段) :  比如重载时候,根据静态类型来选择调用的方法
 *          动态分派(执行阶段) : 比如重写时候,根据方法调用者实际类型选择方法
 *
 */
public class MethodInvoke {
    public static void main(String[] args) {
       You you = new You();
       you.say();
    }


    static void methodHandle() throws Throwable {
        Object obj = System.currentTimeMillis() % 2 == 0 ? new MethodInvoke() : System.out;
        System.out.println(obj.getClass());
        MethodType methodType = MethodType.methodType(void.class, String.class);
        MethodHandle methodHandle = lookup()
                .findVirtual(obj.getClass(), "println", methodType)
                .bindTo(obj);
        methodHandle.invokeExact("hello, world");
    }

    void println(String data) {
        System.out.println(data);
    }

    /**
     * 静态分派
     */
    static void staticDispatch() {
        //演示静态分派
        MethodInvoke mi = new MethodInvoke();
        Human man = new Man(); //Human是静态类型,Man是实际类型
        Human woman = new Woman();
        mi.sayHello(man);
        mi.sayHello(woman);
    }

    void sayHello(Human human) {
        System.out.println("hello, human");
    }

    void sayHello(Man man) {
        System.out.println("hello, man");
    }
}

abstract class Human {

}

class Man extends Human {

}

class Woman extends  Human {

}

class GrandFather {
    void say() {
        System.out.println("I am grandFather");
    }
}

class Father extends GrandFather{
    @Override
    void say() {
        System.out.println("I am Father");
    }
}

class You extends Father {
    @Override
    void say() {
        //调用GrandFather的say()  TODO ??
        MethodType mt = MethodType.methodType(void.class);
        try {
            MethodHandle mh = lookup().findSpecial(GrandFather.class, "say", mt, getClass());
            mh.invoke(this);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}

