package kg.laponandsweezy.registrationlapon.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.laponandsweezy.registrationlapon.dto.request.RelationshipRequest;
import kg.laponandsweezy.registrationlapon.dto.response.RelationshipResponse;
import kg.laponandsweezy.registrationlapon.service.RelationshipService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/relationships")
@Tag(name = "Relationships", description = "Endpoints for managing relationships")
public class RelationshipController {

    private final RelationshipService relationshipService;

    public RelationshipController(RelationshipService relationshipService) {
        this.relationshipService = relationshipService;
    }

    @PostMapping("/create")
    @Operation(summary = "Create relationship", description = "Creates a new relationship")
    public ResponseEntity<RelationshipResponse> createRelationship(@RequestBody RelationshipRequest request) {
        RelationshipResponse response = relationshipService.save(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/get-by/{id}")
    @Operation(summary = "Get relationship by ID", description = "Retrieves a relationship by its ID")
    public ResponseEntity<RelationshipResponse> getRelationshipById(@PathVariable int id) {
        RelationshipResponse response = relationshipService.findById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-all")
    @Operation(summary = "Get all relationships", description = "Retrieves a list of all relationships")
    public ResponseEntity<List<RelationshipResponse>> getAllRelationships() {
        List<RelationshipResponse> responses = relationshipService.findAll();
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update relationship", description = "Updates an existing relationship")
    public ResponseEntity<RelationshipResponse> updateRelationship(@PathVariable int id, @RequestBody RelationshipRequest request) {
        RelationshipResponse response = relationshipService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete relationship", description = "Deletes a relationship by its ID")
    public ResponseEntity<Void> deleteRelationship(@PathVariable int id) {
        relationshipService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}