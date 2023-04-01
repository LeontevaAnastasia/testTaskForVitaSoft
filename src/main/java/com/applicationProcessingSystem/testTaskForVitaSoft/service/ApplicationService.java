package com.applicationProcessingSystem.testTaskForVitaSoft.service;

import com.applicationProcessingSystem.testTaskForVitaSoft.model.Application;
import com.applicationProcessingSystem.testTaskForVitaSoft.model.ApplicationStatus;
import com.applicationProcessingSystem.testTaskForVitaSoft.repository.ApplicationRepository;
import com.applicationProcessingSystem.testTaskForVitaSoft.repository.UserRepository;
import com.applicationProcessingSystem.testTaskForVitaSoft.util.ApplicationUtil;
import com.applicationProcessingSystem.testTaskForVitaSoft.util.Exceptions.IncorrectUpdateException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

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
    public List<Application> getAllForUser(int userId) {
        return checkNotFoundWithId(applicationRepository.getAllForUser(userId), userId);
    }

    public List<Application> getAll() {
        return applicationRepository.findAll();
    }

    public List<Application> getSentApplication() {

        return ApplicationUtil.formatMessage(applicationRepository.findSent());
    }

    public void updateDraft(Application application, int userId) {
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
        Application application =  checkNotFoundWithId(applicationRepository.findById(id).orElse(null), id);
        application.setStatus(ApplicationStatus.valueOf(status));

        applicationRepository.save(application);
    }

    public void sendApplication(Integer id) {
        Application application = checkNotFoundWithId(applicationRepository.findById(id).orElse(null), id);

            application.setStatus(ApplicationStatus.SENT);
            applicationRepository.save(application);
    }

    public void processApplication (Integer id, String status) {
        if (!status.equals(ApplicationStatus.ACCEPTED.toString()) && !status.equals(ApplicationStatus.REJECTED.toString())) {
            throw new IncorrectUpdateException();
        }

        Application application = checkNotFoundWithId(applicationRepository.findById(id), id);
        if(!(application.getStatus().equals(ApplicationStatus.SENT))) {
            throw new IncorrectUpdateException();
        }
            application.setStatus(ApplicationStatus.valueOf(status));
            applicationRepository.save(application);
    }


}
