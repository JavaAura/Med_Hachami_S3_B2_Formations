package com.formations.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name should not be null")
    @Size(min = 2, max = 50, message = "Name should be between 2 and 50 characters")

    private String firstName;

    @NotBlank(message = "Last name should not be null")
    @Size(min = 2, max = 50, message = "Name should be between 2 and 50 characters")

    private String lastName;

    @NotBlank(message = "Email should not be null")
    @Email(message = "This must be an email")

    private String email;

    public Person(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Long getId(){
        return this.id;
    }

    public @NotBlank(message = "First name should not be null") @Size(min = 2, max = 50, message = "Name should be between 2 and 50 characters") String getFirstName() {
        return firstName;
    }

    public @NotBlank(message = "Last name should not be null") @Size(min = 2, max = 50, message = "Name should be between 2 and 50 characters") String getLastName() {
        return lastName;
    }

    public @NotBlank(message = "Email should not be null") @Email(message = "This must be an email") String getEmail() {
        return email;
    }

    public void setId(Long id){
        this.id = id;
    }

    public void setFirstName(@NotBlank(message = "First name should not be null") @Size(min = 2, max = 50, message = "Name should be between 2 and 50 characters") String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(@NotBlank(message = "Last name should not be null") @Size(min = 2, max = 50, message = "Name should be between 2 and 50 characters") String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(@NotBlank(message = "Email should not be null") @Email(message = "This must be an email") String email) {
        this.email = email;
    }
}
