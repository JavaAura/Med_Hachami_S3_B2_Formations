package com.formations.service.impl;

import com.formations.exception.DatabaseOperationException;
import com.formations.exception.InvalidDataException;
import com.formations.exception.ResourceNotFoundException;
import com.formations.model.Group;
import com.formations.repository.GroupRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GroupServiceImplTest {

    @Mock
    private GroupRepository groupRepository;

    @InjectMocks
    private GroupServiceImpl groupService;

    private Group group;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        group = new Group();
        group.setId(1L);
        group.setName("Test Group");
        group.setRoomNumber("Room 101");
    }

    @Test
    void testAddGroup() {
        when(groupRepository.save(any(Group.class))).thenReturn(group);

        Group result = groupService.addGroup(group);

        assertEquals(group, result);
        verify(groupRepository, times(1)).save(group);
    }

    @Test
    void testGetGroupById() {
        when(groupRepository.findById(1L)).thenReturn(Optional.of(group));

        Group result = groupService.getGroupById(1L);

        assertEquals(group, result);
        verify(groupRepository, times(1)).findById(1L);
    }

    @Test
    void testGetGroupByIdNotFound() {
        when(groupRepository.findById(1L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> groupService.getGroupById(1L));
        assertEquals("Group with ID 1 not found", exception.getMessage());
    }

    @Test
    void testGetAllGroups() {
        when(groupRepository.findAll()).thenReturn(List.of(group));

        List<Group> result = groupService.getAllGroups();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(group, result.get(0));
        verify(groupRepository, times(1)).findAll();
    }

    @Test
    void testUpdateGroup() {
        when(groupRepository.findById(1L)).thenReturn(Optional.of(group));
        when(groupRepository.save(any(Group.class))).thenReturn(group);

        group.setName("Updated Group");
        Group updatedGroup = groupService.update(group);

        assertEquals("Updated Group", updatedGroup.getName());
        verify(groupRepository, times(1)).findById(1L);
        verify(groupRepository, times(1)).save(group);
    }

    @Test
    void testUpdateGroupNotFound() {
        when(groupRepository.findById(1L)).thenReturn(Optional.empty());

        InvalidDataException exception = assertThrows(InvalidDataException.class, () -> {
            group.setId(null);
            groupService.update(group);
        });
        assertEquals("Group ID cannot be null for update operation", exception.getMessage());
    }

    @Test
    void testDeleteGroup() {
        when(groupRepository.existsById(1L)).thenReturn(true);

        groupService.delete(group);

        verify(groupRepository, times(1)).delete(group);
    }

    @Test
    void testDeleteGroupNotFound() {
        when(groupRepository.existsById(1L)).thenReturn(false);

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> groupService.delete(group));
        assertEquals("Group with ID 1 not found for deletion", exception.getMessage());
    }

    @Test
    void testDatabaseOperationExceptionOnAddGroup() {
        when(groupRepository.save(any(Group.class))).thenThrow(new RuntimeException("Database error"));

        DatabaseOperationException exception = assertThrows(DatabaseOperationException.class, () -> groupService.addGroup(group));
        assertEquals("Failed to add group to the database", exception.getMessage());
    }

    @Test
    void testDatabaseOperationExceptionOnUpdateGroup() {
        when(groupRepository.findById(1L)).thenReturn(Optional.of(group));
        when(groupRepository.save(any(Group.class))).thenThrow(new RuntimeException("Database error"));

        DatabaseOperationException exception = assertThrows(DatabaseOperationException.class, () -> groupService.update(group));
        assertEquals("Failed to update group with ID 1", exception.getMessage());
    }

    @Test
    void testDatabaseOperationExceptionOnDeleteGroup() {
        when(groupRepository.existsById(1L)).thenReturn(true);
        doThrow(new RuntimeException("Database error")).when(groupRepository).delete(group);

        DatabaseOperationException exception = assertThrows(DatabaseOperationException.class, () -> groupService.delete(group));
        assertEquals("Failed to delete group with ID 1", exception.getMessage());
    }
}
