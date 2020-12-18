package com.liq.rpcfx.nettyclent;

import com.alibaba.fastjson.JSON;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.AttributeKey;

import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @description Netty 客户端
 * author: liquan
 * date: 2020/11/03 15:35
 * version: 1.0
 */
public class NettyHttpClient {

    public static final CountDownLatch latch = new CountDownLatch(1);

    public final static EventLoopGroup workerGroup = new NioEventLoopGroup();

    private static Bootstrap bootstrap = null;

    public static String result;

    static {
        bootstrap = new Bootstrap();

        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast("log", new LoggingHandler(LogLevel.INFO));
                        ch.pipeline().addLast(HttpClientCodec.class.getSimpleName(), new HttpClientCodec());
                        ch.pipeline().addLast(HttpObjectAggregator.class.getSimpleName(),
                                new HttpObjectAggregator(1048576));
                        ch.pipeline().addLast(
                                IdleStateHandler.class.getSimpleName(),
                                new IdleStateHandler(0, 0, 200 * 1000,
                                        TimeUnit.MILLISECONDS));
                        // 客户端接受的是httpResponse响应，所以使用 HttpResponseDecoder 解码
//                        ch.pipeline().addLast(new HttpResponseDecoder());
                        // 客户端发送的是httpRequest，所以要使用HttpRequestEncoder 进行编码
//                        ch.pipeline().addLast(new HttpResponseEncoder());
                        ch.pipeline().addLast(NettyHttpClientOutBoundHandler.class.getSimpleName(), new NettyHttpClientOutBoundHandler());
                    }
                });
    }

    /**
     * 创建连接
     *
     * @param route InetSocketAddress
     * @return
     * @throws Exception
     */
    private ChannelFuture createChannelFuture(InetSocketAddress route) throws Exception {
        // 异步链接到远程节点
        ChannelFuture channelFuture = bootstrap.connect(route.getHostName(), route.getPort()).sync();
        return channelFuture;
    }

    /**
     * 发送请求
     *
     * @param uri     URI
     * @param request HttpRequest
     * @throws Exception
     */
    public void sendRequest(URI uri, HttpRequest request) throws Exception {
        InetSocketAddress route = new InetSocketAddress(uri.getHost(), uri.getPort());

        ChannelFuture channelFuture = createChannelFuture(route);
        AttributeKey<Object> ROUTE_ATTRIBUTE = AttributeKey
                .valueOf("route");
        channelFuture.channel().attr(ROUTE_ATTRIBUTE).set(route);

        // 注册一个，ChannelFutureListener，以便在操作完成时获得通知
        channelFuture.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                System.out.println(future.isSuccess());
                if (future.isSuccess()) {

                    future.channel().closeFuture().addListener(new ChannelFutureListener() {
                        @Override
                        public void operationComplete(ChannelFuture future) throws Exception {

                        }
                    });

                    // 将数据异步发送到远程节点
                    future.channel().writeAndFlush(request).addListener(CLOSE_ON_FAILURE);
                } else {
                    future.cause();
                }
            }
        });
    }

    /**
     * 创建请求 createHttpRequest
     *
     * @param uri        URI
     * @param content    请求内容
     * @param httpMethod 请求方法
     * @return
     */
    public HttpRequest createHttpRequest(final URI uri, final String content, final HttpMethod httpMethod) {
        HttpRequest httpRequest = null;
        ByteBuf byteBuf = Unpooled.copiedBuffer(content, Charset.forName("UTF-8"));
        if (HttpMethod.POST == httpMethod) {
            httpRequest = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, httpMethod, uri.getRawPath(), byteBuf.retain());
            httpRequest.headers().set(HttpHeaders.Names.CONTENT_LENGTH,
                    byteBuf.readableBytes());
        } else {
            httpRequest = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, httpMethod, uri.getRawPath());
        }
        httpRequest.headers().set(HttpHeaders.Names.CONTENT_TYPE, "application/json;charset=UTF-8");
        httpRequest.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
        httpRequest.headers().set(HttpHeaders.Names.HOST, uri.getHost());
        return httpRequest;
    }

    public static void main(String[] args) throws Exception {
//        final String url = "http://127.0.0.1:8094/business-dao/wechat/123?msg=123";
//        NettyHttpClient02 client02 = new NettyHttpClient02();
//        URI uri = URI.create(url);
//        Map<String, String> msg = new HashMap<>();
//        msg.putIfAbsent("msg", "123");
//        HttpRequest httpRequest = client02.createHttpRequest(uri, JSON.toJSONString(msg), HttpMethod.GET);
//        client02.sendRequest(uri, httpRequest);
//        workerGroup.shutdownGracefully();

        final String url = "http://127.0.0.1:8094/";
        NettyHttpClient client02 = new NettyHttpClient();
        URI uri = URI.create(url);
        Map<String, String> msg = new HashMap<>();
        msg.putIfAbsent("msg", "123");

        HttpRequest httpRequest = client02.createHttpRequest(uri, JSON.toJSONString(msg), HttpMethod.POST);
        client02.sendRequest(uri, httpRequest);
        client02.latch.await();
        System.out.println(client02.result);
        client02.workerGroup.shutdownGracefully();

    }
}
