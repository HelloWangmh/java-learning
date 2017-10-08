package wang.mh.base.queue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class QueueSearchFile {


    private static final int File_Queue_Size = 10;
    private static final int Search_Threads = 100;
    private static final File DUMMY = new File("");
    private static final BlockingQueue<File> queue = new ArrayBlockingQueue<File>(File_Queue_Size);

    private static AtomicInteger count = new AtomicInteger();

    private static volatile Boolean isDone = false;


    public static void main(String[] args) {
        try(Scanner in = new Scanner(System.in)){
            long start = System.currentTimeMillis();
            System.out.println("enter base directory:");
            String directory = in.nextLine();
            System.out.println("enter keyword:");
            String keyWord = in.nextLine();

            //将文件放入queue
            new Thread(() -> {
                try {
                    emumerate(new File(directory));
                    queue.put(DUMMY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }).start();

            //读取文件 搜索关键词
            for (int i = 0; i < Search_Threads; i++) {
                new Thread(() -> {
                    boolean done = false;
                    while (!isDone){
                        try {
                            File file = queue.take();
                            if(file == DUMMY){
                                //读到最后一个了
                                queue.put(DUMMY);
                               // System.out.println("total file Number:" + count);
                                isDone = true;
                            }else search(file,keyWord);
                        } catch (InterruptedException | FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }


                }).start();
            }

            while (Thread.activeCount()>1){

            }

            long end = System.currentTimeMillis();
            System.out.println("total file Number:" + count);
            System.out.println("total time:" + (end - start));
        }
    }

    /**
     * 将文件添加到队列
     * @param dict
     * @throws InterruptedException
     */
    private static void emumerate(File dict) throws InterruptedException {
        File[] files = dict.listFiles();
        for (File file : files) {
            if(file.isDirectory()){
                emumerate(file);
            }else {
                count.incrementAndGet();
                queue.put(file);
            }
        }
    }

    /**
     * 读取文件并搜索关键词
     * @param file
     * @param keyWord
     * @throws FileNotFoundException
     */
    private static void search(File file,String keyWord) throws FileNotFoundException {
        try(Scanner in = new Scanner(file,"UTF-8")) {
            int lineNumber = 0;
            while (in.hasNextLine()){
                lineNumber++;
                String line = in.nextLine();
                if(line.contains(keyWord)){
                    System.out.printf("%s:%d:%s%n",file.getPath(),lineNumber,line);
                }
            }
        }
    }
}
