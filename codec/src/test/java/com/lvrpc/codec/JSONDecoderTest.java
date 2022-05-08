package com.lvrpc.codec;

import junit.framework.TestCase;

public class JSONDecoderTest extends TestCase {

    public void testDecoder() {
        JSONEncoder jsonEncoder = new JSONEncoder();
        TestBean testBean = new TestBean();
        testBean.setName("abc");
        testBean.setAge(12);
        byte[] bytes = jsonEncoder.encode(testBean);

        JSONDecoder jsonDecoder = new JSONDecoder();
        TestBean testBean1 = jsonDecoder.decoder(bytes, TestBean.class);

        assertEquals(testBean.getName(),testBean1.getName());
        assertEquals(testBean.getAge(),testBean1.getAge());

    }
}