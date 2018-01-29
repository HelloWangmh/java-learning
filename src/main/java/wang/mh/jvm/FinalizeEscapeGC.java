package wang.mh.jvm;

import java.util.concurrent.TimeUnit;

/**
 *  可达性分析算法宣告对象死亡需要经过两次标记过程
 *  1.当对象被发现没有与GC Roots关联后,会筛选是否有必要调用对象的finalize方法,
 *    若有必要那么放入F-Queued队列,等待jvm的低优先级的Finalizer线程执行
 *        筛选条件 : 是否覆盖finalize()且未被jvm执行过finalize()
 *  2.若对象在finalize()中重新有了引用,那么会从即将回收的对象集合中移除.
 */
public class FinalizeEscapeGC {

    private static FinalizeEscapeGC SAVE_HOOK = null;

    public static void main(String[] args) throws Throwable {
        SAVE_HOOK = new  FinalizeEscapeGC();
        SAVE_HOOK = null;
        System.gc();
        //等待低优先级的Finalizer线程执行
        TimeUnit.SECONDS.sleep(1);

        if (SAVE_HOOK == null) {
            System.out.println("I am dead");
        } else {
            System.out.println("I am alive");
            SAVE_HOOK = null;
            System.gc();
            TimeUnit.SECONDS.sleep(1);

            if (SAVE_HOOK == null) {
                System.out.println("I am dead");
            } else {
                System.out.println("I am alive");
            }
        }






    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        SAVE_HOOK = this;
        System.out.println("I has been saved;");
    }
}
