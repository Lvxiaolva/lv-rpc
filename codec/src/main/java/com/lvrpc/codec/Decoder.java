package com.lvrpc.codec;

/**
 * εεΊεε
 */
public interface Decoder {
    <T> T decoder(byte[] bytes,Class<T> clazz);
}
