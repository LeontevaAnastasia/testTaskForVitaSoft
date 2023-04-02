package com.applicationProcessingSystem.testTaskForVitaSoft;

import com.applicationProcessingSystem.testTaskForVitaSoft.model.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;


@Setter
@Getter
@ToString
public class AuthUser extends org.springframework.security.core.userdetails.User {

    @Serial
    private static final long serialVersionUID = 1L;

    private User user;

    public AuthUser(User user) {
        super(user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true, user.getRoles());
        this.user = user;
    }

    public int getId() {
        return user.id();
    }

}
