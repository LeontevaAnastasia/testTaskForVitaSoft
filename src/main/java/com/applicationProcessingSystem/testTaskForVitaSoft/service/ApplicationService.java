package com.applicationProcessingSystem.testTaskForVitaSoft.service;

import com.applicationProcessingSystem.testTaskForVitaSoft.model.Application;
import com.applicationProcessingSystem.testTaskForVitaSoft.model.ApplicationStatus;
import com.applicationProcessingSystem.testTaskForVitaSoft.repository.ApplicationRepository;
import com.applicationProcessingSystem.testTaskForVitaSoft.repository.UserRepository;
import com.applicationProcessingSystem.testTaskForVitaSoft.util.Exceptions.IncorrectUpdateException;

import java.util.List;

public class ApplicationService {


    ApplicationRepository applicationRepository;
    UserRepository userRepository;

    public Application create(Application application, int userId) {
        return saveApplication(application, userId);
    }

    public void delete(int id, int userId ) {
        applicationRepository.delete(id, userId);
    }

    public Application get(int id, int userId) {
        return applicationRepository.getAppById(id, userId);
    }

    //add sorting
    public List<Application> getAll(int userId) {
        return applicationRepository.getAll(userId);
    }

    public void update(Application application, int userId) {
        if(application.getStatus().equals(ApplicationStatus.valueOf("DRAFT"))){
            saveApplication(application, userId);
        } else  throw new IncorrectUpdateException();
    }


    public Application saveApplication(Application application, int userId){
        if (!application.isNew() && get(application.getId(), userId) == null) {
            return null;
        }
        application.setUser(userRepository.getById(userId));
        return applicationRepository.save(application);
    }

    public void setAppStatus(Integer id, String status) {
        Application application =  applicationRepository.findById(id).orElse(null);
        application.setStatus(ApplicationStatus.valueOf(status));

        applicationRepository.save(application);
    }


}
