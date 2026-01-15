package kg.laponandsweezy.registrationlapon.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.laponandsweezy.registrationlapon.dto.request.CitizenStatusRequest;
import kg.laponandsweezy.registrationlapon.dto.response.CitizenStatusResponse;
import kg.laponandsweezy.registrationlapon.service.CitizenStatusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/citizen-status")
@Tag(name = "Citizen Status", description = "Endpoints for managing citizen statuses")
public class CitizenStatusController {

    private final CitizenStatusService citizenStatusService;

    public CitizenStatusController(CitizenStatusService citizenStatusService) {
        this.citizenStatusService = citizenStatusService;
    }

    @PostMapping("/create")
    @Operation(summary = "Create citizen status", description = "Creates a new citizen status")
    public ResponseEntity<CitizenStatusResponse> createCitizenStatus(@RequestBody CitizenStatusRequest request) {
        CitizenStatusResponse response = citizenStatusService.save(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/get-by/{id}")
    @Operation(summary = "Get citizen status by ID", description = "Retrieves a citizen status by its ID")
    public ResponseEntity<CitizenStatusResponse> getCitizenStatusById(@PathVariable int id) {
        CitizenStatusResponse response = citizenStatusService.findById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-all")
    @Operation(summary = "Get all citizen statuses", description = "Retrieves a list of all citizen statuses")
    public ResponseEntity<List<CitizenStatusResponse>> getAllCitizenStatus() {
        List<CitizenStatusResponse> responses = citizenStatusService.findAll();
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update citizen status", description = "Updates an existing citizen status")
    public ResponseEntity<CitizenStatusResponse> updateCitizenStatus(@PathVariable int id, @RequestBody CitizenStatusRequest request) {
        CitizenStatusResponse response = citizenStatusService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete citizen status", description = "Deletes a citizen status by its ID")
    public ResponseEntity<Void> deleteCitizenStatus(@PathVariable int id) {
        citizenStatusService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}