package kg.laponandsweezy.registrationlapon.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.laponandsweezy.registrationlapon.dto.request.MarriageRequest;
import kg.laponandsweezy.registrationlapon.dto.response.MarriageResponse;
import kg.laponandsweezy.registrationlapon.service.MarriageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/marriages")
@Tag(name = "Marriages", description = "Endpoints for managing marriages")
public class MarriageController {

    private final MarriageService marriageService;

    public MarriageController(MarriageService marriageService) {
        this.marriageService = marriageService;
    }

    @PostMapping("/create")
    @Operation(summary = "Create marriage", description = "Creates a new marriage record")
    public ResponseEntity<MarriageResponse> createMarriage(@RequestBody MarriageRequest request) {
        MarriageResponse response = marriageService.save(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/get-by/{id}")
    @Operation(summary = "Get marriage by ID", description = "Retrieves a marriage record by its ID")
    public ResponseEntity<MarriageResponse> getMarriageById(@PathVariable int id) {
        MarriageResponse response = marriageService.findById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-all")
    @Operation(summary = "Get all marriages", description = "Retrieves a list of all marriage records")
    public ResponseEntity<List<MarriageResponse>> getAllMarriages() {
        List<MarriageResponse> responses = marriageService.findAll();
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update marriage", description = "Updates an existing marriage record")
    public ResponseEntity<MarriageResponse> updateMarriage(@PathVariable int id, @RequestBody MarriageRequest request) {
        MarriageResponse response = marriageService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete marriage", description = "Deletes a marriage record by its ID")
    public ResponseEntity<Void> deleteMarriage(@PathVariable int id) {
        marriageService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}