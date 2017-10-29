package wang.mh.base.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.*;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * url:url地址为了防止一些中间软件解析错误,会将url特殊处理  可通过URLEncoder.encode()得到结果
 * 通过这种方式发生request header user-agent为Java/1.8.0_144
 */
public class UrlDemo {

    public static void main(String[] args) throws Exception {
        testUrlConnection();

    }


    public static void testPostData() throws Exception{
        URL url = new URL("http://localhost:8080/login");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        PrintWriter writer = new PrintWriter(connection.getOutputStream());
        writer.print("username" + "=" + URLEncoder.encode("wmh", "UTF-8") + "&");
        writer.print("password" + "=" + URLEncoder.encode("123", "UTF-8"));
        //关闭流
        writer.close();
        connection.connect();
        InputStream in = connection.getInputStream();
        Scanner sc = new Scanner(in, "UTF-8");
        while (sc.hasNext()){
            System.out.println(sc.nextLine());
        }
    }

    public static void testUrlConnection() throws IOException {
        URL url = new URL("http://localhost:8080/redirect");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("User-Agent","wmh");
        //禁止重定向
        connection.setInstanceFollowRedirects(false);
        connection.connect();
        Map<String, List<String>> map = connection.getHeaderFields();
        map.forEach((k, v) -> {
            System.out.println(k + "     " + v);
        });
        //重定向地址
        System.out.println("Location : " + connection.getHeaderField("Location"));
        InputStream in = connection.getInputStream();
        Scanner sc = new Scanner(in, "UTF-8");
        while (sc.hasNext()){
            System.out.println(sc.nextLine());
        }
    }

    public static void testUrl() throws IOException {
        URL url = new URL("http://www.baidu.com");
        InputStream in = url.openStream();
        Scanner sc = new Scanner(in, "UTF-8");
        while (sc.hasNext()){
            System.out.println(sc.nextLine());
        }
    }
}
