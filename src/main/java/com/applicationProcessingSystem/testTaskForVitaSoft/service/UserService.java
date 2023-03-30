package com.applicationProcessingSystem.testTaskForVitaSoft.service;
import com.applicationProcessingSystem.testTaskForVitaSoft.model.Role;
import com.applicationProcessingSystem.testTaskForVitaSoft.model.User;
import com.applicationProcessingSystem.testTaskForVitaSoft.repository.UserRepository;
import com.applicationProcessingSystem.testTaskForVitaSoft.to.UserTo;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.applicationProcessingSystem.testTaskForVitaSoft.util.UserUtil.updateFromTo;
import static com.applicationProcessingSystem.testTaskForVitaSoft.util.ValidationUtil.checkNotFound;
import static com.applicationProcessingSystem.testTaskForVitaSoft.util.ValidationUtil.checkNotFoundWithId;

@Service
public class UserService {

    public  final  UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User get(int id) {
      return   checkNotFoundWithId(userRepository.getUserById(id), id);
    }

    public User getByName(String name){
        return  userRepository.findByUsername(name);
    }

    public Optional<User> findByEmailIgnoringCase(String email){
        return checkNotFound(userRepository.findByEmailIgnoreCase(email), "email=" + email);
    }

    public void delete(int id) {
        checkNotFoundWithId(userRepository.delete(id),id);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public void update(UserTo userTo) {

        User user = get(userTo.getId());
        updateFromTo(user, userTo);
    }

    public void isEnable(int id, boolean enabled) {
        User user = userRepository.getUserById(id).orElse(null);
        if (user == null) {
            throw new NotFoundException("User with id " + id + " doesn't exists.");
        }
        user.setEnabled(enabled);
    }

    public void setOperatorRole(int id) {
        User user = userRepository.getUserById(id).orElse(null);
        if (user == null) {
            throw new NotFoundException("User with id " + id + " doesn't exists.");
        }
        user.setRoles(Set.of(Role.OPERATOR));
    }


}
