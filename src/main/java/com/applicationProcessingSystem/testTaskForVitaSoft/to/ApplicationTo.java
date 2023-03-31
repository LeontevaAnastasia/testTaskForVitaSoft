package com.applicationProcessingSystem.testTaskForVitaSoft.to;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ApplicationTo extends AbstractBaseTo{

    @NotBlank
    private String text;


}
