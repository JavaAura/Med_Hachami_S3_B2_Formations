package com.formations.controller;


import com.formations.exception.business.DatabaseOperationException;
import com.formations.exception.business.ResourceNotFoundException;
import com.formations.mappers.LearnerMapper;
import com.formations.mappers.TrainerMapper;
import com.formations.model.Dto.AssignUserGroupDto;
import com.formations.model.Dto.AssignUserTrainingDto;
import com.formations.model.Dto.LearnerDto;
import com.formations.model.Learner;
import com.formations.model.Trainer;
import com.formations.service.LearnerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/learner")
public class LearnerController {
    private static final Logger logger = LoggerFactory.getLogger(LearnerController.class);
    private final LearnerService learnerService;

    @Autowired
    public LearnerController(LearnerService learnerService) {
        this.learnerService = learnerService;
    }

    @GetMapping("/all")
    @Operation(summary = "Get all learners", description = "Retrieves a list of all learners")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of learners",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Learner.class))),
            @ApiResponse(responseCode = "404", description = "Learners not found")
    })
    public ResponseEntity<List<LearnerDto>> getAllLearners() {
        List<LearnerDto> learners = learnerService.getAllLearners();

        return ResponseEntity.ok(learners);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get learner by id", description = "Retrieve a specific Learner by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of Learner",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Learner.class))),
            @ApiResponse(responseCode = "404", description = "Learner not found")
    })
    public ResponseEntity<?> getLearnerId(@PathVariable("id") Long id) {
        try {
            Learner learner = learnerService.getLearnerById(id);

            return ResponseEntity.status(HttpStatus.OK).body(LearnerMapper.learnerMapper.toDto(learner));


        } catch (ResourceNotFoundException e) {
            logger.error("Learner not found: {}", e.getMessage());
            return new ResponseEntity<String>("Learner Not found",HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("An error occurred while fetching the learner", e);
            throw new DatabaseOperationException("Failed to get the learner from the database", e);
        }
    }

    @PostMapping("/add")
    @Operation(summary = "Add a new Learner", description = "Creates a new Learner")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Learner created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Learner.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<Learner> addLearner(@Valid @RequestBody LearnerDto learnerDto) {

       Learner createdLearner = learnerService.addLearner(learnerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLearner);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update an existing Learner", description = "Updates the details of a Learner")
    @ApiResponse(responseCode = "200", description = "Learner updated successfully",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Learner.class)))
    @ApiResponse(responseCode = "404", description = "Learner not found")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    public ResponseEntity<LearnerDto> updateLearner(@PathVariable("id") Long id,  @Valid  @RequestBody LearnerDto learnerDto) {
        LearnerDto updatedLearner = learnerService.update(id,learnerDto);
        return ResponseEntity.ok(updatedLearner);
    }

    //delete
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a Learner", description = "Deletes a Learner by ID")
    @ApiResponse(responseCode = "204", description = "Learner deleted successfully")
    @ApiResponse(responseCode = "404", description = "Learner not found")
    public ResponseEntity<Void> DeleteLearner(@PathVariable Long id) {
        Learner l = new Learner();
        l.setId(id);
        learnerService.delete(l);
        return ResponseEntity.noContent().build();
    }

    //Asign learner to formation

    //Assign learner to group
    @PostMapping("/assignLearnerToGroup")
    @Operation(summary = "Assign learner to group", description = "Assigns a learner to a specific group based on learner ID and group ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Learner assigned to group successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = LearnerDto.class))),
            @ApiResponse(responseCode = "404", description = "Learner or Group not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    public ResponseEntity<?> assignLearnerToGroup(@Valid @RequestBody AssignUserGroupDto assignUserDto) {
        try {
            LearnerDto assignedLearner = learnerService.assignLearnerToGroup(assignUserDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(assignedLearner);
        } catch (ResourceNotFoundException e) {
            logger.error("Resource not found: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            logger.error("An error occurred while assigning the learner to the group", e);
            throw new DatabaseOperationException("Failed to assign learner to the database", e);
        }
    }

    //Asign learner to formation

    @PostMapping("/assignLearnerToTraining")
    @Operation(summary = "Assign learner to training", description = "Assigns a learner to a specific training based on learner ID and group ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Learner assigned to training successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = LearnerDto.class))),
            @ApiResponse(responseCode = "404", description = "Learner or training not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    public ResponseEntity<?> assignLearnerToTraining(@Valid @RequestBody AssignUserTrainingDto assignUserDto) {
        try {
            LearnerDto assignedLearner = learnerService.assignLearnerToTraining(assignUserDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(assignedLearner);
        } catch (ResourceNotFoundException e) {
            logger.error("Resource not found: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            logger.error("An error occurred while assigning the learner to the training", e);
            throw new DatabaseOperationException("Failed to assign training to the database", e);
        }
    }



}
