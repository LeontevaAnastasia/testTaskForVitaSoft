package com.applicationProcessingSystem.testTaskForVitaSoft.to;


import com.applicationProcessingSystem.testTaskForVitaSoft.HasId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractBaseTo implements HasId {

    @JsonIgnore
    protected Integer id;
}
