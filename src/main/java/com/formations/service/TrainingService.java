package com.formations.service;

import com.formations.model.Dto.TrainingDto;
import com.formations.model.Group;
import com.formations.model.Trainer;
import com.formations.model.Training;

import java.util.List;

public interface TrainingService {
    public TrainingDto addTraining(TrainingDto trainingDto);
    public Training getTrainingById(Long id);
    public List<TrainingDto> getAllTrainings();
    public TrainingDto update(TrainingDto TrainingDto);
    public void delete(Training training);
}
