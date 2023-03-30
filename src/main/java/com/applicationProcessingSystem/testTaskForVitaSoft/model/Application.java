package com.applicationProcessingSystem.testTaskForVitaSoft.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Application {

    private String text;

    private ApplicationStatus applicationStatus;

    private LocalDateTime dateTime;
    private User user;
}
