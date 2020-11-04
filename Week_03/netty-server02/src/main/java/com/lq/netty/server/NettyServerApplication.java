package com.lq.netty.server;


import com.lq.netty.server.inbound.HttpInboundServer;

public class NettyServerApplication {

    public static void main(String[] args) {
        String proxyServer = System.getProperty("proxyServer", "http://localhost:8801");
        String proxyPort = System.getProperty("proxyPort", "8888");

        int port = Integer.parseInt(proxyPort);

        HttpInboundServer server = new HttpInboundServer(port, proxyServer);

        try {
            server.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
