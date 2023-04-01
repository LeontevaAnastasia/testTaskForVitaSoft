package com.applicationProcessingSystem.testTaskForVitaSoft.web.application;

import com.applicationProcessingSystem.testTaskForVitaSoft.model.Application;
import com.applicationProcessingSystem.testTaskForVitaSoft.service.ApplicationService;
import com.applicationProcessingSystem.testTaskForVitaSoft.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = ApplicationRestControllerForOperator.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ApplicationRestControllerForOperator {

    static final String REST_URL = "/rest/operator/applications";

    private final ApplicationService applicationService;
    private final UserService userService;

    public ApplicationRestControllerForOperator(ApplicationService applicationService, UserService userService) {
        this.applicationService = applicationService;
        this.userService = userService;
    }

    @PostMapping("/{id}")
    public void processApp(@PathVariable Integer id, @RequestBody String status) {
        applicationService.processApplication(id, status);
    }


    @GetMapping
    public List<Application> getAllSent() {
        return applicationService.getAllSentApplication();
    }

    @GetMapping("/by-name")
    public List<Application> getAllByUserName(@RequestParam String name) {
        return applicationService.getAllAppForName(name);
    }


    }


