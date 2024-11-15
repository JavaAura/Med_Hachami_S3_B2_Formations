package com.formations.service;


import com.formations.model.Dto.AssignUserGroupDto;
import com.formations.model.Dto.LearnerDto;
import com.formations.model.Dto.TrainerDto;
import com.formations.model.Learner;
import com.formations.model.Trainer;

import java.util.List;

public interface TrainerService {
    public Trainer addTrainer(TrainerDto addTrainerDto);
    public Trainer getTrainerById(Long id);
    public List<TrainerDto> getAllTrainers();
    public TrainerDto update(Long id , TrainerDto trainerDto);
    public void delete(Trainer trainer);
    public TrainerDto assignLearnerToGroup(AssignUserGroupDto assignUserGroupDto);
}
