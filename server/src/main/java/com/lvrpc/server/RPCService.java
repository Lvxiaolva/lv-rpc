package com.lvrpc.server;

import com.lvrpc.codec.Decoder;
import com.lvrpc.codec.Encoder;
import com.lvrpc.common.utils.ReflectionUtils;
import com.lvrpc.rpc.Request;
import com.lvrpc.rpc.Response;
import com.lvrpc.transport.RequestHandler;
import com.lvrpc.transport.TransportServer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Slf4j
public class RPCService {
    private RPCServerConfig config;
    private TransportServer net;
    private Encoder encoder;
    private Decoder decoder;
    private ServiceManager serviceManager;
    private ServiceInvoker serviceInvoker;

    public RPCService(RPCServerConfig config) {
        this.config = config;
        this.net = ReflectionUtils.newInstance(config.getTransportClass());
        this.net.init(config.getPort(), this.handler);
        this.encoder = ReflectionUtils.newInstance(config.getEncoderClass());
        this.decoder = ReflectionUtils.newInstance(config.getDecoderClass());
        this.serviceManager = new ServiceManager();
        this.serviceInvoker = new ServiceInvoker();

    }

    public<T> void register(Class<T> interfaceClass,T bean){
        serviceManager.register(interfaceClass,bean);
    }

    public void start(){
        this.net.start();
    }

    public void stop(){
        this.net.stop();
    }

    private RequestHandler handler = new RequestHandler() {
        @Override
        public void onRequest(InputStream recive, OutputStream onResp) {
            Response response = new Response();
            try {
                byte[] inBytes = IOUtils.readFully(recive,recive.available());
                Request request = decoder.decoder(inBytes,Request.class);
                log.info("get request: {}",request);
                ServiceInstance sis = serviceManager.lookup(request);
                Object invoke = serviceInvoker.invoke(sis, request);
                response.setData(invoke);
            } catch (Exception e) {
                log.warn(e.getMessage(),e);
                response.setCode(1);
                response.setMessage("RPCService got error: " + e.getClass().getName() + ":" + e.getMessage());
            } finally {
                byte[] outBytes = encoder.encode(response);
                try {
                    onResp.write(outBytes);
                    log.info("response client");
                } catch (IOException e) {
                    log.warn(e.getMessage(),e);
                }
            }
        }
    };
}
