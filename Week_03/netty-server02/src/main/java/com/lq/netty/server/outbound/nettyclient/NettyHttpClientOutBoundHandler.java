package com.lq.netty.server.outbound.nettyclient;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.AttributeKey;
import io.netty.util.CharsetUtil;
import okhttp3.Response;

import java.io.IOException;

/**
 * @description author: liquan
 * date: 2020/11/03 16:10
 * version: 1.0
 */
public class NettyHttpClientOutBoundHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {

    }

    /**
     * 处理请求数据
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        if (msg instanceof HttpResponse) {
            HttpResponse response = (HttpResponse) msg;
            System.out.println("CONTENT_TYPE:" + response.headers().get(HttpHeaders.Names.CONTENT_TYPE));
        }

        if (msg instanceof HttpContent) {
            HttpContent content = (HttpContent) msg;
            ByteBuf buf = content.content();
            System.out.println("请求结果：" + buf.toString(CharsetUtil.UTF_8));
            buf.release();

            String result = buf.toString(CharsetUtil.UTF_8);
            AttributeKey key = AttributeKey.valueOf("result");
            ctx.channel().attr(key).set(result);
            ctx.channel().close();
        }


    }

    /**
     * 数据读取完毕的处理
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.err.println("客户端读取数据完毕");
        ctx.flush();
    }

    /**
     * 出现异常的处理
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.err.println("client 读取数据出现异常");
        System.out.println(cause.getMessage());
        ctx.close();
    }


}
