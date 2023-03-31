package com.applicationProcessingSystem.testTaskForVitaSoft.util;

import com.applicationProcessingSystem.testTaskForVitaSoft.model.Application;
import com.applicationProcessingSystem.testTaskForVitaSoft.model.ApplicationStatus;
import com.applicationProcessingSystem.testTaskForVitaSoft.model.User;
import com.applicationProcessingSystem.testTaskForVitaSoft.to.ApplicationTo;

import java.time.LocalDateTime;
import java.util.List;

public class ApplicationUtil {

    public static List<Application> formatMessage(List<Application> sentAppList){
        for (Application application : sentAppList) {
            String request = application.getText();
            StringBuilder formatMessageBuilder = new StringBuilder();

            for (int i = 0; i < request.length() - 1; i++) {
                formatMessageBuilder.append(request.charAt(i));
                formatMessageBuilder.append('-');
            }
            formatMessageBuilder.append(request.charAt(request.length() - 1));

            application.setText(formatMessageBuilder.toString());
        }

        return sentAppList;
    }

    public static Application createNewFromTo(ApplicationTo applicationTo, User user) {
        return new Application(null, applicationTo.getText(), ApplicationStatus.DRAFT, LocalDateTime.now(), user);
    }

    public static Application updateFromTo(Application application, ApplicationTo applicationTo) {
        application.setText(applicationTo.getText());
        return application;
    }
}
