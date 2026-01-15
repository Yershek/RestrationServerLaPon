package kg.laponandsweezy.registrationlapon.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.laponandsweezy.registrationlapon.dto.request.CitizenResidencyRequest;
import kg.laponandsweezy.registrationlapon.dto.response.CitizenResidencyResponse;
import kg.laponandsweezy.registrationlapon.service.CitizenResidencyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/citizen-residency")
@Tag(name = "Citizen Residency", description = "Endpoints for managing citizen residencies")
public class CitizenResidencyController {

    private final CitizenResidencyService citizenResidencyService;

    public CitizenResidencyController(CitizenResidencyService citizenResidencyService) {
        this.citizenResidencyService = citizenResidencyService;
    }

    @PostMapping("/create")
    @Operation(summary = "Create citizen residency", description = "Creates a new citizen residency record")
    public ResponseEntity<CitizenResidencyResponse> createCitizenResidency(@RequestBody CitizenResidencyRequest request) {
        CitizenResidencyResponse response = citizenResidencyService.save(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/get-by/{id}")
    @Operation(summary = "Get citizen residency by ID", description = "Retrieves a citizen residency record by its ID")
    public ResponseEntity<CitizenResidencyResponse> getCitizenResidencyById(@PathVariable int id) {
        CitizenResidencyResponse response = citizenResidencyService.findById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-all")
    @Operation(summary = "Get all citizen residencies", description = "Retrieves a list of all citizen residency records")
    public ResponseEntity<List<CitizenResidencyResponse>> getAllCitizenResidency() {
        List<CitizenResidencyResponse> responses = citizenResidencyService.findAll();
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update citizen residency", description = "Updates an existing citizen residency record")
    public ResponseEntity<CitizenResidencyResponse> updateCitizenResidency(@PathVariable int id, @RequestBody CitizenResidencyRequest request) {
        CitizenResidencyResponse response = citizenResidencyService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete citizen residency", description = "Deletes a citizen residency record by its ID")
    public ResponseEntity<Void> deleteCitizenResidency(@PathVariable int id) {
        citizenResidencyService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}