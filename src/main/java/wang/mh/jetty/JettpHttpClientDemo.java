package wang.mh.jetty;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Response;
import org.eclipse.jetty.client.api.Result;
import org.eclipse.jetty.http.HttpMethod;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class JettpHttpClientDemo {

    public static void main(String[] args) throws Exception {
        testAsync();
    }


    public static void testAsync()  throws Exception{
        JettyHttpClient.httpClient.newRequest("http://localhost:8080/testAsync")
                .send((Result result) -> {
                    Response response = result.getResponse();
                    result.getResponse().getListeners(Response.ResponseListener.class).get(0);
                });
        System.out.println("请求完成");
    }

    public static void testTimeOut() throws InterruptedException, ExecutionException, TimeoutException {
        String result = JettyHttpClient.httpClient.newRequest("http://localhost:8080/testAsync")
                .timeout(3, TimeUnit.SECONDS).send().getContentAsString();
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
