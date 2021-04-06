package Utility;

import com.alibaba.fastjson.JSON;

import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;

public class JsonTool {

    static public String ByteToString(byte[] bytes) throws UnsupportedEncodingException {
        return new String(bytes, "utf-8");
    }

    static public <T> T Parse(byte[] bytes, Class<T> tClass) throws UnsupportedEncodingException {
        return JSON.parseObject(ByteToString(bytes), tClass);
    }

    static public <T> String ObjectToJsonStr(T obj) {
        return JSON.toJSONString(obj);
    }

    static public <T> byte[] ObjectToJsonByte(T obj) {
        return ObjectToJsonStr(obj).getBytes();
    }

}
