package com.formations.service.impl;

import com.formations.exception.business.DatabaseOperationException;
import com.formations.exception.business.InvalidDataException;
import com.formations.exception.business.ResourceNotFoundException;
import com.formations.mappers.TrainerMapper;
import com.formations.mappers.TrainingMapper;
import com.formations.model.Dto.TrainingDto;
import com.formations.model.Trainer;
import com.formations.model.Training;
import com.formations.repository.TrainerRepository;
import com.formations.repository.TrainingRepository;
import com.formations.service.TrainingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainingServiceImpl implements TrainingService {
    private static final Logger logger = LoggerFactory.getLogger(TrainingServiceImpl.class);
    private final TrainingRepository trainingRepository;
    private final TrainerRepository trainerRepository;

    @Autowired
    public TrainingServiceImpl(TrainingRepository trainingRepository, TrainerRepository trainerRepository) {
        this.trainingRepository = trainingRepository;
        this.trainerRepository = trainerRepository;
    }

    @Override
    public TrainingDto addTraining(TrainingDto trainingDto) {
        Training training = TrainingMapper.trainingMapper.toEntity(trainingDto);

        Trainer existingTrainer = this.trainerRepository.findById(trainingDto.getTrainer().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Trainer not found with id " + trainingDto.getTrainer().getId()));

        try {
            training.setTrainer(existingTrainer);
            return TrainingMapper.trainingMapper.toDto(trainingRepository.save(training));
        } catch (Exception e) {
            logger.error("Error in adding training", e.getMessage());
            throw new DatabaseOperationException("Failed to add training to the database", e);
        }

    }

    @Override
    public Training getTrainingById(Long id) {
        return this.trainingRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Training with ID " + id + " not found"));

    }

    @Override
    public List<TrainingDto> getAllTrainings() {
        List<Training> trainings = trainingRepository.findAll();


        if (trainings.isEmpty()) {
            throw new ResourceNotFoundException("No trainings found");
        }

        return trainings.stream().map(t->TrainingMapper.trainingMapper.toDto(t)).collect(Collectors.toList());

    }

    @Override
    public TrainingDto update(TrainingDto trainingDto) {
        if( trainingDto.getId() == null){
            throw new InvalidDataException("Trainer ID cannot be null for update operation");
        }
        Training existingTraining = this.trainingRepository.findById(trainingDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Training not found with id " + trainingDto.getId()));

        Training training = TrainingMapper.trainingMapper.toEntity(trainingDto);

        try {
            return TrainingMapper.trainingMapper.toDto(trainingRepository.save(training));

        } catch (Exception e) {
            throw new DatabaseOperationException("Failed to update Trainer with ID " + trainingDto.getId(), e);
        }

    }

    @Override
    public void delete(Training training) {

    }


}
