package com.formations.service.impl;

import com.formations.exception.DatabaseOperationException;
import com.formations.exception.DuplicateResourceException;
import com.formations.exception.InvalidDataException;
import com.formations.exception.ResourceNotFoundException;
import com.formations.model.Group;
import com.formations.repository.GroupRepository;
import com.formations.service.GroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {
    private static final Logger logger = LoggerFactory.getLogger(GroupServiceImpl.class);
    private final GroupRepository groupRepository;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }


    @Override
    public boolean addGroup(Group group) {
        boolean result = false;
        if(groupRepository.existsByName(group.getName())){
            throw new DuplicateResourceException("Group with name " + group.getName() + " already exists");
        }
        try {
            this.groupRepository.save(group);
            result = true;
        } catch (Exception e) {
            logger.error("Error in adding group", e);
            throw new DatabaseOperationException("Failed to add group to the database", e);
        }

        return result;
    }

    @Override
    public Group getGroupById(Long id) {
        return groupRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Group with ID " + id + " not found"));
    }

    @Override
    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    @Override
    public void update(Group group) {
        if (group.getId() == null) {
            throw new InvalidDataException("Group ID cannot be null for update operation");
        }

        if (!groupRepository.existsById(group.getId())) {
            throw new ResourceNotFoundException("Group with ID " + group.getId() + " not found for update");
        }

        try {
            groupRepository.save(group);
        } catch (Exception e) {
            throw new DatabaseOperationException("Failed to update group with ID " + group.getId(), e);
        }
    }

    @Override
    public void delete(Group group) {
        if (!groupRepository.existsById(group.getId())) {
            throw new ResourceNotFoundException("Group with ID " + group.getId() + " not found for deletion");
        }

        try {
            groupRepository.delete(group);
        } catch (Exception e) {
            throw new DatabaseOperationException("Failed to delete group with ID " + group.getId(), e);
        }
    }
}