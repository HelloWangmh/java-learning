package wang.mh;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class JvmLearnDemo {

    private static List<byte[]> list = new ArrayList<>();
    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        visualVm();
    }

    /**
     * -Xmx20M -XX:+PrintGCDetails
     */
    private static void heapOOM() {
        for (int i = 0; i < 50; i++) {
            list.add(new byte[1024 * 1024 * 5]);
        }
    }

    /**
     * -Xss200K
     */
    private static void stackOverFlow() {
        try {
            recursion();
        } catch (Throwable t) {
            System.out.println("count : " + count);
            t.printStackTrace();
        }
    }


    private static void recursion() {
        count++;
        recursion();
    }

    private static void visualVm() throws InterruptedException {
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            persons.add(new Person());
            TimeUnit.SECONDS.sleep(2);
        }
    }


    static class Person {
        private int[] content = new int[1024 * 1024];
    }
}
