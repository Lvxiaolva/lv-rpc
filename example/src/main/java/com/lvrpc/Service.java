package com.lvrpc;

import com.lvrpc.server.RPCServerConfig;
import com.lvrpc.server.RPCService;

public class Service {
    public static void main(String[] args) {
        RPCService service = new RPCService(new RPCServerConfig());
        service.register(CalcService.class,new CalcServiceImpl());
        service.start();
    }
}
