package com.woniuxy.servicelayer.util;

import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;

/**
 * md5加密工具
 */
public class Md5Util {

    private static String salt = "aslkdfj19234!@#$!@#asdjfkl加上了快递费@#$%#@$%@#$%#21023409129";

    /**
     * 对原始字符串做加盐并MD5加密
     * @param oriString
     * @return
     */
    public static String encode(String oriString){

        if (StringUtils.isEmpty(oriString)){
            return null;
        }

        oriString+=salt;

        //md5加密
        String encodeString = DigestUtils.md5DigestAsHex(oriString.getBytes(StandardCharsets.UTF_8));

        return encodeString;
    }


    public static void main(String[] args) {
        System.out.println( encode("123456") );
    }

}
