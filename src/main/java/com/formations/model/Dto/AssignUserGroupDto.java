package com.formations.model.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
public class AssignUserGroupDto {

    @NotBlank(message = "userId should not be null")
    private String userId;

    @NotBlank(message = "groupId should not be null")
    private String groupId;

    public @NotBlank(message = "userId should not be null") String getUserId() {
        return userId;
    }

    public @NotBlank(message = "groupId should not be null") String getGroupId() {
        return groupId;
    }

    public void setUserId(@NotBlank(message = "userId should not be null") String userId) {
        this.userId = userId;
    }

    public void setGroupId(@NotBlank(message = "groupId should not be null") String groupId) {
        this.groupId = groupId;
    }
}
