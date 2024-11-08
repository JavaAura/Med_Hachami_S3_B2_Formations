package com.formations.service.impl;

import com.formations.exception.business.DatabaseOperationException;
import com.formations.exception.business.InvalidDataException;
import com.formations.exception.business.ResourceNotFoundException;
import com.formations.mappers.LearnerMapper;
import com.formations.model.Dto.AssignUserGroupDto;
import com.formations.model.Dto.AssignUserTrainingDto;
import com.formations.model.Dto.LearnerDto;
import com.formations.model.Group;
import com.formations.model.Learner;
import com.formations.model.Training;
import com.formations.repository.GroupRepository;
import com.formations.repository.LearnerRepository;
import com.formations.service.LearnerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LearnerServiceImpl implements LearnerService {

    private static final Logger logger = LoggerFactory.getLogger(LearnerServiceImpl.class);
    private final LearnerRepository learnerRepository;
    private final GroupRepository groupRepository;


    @Autowired
    public LearnerServiceImpl(LearnerRepository learnerRepository,GroupRepository groupRepository) {
        this.learnerRepository = learnerRepository;
        this.groupRepository = groupRepository;
    }


    @Override
    public Learner addLearner(LearnerDto addLearnerDto) {

        Learner learner = LearnerMapper.learnerMapper.toEntity(addLearnerDto);

        try {
            return this.learnerRepository.save(learner);
        } catch (Exception e) {
            logger.error("Error in adding learner", e);
            throw new DatabaseOperationException("Failed to add learner to the database", e);
        }
    }

    @Override
    public Learner getLearnerById(Long id) {
        return this.learnerRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Learner with ID " + id + " not found"));
    }

    @Override
    public List<LearnerDto> getAllLearners() {
        List<Learner> learners = learnerRepository.findAll();
        if (learners.isEmpty()) {
            throw new ResourceNotFoundException("No learners found");
        }
        return learners.stream().map(l->LearnerMapper.learnerMapper.toDto(l)).collect(Collectors.toList());

    }

    @Override
    public LearnerDto update(Long id ,LearnerDto learnerDto) {
        if (id == null) {
            throw new InvalidDataException("Learner ID cannot be null for update operation");
        }

        Learner existingLearner = this.learnerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Learner not found with id " + id));

        Learner learner = LearnerMapper.learnerMapper.toEntity(learnerDto);
        existingLearner.setEmail(learner.getEmail());
        existingLearner.setFirstName(learnerDto.getFirstName());
        existingLearner.setLastName(learner.getLastName());
        existingLearner.setLevel(learner.getLevel());
        if(learnerDto.getGroup() != null){
            existingLearner.setGroup(learner.getGroup());
        }
        if(learnerDto.getTraining() !=null){
            existingLearner.setTraining(learner.getTraining());
        }
        try {
            return LearnerMapper.learnerMapper.toDto(this.learnerRepository.save(existingLearner));

        } catch (Exception e) {
            throw new DatabaseOperationException("Failed to update learner with ID " + id, e);
        }
    }

    @Override
    public void delete(Learner learner) {
        if (!learnerRepository.existsById(learner.getId())) {
            throw new ResourceNotFoundException("Learner with ID " + learner.getId() + " not found for deletion");
        }

        try {
            learnerRepository.delete(learner);
        } catch (Exception e) {
            throw new DatabaseOperationException("Failed to delete learner with ID " + learner.getId(), e);
        }
    }

    @Override
    public LearnerDto assignLearnerToGroup(AssignUserGroupDto assignUserGroupDto) {

        Long learnerId = Long.parseLong(assignUserGroupDto.getUserId());
        Long groupId = Long.parseLong(assignUserGroupDto.getGroupId());

       Learner learner = learnerRepository.findById(learnerId)
               .orElseThrow(() -> new ResourceNotFoundException("Learner not found with id " + learnerId));

       Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new ResourceNotFoundException("Group not found with id " + groupId));

       learner.setGroup(group);

        try {
                Learner l = this.learnerRepository.save(learner);
            return LearnerMapper.learnerMapper.toDto( l);
        } catch (Exception e) {
            logger.error("Error in assigning learner to a group", e);
            throw new DatabaseOperationException("Failed to assigning learner to the database", e);
        }

    }

    @Override
    public LearnerDto assignLearnerToTraining(AssignUserTrainingDto assignUserTrainingDto) {
        Long learnerId = Long.parseLong(assignUserTrainingDto.getUserId());
        Long trainingId = Long.parseLong(assignUserTrainingDto.getTrainingId());

        Learner learner = learnerRepository.findById(learnerId)
                .orElseThrow(() -> new ResourceNotFoundException("Learner not found with id " + learnerId));


//        Training training = learnerRepository.findById(learnerId)
//                .orElseThrow(() -> new ResourceNotFoundException("Learner not found with id " + learnerId));


        return new LearnerDto();
    }
}
