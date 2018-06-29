package wang.mh.base.proxy;

import org.mockito.cglib.proxy.Enhancer;
import org.mockito.cglib.proxy.MethodInterceptor;
import org.mockito.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;


public class CglibDemo {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Person.class);
        enhancer.setCallback(new AroundAdvice());
        Person person = (Person)enhancer.create();
        person.eat("meat");
        System.out.println(person.getClass());
    }
}

class Person {
    public void eat(String food) {
        System.out.println("eat " + food);
    }

    public void run() {
        System.out.println("run");
    }
}

class AroundAdvice implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("Before--");
        methodProxy.invokeSuper(o, objects);
        System.out.println("After--");
        return null;
    }
}
