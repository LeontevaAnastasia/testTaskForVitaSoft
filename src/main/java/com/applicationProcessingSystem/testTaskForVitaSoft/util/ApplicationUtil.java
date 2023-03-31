package com.applicationProcessingSystem.testTaskForVitaSoft.util;

import com.applicationProcessingSystem.testTaskForVitaSoft.model.Application;

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
}
