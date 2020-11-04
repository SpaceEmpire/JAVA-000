package com.lq.netty.server.outbound.nettyclient;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

/**
 * @description Netty 客户端
 * author: liquan
 * date: 2020/11/03 15:35
 * version: 1.0
 */
public class NettyHttpClient {

    public void connect(String host, int port) throws Exception {

        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();

            bootstrap.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // 客户端接受的是httpResponse响应，所以使用 HttpResponseDecoder 解码
                            ch.pipeline().addLast(new HttpResponseDecoder());
                            // 客户端发送的是httpRequest，所以要使用HttpRequestEncoder 进行编码
                            ch.pipeline().addLast(new HttpResponseEncoder());
                            ch.pipeline().addLast(new NettyHttpClientOutBoundHandler());
                        }
                    });
            ChannelFuture cf = bootstrap.connect(host, port).sync();
            // 像服务端发送请求
//            cf.channel().write();

            cf.channel().flush();
            cf.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        NettyHttpClient client = new NettyHttpClient();
        client.connect("127.0.0.1", 8801);
    }
}
