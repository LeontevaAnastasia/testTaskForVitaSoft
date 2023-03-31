package com.applicationProcessingSystem.testTaskForVitaSoft.web.user;


import com.applicationProcessingSystem.testTaskForVitaSoft.model.Application;
import com.applicationProcessingSystem.testTaskForVitaSoft.model.User;
import com.applicationProcessingSystem.testTaskForVitaSoft.service.ApplicationService;
import com.applicationProcessingSystem.testTaskForVitaSoft.service.UserService;
import com.applicationProcessingSystem.testTaskForVitaSoft.to.ApplicationTo;
import com.applicationProcessingSystem.testTaskForVitaSoft.util.ApplicationUtil;
import com.applicationProcessingSystem.testTaskForVitaSoft.util.SecurityUtil;
import com.applicationProcessingSystem.testTaskForVitaSoft.util.ValidationUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static com.applicationProcessingSystem.testTaskForVitaSoft.util.ValidationUtil.assureIdConsistent;

@RestController
@RequestMapping(value = ApplicationRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ApplicationRestController {

    static final String REST_URL = "/rest/profile/applications";

   private final ApplicationService applicationService;
    private final UserService userService;

    public ApplicationRestController(ApplicationService applicationService, UserService userService) {
        this.applicationService = applicationService;
        this.userService = userService;
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Application> create(@Valid @RequestBody ApplicationTo applicationTo) {
        int userId = SecurityUtil.authUserId();
        User user = userService.get(userId);
        Application application = ApplicationUtil.createNewFromTo(applicationTo, user);

        ValidationUtil.checkNew(application);
        Application created = applicationService.create(application, userId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody ApplicationTo applicationTo, @PathVariable int id) {
        int userId = SecurityUtil.authUserId();
        assureIdConsistent(applicationTo, id);
        applicationService.updateDraft(ApplicationUtil.updateFromTo((applicationService.get(id, userId)), applicationTo) , userId);
    }

    @PostMapping("{/id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void sendApplication(@PathVariable Integer id){
        applicationService.sendApplication(id);

    }

    @GetMapping
    public List<Application> getAll() {
        int userId = SecurityUtil.authUserId();
        return applicationService.getAllForUser(userId);
    }

    @GetMapping("/{id}")
    public Application get(@PathVariable int id) {
        int userId = SecurityUtil.authUserId();
        return applicationService.get(id, userId);
    }






}