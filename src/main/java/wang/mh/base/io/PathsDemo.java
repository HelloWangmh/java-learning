package wang.mh.base.io;

import com.google.common.collect.Lists;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class PathsDemo {

    private static Path path = Paths.get("src/main/resources/num.properties");

    public static void main(String[] args) throws IOException {

        List<String> origin = Lists.newArrayList();
        for (int i = 0; i < 100000000; i++) {
            origin.add(String.valueOf(i));
        }
        long s1 = System.currentTimeMillis();
        Iterator<String> iterator = origin.iterator();
        List<String> dest1 = Lists.newArrayList(iterator);
        long s2 = System.currentTimeMillis();
        System.out.println("cost time : " + (s2 - s1));
        List<String> dist2 = Lists.newArrayListWithCapacity(100000000);
        Iterator<String> iterator1 = origin.iterator();
        while (iterator1.hasNext()) {
            dist2.add(iterator1.next());
        }
        long s3 = System.currentTimeMillis();
        System.out.println("cost time : " + (s3 - s2));

       /* Path path = Paths.get("C:\\Users\\wmh\\Desktop\\data\\JTP\\download\\product\\RoomStock_ALL_20180112.zip");
        ZipInputStream zipIn = new ZipInputStream(Files.newInputStream(path));
        ZipEntry entry = zipIn.getNextEntry();
        BufferedReader br = new BufferedReader(new InputStreamReader(zipIn));
        System.out.println(br.lines().count());*/
    }

    /**
     * travelsal a direction
     */
    public static void testTravelsalDir() throws IOException {
        try (Stream<Path> paths = Files.list(Paths.get("src"))) {
            //not enter subdirectories
            paths.forEach(System.out::println);
        }

        try (Stream<Path> paths = Files.walk(Paths.get("src"))) {
            //enter subdirectories
            //paths.forEach(System.out::println);
        }

        try (DirectoryStream<Path> paths = Files.newDirectoryStream(Paths.get("src"), "*.DS_Store")) {
            //not enter subdirectories
            for (Path path1 : paths) {
                System.out.println(path1);
            }
        }

        //enter subdirectories  通过FileVisitor接口的一些方法做一些事情
        Files.walkFileTree(Paths.get("/"), new SimpleFileVisitor<Path>() {
            public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes
                    attrs)
                    throws IOException {
                System.out.println(path);
                return FileVisitResult.CONTINUE;
            }

            public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
                return FileVisitResult.CONTINUE;
            }

            public FileVisitResult visitFileFailed(Path path, IOException exc) throws
                    IOException {
                throw new IOException(path.toString());
            }
        });

    }


    /**
     * Path
     *
     * @throws IOException
     */
    public static void testPath() throws IOException {
        Path p = Paths.get("/home", "fred", "myprog.properties");
        Path parent = p.getParent(); // the path /home/fred
        Path file = p.getFileName(); // the path myprog.properties
        Path root = p.getRoot(); // the path /
        System.out.println(parent);
        System.out.println(file);
        System.out.println(root);

        File file1 = p.toFile();
        System.out.println(file1.isDirectory());

        //temporary file
        //Path temp = Files.createTempFile(Paths.get("src/main/resources/"), "hello", ".txt");
        //System.out.println(temp);
    }

    /**
     * Files
     *
     * @throws IOException
     */
    public static void testFiles() throws IOException {

        //字节方式写入,读取
        Files.write(path, "hello,world".getBytes(StandardCharsets.UTF_8));
        byte[] bytes = Files.readAllBytes(path);
        System.out.println(new String(bytes, StandardCharsets.UTF_8));

        //以行读取
        List<String> list = Files.readAllLines(path);
        System.out.println(list);


        //通过流的方式
        try (BufferedReader read = Files.newBufferedReader(path);) {
            System.out.println(read.readLine());

        }

    }
}
