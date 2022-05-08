package com.lvrpc;

import com.lvrpc.client.RPCClient;

public class Client {
    public static void main(String[] args) {
        RPCClient client = new RPCClient();
        CalcService service = client.getProxy(CalcService.class);

        int r1 = service.add(1,2);
        int r2 = service.minus(10,2);

        System.out.println(r1);
        System.out.println(r2);
    }
}
