package io.github.kimmking.netty.server;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * @description TODO
 * author: liquan
 * date: 2020/10/28 21:11
 * version: 1.0
 */
public class HttpClientTest {

    public static void main(String[] args) {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().get().url("http://localhost:8801").build();

        try {
            Response response = client.newCall(request).execute();
            String result = response.body().string();
            System.out.println("请求结果：" + result);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
