package com.applicationProcessingSystem.testTaskForVitaSoft.web.user;

import com.applicationProcessingSystem.testTaskForVitaSoft.model.User;
import com.applicationProcessingSystem.testTaskForVitaSoft.service.UserService;
import com.applicationProcessingSystem.testTaskForVitaSoft.to.UserTo;
import com.applicationProcessingSystem.testTaskForVitaSoft.util.ValidationUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.applicationProcessingSystem.testTaskForVitaSoft.util.SecurityUtil.authUserId;

@RestController
@RequestMapping(value = ProfileRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)

public class ProfileRestController {

    static final String REST_URL = "/rest/profile";

    private final UserService userService;

    public ProfileRestController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping()
    public User get(User user) {
        return userService.get(user.getId());
    }


    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(User user) {
        userService.delete(user.getId());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody UserTo userTo) {
        ValidationUtil.assureIdConsistent(userTo, authUserId());
        userService.update(userTo);
    }


}
