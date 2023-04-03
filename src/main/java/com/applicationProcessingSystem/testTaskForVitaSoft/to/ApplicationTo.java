package com.applicationProcessingSystem.testTaskForVitaSoft.to;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ApplicationTo extends AbstractBaseTo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @NotBlank
    private String text;


}
