package org.michael.secondkilldemo.utils;

import org.apache.commons.codec.digest.Md5Crypt;
import org.springframework.util.Assert;

/**
 * md5工具类
 * 本demo采取2次md5加密过程，MD5盐值加密是一种不可逆加密法.
 * 1. 前段根据salt进行第一次md5加密，通过网络传到后端
 * 2. 后端通过2次salt加密，存入db加密后结果+salt值
 * 3 需要比对password时，通过前段传入1次加密的值进行2次加密后，跟db中password值进行比对
 *
 * @author Dijunjie
 * @Description TODO
 * @date 2022/6/7-12:16
 */
public class MD5Utils {

    // 默认系统md5加密盐，防止MD5被暴力破解
    private static final String DEFAULT_SALT = "abcd1234";
    // md5加密盐前缀
    private static final String MD5_PREFIX = "$1$";

    /**
     * md5加密方法
     * @param src
     * @return
     */
    public static String encrypt(String src) {
        Assert.notNull(src, "加密字段不能为空");
        return Md5Crypt.md5Crypt(src.getBytes(), MD5_PREFIX+DEFAULT_SALT);
    }

    /**
     * md5加密方法
     * @param src
     * @param salt
     * @return
     */
    public static String encryptSalt(String src, String salt) {
        Assert.notNull(src, "加密字段不能为空");
        Assert.notNull(salt, "加密盐不能为空");
        return Md5Crypt.md5Crypt(src.getBytes(), MD5_PREFIX+salt);
    }

    /**
     * 明文password2次加密变成db存储值
     * @param password
     * @param salt
     * @return
     */
    public static String inputPwdToDbPwd(String password, String salt) {
        //第一次加密结果
        String encryptPwd = encrypt(password);
        //第二次MD5盐值加密
        return encryptSalt(encryptPwd, salt);
    }

    public static void main(String[] args) {
        String input = "123456";
        String fromPwd = encrypt(input);
        System.out.println(fromPwd);
        String dbPwd = encryptSalt(fromPwd, "1a2b3c4d");
        System.out.println(dbPwd);
        System.out.println(inputPwdToDbPwd(input,"1a2b3c4d"));
    }

}
