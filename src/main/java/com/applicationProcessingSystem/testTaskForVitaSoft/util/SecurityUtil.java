package com.applicationProcessingSystem.testTaskForVitaSoft.util;

public class SecurityUtil {

    //mock
    private static int id = 100002;

    public static int authUserId() {
        return id;
    }

    public static void setAuthUserId(int id) {
        SecurityUtil.id = id;
    }
}
