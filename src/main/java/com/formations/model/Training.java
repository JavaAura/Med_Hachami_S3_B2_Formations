package com.formations.model;

import com.formations.model.enums.TrainingStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "training")
public class Training {



    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;


    @Size(min = 5, max = 150 ,message = "Name should be bewtween 5 and 150 characters")
    @NotBlank(message = "title can not be null")
    private String title;

    @NotBlank(message = "level can not be null")
    private String level;

    @NotNull(message = "prerequisites can not be null")
    private String prerequisites;

    @Min(message = "Minimum capacity cannot be null", value = 10L)
    private int minCapacity;

    @Max(message = "Maximum capacity cannot be null", value = 30L)
    private int maxCapacity;

    @Future(message = "Date must be in future")
    @Column(name = "startDate", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDate startDate;

    @Future(message = "Date must be in future")
    @Column(name = "endDate", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDate endDate;


    @Enumerated(EnumType.STRING)
    private TrainingStatus status;


    @ManyToOne
    @JoinColumn(name = "trainer_id", referencedColumnName = "id")
    private Trainer trainer;


    @OneToMany
    private List<Learner> learners;

    public Training(){}

    public @Size(min = 5, max = 150, message = "Name should be bewtween 5 and 150 characters") @NotBlank(message = "title can not be null") String getTitle() {
        return title;
    }

    public @NotBlank(message = "level can not be null") String getLevel() {
        return level;
    }

    public @NotBlank(message = "prerequisites can not be null") String getPrerequisites() {
        return prerequisites;
    }

    @NotNull(message = "minCapacity can not be null")
    public int getMinCapacity() {
        return minCapacity;
    }

    @NotNull(message = "maxCapacity can not be null")
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

    public void setMaxCapacity(@NotNull(message = "maxCapacity can not be null") int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public void setMinCapacity(@NotNull(message = "minCapacity can not be null") int minCapacity) {
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

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStatus(TrainingStatus status) {
        this.status = status;
    }

    public void setLearners(List<Learner> learners) {
        this.learners = learners;
    }



    public Trainer getTrainer() {
        return trainer;
    }

    public TrainingStatus getStatus() {
        return status;
    }

    public List<Learner> getLearners() {
        return learners;
    }

    public Long getId() {
        return id;
    }
}
