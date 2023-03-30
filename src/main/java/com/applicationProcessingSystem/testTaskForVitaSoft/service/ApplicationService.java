package com.applicationProcessingSystem.testTaskForVitaSoft.service;

import com.applicationProcessingSystem.testTaskForVitaSoft.model.Application;
import com.applicationProcessingSystem.testTaskForVitaSoft.model.ApplicationStatus;
import com.applicationProcessingSystem.testTaskForVitaSoft.repository.ApplicationRepository;
import com.applicationProcessingSystem.testTaskForVitaSoft.repository.UserRepository;
import com.applicationProcessingSystem.testTaskForVitaSoft.util.Exceptions.IncorrectUpdateException;

import java.util.List;

import static com.applicationProcessingSystem.testTaskForVitaSoft.util.ValidationUtil.checkNotFoundWithId;

public class ApplicationService {

    ApplicationRepository applicationRepository;
    UserRepository userRepository;

    public Application create(Application application, int userId) {
        return saveApplication(application, userId);
    }

    public void delete(int id, int userId ) {
        checkNotFoundWithId(applicationRepository.delete(id, userId), id);
    }

    public Application get(int id, int userId) {
        return checkNotFoundWithId(applicationRepository.getAppById(id, userId), id);
    }

    //add sorting
    public List<Application> getAll(int userId) {
        return checkNotFoundWithId(applicationRepository.getAll(userId), userId);
    }

    public void update(Application application, int userId) {
        if(application.getStatus().equals(ApplicationStatus.valueOf("DRAFT"))){
            checkNotFoundWithId(saveApplication(application, userId), application.getId());
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

        checkNotFoundWithId(applicationRepository.save(application), id);
    }


}
