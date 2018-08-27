import com.google.common.collect.Lists;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by 明辉 on 2017/7/30.
 * 14206
 * 1506  9685
 */
public class Main {

    private static List<File> files = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
    }

    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i != j && nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                }
            }
        }
        return result;
    }

    private static void recur(File file) {
        if (file.getName().contains("MACOSX"))
            return;
       if (file.isDirectory()) {
           File[] files = file.listFiles();
           for (File f : files) {
               recur(f);
           }
       } else {
           if (file.getName().contains(".mp3")) {
               files.add(file);
           }
       }
    }


    private static void read(String path) throws Exception {
        byte[] arr = new byte[4];
        InputStream in = Files.newInputStream(Paths.get(path));
        in.read(arr, 0, 4);
        System.out.println(Arrays.toString(arr));
    }




}

class p {

}
class q {

}


