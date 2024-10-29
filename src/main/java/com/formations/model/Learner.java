package com.formations.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "learner")
public class Learner extends Person implements Serializable {


    @NotBlank(message = "level should not be null")
    private String level;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "training_id")
    private Training training;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;



    public Learner(){
        super("","","");
    }

    public Learner(String firstName, String lastName, String email) {
        super(firstName, lastName, email);
    }

    public Learner(String firstName ,String lastName ,String email,String level) {
        super(firstName,lastName,email);
        this.level = level;
    }

    public void setLevel(@NotBlank(message = "level should not be null") String level) {
        this.level = level;
    }

    public @NotBlank(message = "level should not be null") String getLevel() {
        return level;
    }
}
