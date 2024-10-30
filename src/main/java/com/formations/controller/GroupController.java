package com.formations.controller;

import com.formations.exception.ResourceNotFoundException;
import com.formations.model.Group;
import com.formations.service.GroupService;
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
@RequestMapping("/api/group")
public class GroupController {

    private static final Logger logger = LoggerFactory.getLogger(GroupController.class);
    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/all")
    @Operation(summary = "Get all groups", description = "Retrieves a list of all groups")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of groups",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Group.class))),
            @ApiResponse(responseCode = "404", description = "Groups not found")
    })
    public ResponseEntity<List<Group>> getAllGroups() {
        List<Group> groups = groupService.getAllGroups();
        if (groups == null || groups.isEmpty()) {
            throw new ResourceNotFoundException("No groups found");
        }
        return ResponseEntity.ok(groups);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get group by id", description = "Retrieve a specific group by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of group",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Group.class))),
            @ApiResponse(responseCode = "404", description = "Group not found")
    })
    public ResponseEntity<Group> getGroupById(@PathVariable("id") Long id) {
        Group group = groupService.getGroupById(id);
        if (group == null) {
            throw new ResourceNotFoundException("Group with ID " + id + " not found");
        }
        return ResponseEntity.ok(group);
    }

    @PostMapping("/add")
    @Operation(summary = "Add a new group", description = "Creates a new group")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Group created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Group.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<Group> addGroup(@Valid @RequestBody Group group) {
        Group createdGroup = groupService.addGroup(group);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGroup);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update an existing group", description = "Updates the details of a group")
    @ApiResponse(responseCode = "200", description = "Group updated successfully",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Group.class)))
    @ApiResponse(responseCode = "404", description = "Group not found")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    public ResponseEntity<Group> updateGroup( @Valid @RequestBody Group group) {
        Group updatedGroup = groupService.update(group);
        return ResponseEntity.ok(updatedGroup);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a group", description = "Deletes a group by ID")
    @ApiResponse(responseCode = "204", description = "Group deleted successfully")
    @ApiResponse(responseCode = "404", description = "Group not found")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long id) {
        Group g = new Group();
        g.setId(id);
        groupService.delete(g);
        return ResponseEntity.noContent().build();
    }







}
