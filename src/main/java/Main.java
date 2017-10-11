import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import wang.mh.tool.CacheKey;
import wang.mh.tool.Constants;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by 明辉 on 2017/7/30.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("/Users/wmh/mine/IdeaProjects/test");
        System.out.println(file.exists());
        System.out.println(file.listFiles());
        File[] files = file.listFiles();
        for (File file1 : files) {
            System.out.println(file1.exists());
        }
    }



}
class  User{
    public int age;


}
