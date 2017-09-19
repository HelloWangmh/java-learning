package wang.mh.jetty;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.util.ssl.SslContextFactory;

public class JettyHttpClient {

    public static  HttpClient httpClient;

    static {
        //支持https
        SslContextFactory sslContextFactory = new SslContextFactory();
        httpClient = new HttpClient(sslContextFactory);
        try {
            httpClient.start();
        } catch (Exception e) {
            throw new RuntimeException("httpClient初始化失败");
        }
    }
}
