package com.formations.model.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
public class AssignUserTrainingDto {
    @NotBlank(message = "userId should not be null")
    private String userId;

    @NotBlank(message = "trainingId should not be null")
    private String trainingId;

    public @NotBlank(message = "userId should not be null") String getUserId() {
        return userId;
    }

    public @NotBlank(message = "trainingId should not be null") String getTrainingId() {
        return trainingId;
    }

    public void setUserId(@NotBlank(message = "userId should not be null") String userId) {
        this.userId = userId;
    }

    public void setTrainingIdId(@NotBlank(message = "trainingId should not be null") String trainingId) {
        this.trainingId = trainingId;
    }
}
