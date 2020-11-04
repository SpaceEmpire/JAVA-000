package com.lq.netty.server.inbound;

import com.lq.netty.server.filter.HttpHeaderFilterInboundHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

public class HttpInboundInitializer extends ChannelInitializer<SocketChannel> {

    private String proxyServer;

    public HttpInboundInitializer(String proxyServer) {
        this.proxyServer = proxyServer;
    }

    @Override
    public void initChannel(SocketChannel ch) {
        ChannelPipeline p = ch.pipeline();
        p.addLast(new HttpServerCodec());
        p.addLast(new HttpObjectAggregator(1024 * 1024));
        // 设置请求 header
        p.addLast(new HttpHeaderFilterInboundHandler());
        // 使用 httpclient 请求后端服务
        p.addLast(new HttpInboundHandler(this.proxyServer));
        // nettyclient
//        p.addLast(new NettyHttpClientInboundHandler(this.proxyServer));
    }
}
