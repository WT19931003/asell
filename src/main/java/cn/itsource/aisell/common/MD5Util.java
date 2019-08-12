package cn.itsource.aisell.common;

import org.apache.shiro.crypto.hash.SimpleHash;

public class MD5Util {

    //准备盐值与次数
    public static final String SALT = "itsource";
    public static final int HASHITERATIONS = 10;

    //根据传过来的密码进行MD5加密
    // 记住:你的加密算法要保持一致(算法,次数，盐值)
    // MD5 10 itsource
    public static String createMD5Str(String password){
        SimpleHash hash = new SimpleHash("MD5",password,SALT,HASHITERATIONS);
        return hash.toString();
    }
}
