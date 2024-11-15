package com.formations.model.Dto;

import com.formations.model.Group;
import com.formations.model.Training;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TrainerDto {


    private Long id;
    @NotBlank(message = "first name should not be null")
    @Size(min = 2, max = 50 ,message = "Name should be bewtween 2 and 50 characters")
    private String firstName;

    @NotBlank(message = "first name should not be null")
    @Size(min = 2, max = 50 ,message = "Name should be bewtween 2 and 50 characters")
    private String lastName;

    @NotBlank(message = "email should not be null")
    @Email(message = "this must be an email")
    private String email;

    @NotBlank(message = "specialty should not be null")
    private String specialty;

    private Training training;
    private Group group;


    public TrainerDto(){

    }

    public TrainerDto(String firstName , String lastName , String email , String specialty){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.specialty = specialty;
    }

    public @NotBlank(message = "first name should not be null") @Size(min = 2, max = 50, message = "Name should be bewtween 2 and 50 characters") String getFirstName() {
        return firstName;
    }

    public @NotBlank(message = "first name should not be null") @Size(min = 2, max = 50, message = "Name should be bewtween 2 and 50 characters") String getLastName() {
        return lastName;
    }

    public @NotBlank(message = "email should not be null") @Email(message = "this must be an email") String getEmail() {
        return email;
    }

    public @NotBlank(message = "specialty should not be null") String getSpecialty() {
        return specialty;
    }

    public Training getTraining() {
        return training;
    }

    public Group getGroup() {
        return group;
    }

    public void setFirstName(@NotBlank(message = "first name should not be null") @Size(min = 2, max = 50, message = "Name should be bewtween 2 and 50 characters") String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(@NotBlank(message = "first name should not be null") @Size(min = 2, max = 50, message = "Name should be bewtween 2 and 50 characters") String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(@NotBlank(message = "email should not be null") @Email(message = "this must be an email") String email) {
        this.email = email;
    }

    public void setSpecialty(@NotBlank(message = "specialty should not be null") String specialty) {
        this.specialty = specialty;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
