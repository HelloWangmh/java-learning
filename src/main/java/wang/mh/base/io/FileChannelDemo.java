package wang.mh.base.io;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.CRC32;

/**
 *通过FileChannel将文件映射到内存中,进行处理,提高效率
 * 多个application 操作一个文件的时候,需要同lock()和release() 加锁,释放锁
 */
@Slf4j
public class FileChannelDemo {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("/Users/wmh/Downloads/test.pdf");
        long s1 = System.currentTimeMillis();
        long d1 = checkSumInputStream(path);
        long s2 = System.currentTimeMillis();
        log.info("inputStream耗时: {} ms, sum : {}", (s2 - s1), d1);

        long d2 = checkSumBuffer(path);
        long s3 = System.currentTimeMillis();
        log.info("bufferInput耗时: {} ms, sum : {}", (s3 - s2), d2);

        long d3 = checkSumRandomFile(path);
        long s4 = System.currentTimeMillis();
        log.info("randomFile耗时: {} ms, sum : {}", (s4 - s3), d3);

        long d4 = checkSumMappingFiel(path);
        long s5 = System.currentTimeMillis();
        log.info("fileMapping耗时: {} ms, sum : {}", (s5 - s4), d4);

    }

    public static long checkSumInputStream(Path path) throws IOException {
        CRC32 crc = new CRC32();
        try (InputStream in = Files.newInputStream(path)){
            int b;
            while ((b = in.read()) != -1){
                crc.update(b);
            }
            return crc.getValue();
        }
    }


    public static long checkSumBuffer(Path path) throws IOException {
        CRC32 crc = new CRC32();
        try (InputStream in = new BufferedInputStream(Files.newInputStream(path))){
            int b;
            while ((b = in.read()) != -1){
                crc.update(b);
            }
            return crc.getValue();
        }
    }

    public static long checkSumRandomFile(Path path) throws IOException {
        CRC32 crc = new CRC32();

        try (RandomAccessFile r = new RandomAccessFile(path.toFile(),"r")){
            int b;
            long length = r.length();
            for (int i = 0; i < length; i++) {
                r.seek(i);
                b = r.read();
                crc.update(b);
            }
            return crc.getValue();
        }
    }

    public static long checkSumMappingFiel(Path path) throws IOException {
        CRC32 crc = new CRC32();
        try (FileChannel channel = FileChannel.open(path)){
            int b;
            long length =  channel.size();
            MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, length);
            for (int i = 0; i < length; i++) {
                b = buffer.get(i);
                crc.update(b);
            }
            return crc.getValue();
        }
    }


}
