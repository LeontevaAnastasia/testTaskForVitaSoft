package com.applicationProcessingSystem.testTaskForVitaSoft.service;

import com.applicationProcessingSystem.testTaskForVitaSoft.model.Application;
import com.applicationProcessingSystem.testTaskForVitaSoft.model.ApplicationStatus;
import com.applicationProcessingSystem.testTaskForVitaSoft.repository.ApplicationRepository;
import com.applicationProcessingSystem.testTaskForVitaSoft.repository.UserRepository;
import com.applicationProcessingSystem.testTaskForVitaSoft.util.ApplicationUtil;
import com.applicationProcessingSystem.testTaskForVitaSoft.util.Exceptions.IncorrectUpdateException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static com.applicationProcessingSystem.testTaskForVitaSoft.util.ValidationUtil.checkNotFoundWithId;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;

    public ApplicationService(ApplicationRepository applicationRepository, UserRepository userRepository) {
        this.applicationRepository =applicationRepository;
        this.userRepository = userRepository;

    }

    //for users
    public Application create(Application application, int userId) {
        Assert.notNull(application, "application must not be null");
        return saveApplication(application, userId);
    }

    //for users
    public void delete(int id, int userId ) {
        checkNotFoundWithId(applicationRepository.delete(id, userId), id);
    }

    //for users
    public Application get(int id, int userId) {
        return checkNotFoundWithId(applicationRepository.getAppById(id, userId), id);
    }

    //for operator
    public List<Application> getAllForUser(int userId) {
        return checkNotFoundWithId(applicationRepository.getAllForUser(userId), userId);
    }

    //for operator
    public List<Application> getAllSentApplication() {

        return ApplicationUtil.formatMessage(applicationRepository.findSent());
    }

    public List<Application> getAllAppForName(String name){

        return ApplicationUtil.formatMessage(applicationRepository.getAllForUserName(name));
    }

    //for users
    public void updateDraft(Application application, int userId) {
        Assert.notNull(application, "application must not be null");
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


    //for users
    public void sendApplication(Integer id) {
        Application application = checkNotFoundWithId(applicationRepository.findById(id).orElse(null), id);

            application.setStatus(ApplicationStatus.SENT);
            applicationRepository.save(application);
    }

    //for operator
    public void processApplication (Integer id, String status) {

           if (ApplicationStatus.valueOf(status).equals(ApplicationStatus.ACCEPTED) || (ApplicationStatus.valueOf(status).equals(ApplicationStatus.REJECTED))) {
               Application application = checkNotFoundWithId(applicationRepository.findById(id), id);
               if(!(application.getStatus().equals(ApplicationStatus.SENT))) {
                   throw new IncorrectUpdateException();
               }
               application.setStatus(ApplicationStatus.valueOf(status));
               applicationRepository.save(application);
        } else   throw new IncorrectUpdateException();
    }


}
