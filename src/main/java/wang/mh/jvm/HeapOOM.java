package wang.mh.jvm;

import com.google.common.collect.Lists;
import sun.misc.Unsafe;
import wang.mh.base.io.User;

import java.lang.reflect.Field;
import java.util.List;

/**
 * JVM数据区域:
 *      线程隔离的数据区 : 虚拟机栈,本地方法栈,程序计数器
 *      线程共享的数据区 : 方法区,堆
 *  直接内存 : NIO通过Native函数库直接分配堆外内存,然后通过一个存储在Java堆中的DirectByteBuffer对象作为
 *     这一块内存的引用进行操作
 */
public class HeapOOM {

    private static int i= 0;

    public static void main(String[] args) {
        javaMethodAreaOOM();
    }

    /**
     * -Xmx20M -XX:MaxDirectMemorySize=10M
     * 本机直接内存溢出
     */
    public static void directMemoryOOM() throws IllegalAccessException {
        Field[] fields = Unsafe.class.getDeclaredFields();
        Field field = fields[0];
        field.setAccessible(true);
        Unsafe unsafe = (Unsafe) field.get(null);
        while (true) {
            unsafe.allocateMemory(1024 * 1024);
        }

    }

    /**
     * -XX:MetaspaceSize=5M -XX:MaxMetaspaceSize=7M
     * java8 已经移除了永久代,类的元数据放在native堆中,称作Metaspace
     */
    public static void javaMethodAreaOOM(){
        /*while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(User.class);
            enhancer.setUseCache(false);
            enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) ->
                    methodProxy.invokeSuper(o, objects));
            enhancer.create();
        }*/
    }

    /**
     * JDK1.7开始  intern()方法只会在常量池中记录首次出现的实例引用
     */
    public static void testConstantPool(){
        String s1 = new StringBuilder("王").append("mh").toString();
        System.out.println(s1 == s1.intern());  //true
        String s2 = new StringBuilder("ja").append("va").toString();
        String s22 = s2.intern();
        System.out.println(s2 == s22); //false
        String s3 = "java";
        System.out.println(s3 == s22); //true
    }

    /**
     * -XX:PermSize=10M -XX:MaxPermSize=10M   JDK1.8下这两个参数无效了
     * JDK1.6下会产生OOM异常,因为字符串常量池放在永久代.
     * JDK1.7开始已经移出
     */
    public static void constantPoolOOM() {
        List<String> list = Lists.newArrayList();
        int i = 0;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }

    /**
     * 栈溢出 -Xss160k
     */
    public  void VMStackSOF(){
        i++;
        VMStackSOF();
    }

    /**
     * -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
     * java 堆溢出
     */
    public static void heapOOM(){
        List<User> list = Lists.newArrayList();
        while (true) {
            list.add(new User());
        }
    }
}
