package wang.mh.thread.core_learn;

import lombok.AllArgsConstructor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class FutureDemo {

    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            long start = System.currentTimeMillis();
            System.out.println("enter base directory:");
            String directory = in.nextLine();
            System.out.println("enter keyword:");
            String keyWord = in.nextLine();

            MatchCounter countter = new MatchCounter(new File(directory), keyWord);
            FutureTask<Integer> task = new FutureTask<Integer>(countter);
            new Thread(task).start();
            int count = task.get()==null?0:task.get();
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

    public MatchCounter(File dict, String word) {
        this.dict = dict;
        this.word = word;
    }

    @Override
    public Integer call() throws Exception {
        int count = 0;
        List<Future<Integer>> futures = new ArrayList<>();
        File[] files = dict.listFiles();
        for (File file : files) {
            if(file.isDirectory()){
                MatchCounter counter = new MatchCounter(file,word);
                FutureTask<Integer> task = new FutureTask<Integer>(counter);
                futures.add(task);
                new Thread(task).start();

            }else {
                if(search(file)) count++;
            }
        }

        for (Future<Integer> future : futures) {
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
