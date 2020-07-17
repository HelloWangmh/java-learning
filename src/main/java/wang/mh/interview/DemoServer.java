package wang.mh.interview;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DemoServer extends Thread {
    private ServerSocket serverSocket;

    public int getPort() {
        return  serverSocket.getLocalPort();
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(0);
            ExecutorService executorService = Executors.newFixedThreadPool(5);
            int i = 1;
            while (true) {
                Socket socket = serverSocket.accept();
                RequestHandler requestHandler = new RequestHandler(socket, i++);
                executorService.execute(requestHandler);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args) throws Exception {
        DemoServer server = new DemoServer();
        server.start();

        for (int i = 0; i < 8; i++) {
           new Thread(() -> {
               try (Socket client = new Socket(InetAddress.getLocalHost(), server.getPort())) {
                   BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                   bufferedReader.lines().forEach(s -> System.out.println(s));
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }).start();
        }
    }
}
// 简化实现，不做读取，直接发送字符串
class RequestHandler extends Thread {

    private Socket socket;
    private int count;

    RequestHandler(Socket socket, int count) {
        this.socket = socket;
        this.count = count;
    }

    @Override
    public void run() {
        try (PrintWriter out = new PrintWriter(socket.getOutputStream())) {
            out.println("Hello world!   count :  "+ count +" thread Id : " + currentThread().getId());
            TimeUnit.SECONDS.sleep((5 - count > 0 ? 5 - count : 0));//强行制造不同线程处理时间的差异
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
