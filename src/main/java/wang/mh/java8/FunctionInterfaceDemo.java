package wang.mh.java8;

import wang.mh.common.Father;

import java.text.SimpleDateFormat;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Created by 明辉 on 2017/9/9.
 * 测试常用函数式接口
 */
public class FunctionInterfaceDemo {
    public static void main(String[] args) {
        check((i) -> i >0);

    }

    /**
     * test 类型推断
     */
    static void check(IntPred predicate) {
        System.out.println(predicate.test(10));
    }



    /**
     * ThreadLocal java8 新增的工厂类方法
     */
    static void testThreadLocal() {
        ThreadLocal<Father> threadLocal = ThreadLocal.withInitial(() -> new Father("wmh", 23));
        System.out.println(threadLocal.get().getName());
    }


    static void repeat(int n, Runnable runnable) {
        for (int i = 0; i < n; i++) {
            runnable.run();
        }
    }

    static Father get(Supplier<Father> supplier) {
        return supplier.get();
    }

    static void toConsume(Supplier<Father> supplier, Predicate<Father> predicate, Consumer<Father> consumer) {
        if (predicate.test(supplier.get())) {
            consumer.accept(supplier.get());
        }

    }

    static String getFatherName(Function<Father, String> function, Father father) {
        return function.apply(father);
    }
}

@FunctionalInterface
interface IntPred {
    boolean test(Integer value);
}
