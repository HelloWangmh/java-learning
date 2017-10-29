package wang.mh.base.io;

import java.io.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class IODemo {
    public static byte b;
    public static final String BASE_PATH = "src/main/resources/";
    public static void main(String[] args) throws IOException {
        testPrintWriter();

    }

    public static void testPrintWriter() throws IOException {
        Path path = Paths.get(BASE_PATH + "num.properties");

        try (PrintWriter writer = new PrintWriter(Files.newOutputStream(path),true)) {
            writer.write("hello");
            writer.println("world");
            System.out.println(1);
        }
    }
}
