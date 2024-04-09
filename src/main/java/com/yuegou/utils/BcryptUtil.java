package com.yuegou.utils;


import cn.hutool.crypto.digest.BCrypt;

public class BcryptUtil {

    public static String getPasswordBcrypt(String password) {
        return BCrypt.hashpw(password);
    }

    public static boolean equalsPassword(String password, String sqlPassword){
        return BCrypt.checkpw(password,sqlPassword);
    }

}
