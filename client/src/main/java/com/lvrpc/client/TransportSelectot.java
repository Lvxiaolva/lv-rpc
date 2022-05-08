package com.lvrpc.client;

import com.lvrpc.rpc.Peer;
import com.lvrpc.transport.TransportClient;

import java.util.List;

public interface TransportSelectot {
    void init(List<Peer> peers,int count,Class<? extends TransportClient> clazz);

    TransportClient select();

    void release(TransportClient client);

    void close();

}
