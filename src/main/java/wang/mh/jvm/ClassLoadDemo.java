package wang.mh.jvm;

import java.io.IOException;
import java.io.InputStream;

/**
 * 类加载 : 加载,验证,准备,解析,初始化,使用,卸载
 *      1.加载 : 首先获取二进制字节流,将这个字节流所代表的静态存储结构转化为
 *              方法去的运行时数据结构,然后内存中生成Class对象,作为方法区这个类
 *              的各种数据的入口
 *      2.验证 : 格式验证(验证class文件,后三个是在方法区验证),元数据验证,字节码验证,符合引用验证
 *      3.准备阶段 : 为类变量在方法区分配内存并设置类初始值
 *      4.解析阶段 : jvm将常量池内的符号引用转化为直接饮用的过程
 *
 * 何时初始化 ?
 *      1.new, 调用静态方法,字段
 *      2.反射
 *      3.子类初始化时,若父类未初始化,父类需要初始化
 *          接口不满足这个条件,只有用到父接口常量才会初始化父接口
 *      4.jvm启动时,用户指定要执行的主类
 *      5.动态语言支持 1.7 ??
 *      以上称为对类的主动引用
 * 类加载器 :
 *      启动类加载器  -> 扩展类加载器 -> 应用程序加载器 -> 自定义加载器
 * 栈帧是jvm方法调用和执行的数据结构,每一个方法从开始到到结束都对应一个栈帧入栈到出栈的过程
 * 栈帧包含 :
 *      1.局部变量表 : 存放方法参数和局部变量
 *      2.操作数栈 : 方法执行的时候,会有各种字节码指令写入
 *      3.动态连接
 *      4.
 */
public class ClassLoadDemo {

    public static void main(String[] args) throws Exception {
        localVariableTable();
    }

    /**
     *-verbose:gc
     * 局部变量表里的slot可以复用
     */
    static void localVariableTable() {
        {
            byte[] arr = new byte[64 * 1024 * 1024];
        }
        int a = 0;//加上这句后,arr的内存会被回收,因为arr的slot被a占用了
        System.gc();
    }


    /**
     *  同一个类两个类加载器加载,那么是两个不同的类
     * @throws Exception
     */
    static void classLoader() throws Exception {
        ClassLoader myLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                InputStream in = getClass().getResourceAsStream(fileName);
                if (in == null) {
                    return super.loadClass(name);
                }
                try {
                    byte[] arr = new byte[in.available()];
                    in.read(arr);
                    return defineClass(name, arr, 0, arr.length);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return super.loadClass(name);
            }
        };

        Object obj = myLoader.loadClass("wang.mh.jvm.ClassLoadDemo").newInstance();
        System.out.println(obj.getClass());
        System.out.println(obj instanceof ClassLoadDemo);  //false
    }

    /**
     * 多线程初始化同一个类,jvm会加锁来保证只有一个线程去执行<client>()方法
     */
    static void mutiThreadLoad() {
        new Thread(() -> {
            System.out.println("thread-1 start ");
            DeadLoopClass loopClass = new DeadLoopClass();
            System.out.println("thread-1 end ");
        }, "thread-1").start();

        new Thread(() -> {
            System.out.println("thread-2 start ");
            DeadLoopClass loopClass = new DeadLoopClass();
            System.out.println("thread-2 end ");
        }, "thread-2").start();
    }

    /**
     * 类初始化过程中jvm要确保子类的<client>() 方法执行前,父类的<client>()先执行完
     */
    static void loadOrder() {
        System.out.println(SubClass.subValue);
    }


    /**
     * 通过子类引用父类字段,不会导致子类初始化
     * 可通过-XX:+TraceClassLoading 看到子类加载了
     */
    static void subUseSuperValue() {
        System.out.println(SubClass.value);
    }

    /**
     * 通过数组来引用类,不会导致类的初始化
     */
    static void arrUseClass() {
        SuperClass[] arr = new SuperClass[10];
    }

    /**
     * 引用常量,不会导致类的初始化,
     * 因为经过编译阶段的常量传播优化,已经将该常量存储在了
     * ClassLoadDemo类的常量池中了
     */
    static void useConstant() {
        System.out.println(SubClass.HELLO);
    }
}

class SuperClass {

    static {
        System.out.println("SuperClass init");
        value = 1;
    }

    public static int value = 123;
    public static final String HELLO = "Hello";
}

class SubClass extends SuperClass {
    static {
        System.out.println("SubClass init");
    }

    public static int subValue = value;
}

class DeadLoopClass {
    static {
       if (true) {
           System.out.println(Thread.currentThread().getName() + "DeadLoopClass init");
           while (true) {
           }
       }
    }
}


