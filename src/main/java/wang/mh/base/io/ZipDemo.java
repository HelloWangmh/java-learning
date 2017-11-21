package wang.mh.base.io;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * bug
 */
public class ZipDemo {

    public static void main(String[] args) throws IOException, InterruptedException {
        write();
    }

    public static void write() throws IOException, InterruptedException {
        FileOutputStream out = new FileOutputStream(IODemo.BASE_PATH + "io/test.zip");
        ZipOutputStream zipOut = new ZipOutputStream(out);
        ZipEntry entry = new ZipEntry(IODemo.BASE_PATH + "temp.properties");
        zipOut.putNextEntry(entry);
        zipOut.closeEntry();
    }

    public static void read() throws IOException {
        File file = new File(IODemo.BASE_PATH + "io/test.zip");
        ZipFile zipFile = new ZipFile(file);
        FileInputStream in = new FileInputStream(file);
        ZipInputStream zipIn = new ZipInputStream(in);
        ZipEntry entry = zipIn.getNextEntry();
        System.out.println("name : " + entry.getName());
        System.out.println("time : " + entry.getTime());

        InputStream readIn = zipFile.getInputStream(entry);
        byte[] arr = new byte[1024];
        while (readIn.read(arr,0,1024) != -1){
            String s = new String(arr);
            System.out.println(s);
        }
        zipIn.close();
    }
}
