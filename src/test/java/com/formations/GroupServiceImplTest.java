package com.formations;
import com.formations.exception.DatabaseOperationException;
import com.formations.exception.DuplicateResourceException;
import com.formations.exception.InvalidDataException;
import com.formations.exception.ResourceNotFoundException;
import com.formations.model.Group;
import com.formations.repository.GroupRepository;
import com.formations.service.impl.GroupServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class GroupServiceImplTest {
    @Mock
    private GroupRepository groupRepository;

    @InjectMocks
    private GroupServiceImpl groupService;

    private Group group;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        group = new Group();
        group.setId(1L);
        group.setName("Test Group");
    }

    @Test
    public void testAddGroup_Success() {
        when(groupRepository.existsByName(group.getName())).thenReturn(false);
        when(groupRepository.save(any(Group.class))).thenReturn(group);

        boolean result = groupService.addGroup(group);

        assertTrue(result);
        verify(groupRepository, times(1)).save(group);
    }

    @Test
    public void testAddGroup_DuplicateResourceException() {
        when(groupRepository.existsByName(group.getName())).thenReturn(true);

        assertThrows(DuplicateResourceException.class, () -> groupService.addGroup(group));
        verify(groupRepository, never()).save(any(Group.class));
    }

    @Test
    public void testAddGroup_DatabaseOperationException() {
        when(groupRepository.existsByName(group.getName())).thenReturn(false);
        when(groupRepository.save(any(Group.class))).thenThrow(new RuntimeException("Database error"));

        Exception exception = assertThrows(DatabaseOperationException.class, () -> groupService.addGroup(group));
        assertEquals("Failed to add group to the database", exception.getMessage());
        verify(groupRepository, times(1)).save(group);
    }

    @Test
    public void testGetGroupById_Success() {
        when(groupRepository.findById(group.getId())).thenReturn(Optional.of(group));

        Group result = groupService.getGroupById(group.getId());

        assertNotNull(result);
        assertEquals(group.getId(), result.getId());
    }

    @Test
    public void testGetGroupById_ResourceNotFoundException() {
        when(groupRepository.findById(group.getId())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> groupService.getGroupById(group.getId()));
    }

    @Test
    public void testGetAllGroups() {
        when(groupRepository.findAll()).thenReturn(List.of(group));

        List<Group> groups = groupService.getAllGroups();

        assertNotNull(groups);
        assertEquals(1, groups.size());
        assertEquals(group.getId(), groups.get(0).getId());
    }

    @Test
    public void testUpdate_Success() {
        when(groupRepository.existsById(group.getId())).thenReturn(true);
        when(groupRepository.save(any(Group.class))).thenReturn(group);

        assertDoesNotThrow(() -> groupService.update(group));
        verify(groupRepository, times(1)).save(group);
    }

    @Test
    public void testUpdate_InvalidDataException() {
        group.setId(null);

        assertThrows(InvalidDataException.class, () -> groupService.update(group));
        verify(groupRepository, never()).save(any(Group.class));
    }

    @Test
    public void testUpdate_ResourceNotFoundException() {
        when(groupRepository.existsById(group.getId())).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> groupService.update(group));
        verify(groupRepository, never()).save(any(Group.class));
    }

    @Test
    public void testUpdate_DatabaseOperationException() {
        when(groupRepository.existsById(group.getId())).thenReturn(true);
        when(groupRepository.save(any(Group.class))).thenThrow(new RuntimeException("Database error"));

        Exception exception = assertThrows(DatabaseOperationException.class, () -> groupService.update(group));
        assertEquals("Failed to update group with ID " + group.getId(), exception.getMessage());
        verify(groupRepository, times(1)).save(group);
    }

    @Test
    public void testDelete_Success() {
        when(groupRepository.existsById(group.getId())).thenReturn(true);

        assertDoesNotThrow(() -> groupService.delete(group));
        verify(groupRepository, times(1)).delete(group);
    }

    @Test
    public void testDelete_ResourceNotFoundException() {
        when(groupRepository.existsById(group.getId())).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> groupService.delete(group));
        verify(groupRepository, never()).delete(any(Group.class));
    }

    @Test
    public void testDelete_DatabaseOperationException() {
        when(groupRepository.existsById(group.getId())).thenReturn(true);
        doThrow(new RuntimeException("Database error")).when(groupRepository).delete(any(Group.class));

        Exception exception = assertThrows(DatabaseOperationException.class, () -> groupService.delete(group));
        assertEquals("Failed to delete group with ID " + group.getId(), exception.getMessage());
        verify(groupRepository, times(1)).delete(group);
    }
}
