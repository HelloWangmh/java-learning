package wang.mh.jetty;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.client.api.Response;
import org.eclipse.jetty.client.api.Result;
import org.eclipse.jetty.client.util.BufferingResponseListener;
import org.eclipse.jetty.client.util.FutureResponseListener;
import org.eclipse.jetty.http.HttpMethod;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Slf4j
public class JettpHttpClientDemo {

    public static void main(String[] args) throws Exception {
        testAsyncWithBufferingResponseListener();
    }

    /**
     *通过BufferingResponseListener
     */
    public static void testAsyncWithBufferingResponseListener()  throws Exception{
        Request req = JettyHttpClient.httpClient.newRequest("http://localhost:8080/testAsync");

        req.send(new BufferingResponseListener() {
            @Override
            public void onComplete(Result result) {
                if(result.isSucceeded()){
                    String resultData = new String(getContent());
                    log.info("result:{},耗时:{}毫秒",resultData);
                }
            }
        });
    }




    /**
     * 通过FutureResponseListener监听响应      程序可以继续执行
     * @throws Exception
     */
    public static void testAsyncWithListener()  throws Exception{
        Request req = JettyHttpClient.httpClient.newRequest("http://localhost:8080/testAsync");

        FutureResponseListener listener = new FutureResponseListener(req);
        req.send(listener);
        System.out.println("异步请求完成");

        ContentResponse getResutl = JettyHttpClient.httpClient.GET("http://www.eclipse.org/jetty/documentation/current/http-client.html");
        System.out.println(getResutl.getContentAsString());

        long start = System.currentTimeMillis();
        String resuslt = listener.get().getContentAsString();
       log.info("result:{},耗时:{}毫秒",resuslt,(System.currentTimeMillis()-start));
    }

    /**
     * 第一个请求耗时很久才返回  那么第二个请求会等待
     * @throws Exception
     */
    public static void testAsync()  throws Exception{
        Request req = JettyHttpClient.httpClient.newRequest("http://localhost:8080/testAsync");
        long start = System.currentTimeMillis();
        String resuslt = req.send().getContentAsString();
        log.info("result:{},耗时:{}毫秒",resuslt,(System.currentTimeMillis()-start));
    }

    public static void testTimeOut() throws InterruptedException, ExecutionException, TimeoutException {
        String result = JettyHttpClient.httpClient.newRequest("http://localhost:8080/testAsync")
                .timeout(2,TimeUnit.SECONDS).send().getContentAsString();
        System.out.println(result);
    }

    public static void  testGet() {
       try {
           HttpClient httpClient = JettyHttpClient.httpClient;
           ContentResponse response = httpClient.newRequest("http://localhost:8080/redirectBaidu")
                   .method(HttpMethod.GET)
                   .agent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36")
                   .send();
           System.out.println(response.getContentAsString());
       }catch (Exception e){

       }
    }

    public static  void testPost() throws Exception {
        String result = JettyHttpClient.httpClient.POST("http://localhost:8080/testPost")
                .param("userName", "wmh")
                .param("password", "123")
                .header("myHeader", "test")
                .send().getContentAsString();
        System.out.println("======:"+result);
    }
}

class MyCompleteListener implements Response.CompleteListener {

    public MyCompleteListener(Result result) {

    }

    @Override
    public void onComplete(Result result) {
        System.out.println("test123");
        System.out.println(result);
    }
}
