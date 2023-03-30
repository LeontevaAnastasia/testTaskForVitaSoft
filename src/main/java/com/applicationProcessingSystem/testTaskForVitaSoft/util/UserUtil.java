package com.applicationProcessingSystem.testTaskForVitaSoft.util;

import com.applicationProcessingSystem.testTaskForVitaSoft.model.User;
import com.applicationProcessingSystem.testTaskForVitaSoft.to.UserTo;

public class UserUtil {

    public static User updateFromTo(User user, UserTo userTo) {
        user.setName(userTo.getName());
        user.setEmail(userTo.getEmail().toLowerCase());
        user.setPassword(userTo.getPassword());
        return user;
    }
}
