package com.lq.netty.server.outbound.httpclient;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Set;

/**
 * @description TODO
 * author: liquan
 * date: 2020/11/01 22:09
 * version: 1.0
 */
public class HttpOutboundHandler {

    private String backendUrl;

    public HttpOutboundHandler(String backendUrl) {
        this.backendUrl = backendUrl;
    }

    public void handle(final FullHttpRequest fullHttpRequest, final ChannelHandlerContext ctx) {
        this.fetchGet(fullHttpRequest, ctx, backendUrl);
    }

    private void fetchGet(final FullHttpRequest inbound, final ChannelHandlerContext ctx, final String url) {

        // 获取 header
        String nioKey = "";
        HttpHeaders httpHeaders = inbound.headers();

        Set<String> headers = httpHeaders.names();
        for (String str : headers) {
            if (str.equals("nio")) {
                nioKey = str;
                break;
            }
        }

        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        // 设置 header
        builder.header(nioKey, httpHeaders.get(nioKey));

        Request request = builder.get().url(url).build();


        try {
            Response response = client.newCall(request).execute();
            handleResponse(inbound, ctx, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleResponse(final FullHttpRequest fullHttpRequest, final ChannelHandlerContext ctx, final Response response) {
        FullHttpResponse fullHttpResponse = null;
        try {
            byte[] body = response.body().bytes();

            fullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(body));
            fullHttpResponse.headers().set("Content-Type", "application/json");
            fullHttpResponse.headers().set("Content-Length", body.length);

        } catch (IOException e) {
            e.printStackTrace();
            fullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.NO_CONTENT);
        } finally {
            if (fullHttpRequest != null) {
                if (!HttpUtil.isKeepAlive(fullHttpRequest)) {
                    ctx.write(fullHttpResponse).addListener(ChannelFutureListener.CLOSE);
                } else {
                    ctx.write(fullHttpResponse);
                }
            }
            ctx.flush();
        }
    }

    private void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
