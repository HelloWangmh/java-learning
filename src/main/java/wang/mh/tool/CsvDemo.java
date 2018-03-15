package wang.mh.tool;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class CsvDemo {

    public static void main(String[] args) throws IOException {
        String path = "C:\\Users\\wmh\\Desktop\\data\\JTP\\download\\GenericMaster_ALL_20171208.zip";
        ZipFile zipFile = new ZipFile(path);
        ZipInputStream zipIn = new ZipInputStream(Files.newInputStream(Paths.get(path)), StandardCharsets.UTF_8);
        ZipEntry entry = zipIn.getNextEntry();
        System.out.println("name : " + entry.getName());
        System.out.println("time : " + entry.getTime());
        InputStream in = zipFile.getInputStream(entry);

    }
}
