package com.formations.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "learner")
public class Learner extends Person implements Serializable {

    @NotBlank(message = "Level should not be null")
    private String level;

    @ManyToOne
    @JoinColumn(name = "training_id")
    private Training training;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;


    public void setGroup(Group group) {
        this.group = group;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public void setLevel(@NotBlank(message = "Level should not be null") String level) {
        this.level = level;
    }



    public Learner() {
        super("", "", ""); // Consider using a more meaningful default if applicable
        this.level = ""; // Initialize level appropriately if needed
    }

    public Learner(String firstName, String lastName, String email) {
        super(firstName, lastName, email);
    }

    public @NotBlank(message = "Level should not be null") String getLevel() {
        return level;
    }

    public Training getTraining() {
        return training;
    }

    public Group getGroup() {
        return group;
    }

    public Learner(String firstName, String lastName, String email, String level) {
        super(firstName, lastName, email);
        this.level = level;
    }





}
