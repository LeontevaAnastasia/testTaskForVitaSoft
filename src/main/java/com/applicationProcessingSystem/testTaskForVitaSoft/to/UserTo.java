package com.applicationProcessingSystem.testTaskForVitaSoft.to;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserTo extends AbstractBaseTo {

    @Size(min =1, max = 128)
    private String name;


    @Size(min=1, max = 128)
    private String email;

    @Size(min = 4, max = 100)
    private String password;

    public UserTo(Integer id, String name, String email, String password) {
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
