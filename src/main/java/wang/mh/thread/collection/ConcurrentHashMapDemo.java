package wang.mh.thread.collection;

import com.google.common.collect.Maps;

import java.util.HashMap;

/**
 * Created by 明辉 on 2017/7/29.
 */
public class ConcurrentHashMapDemo {

    public static void main(String[] args) {
        HashMap<Integer, String> map = Maps.newHashMap();
        map.put(1,"");
    }
}
