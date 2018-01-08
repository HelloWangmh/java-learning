package wang.mh.thread.artOfConcurrency;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class MultiThread {


    public static void main(String[] args) {
        showJavaMultiThread();
    }

    public static void showJavaMultiThread(){
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println("id : " + threadInfo.getThreadId() + ", name : " + threadInfo.getThreadName());
        }

    }
}
