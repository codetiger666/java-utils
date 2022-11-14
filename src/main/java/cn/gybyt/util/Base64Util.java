package cn.gybyt.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Base64;

/**
 * Base64工具类
 *
 * @program: utils
 * @classname: BaseUtil
 * @author: Codetiger
 * @create: 2022/5/15 21:48
 **/
public class Base64Util {

    public static Logger log = LoggerFactory.getLogger(Base64Util.class);

    /**
     * @param content 需要编码的字符串
     * @return 编码后的字符串
     * @Author codetiger
     * @Description //TODO
     * @Date 22:08 2022/5/15
     * @Param
     **/
    public static String strToBase64(String content) {
        Base64.Encoder base64 = Base64.getEncoder();
        return base64.encodeToString(content.getBytes());
    }

    /**
     * @param base64 需要解码的base64字符串
     * @return 解码后的字符串
     * @Author codetiger
     * @Description //TODO
     * @Date 21:54 2022/5/15
     * @Param
     **/
    public static String base64ToStr(String base64) {
        Base64.Decoder decoder = Base64.getDecoder();
        return new String(decoder.decode(base64));
    }
}
