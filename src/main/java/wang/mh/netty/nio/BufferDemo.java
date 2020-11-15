package wang.mh.netty.nio;

import io.netty.buffer.ByteBuf;

import java.nio.IntBuffer;

public class BufferDemo {
    public static void main(String[] args) {
        IntBuffer buffer = IntBuffer.allocate(20);

        //write
        buffer.put(0);
        buffer.put(1);
        buffer.put(2);
        buffer.put(3);

        //write -> read
        buffer.flip();
        System.out.println(buffer.get());
        System.out.println(buffer.get());

        //read -> write
        buffer.clear();
    }
}
