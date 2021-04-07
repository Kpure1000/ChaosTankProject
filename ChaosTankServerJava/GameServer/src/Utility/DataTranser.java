package Utility;

import com.alibaba.fastjson.JSON;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class DataTranser {

    ////////////////////////////////////
    /// JSON
    ////////////////////////////////////

    static public String ByteToJsonStr(byte[] bytes) {
        return new String(bytes, StandardCharsets.UTF_8);
    }

    static public <T> T ByteToObject(byte[] bytes, Class<T> tClass) {
        return JSON.parseObject(ByteToJsonStr(bytes), tClass);
    }

    static public <T> String ObjectToJsonStr(T obj) {
        return JSON.toJSONString(obj);
    }

    static public <T> byte[] ObjectToJsonByte(T obj) {
        return ObjectToJsonStr(obj).getBytes();
    }

    ////////////////////////////////////
    /// TODO other like MD5?
    ////////////////////////////////////



}
