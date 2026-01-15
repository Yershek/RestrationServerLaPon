package kg.laponandsweezy.registrationlapon.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.laponandsweezy.registrationlapon.dto.request.AgencyRequest;
import kg.laponandsweezy.registrationlapon.dto.response.AgencyResponse;
import kg.laponandsweezy.registrationlapon.service.AgencyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agencies")
@Tag(name = "Agencies", description = "Endpoints for managing agencies")
public class AgencyController {

    private final AgencyService agencyService;

    public AgencyController(AgencyService agencyService) {
        this.agencyService = agencyService;
    }

    @PostMapping("/create")
    @Operation(summary = "Create agency", description = "Creates a new agency")
    public ResponseEntity<AgencyResponse> createAgency(@RequestBody AgencyRequest request) {
        AgencyResponse response = agencyService.save(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/get-by/{id}")
    @Operation(summary = "Get agency by ID", description = "Retrieves an agency by its ID")
    public ResponseEntity<AgencyResponse> getAgencyById(@PathVariable int id) {
        AgencyResponse response = agencyService.findById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-all")
    @Operation(summary = "Get all agencies", description = "Retrieves a list of all agencies")
    public ResponseEntity<List<AgencyResponse>> getAllAgencies() {
        List<AgencyResponse> responses = agencyService.findAll();
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update agency", description = "Updates an existing agency")
    public ResponseEntity<AgencyResponse> updateAgency(@PathVariable int id, @RequestBody AgencyRequest request) {
        AgencyResponse response = agencyService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete agency", description = "Deletes an agency by its ID")
    public ResponseEntity<Void> deleteAgency(@PathVariable int id) {
        agencyService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}