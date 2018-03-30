package wang.mh.jvm;

/**
 * -XX:+PrintCompilation  : 打印出被编译成本地代码的方法
 *
 */
public class RunTimeOptimize {
    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            calcSUm();
        }
    }

    public static int doubleValue(int i) {
        for (int j = 0; j < 100000; j++) {

        }
        return i * 2;
    }

    public static long calcSUm() {
        long sum = 0;
        for (int i = 0; i < 100; i++) {
            sum += doubleValue(i);
        }
        return sum;
    }
}
