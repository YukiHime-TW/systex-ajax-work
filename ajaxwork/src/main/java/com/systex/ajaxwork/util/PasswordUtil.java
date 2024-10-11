package com.systex.ajaxwork.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {

    // 使用 BCrypt 加密密碼
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    // 驗證密碼是否匹配
    public static boolean verifyPassword(String rawPassword, String hashedPassword) {
        return BCrypt.checkpw(rawPassword, hashedPassword);
    }
}