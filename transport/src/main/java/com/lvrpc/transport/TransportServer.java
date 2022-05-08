package com.lvrpc.transport;

/**
 * 1.启动、监听
 * 2.接受请求
 * 3.关闭监听
 */
public interface TransportServer {
    void init(Integer port,RequestHandler handler);

    void start();

    void stop();
}
