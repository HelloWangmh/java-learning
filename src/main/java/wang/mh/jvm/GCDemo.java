package wang.mh.jvm;

/**
 * Jvm通过可达性分析算法来判断对象是否存货
 *
 * 方法区回收 :
 *  回收效率低,一般用来回收废弃常量和废弃的类,可通过-Xnoclassgc控制是否开启
 *  在大量使用反射,动态代理,CGLib,动态生成Jsp,这种频繁自定义ClassLoader的场景都需要虚拟机具备类卸载的功能
 *
 *  垃圾收集算法 :
 *      1.标记清除算法,但是效率不高,内存碎片太多
 *      2.复制算法 : 代价是部分内存不能使用,jvm的新生代便是采用这个算法,将内存
 *          分为一个较大的Eden区和两个较小的Survivor
 *      3.标记整理算法 : 老年代由于存活率高,便采用了这种算法
 *  垃圾收集器 :
 *      1.Serial收集器 : 新生代单线程收集器
 *      2.Serial Old收集器 : Serial收集器的老年代版本
 *      3.ParNew收集器 : 多线程版本的Serial收集器
 *      4.Parallel Scavenge收集器 : 多线程的新生代收集器,与其他收集器不同 :
 *          1. 该收集器尽可能满足吞吐量的要求,而不是每次GC时间尽可能短
 *          2. 提供GC的自适应调节策略
 *      5. Parallel Old收集器 : Parallel Scavenge收集器的老年代
 *      6.CMS收集器 : 以获取最短停顿时间为目标的老年代收集器,基于标记-清除算法实现,在垃圾收集过程中可以和用户线程
 *          并发执行
 *      7.G1收集器 :
 *          1.并发与并行   2.分代收集  3.空间整合   4.可预测的停顿
 *
 */
public class GCDemo {

    private static final int ONE_MB = 1024 * 1024;

    public static void main(String[] args) {
        testTenuringThreshold();
    }


    public static void testTenuringThreshold() {
        byte[] arr1 = new byte[ONE_MB / 4];
        byte[] arr2 = new byte[3 * ONE_MB];
        byte[] arr3 = new byte[3 * ONE_MB];
        arr3 = null;
        arr3 = new byte[3 * ONE_MB];
        System.out.println(1);
    }


    /**
     *-XX:+PrintGCDetails   -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=3
     */
    public static void testAllocation() {
        byte[] arr1 = new byte[2 * ONE_MB];
        byte[] arr2 = new byte[1 * ONE_MB];
        byte[] arr3 = new byte[1 * ONE_MB];
        byte[] arr4 = new byte[6 * ONE_MB];
    }



    /**
     * -XX:+PrintGCDetails
     * 测试当前jvm是否通过引用计数算法来判断对象是否存活
     * 引用计数算法实现简单,效率高,但是不能解决对象循环引用的问题
     */
    public static void testReferenceCount() {
        ReferenceCount r1 = new ReferenceCount();
        ReferenceCount r2 = new ReferenceCount();
        r1.obj = r2;
        r2.obj = r1;
        r1 = null;
        r2 = null;
        System.gc();
    }
}

class ReferenceCount {

    private byte[] arr = new byte[1024 * 1024];

    public Object obj;
}
