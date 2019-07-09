package com.anthonyzero.scaffold.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * 消息摘要
 * @author anthonyzero
 */
public class MD5Util {

    //散列算法类型为MD5
    private static final String ALGORITH_NAME = "md5";
    //hash的次数
    private static final int HASH_ITERATIONS = 5;

    public static String encrypt(String password, String salt) {
        salt = StringUtils.lowerCase(salt);
        password = StringUtils.lowerCase(password);
        String newPassword = new SimpleHash(ALGORITH_NAME, password, ByteSource.Util.bytes(salt), HASH_ITERATIONS).toHex();
        return newPassword;
    }
}
