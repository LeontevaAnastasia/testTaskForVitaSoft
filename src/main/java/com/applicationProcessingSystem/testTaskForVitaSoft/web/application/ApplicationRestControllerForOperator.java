package com.applicationProcessingSystem.testTaskForVitaSoft.web.application;

import com.applicationProcessingSystem.testTaskForVitaSoft.model.Application;
import com.applicationProcessingSystem.testTaskForVitaSoft.service.ApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
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

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void processApp(@PathVariable Integer id, @RequestParam String status) {
        log.info(" process application with id {}, set status {}", id, status);
        applicationService.processApplication(id, status);
    }


    @GetMapping
    public List<Application> getAllSentSortAsc(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "5") int size)
    {
        log.info(" get all sent application sort by date asc");
        return applicationService.getAllSentApplication(PageRequest.of(page, size, Sort.by("dateTime").ascending()));
    }

    @GetMapping("/sortDesc")
    public List<Application> getAllSentSortDesc(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "5") int size)
    {
        log.info(" get all sent application sort by date desc");
        return applicationService.getAllSentApplication(PageRequest.of(page, size, Sort.by("dateTime").descending()));
    }

    @GetMapping("/by-name")
    public List<Application> getAllByUserName(
            @RequestParam String name,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "5") int size) {
        log.info(" get application by userName {}, sort by date asc", name);
        return applicationService.getAllAppForName(name, PageRequest.of(page, size, Sort.by("dateTime").ascending()));
    }


    }


