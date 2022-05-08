package com.lvrpc.server;

import com.lvrpc.common.utils.ReflectionUtils;
import com.lvrpc.rpc.Request;

/**
 * 调用具体服务
 */
public class ServiceInvoker {
    public Object invoke(ServiceInstance service, Request request){
        return ReflectionUtils.invoke(service.getTarget(),service.getMethod(),request.getParameters());
    }
}
