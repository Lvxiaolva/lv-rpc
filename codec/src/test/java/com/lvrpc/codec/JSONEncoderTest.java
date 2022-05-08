package com.lvrpc.codec;

import junit.framework.TestCase;

public class JSONEncoderTest extends TestCase {

    public void testEncode() {
        JSONEncoder jsonEncoder = new JSONEncoder();
        TestBean testBean = new TestBean();
        testBean.setName("abc");
        testBean.setAge(12);
        byte[] bytes = jsonEncoder.encode(testBean);

        assertNotNull(bytes);

    }
}