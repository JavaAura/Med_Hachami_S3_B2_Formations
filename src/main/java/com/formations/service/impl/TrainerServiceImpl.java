package com.formations.service.impl;


import com.formations.exception.business.DatabaseOperationException;
import com.formations.exception.business.InvalidDataException;
import com.formations.exception.business.ResourceNotFoundException;
import com.formations.mappers.LearnerMapper;
import com.formations.mappers.TrainerMapper;
import com.formations.mappers.TrainingMapper;
import com.formations.model.Dto.AssignUserGroupDto;
import com.formations.model.Dto.TrainerDto;
import com.formations.model.Group;
import com.formations.model.Learner;
import com.formations.model.Trainer;
import com.formations.repository.GroupRepository;
import com.formations.repository.TrainerRepository;
import com.formations.service.TrainerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainerServiceImpl implements TrainerService {
    private static final Logger logger = LoggerFactory.getLogger(TrainerServiceImpl.class);
    private final TrainerRepository trainerRepository;
    private final GroupRepository groupRepository;

    @Autowired
    public TrainerServiceImpl(TrainerRepository trainerRepository, GroupRepository groupRepository) {
        this.trainerRepository = trainerRepository;
        this.groupRepository = groupRepository;
    }

    @Override
    public Trainer addTrainer(TrainerDto addTrainerDto) {
        Trainer trainer = TrainerMapper.trainerMapper.toEntity(addTrainerDto);

        try {
            return this.trainerRepository.save(trainer);
        } catch (Exception e) {
            logger.error("Error in adding trainer", e);
            throw new DatabaseOperationException("Failed to add trainer to the database", e);
        }

    }

    @Override
    public Trainer getTrainerById(Long id) {
        return this.trainerRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Trainer with ID " + id + " not found"));
    }

    @Override
    public List<TrainerDto> getAllTrainers() {
        List<Trainer> trainers = trainerRepository.findAll();

        if (trainers.isEmpty()) {
            throw new ResourceNotFoundException("No trainers found");
        }
        return trainers.stream().map(t-> TrainerMapper.trainerMapper.toDto(t)).collect(Collectors.toList());
    }

    @Override
    public TrainerDto update(Long id, TrainerDto trainerDto) {
        if(id == null){
            throw new InvalidDataException("Trainer ID cannot be null for update operation");
        }

        Trainer existingTrainer = this.trainerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trainer not found with id " + id));

        Trainer trainer = TrainerMapper.trainerMapper.toEntity(trainerDto);
        try {
            return TrainerMapper.trainerMapper.toDto(this.trainerRepository.save(trainer));

        } catch (Exception e) {
            throw new DatabaseOperationException("Failed to update Trainer with ID " + id, e);
        }
    }

    @Override
    public void delete(Trainer trainer) {
        if (!trainerRepository.existsById(trainer.getId())) {
            throw new ResourceNotFoundException("Trainer with ID " + trainer.getId() + " not found for deletion");
        }

        try {
            trainerRepository.delete(trainer);
        } catch (Exception e) {
            throw new DatabaseOperationException("Failed to delete trainer with ID " + trainer.getId(), e);
        }
    }

    @Override
    public TrainerDto assignLearnerToGroup(AssignUserGroupDto assignUserGroupDto) {
        Long trainerId = Long.parseLong(assignUserGroupDto.getUserId());
        Long groupId = Long.parseLong(assignUserGroupDto.getGroupId());

        Trainer trainer = trainerRepository.findById(trainerId)
                .orElseThrow(() -> new ResourceNotFoundException("Trainer not found with id " + trainerId));

        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new ResourceNotFoundException("Group not found with id " + groupId));

        trainer.setGroup(group);

        try {
            Trainer t = this.trainerRepository.save(trainer);
            return TrainerMapper.trainerMapper.toDto(t);
        } catch (Exception e) {
            logger.error("Error in assigning trainer to a group", e);
            throw new DatabaseOperationException("Failed to assigning trainer to the database", e);
        }
    }
}
