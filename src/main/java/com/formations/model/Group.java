package com.formations.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "\"groups\"")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "name should not be null")
    @Size(min = 2, max = 50 ,message = "Name should be bewtween 2 and 50 characters")
    private String name;

    @NotEmpty(message = "Room number should not be null")
    private String roomNumber;

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public @NotEmpty(message = "name should not be null") @Size(min = 2, max = 50, message = "Name should be bewtween 2 and 50 characters") String getName() {
        return name;
    }

    public void setName(@NotEmpty(message = "name should not be null") @Size(min = 2, max = 50, message = "Name should be bewtween 2 and 50 characters") String name) {
        this.name = name;
    }

    public void setRoomNumber(@NotEmpty(message = "Room number should not be null") String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public @NotEmpty(message = "Room number should not be null") String getRoomNumber() {
        return roomNumber;
    }


}
