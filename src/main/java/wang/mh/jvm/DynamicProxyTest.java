package wang.mh.jvm;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 */
public class DynamicProxyTest {
    public static void main(String[] args) {
        IHello hello = (IHello) new DynamicProxy().bind(new Hello());
        hello.say();

    }

    interface IHello {
        void say();
    }

    static class Hello implements IHello {
        @Override
        public void say() {
            System.out.println("hello,world");
        }
    }

    static class DynamicProxy implements InvocationHandler {
        Object originObj = null;
        Object bind (Object originObj) {
            this.originObj = originObj;
            return Proxy.newProxyInstance(originObj.getClass().getClassLoader(),
                    originObj.getClass().getInterfaces(), this);
        }
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("method name : " + method.getName());
            System.out.println("hi");
            return method.invoke(originObj, args);
        }
    }
}
