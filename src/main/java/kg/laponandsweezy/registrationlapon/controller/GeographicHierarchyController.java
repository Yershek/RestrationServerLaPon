package kg.laponandsweezy.registrationlapon.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.laponandsweezy.registrationlapon.dto.request.GeographicHierarchyRequest;
import kg.laponandsweezy.registrationlapon.dto.response.GeographicHierarchyResponse;
import kg.laponandsweezy.registrationlapon.service.GeographicHierarchyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/geographic-hierarchy")
@Tag(name = "Geographic Hierarchy", description = "Endpoints for managing geographic hierarchies")
public class GeographicHierarchyController {

    private final GeographicHierarchyService geographicHierarchyService;

    public GeographicHierarchyController(GeographicHierarchyService geographicHierarchyService) {
        this.geographicHierarchyService = geographicHierarchyService;
    }

    @PostMapping("/create")
    @Operation(summary = "Create geographic hierarchy", description = "Creates a new geographic hierarchy entry")
    public ResponseEntity<GeographicHierarchyResponse> createGeographicHierarchy(@RequestBody GeographicHierarchyRequest request) {
        GeographicHierarchyResponse response = geographicHierarchyService.save(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/get-by/{id}")
    @Operation(summary = "Get geographic hierarchy by ID", description = "Retrieves a geographic hierarchy entry by its ID")
    public ResponseEntity<GeographicHierarchyResponse> getGeographicHierarchyById(@PathVariable int id) {
        GeographicHierarchyResponse response = geographicHierarchyService.findById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-all")
    @Operation(summary = "Get all geographic hierarchies", description = "Retrieves a list of all geographic hierarchy entries")
    public ResponseEntity<List<GeographicHierarchyResponse>> getAllGeographicHierarchies() {
        List<GeographicHierarchyResponse> responses = geographicHierarchyService.findAll();
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update geographic hierarchy", description = "Updates an existing geographic hierarchy entry")
    public ResponseEntity<GeographicHierarchyResponse> updateGeographicHierarchy(@PathVariable int id, @RequestBody GeographicHierarchyRequest request) {
        GeographicHierarchyResponse response = geographicHierarchyService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete geographic hierarchy", description = "Deletes a geographic hierarchy entry by its ID")
    public ResponseEntity<Void> deleteGeographicHierarchy(@PathVariable int id) {
        geographicHierarchyService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}