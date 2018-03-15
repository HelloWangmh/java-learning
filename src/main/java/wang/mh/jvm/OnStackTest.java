package wang.mh.jvm;

import org.omg.SendingContext.RunTime;

/**
 * 栈上分配
 *   线程私有的数据可以在栈上分配,而不是分配在堆上
 *    需要进行逃逸分析
 */
public class OnStackTest {

    static class User {

        private int age;

        private String name;
    }

    static void alloc() {
        User user = new User();
        user.age = 10;
        user.name = "wmh";
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100000000; i++) {
            alloc();
        }
    }
}
