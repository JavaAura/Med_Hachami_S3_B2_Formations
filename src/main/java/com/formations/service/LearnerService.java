package com.formations.service;

import com.formations.model.Dto.AssignUserGroupDto;
import com.formations.model.Dto.AssignUserTrainingDto;
import com.formations.model.Dto.LearnerDto;
import com.formations.model.Learner;

import java.util.List;

public interface LearnerService {
    public Learner addLearner(LearnerDto addLearnerDto);
    public Learner getLearnerById(Long id);
    public List<LearnerDto> getAllLearners();
    public LearnerDto update(Long id , LearnerDto learnerDto);
    public void delete(Learner learner);
    public LearnerDto assignLearnerToGroup(AssignUserGroupDto assignUserGroupDto);
    public LearnerDto assignLearnerToTraining(AssignUserTrainingDto assignUserTrainingDto);

}
