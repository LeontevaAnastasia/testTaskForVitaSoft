package com.applicationProcessingSystem.testTaskForVitaSoft.web.application;

import com.applicationProcessingSystem.testTaskForVitaSoft.model.Application;
import com.applicationProcessingSystem.testTaskForVitaSoft.service.ApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = ApplicationRestControllerForOperator.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ApplicationRestControllerForOperator {

    protected final Logger log = LoggerFactory.getLogger(getClass());
    static final String REST_URL = "/rest/operator/applications";

    private final ApplicationService applicationService;

    public ApplicationRestControllerForOperator(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping("/{id}")
    public void processApp(@PathVariable Integer id, @RequestBody String status) {
        log.info(" process application with id {}, set status {}", id, status);
        applicationService.processApplication(id, status);
    }


    @GetMapping
    public List<Application> getAllSent() {
        log.info(" get all sent application");
        return applicationService.getAllSentApplication();
    }

    @GetMapping("/by-name")
    public List<Application> getAllByUserName(@RequestParam String name) {
        log.info(" get application by userName {}", name);
        return applicationService.getAllAppForName(name);
    }


    }


