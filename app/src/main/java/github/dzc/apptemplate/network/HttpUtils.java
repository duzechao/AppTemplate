package github.dzc.apptemplate.network;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by dzc on 15/11/8.
 */
public class HttpUtils {
    private static OkHttpClient client = null;
    public static void initHttpClient(){
        if(client==null){
            client = new OkHttpClient();
        }
    }

    public static void initHttpClientIntercept(){
        client = new OkHttpClient();
        client.networkInterceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                request = request.newBuilder().url(request.urlString()+"s?ie=utf-8&f=8&rsv_bp=0&rsv_idx=1&tn=98996590_hao_pg&wd=he&rsv_pq=f4953a2f000ff24d&rsv_t=04cbC9bfdepmyhGqXX6mksqeAGbXZUDDVjdafZ4rpa6%2BPtgbIZHlYi2IqlmTxdO8OxBTvKr3&rsv_enter=1&rsv_sug3=2&rsv_sug1=1&rsv_sug2=0&inputT=680&rsv_sug4=680").build();
                return chain.proceed(request);
            }
        });
        client.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                request = request.newBuilder().url(request.urlString()+"s?ie=utf-8&f=8&rsv_bp=0&rsv_idx=1&tn=98996590_hao_pg&wd=he&rsv_pq=f4953a2f000ff24d&rsv_t=04cbC9bfdepmyhGqXX6mksqeAGbXZUDDVjdafZ4rpa6%2BPtgbIZHlYi2IqlmTxdO8OxBTvKr3&rsv_enter=1&rsv_sug3=2&rsv_sug1=1&rsv_sug2=0&inputT=680&rsv_sug4=680").build();
                return chain.proceed(request);

            }
        });
    }
    private HttpUtils() {
    }


    public static void get(String url,Callback callback){
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(callback);
    }


}
