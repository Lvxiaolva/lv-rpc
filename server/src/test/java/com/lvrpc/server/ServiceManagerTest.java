package com.lvrpc.server;

import com.lvrpc.common.utils.ReflectionUtils;
import com.lvrpc.rpc.Request;
import com.lvrpc.rpc.ServiceDescriptor;
import junit.framework.TestCase;

import java.lang.reflect.Method;

public class ServiceManagerTest extends TestCase {
    ServiceManager sm = new ServiceManager();


    public void testRegister() {
        TestInterface bean = new TestClass();
        sm.register(TestInterface.class,bean);
    }

    public void testLookup() {
        TestInterface bean = new TestClass();
        sm.register(TestInterface.class,bean);

        Method method = ReflectionUtils.getPublicMethods(TestInterface.class)[0];
        ServiceDescriptor sdp = ServiceDescriptor.from(TestInterface.class,method);

        Request request = new Request();
        request.setService(sdp);
        ServiceInstance serviceInstance = sm.lookup(request);

        assertNotNull(serviceInstance);
    }
}