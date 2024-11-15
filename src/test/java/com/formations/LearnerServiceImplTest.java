package com.formations;


import com.formations.exception.business.InvalidDataException;
import com.formations.exception.business.ResourceNotFoundException;
import com.formations.model.Dto.LearnerDto;
import com.formations.model.Learner;
import com.formations.repository.LearnerRepository;
import com.formations.service.impl.LearnerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class LearnerServiceImplTest {

    @Mock
    private LearnerRepository learnerRepository;

    @InjectMocks
    private LearnerServiceImpl learnerService;

    private Learner learner;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        learner = new Learner();
        learner.setId(1L);
        learner.setFirstName("John");
        learner.setLastName("Doe");
        learner.setEmail("john.doe@example.com");
        learner.setLevel("Beginner");
        // Set other fields as necessary
    }

    @Test
    void testAddLearner() {
        Learner savedLearner = new Learner();
        savedLearner.setId(1L);
        savedLearner.setFirstName("John");
        savedLearner.setLastName("Doe");
        savedLearner.setEmail("john.doe@example.com");
        savedLearner.setLevel("Beginner");

        when(learnerRepository.save(any(Learner.class))).thenReturn(savedLearner);

        Learner result = learnerService.addLearner(new LearnerDto(learner.getFirstName(),learner.getLastName(),learner.getEmail(),learner.getLevel()));

        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
        assertEquals("john.doe@example.com", result.getEmail());
        assertEquals("Beginner", result.getLevel());
        verify(learnerRepository, times(1)).save(any(Learner.class));
    }

    @Test
    void testGetLearnerById() {
        when(learnerRepository.findById(1L)).thenReturn(Optional.of(learner));

        Learner foundLearner = learnerService.getLearnerById(1L);

        assertNotNull(foundLearner);
        assertEquals("John", foundLearner.getFirstName());
        verify(learnerRepository, times(1)).findById(1L);
    }

    @Test
    void testGetLearnerByIdNotFound() {
        when(learnerRepository.findById(1L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            learnerService.getLearnerById(1L);
        });

        assertEquals("Learner with ID 1 not found", exception.getMessage());
    }

   /* @Test
    void testUpdateLearner() {
        when(learnerRepository.findById(1L)).thenReturn(Optional.of(learner));
        when(learnerRepository.save(any(Learner.class))).thenReturn(learner);

        learner.setFirstName("Jane");
        Learner updatedLearner = learnerService.update(learner);

        assertEquals("Jane", updatedLearner.getFirstName());
        verify(learnerRepository, times(1)).save(learner);
    }

    @Test
    void testUpdateLearnerNotFound() {
        when(learnerRepository.findById(1L)).thenReturn(Optional.empty());

        InvalidDataException exception = assertThrows(InvalidDataException.class, () -> {
            learner.setId(null);
            learnerService.update(learner);
        });

        assertEquals("Learner ID cannot be null for update operation", exception.getMessage());
    }*/

    @Test
    void testDeleteLearner() {
        when(learnerRepository.existsById(1L)).thenReturn(true);

        learnerService.delete(learner);

        verify(learnerRepository, times(1)).delete(learner);
    }

    @Test
    void testDeleteLearnerNotFound() {
        when(learnerRepository.existsById(1L)).thenReturn(false);

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            learnerService.delete(learner);
        });

        assertEquals("Learner with ID 1 not found for deletion", exception.getMessage());
    }
}
