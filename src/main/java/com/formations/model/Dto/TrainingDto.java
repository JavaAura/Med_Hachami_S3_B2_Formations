package com.formations.model.Dto;

import com.formations.model.Training;
import com.formations.model.enums.TrainingStatus;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

public class TrainingDto {

    private Long id;

    @Size(min = 5, max = 150, message = "Title should be between 5 and 150 characters")
    @NotBlank(message = "Title cannot be null or blank")
    private String title;

    @NotBlank(message = "Level cannot be null or blank")
    private String level;

    @NotBlank(message = "Prerequisites cannot be null or blank")
    private String prerequisites;

    @Min(message = "Minimum capacity cannot be null", value = 10L)
    private Integer minCapacity;

    @Max(message = "Maximum capacity cannot be null", value = 30L)
    private Integer maxCapacity;

    @Future(message = "Start date must be in the future")
    private LocalDate startDate;

    @Future(message = "End date must be in the future")
    private LocalDate endDate;


    private TrainingStatus status;



    private TrainerDto trainer;

    private List<Long> learnerIds;

    public @Size(min = 5, max = 150, message = "Title should be between 5 and 150 characters") @NotBlank(message = "Title cannot be null or blank") String getTitle() {
        return title;
    }

    public @NotBlank(message = "Level cannot be null or blank") String getLevel() {
        return level;
    }

    public @NotBlank(message = "Prerequisites cannot be null or blank") String getPrerequisites() {
        return prerequisites;
    }

    public @NotNull(message = "Minimum capacity cannot be null") Integer getMinCapacity() {
        return minCapacity;
    }

    public @NotNull(message = "Maximum capacity cannot be null") Integer getMaxCapacity() {
        return maxCapacity;
    }

    public @Future(message = "Start date must be in the future") LocalDate getStartDate() {
        return startDate;
    }

    public @Future(message = "End date must be in the future") LocalDate getEndDate() {
        return endDate;
    }

    public TrainingStatus getStatus() {
        return status;
    }



    public List<Long> getLearnerIds() {
        return learnerIds;
    }

    public void setTitle(@Size(min = 5, max = 150, message = "Title should be between 5 and 150 characters") @NotBlank(message = "Title cannot be null or blank") String title) {
        this.title = title;
    }

    public void setLevel(@NotBlank(message = "Level cannot be null or blank") String level) {
        this.level = level;
    }

    public void setPrerequisites(@NotBlank(message = "Prerequisites cannot be null or blank") String prerequisites) {
        this.prerequisites = prerequisites;
    }

    public void setMinCapacity(@NotNull(message = "Minimum capacity cannot be null") Integer minCapacity) {
        this.minCapacity = minCapacity;
    }

    public void setMaxCapacity(@NotNull(message = "Maximum capacity cannot be null") Integer maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public void setStartDate(@Future(message = "Start date must be in the future") LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(@Future(message = "End date must be in the future") LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setStatus(TrainingStatus status) {
        this.status = status;
    }


    public void setLearnerIds(List<Long> learnerIds) {
        this.learnerIds = learnerIds;
    }

    public TrainerDto getTrainer() {
        return trainer;
    }

    public void setTrainer(TrainerDto trainer) {
        this.trainer = trainer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
