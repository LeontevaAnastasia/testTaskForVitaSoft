package com.applicationProcessingSystem.testTaskForVitaSoft.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "applications")
public class Application extends AbstractBaseEntity {

    @Column(name = "text")
    @NotBlank
    private String text;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status", columnDefinition = "DRAFT")
    private ApplicationStatus status;

    @Column(name = "date_time", nullable = false, columnDefinition = "timestamp default now()", updatable = false)
    private LocalDateTime dateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    public Application (Integer id, String text, ApplicationStatus status, LocalDateTime dateTime, User user){
        super(id);
        this.text=text;
        this.status = status;
        this.dateTime = dateTime;
        this.user = user;

    }
}
