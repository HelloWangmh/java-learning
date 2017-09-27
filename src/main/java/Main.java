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

        ArrayList<Integer> list = Lists.newArrayList();
        List<Integer> collect = list.stream().collect(Collectors.toList());
        System.out.println(collect.size());
    }


}
class  User{
    public int age;


}
