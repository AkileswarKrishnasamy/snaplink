package com.snaplink.url_service.utils;


import io.seruco.encoding.base62.Base62;

public class EncoderUtil {

    public static String encode(String url) {
        final byte[] bytes = Base62.createInstance().encode(url.getBytes());
        return new String(bytes);
    }
}
