package com.lvrpc.client;

import com.lvrpc.codec.Decoder;
import com.lvrpc.codec.Encoder;
import com.lvrpc.rpc.Request;
import com.lvrpc.rpc.Response;
import com.lvrpc.rpc.ServiceDescriptor;
import com.lvrpc.transport.TransportClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Slf4j
public class RemoteInvoer implements InvocationHandler {
    private Class clazz;
    private Encoder encoder;
    private Decoder decoder;
    private TransportSelectot selectotr;
    RemoteInvoer(Class clazz, Encoder encoder, Decoder decoder,TransportSelectot selectotr){
        this.clazz = clazz;
        this.encoder = encoder;
        this.decoder = decoder;
        this.selectotr = selectotr;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Request request = new Request();
        request.setService(ServiceDescriptor.from(clazz,method));
        request.setParameters(args);

        Response response = invokeRemote(request);
        if (response == null || response.getCode() != 0){
            throw new IllegalStateException("fail to invoke remote:" + response);
        }

        return response.getData();
    }

    private Response invokeRemote(Request request) {
        TransportClient client = null;
        Response resp = null;
        try {
            client = selectotr.select();

            byte[] outBytes = encoder.encode(request);
            InputStream revice = client.write(new ByteArrayInputStream(outBytes));

            byte[] inBytes = IOUtils.readFully(revice,revice.available());
            resp = decoder.decoder(inBytes,Response.class);
        } catch (IOException e) {
            log.warn(e.getMessage(),e);
            resp = new Response();
            resp.setCode(1);
            resp.setMessage("RPCClient got error:" + e.getClass() + ":" + e.getMessage());
        } finally {
            if (client != null){
                selectotr.release(client);
            }
        }
        return resp;
    }
}
