package wang.mh.base.network;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * Server的实现
 */
public class ServerSocketDemo {

    public static void main(String[] args) throws IOException {
        testInterruptServer();
    }


    public static void testInterruptServer() throws IOException {
        try (ServerSocket server = new ServerSocket(8080)) {
            //accept()阻塞
            try (Socket socket = server.accept()) {
                System.out.println("A client is coming");
                InputStream in = socket.getInputStream();
                OutputStream out = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(new OutputStreamWriter(out, "UTF-8"), true);
                for (int i = 0; i < 10000; i++) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    writer.println(i);
                }
            }
        }
    }


    /**
     * 可以有多个client同时访问server
     * @throws IOException
     */
    public static void testServerForMutiClient() throws IOException {
        try (ServerSocket server = new ServerSocket(8080)){
            int num = 1;
            while (true){
                Socket socket = server.accept();
                System.out.println("The " + num + " client is coming ");
                new Thread(new ThreadedEchoHandler(socket)).start();
                num ++;
            }
        }
    }



    /**
     * 简单的Server
     * @throws IOException
     */
    public static void testServet() throws IOException {
        try (ServerSocket server = new ServerSocket(8080)) {
            //accept()阻塞
            try (Socket socket = server.accept()) {
                System.out.println("A client is coming");
                InputStream in = socket.getInputStream();
                OutputStream out = socket.getOutputStream();
                try (Scanner sc = new Scanner(in, "UTF-8")) {
                    PrintWriter writer = new PrintWriter(new OutputStreamWriter(out, "UTF-8"), true);
                    writer.println("please enter exit to quit!");
                    //Thread.currentThread().interrupt();
                    boolean isEnd = false;
                    while (!isEnd && sc.hasNextLine()) {
                        String line = sc.nextLine();
                        //TODO  为什么不能用write()?
                        writer.println("echo : " + line);
                        if (line.equals("exit")) isEnd = true;
                    }
                }
            }
        }
    }
}

class ThreadedEchoHandler implements Runnable{

    private Socket socket;

    public ThreadedEchoHandler(Socket inComing) {
        this.socket = inComing;
    }

    @Override
    public void run() {
        try (InputStream in = socket.getInputStream();){
            OutputStream out = socket.getOutputStream();
            try (Scanner sc = new Scanner(in, "UTF-8")) {
                PrintWriter writer = new PrintWriter(new OutputStreamWriter(out, "UTF-8"), true);
                writer.println("please enter exit to quit!");
                boolean isEnd = false;
                while (!isEnd && sc.hasNextLine()) {
                    String line = sc.nextLine();
                    //TODO  为什么不能用write()?
                    writer.println("echo : " + line);
                    if (line.equals("exit")) isEnd = true;
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }


    }
}
