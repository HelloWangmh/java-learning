package wang.mh.base.network;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.Scanner;

public class SocketDemo {

    public static void main(String[] args) throws Exception {
        testSocket();

    }

    /**
     * Interruptible Socket
     * @throws Exception
     */
    public static void testSocketChannel() throws Exception{
        SocketChannel channel = SocketChannel.open(new InetSocketAddress("localhost", 8080));
        InputStream in = Channels.newInputStream(channel);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        String data = null;
        while ((data = reader.readLine()) != null){
            System.out.println(data);
            if (data.equals("50")) Thread.currentThread().interrupt();
        }
    }


    public static void testInetAddress() throws UnknownHostException {
        InetAddress[] addresses = InetAddress.getAllByName("baidu.com");
        System.out.println("length : " + addresses.length);
        Arrays.stream(addresses).forEach(System.out::println);

        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost.getHostAddress());
    }

    public static void testTimeOut() throws IOException {
        try (Socket socket = new Socket("localhost", 8080)){
            socket.setSoTimeout(2000);
            Scanner scanner = new Scanner(socket.getInputStream(), "UTF-8");
            while (scanner.hasNextLine()){
                System.out.println(scanner.nextLine());
            }
        }
    }


    public static void testSocket() throws IOException {
        try (Socket socket = new Socket("localhost", 8080)){
            Scanner scanner = new Scanner(socket.getInputStream(), "UTF-8");
            while (scanner.hasNextLine()){
                System.out.println(scanner.nextLine());
            }
        }
    }
}
