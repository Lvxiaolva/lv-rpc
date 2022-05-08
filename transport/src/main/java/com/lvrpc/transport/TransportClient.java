package com.lvrpc.transport;

import com.lvrpc.rpc.Peer;

import java.io.InputStream;

/**
 * 1.创建连接
 * 2.发送数据，并等待相应
 * 3.关闭连接
 */
public interface TransportClient {
    void connect(Peer peer);

    InputStream write(InputStream data);

    void close();
}
