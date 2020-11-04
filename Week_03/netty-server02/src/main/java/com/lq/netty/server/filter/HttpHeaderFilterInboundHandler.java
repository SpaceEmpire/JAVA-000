package com.lq.netty.server.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @description 设置请求 Header
 * author: liquan
 * date: 2020/11/04 14:04
 * version: 1.0
 */
public class HttpHeaderFilterInboundHandler extends ChannelInboundHandlerAdapter {

    private static Logger logger = LoggerFactory.getLogger(HttpHeaderFilterInboundHandler.class);

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        FullHttpRequest fullHttpRequest = (FullHttpRequest) msg;
        fullHttpRequest.headers().set("nio","liquan");
        // 显示调用fireChannelRead将消息传递到下一个处理器
        ctx.fireChannelRead(msg);
    }

}
