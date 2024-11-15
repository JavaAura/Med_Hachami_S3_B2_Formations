package com.formations.controller;

import com.formations.exception.business.DatabaseOperationException;
import com.formations.exception.business.ResourceNotFoundException;
import com.formations.mappers.TrainerMapper;
import com.formations.mappers.TrainingMapper;
import com.formations.model.Dto.TrainerDto;
import com.formations.model.Dto.TrainingDto;
import com.formations.model.Trainer;
import com.formations.model.Training;
import com.formations.service.TrainingService;
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
@RequestMapping("/api/v1/training")
public class TrainingController {
    private static final Logger logger = LoggerFactory.getLogger(TrainingController.class);
    private final TrainingService trainingService;


    public TrainingController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @PostMapping("/add")
    @Operation(summary = "Add a new training", description = "Creates a new training")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Training created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TrainingDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<?> addTraining(@Valid @RequestBody TrainingDto trainingDto) {
        try {
            TrainingDto newTraining = trainingService.addTraining(trainingDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(newTraining);
        } catch (ResourceNotFoundException e) {
            logger.error("Resource not found: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            logger.error("An error occurred while assigning the Trainer to the group", e.getMessage());
            throw new DatabaseOperationException("Failed to assign Trainer to the database", e);
        }
    }

    @GetMapping("/all")
    @Operation(summary = "Get all trainings", description = "Retrieves a list of all trainings")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of trainings",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TrainingDto.class))),
            @ApiResponse(responseCode = "404", description = "Trainings not found")
    })
    public ResponseEntity<List<TrainingDto>> getAllTrainings() {
        List<TrainingDto> trainings = trainingService.getAllTrainings();

        return ResponseEntity.ok(trainings);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get training by id", description = "Retrieve a specific training by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of training",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TrainingDto.class))),
            @ApiResponse(responseCode = "404", description = "Trainer not found")
    })
    public ResponseEntity<?> getTrainingId(@PathVariable("id") Long id) {
        try {
            Training training = trainingService.getTrainingById(id);

            return ResponseEntity.status(HttpStatus.OK).body(TrainingMapper.trainingMapper.toDto(training));


        } catch (ResourceNotFoundException e) {
            logger.error("Training not found: {}", e.getMessage());
            return new ResponseEntity<String>("Training Not found",HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("An error occurred while fetching the trainer", e);
            throw new DatabaseOperationException("Failed to get the Training from the database", e);
        }
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update an existing Training", description = "Updates the details of a Training")
    @ApiResponse(responseCode = "200", description = "Training updated successfully",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = TrainingDto.class)))
    @ApiResponse(responseCode = "404", description = "Training not found")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    public ResponseEntity<TrainingDto> updateTraining(@PathVariable("id") Long id,  @Valid  @RequestBody TrainingDto trainingDto) {
       TrainingDto updatedTraining = trainingService.update(trainingDto);
        return ResponseEntity.ok(updatedTraining);
    }


    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a training", description = "Deletes a training by ID")
    @ApiResponse(responseCode = "204", description = "training deleted successfully")
    @ApiResponse(responseCode = "404", description = "training not found")
    public ResponseEntity<Void> DeleteTraining(@PathVariable("id") Long id) {
        Training t = new Training();
        t.setId(id);
        trainingService.delete(t);
        return ResponseEntity.noContent().build();
    }


   /* @PostMapping("/add")
    @Operation(summary = "Add a new training", description = "Creates a new training")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Training created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TrainingDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<?> assignLearnerToTraining(@Valid @RequestBody TrainingDto trainingDto) {
        try {
            TrainingDto newTraining = trainingService.addTraining(trainingDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(newTraining);
        } catch (ResourceNotFoundException e) {
            logger.error("Resource not found: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            logger.error("An error occurred while assigning the Trainer to the group", e.getMessage());
            throw new DatabaseOperationException("Failed to assign Trainer to the database", e);
        }
    }*/





}
