package com.formations.model.Dto;

import com.formations.model.Group;
import com.formations.model.Training;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
public class LearnerDto {


    @NotBlank(message = "first name should not be null")
    @Size(min = 2, max = 50 ,message = "Name should be bewtween 2 and 50 characters")
    private String firstName;

    @NotBlank(message = "first name should not be null")
    @Size(min = 2, max = 50 ,message = "Name should be bewtween 2 and 50 characters")
    private String lastName;

    @NotBlank(message = "email should not be null")
    @Email(message = "this must be an email")
    private String email;

    @NotBlank(message = "level should not be null")
    private String level;

    private Training training;
    private Group group;


    public LearnerDto(){

    }

    public LearnerDto(String firstName , String lastName , String email , String level){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.level = level;
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

    public @NotBlank(message = "level should not be null") String getLevel() {
        return level;
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

    public void setLevel(@NotBlank(message = "level should not be null") String level) {
        this.level = level;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "addLearnerDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", level='" + level + '\'' +
                '}';
    }
}
