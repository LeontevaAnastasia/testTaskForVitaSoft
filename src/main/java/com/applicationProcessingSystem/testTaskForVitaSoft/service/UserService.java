package com.applicationProcessingSystem.testTaskForVitaSoft.service;
import com.applicationProcessingSystem.testTaskForVitaSoft.model.Role;
import com.applicationProcessingSystem.testTaskForVitaSoft.model.User;
import com.applicationProcessingSystem.testTaskForVitaSoft.repository.UserRepository;
import com.applicationProcessingSystem.testTaskForVitaSoft.to.UserTo;
import com.applicationProcessingSystem.testTaskForVitaSoft.util.Exceptions.IncorrectUpdateException;
import com.applicationProcessingSystem.testTaskForVitaSoft.util.UserUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Set;

import static com.applicationProcessingSystem.testTaskForVitaSoft.util.UserUtil.prepareToSave;
import static com.applicationProcessingSystem.testTaskForVitaSoft.util.ValidationUtil.checkNotFoundWithId;

@Service
public class UserService {

    public  final  UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public User get(int id) {
      return userRepository.getOne(id);
    }

    public List<User> getByName(String name){

        if(name.contains("%")){
            if(name.endsWith("%")){
              return   userRepository.findByUsernameStartWith(name);
            } else if(name.startsWith("%")){
                return   userRepository.findByUsernameEndWith(name);
            } else if (name.startsWith("%") && name.endsWith("%")){
                return  userRepository.findByPartOfUsername(name);
            }
        }


        return  userRepository.findByUsername(name);
    }

    public void delete(int id) {
        checkNotFoundWithId(userRepository.delete(id),id);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        return userRepository.save(user);
    }

    public void update(UserTo userTo) {
        User user = get(userTo.getId());
        prepareAndSave(UserUtil.updateFromTo(user, userTo));
    }

    @Transactional
    public void isEnable(int id, boolean enabled) {
        User user = userRepository.getUserById(id).orElse(null);
        if (user == null) {
            throw new NotFoundException("User with id " + id + " doesn't exists.");
        }
        user.setEnabled(enabled);
    }


    @Transactional
    public void setOperatorRole(int id) {
        User user = userRepository.getUserById(id).orElse(null);
        if (user == null) {
            throw new NotFoundException("User with id " + id + " doesn't exists.");
        }
        if(user.getRoles().toString().contains("ADMIN")){
            user.setRoles(Set.of(Role.OPERATOR, Role.ADMIN));
        } else user.setRoles(Set.of(Role.OPERATOR));
    }

    @Transactional
    public void removeOperatorRole(int id) {
        User user = userRepository.getUserById(id).orElse(null);
        if (user == null) {
            throw new NotFoundException("User with id " + id + " doesn't exists.");
        }
        if (!(user.getRoles().toString().contains("OPERATOR"))) {
            throw new IncorrectUpdateException();
        }

        Set<Role> roles = user.getRoles();
        roles.remove(Role.OPERATOR);
        if (!(user.getRoles().toString().contains("ADMIN"))) {
            roles.add(Role.USER);
        }
        user.setRoles(roles);
    }

    private User prepareAndSave(User user) {
        return userRepository.save(prepareToSave(user, passwordEncoder));
    }


}
