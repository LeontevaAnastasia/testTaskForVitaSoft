package com.applicationProcessingSystem.testTaskForVitaSoft.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class User {

    private String name;

    private String email;

    private String password;

    private boolean enabled = true;


    private LocalDate registered;

    private Set<Role> roles;

    private List<Application> applications;



}
