package kg.laponandsweezy.registrationlapon.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.laponandsweezy.registrationlapon.dto.request.MaritalStatusRequest;
import kg.laponandsweezy.registrationlapon.dto.response.MaritalStatusResponse;
import kg.laponandsweezy.registrationlapon.service.MaritalStatusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/marital-status")
@Tag(name = "Marital Status", description = "Endpoints for managing marital statuses")
public class MaritalStatusController {

    private final MaritalStatusService maritalStatusService;

    public MaritalStatusController(MaritalStatusService maritalStatusService) {
        this.maritalStatusService = maritalStatusService;
    }

    @PostMapping("/create")
    @Operation(summary = "Create marital status", description = "Creates a new marital status")
    public ResponseEntity<MaritalStatusResponse> createMaritalStatus(@RequestBody MaritalStatusRequest request) {
        MaritalStatusResponse response = maritalStatusService.save(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/get-by/{id}")
    @Operation(summary = "Get marital status by ID", description = "Retrieves a marital status by its ID")
    public ResponseEntity<MaritalStatusResponse> getMaritalStatusById(@PathVariable int id) {
        MaritalStatusResponse response = maritalStatusService.findById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-all")
    @Operation(summary = "Get all marital statuses", description = "Retrieves a list of all marital statuses")
    public ResponseEntity<List<MaritalStatusResponse>> getAllMaritalStatus() {
        List<MaritalStatusResponse> responses = maritalStatusService.findAll();
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update marital status", description = "Updates an existing marital status")
    public ResponseEntity<MaritalStatusResponse> updateMaritalStatus(@PathVariable int id, @RequestBody MaritalStatusRequest request) {
        MaritalStatusResponse response = maritalStatusService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete marital status", description = "Deletes a marital status by its ID")
    public ResponseEntity<Void> deleteMaritalStatus(@PathVariable int id) {
        maritalStatusService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}