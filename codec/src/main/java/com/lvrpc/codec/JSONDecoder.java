package com.lvrpc.codec;

import com.alibaba.fastjson.JSON;

/**
 * 基于JSON的反序列化实现
 */
public class JSONDecoder implements Decoder{
    @Override
    public <T> T decoder(byte[] bytes, Class<T> clazz) {
        return JSON.parseObject(bytes,clazz);
    }
}
