package com.lvrpc.server;

import com.lvrpc.codec.Decoder;
import com.lvrpc.codec.Encoder;
import com.lvrpc.codec.JSONDecoder;
import com.lvrpc.codec.JSONEncoder;
import com.lvrpc.transport.HTTPTransportServer;
import com.lvrpc.transport.TransportServer;
import lombok.Data;

/**
 * server配置
 */
@Data
public class RPCServerConfig {
    private Class<? extends TransportServer> transportClass = HTTPTransportServer.class;
    private Class<? extends Encoder> encoderClass = JSONEncoder.class;
    private Class<? extends Decoder> decoderClass = JSONDecoder.class;
    private int port = 3000;
}
