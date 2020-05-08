package com.github.restaurantvotingsystem.util;

public class SecurityUtil {
    public static Integer getUserId() {
        return 100000;
    }

    public static Integer getAdminId() {
        return 100001;
    }
}

//    public static String getUserName() {
//        return SecurityContextHolder.getContext().getAuthentication().getName();
//    }
