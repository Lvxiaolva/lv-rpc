package com.lvrpc.client;

import com.lvrpc.codec.Decoder;
import com.lvrpc.codec.Encoder;
import com.lvrpc.common.utils.ReflectionUtils;

import java.lang.reflect.Proxy;

public class RPCClient {
    private RPCClientConfig config;
    private Encoder encoder;
    private Decoder decoder;
    private TransportSelectot selectotr;

    public RPCClient() {
        this(new RPCClientConfig());
    }

    public RPCClient(RPCClientConfig config) {
        this.config = config;
        this.encoder = ReflectionUtils.newInstance(this.config.getEncoderClass());
        this.decoder = ReflectionUtils.newInstance(this.config.getDecoderClass());
        this.selectotr = ReflectionUtils.newInstance(this.config.getSelsectorClass());
        this.selectotr.init(
                this.config.getServers(),
                this.config.getConnectCount(),
                this.config.getTransportClass()
        );
    }

    public <T> T getProxy(Class clazz){
        return(T) Proxy.newProxyInstance(
                getClass().getClassLoader(),
                new Class[]{clazz},
                new RemoteInvoer(clazz ,encoder,decoder,selectotr)
        );
    }

}
