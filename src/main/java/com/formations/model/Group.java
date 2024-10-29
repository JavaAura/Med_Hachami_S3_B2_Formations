package com.formations.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "group")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "name should not be null")
    @Size(min = 2, max = 50 ,message = "Name should be bewtween 2 and 50 characters")
    private String name;

    @NotBlank
    private String roomNumber;
}
