package com.formations.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Person {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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



    public Person(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public @NotBlank(message = "email should not be null") @Email(message = "this must be an email") String getEmail() {
        return email;
    }

    public @NotBlank(message = "first name should not be null") @Size(min = 2, max = 50, message = "Name should be bewtween 2 and 50 characters") String getLastName() {
        return lastName;
    }

    public @NotBlank(message = "first name should not be null") @Size(min = 2, max = 50, message = "Name should be bewtween 2 and 50 characters") String getFirstName() {
        return firstName;
    }

    public void setEmail(@NotBlank(message = "email should not be null") @Email(message = "this must be an email") String email) {
        this.email = email;
    }

    public void setLastName(@NotBlank(message = "first name should not be null") @Size(min = 2, max = 50, message = "Name should be bewtween 2 and 50 characters") String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(@NotBlank(message = "first name should not be null") @Size(min = 2, max = 50, message = "Name should be bewtween 2 and 50 characters") String firstName) {
        this.firstName = firstName;
    }

}
