package wang.mh.netty.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelDemo {
    private static  String path = "E:\\test.log";

    public static void main(String[] args) throws Exception{
        write();
    }

    static void read() throws Exception{
        FileInputStream fis = new FileInputStream(path);
        FileChannel channel = fis.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int length = channel.read(buffer);
    }

    static void write() throws Exception {
        FileOutputStream fos = new FileOutputStream(path);
        FileChannel channel = fos.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put("buffer write".getBytes());
        buffer.flip();
        channel.write(buffer);
        channel.close();
    }
}
