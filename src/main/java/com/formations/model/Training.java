package com.formations.model;

import com.formations.model.enums.TrainingStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "training")
public class Training {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Size(min = 5, max = 150 ,message = "Name should be bewtween 5 and 150 characters")
    @NotBlank(message = "title can not be null")
    private String title;

    @NotBlank(message = "level can not be null")
    private String level;

    @NotBlank(message = "prerequisites can not be null")
    private String prerequisites;

    @NotBlank(message = "minCapacity can not be null")
    private int minCapacity;

    @NotBlank(message = "maxCapacity can not be null")
    private int maxCapacity;

    @Future(message = "Date must be in future")
    @Column(name = "startDate", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDate startDate;

    @Future(message = "Date must be in future")
    @Column(name = "endDate", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDate endDate;

    @Setter
    @Getter
    @Enumerated(EnumType.STRING)
    private TrainingStatus status;


    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

    @Setter
    @Getter
    @OneToMany
    private List<Learner> learners;

    public @Size(min = 5, max = 150, message = "Name should be bewtween 5 and 150 characters") @NotBlank(message = "title can not be null") String getTitle() {
        return title;
    }

    public @NotBlank(message = "level can not be null") String getLevel() {
        return level;
    }

    public @NotBlank(message = "prerequisites can not be null") String getPrerequisites() {
        return prerequisites;
    }

    @NotBlank(message = "minCapacity can not be null")
    public int getMinCapacity() {
        return minCapacity;
    }

    @NotBlank(message = "maxCapacity can not be null")
    public int getMaxCapacity() {
        return maxCapacity;
    }

    public @Future(message = "Date must be in future") LocalDate getStartDate() {
        return startDate;
    }

    public @Future(message = "Date must be in future") LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(@Future(message = "Date must be in future") LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setStartDate(@Future(message = "Date must be in future") LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setMaxCapacity(@NotBlank(message = "maxCapacity can not be null") int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public void setMinCapacity(@NotBlank(message = "minCapacity can not be null") int minCapacity) {
        this.minCapacity = minCapacity;
    }

    public void setPrerequisites(@NotBlank(message = "prerequisites can not be null") String prerequisites) {
        this.prerequisites = prerequisites;
    }

    public void setLevel(@NotBlank(message = "level can not be null") String level) {
        this.level = level;
    }

    public void setTitle(@Size(min = 5, max = 150, message = "Name should be bewtween 5 and 150 characters") @NotBlank(message = "title can not be null") String title) {
        this.title = title;
    }


}
