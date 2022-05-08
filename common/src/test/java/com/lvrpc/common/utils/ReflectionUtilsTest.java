package com.lvrpc.common.utils;

import junit.framework.TestCase;

import java.lang.reflect.Method;

public class ReflectionUtilsTest extends TestCase {

    public void testNewInstance() {
        TestClass testClass = ReflectionUtils.newInstance(TestClass.class);
        assertNotNull(testClass);
    }

    public void testGetPublicMethods() {
        Method[] methods = ReflectionUtils.getPublicMethods(TestClass.class);
        assertEquals(1,methods.length);
        assertEquals("b",methods[0].getName());
    }

    public void testInvoke() {
        Method[] methods = ReflectionUtils.getPublicMethods(TestClass.class);
        TestClass t = new TestClass();
        Object invoke = ReflectionUtils.invoke(t, methods[0]);

        assertEquals("b",invoke);
    }
}