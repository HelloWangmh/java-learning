package wang.mh.thread.pool;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

/**线程池方式实现搜索文件内容
 * 相对于普通线程方式的8秒   wang.mh.thread.core_learn.FutureDemo,
 * 线程池需要14秒
 */
public class FutureThreadPool {

    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            long start = System.currentTimeMillis();
            System.out.println("enter base directory:");
            String directory = in.nextLine();
            System.out.println("enter keyword:");
            String keyWord = in.nextLine();

            ExecutorService service = Executors.newCachedThreadPool();
            MatchCounter countter = new MatchCounter(new File(directory), keyWord,service);
            Future<Integer> result = service.submit(countter);


            int count = result.get()==null?0:result.get();
            long end = System.currentTimeMillis();
            System.out.println("total file Number:" + count);
            System.out.println("total time:" + (end - start));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

class MatchCounter implements Callable<Integer>{

    private File dict;

    private String word;

    private ExecutorService service;

    public MatchCounter(File dict, String word,ExecutorService service) {
        this.dict = dict;
        this.word = word;
        this.service = service;
    }

    @Override
    public Integer call() throws Exception {
        int count = 0;
        File[] files = dict.listFiles();
        List<Future<Integer>> result = new ArrayList<>();
        for (File file : files) {
            if(file.isDirectory()){
                MatchCounter counter = new MatchCounter(file,word,service);
                Future<Integer> future = service.submit(counter);
                result.add(future);
            }else {
                if(search(file)) count++;
            }
        }

        for (Future<Integer> future : result) {
            int tmp = future.get() ==null?0:future.get();
            //System.out.printf("num:%d%n",tmp);
            count += tmp;

        }
        return count;
    }


    public boolean search(File file) {
        try(Scanner in = new Scanner(file,"UTF-8")){
            boolean found = false;
            while (!found && in.hasNextLine()){
                if(in.nextLine().contains(word))
                    found = true;
            }
            return found;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
