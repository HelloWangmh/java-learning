package wang.mh.netty.syncDemo;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class IOServer {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8000);
        new Thread(() -> {
            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    System.out.println("新连接来了");
                    new Thread(() -> {
                        try {
                            int len;
                            byte[] data = new byte[1024];
                            InputStream inputStream = socket.getInputStream();
                            while ((len = inputStream.read(data)) != -1) {
                                System.out.println(new String(data, 0, len) + "--Thread Id :->" + Thread.currentThread().getId()
                                + "--HashCode : ->" + System.identityHashCode(socket));
                            }
                        } catch (Exception e) {

                        }
                    }).start();
                } catch (Exception e) {

                }
            }
        }).start();
    }
}
