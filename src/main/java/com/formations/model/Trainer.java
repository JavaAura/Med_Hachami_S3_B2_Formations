package com.formations.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "trainer")
public class Trainer extends Person implements Serializable {

    @NotBlank(message = "specialty should not be null")
    private String specialty;

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


    public Trainer(){
        super("","","");
    }

    public Trainer(String firstName, String lastName, String email) {
        super(firstName, lastName, email);
    }

    public Trainer(String firstName ,String lastName ,String email,String specialty) {
        super(firstName,lastName,email);
        this.specialty = specialty;
    }

    public @NotBlank(message = "specialty should not be null") String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(@NotBlank(message = "specialty should not be null") String specialty) {
        this.specialty = specialty;
    }
}
