package com.formations.controller;


import com.formations.exception.business.DatabaseOperationException;
import com.formations.exception.business.ResourceNotFoundException;
import com.formations.mappers.TrainerMapper;
import com.formations.model.Dto.AssignUserGroupDto;
import com.formations.model.Dto.LearnerDto;
import com.formations.model.Dto.TrainerDto;
import com.formations.model.Learner;
import com.formations.model.Trainer;
import com.formations.service.TrainerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/trainer")
public class TrainerController {

    private static final Logger logger = LoggerFactory.getLogger(TrainerController.class);
    private final TrainerService trainerService;

    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }


    @GetMapping("/all")
    @Operation(summary = "Get all trainers", description = "Retrieves a list of all trainers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of trainers",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Trainer.class))),
            @ApiResponse(responseCode = "404", description = "Trainers not found")
    })
    public ResponseEntity<List<TrainerDto>> getAllTrainers() {
        List<TrainerDto> trainers = trainerService.getAllTrainers();

        return ResponseEntity.ok(trainers);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get trainer by id", description = "Retrieve a specific trainer by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of trainer",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Trainer.class))),
            @ApiResponse(responseCode = "404", description = "Trainer not found")
    })
    public ResponseEntity<?> getTrainerId(@PathVariable("id") Long id) {
        try {
            Trainer trainer = trainerService.getTrainerById(id);

            return ResponseEntity.status(HttpStatus.OK).body(TrainerMapper.trainerMapper.toDto(trainer));


        } catch (ResourceNotFoundException e) {
            logger.error("Trainer not found: {}", e.getMessage());
            return new ResponseEntity<String>("Trainer Not found",HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("An error occurred while fetching the trainer", e);
            throw new DatabaseOperationException("Failed to get the trainer from the database", e);
        }
    }


    @PostMapping("/add")
    @Operation(summary = "Add a new trainer", description = "Creates a new trainer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Trainer created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Trainer.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<Trainer> addTrainer(@Valid @RequestBody TrainerDto trainerDto) {

        Trainer createdTrainer = trainerService.addTrainer(trainerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTrainer);
    }


    @PutMapping("/update/{id}")
    @Operation(summary = "Update an existing Trainer", description = "Updates the details of a Trainer")
    @ApiResponse(responseCode = "200", description = "Trainer updated successfully",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Trainer.class)))
    @ApiResponse(responseCode = "404", description = "Trainer not found")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    public ResponseEntity<TrainerDto> updateTrainer(@PathVariable("id") Long id,  @Valid  @RequestBody TrainerDto trainerDto) {
        TrainerDto updatedTrainer = trainerService.update(id,trainerDto);
        return ResponseEntity.ok(updatedTrainer);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a Trainer", description = "Deletes a Trainer by ID")
    @ApiResponse(responseCode = "204", description = "Trainer deleted successfully")
    @ApiResponse(responseCode = "404", description = "Trainer not found")
    public ResponseEntity<Void> DeleteTrainer(@PathVariable Long id) {
        Trainer t = new Trainer();
        t.setId(id);
        trainerService.delete(t);
        return ResponseEntity.noContent().build();
    }

    //Assign trainer to group
    @PostMapping("/assignTrainerToGroup")
    @Operation(summary = "Assign trainer to group", description = "Assigns a trainer to a specific group based on Trainer ID and group ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Trainer assigned to group successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TrainerDto.class))),
            @ApiResponse(responseCode = "404", description = "Trainer or Group not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    public ResponseEntity<?> assignTrainerToGroup(@Valid @RequestBody AssignUserGroupDto assignUserDto) {
        try {
            TrainerDto assignedTrainer = trainerService.assignLearnerToGroup(assignUserDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(assignedTrainer);
        } catch (ResourceNotFoundException e) {
            logger.error("Resource not found: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            logger.error("An error occurred while assigning the Trainer to the group", e);
            throw new DatabaseOperationException("Failed to assign Trainer to the database", e);
        }
    }

}
