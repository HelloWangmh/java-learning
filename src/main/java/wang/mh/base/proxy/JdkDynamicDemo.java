package wang.mh.base.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkDynamicDemo {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        MyInvocationHandler handler = new MyInvocationHandler(userService);
        UserService userProxy = (UserService)Proxy.newProxyInstance(userService.getClass().getClassLoader(),
                userService.getClass().getInterfaces(),
                handler);
        userProxy.eat("meat");
        System.out.println(userProxy.getClass());
        System.out.println(userProxy);
    }
}

class MyInvocationHandler implements InvocationHandler {

    private Object target;

    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    /**
     *
     * @param proxy 代理对象
     * @param method 执行的方法
     * @param args 方法参数
     * @return method返回数据
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        if (method.getName().equals("eat")) {
            System.out.println("before");
            // 程序执行
            result = method.invoke(target, args);  //这里是target  若填proxy对象那么会不断递归调用,知道StackOverFlow
            // 程序执行后加入逻辑，MethodAfterAdviceInterceptor
            System.out.println("after");
        } else {
            result = method.invoke(target, args);
        }
        return result;
    }
}


interface UserService {

    void eat(String food);

    String getName();
}

class UserServiceImpl implements UserService {

    @Override
    public void eat(String food) {
        System.out.println("eat " + food);
    }

    @Override
    public String getName() {
        return "user";
    }
}
