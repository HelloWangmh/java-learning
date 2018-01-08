package wang.mh.thread.demo.thread_pool;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 简单的HttpServer
 */
public class SimpleHttpServer {
    private static MineThreadPool<HttpRequestHandler> pool = new MineThreadPool<>(1);

    private static String base_path = "/Users/wmh/mine/IdeaProjects/mine/java-learning/src/main/resources/";

    private static int port = 8080;


    public static void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            Socket socket = null;
            while ((socket = serverSocket.accept()) != null) {
                pool.execute(new HttpRequestHandler(socket));
            }
        }
    }



    /**
     * 处理socket
     */
    static class HttpRequestHandler implements Runnable {

        private Socket socket;

        private BufferedReader reader;

        private PrintWriter out;

        private FileInputStream in;

        public HttpRequestHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                System.out.println(reader.readLine());
                //读取图片为字节
                in = new FileInputStream(base_path + "/timg.jpeg");
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                int i = 0;
                while ((i = in.read()) != -1){
                    bos.write(i);
                }
                byte[] array = bos.toByteArray();
                out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                out.println("HTTP/1.1 200 OK");
                out.println("Content-Type: image/jpeg");
                out.println("Content-Length: " + array.length);
                out.println("");
                out.flush();
                socket.getOutputStream().write(array, 0, array.length);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                close(reader, out, socket);
            }
        }

        public void close(Closeable... closeables){
            for (Closeable closeable : closeables) {
                try {
                    closeable.close();
                } catch (IOException e) {

                }
            }
        }
    }
}
