
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import wang.mh.common.Son;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;


/**
 * Created by 明辉 on 2017/7/30.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        String path = "C:\\Users\\wmh\\Desktop\\data.txt";
        BufferedReader reader = Files.newBufferedReader(Paths.get(path));
        String s = reader.lines().collect(Collectors.joining());
        System.out.println(s.length());
        ByteArrayOutputStream os = new ByteArrayOutputStream(s.getBytes().length);
        try (GZIPOutputStream gos = new GZIPOutputStream(os)) {
            gos.write(s.getBytes());
        }

        System.out.println(os.toByteArray().length);
        System.out.println(s.getBytes().length);

        final int BUFFER_SIZE = 128;
        StringBuilder sb = new StringBuilder();
        ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());
        try (GZIPInputStream gis = new GZIPInputStream(is, BUFFER_SIZE)) {
            byte[] data = new byte[BUFFER_SIZE];
            int bytesRead;
            while ((bytesRead = gis.read(data)) != -1) {
                sb.append(new String(data, 0, bytesRead));
            }
        }
        System.out.println(sb.length());
    }

    private static String getName(String name){
        return name;
    }
}

interface A {
    default void a(){
        System.out.println("A");
    }
}

interface B {
    default void a(){
        System.out.println("B");
    }

    static void print(){
        System.out.println("hello,world");
    }
}
class Test implements A,B {

    @Override
    public void a() {

    }

}
class SonTest extends Test {


}


