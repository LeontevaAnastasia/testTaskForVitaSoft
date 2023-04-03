package com.applicationProcessingSystem.testTaskForVitaSoft.web.user;

import com.applicationProcessingSystem.testTaskForVitaSoft.AuthUser;
import com.applicationProcessingSystem.testTaskForVitaSoft.model.User;
import com.applicationProcessingSystem.testTaskForVitaSoft.service.UserService;
import com.applicationProcessingSystem.testTaskForVitaSoft.to.UserTo;
import com.applicationProcessingSystem.testTaskForVitaSoft.util.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = ProfileRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)

public class ProfileRestController {

    static final String REST_URL = "/rest/profile";
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private final UserService userService;

    public ProfileRestController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping()
    public User get(@AuthenticationPrincipal AuthUser authUser) {
        log.info("Get userTo by id {}.", authUser.getUserTo());
        return userService.get(authUser.getId());
    }


    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@AuthenticationPrincipal AuthUser authUser) {
        log.info("Delete profile id {} by user.", authUser.getId());
        userService.delete(authUser.getId());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody UserTo userTo, @AuthenticationPrincipal AuthUser authUser) {
        log.info("Update user to {} by user id {}.", userTo, authUser.getId());
        ValidationUtil.assureIdConsistent(userTo, authUser.getId());
       // UserTo userTo = authUser.getUserTo();
        userService.update(userTo);


    }
}
