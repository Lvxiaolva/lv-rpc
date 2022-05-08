package com.lvrpc.client;

import com.lvrpc.codec.Decoder;
import com.lvrpc.codec.Encoder;
import com.lvrpc.codec.JSONDecoder;
import com.lvrpc.codec.JSONEncoder;
import com.lvrpc.rpc.Peer;
import com.lvrpc.transport.HTTPTransportClient;
import com.lvrpc.transport.TransportClient;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class RPCClientConfig {
    private Class<? extends TransportClient> transportClass = HTTPTransportClient.class;
    private Class<? extends Encoder> encoderClass = JSONEncoder.class;
    private Class<? extends Decoder> decoderClass = JSONDecoder.class;
    private Class<? extends TransportSelectot> selsectorClass = RandomTransportSelector.class;
    private int connectCount = 1;
    private List<Peer> servers = Arrays.asList(new Peer("127.0.0.1",3000));

}
