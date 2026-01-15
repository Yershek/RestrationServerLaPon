package kg.laponandsweezy.registrationlapon.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.laponandsweezy.registrationlapon.dto.request.CitizenRequest;
import kg.laponandsweezy.registrationlapon.dto.response.CitizenResponse;
import kg.laponandsweezy.registrationlapon.service.CitizenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/citizens")
@Tag(name = "Citizens", description = "Endpoints for managing citizens")
public class CitizenController {

    private final CitizenService citizenService;

    public CitizenController(CitizenService citizenService) {
        this.citizenService = citizenService;
    }

    @PostMapping("/create")
    @Operation(summary = "Create citizen", description = "Creates a new citizen")
    public ResponseEntity<CitizenResponse> createCitizen(@RequestBody CitizenRequest request) {
        CitizenResponse response = citizenService.save(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/get-by/{id}")
    @Operation(summary = "Get citizen by ID", description = "Retrieves a citizen by their ID")
    public ResponseEntity<CitizenResponse> getCitizenById(@PathVariable int id) {
        CitizenResponse response = citizenService.findById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-all")
    @Operation(summary = "Get all citizens", description = "Retrieves a list of all citizens")
    public ResponseEntity<List<CitizenResponse>> getAllCitizens() {
        List<CitizenResponse> responses = citizenService.findAll();
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update citizen", description = "Updates an existing citizen")
    public ResponseEntity<CitizenResponse> updateCitizen(@PathVariable int id, @RequestBody CitizenRequest request) {
        CitizenResponse response = citizenService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete citizen", description = "Deletes a citizen by their ID")
    public ResponseEntity<Void> deleteCitizen(@PathVariable int id) {
        citizenService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}