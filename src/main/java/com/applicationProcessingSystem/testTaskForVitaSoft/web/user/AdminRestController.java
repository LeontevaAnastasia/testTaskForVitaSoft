package com.applicationProcessingSystem.testTaskForVitaSoft.web.user;

import com.applicationProcessingSystem.testTaskForVitaSoft.model.User;
import com.applicationProcessingSystem.testTaskForVitaSoft.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = AdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRestController {


    static final String REST_URL = "/rest/admin/users";

    private final UserService userService;

    public AdminRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void setRole(@PathVariable int id) {
        userService.setOperatorRole(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeRole(@PathVariable int id) {
        userService.removeOperatorRole(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void isEnable(@PathVariable int id, @RequestParam boolean enabled) {
        userService.isEnable(id, enabled);
    }


    @GetMapping("/by-name")
    public User findByName(@RequestParam String name){
        return userService.getByName(name);
    }

}



