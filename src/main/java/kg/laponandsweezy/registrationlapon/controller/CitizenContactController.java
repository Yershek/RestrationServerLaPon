package kg.laponandsweezy.registrationlapon.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.laponandsweezy.registrationlapon.dto.request.CitizenContactRequest;
import kg.laponandsweezy.registrationlapon.dto.response.CitizenContactResponse;
import kg.laponandsweezy.registrationlapon.service.CitizenContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/citizen-contacts")
@Tag(name = "Citizen Contacts", description = "Endpoints for managing citizen contacts")
public class CitizenContactController {

    private final CitizenContactService citizenContactService;

    public CitizenContactController(CitizenContactService citizenContactService) {
        this.citizenContactService = citizenContactService;
    }

    @PostMapping("/create")
    @Operation(summary = "Create citizen contact", description = "Creates a new citizen contact")
    public ResponseEntity<CitizenContactResponse> createCitizenContact(@RequestBody CitizenContactRequest request) {
        CitizenContactResponse response = citizenContactService.save(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/get-by/{id}")
    @Operation(summary = "Get citizen contact by ID", description = "Retrieves a citizen contact by its ID")
    public ResponseEntity<CitizenContactResponse> getCitizenContactById(@PathVariable int id) {
        CitizenContactResponse response = citizenContactService.findById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-all")
    @Operation(summary = "Get all citizen contacts", description = "Retrieves a list of all citizen contacts")
    public ResponseEntity<List<CitizenContactResponse>> getAllCitizenContacts() {
        List<CitizenContactResponse> responses = citizenContactService.findAll();
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update citizen contact", description = "Updates an existing citizen contact")
    public ResponseEntity<CitizenContactResponse> updateCitizenContact(@PathVariable int id, @RequestBody CitizenContactRequest request) {
        CitizenContactResponse response = citizenContactService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete citizen contact", description = "Deletes a citizen contact by its ID")
    public ResponseEntity<Void> deleteCitizenContact(@PathVariable int id) {
        citizenContactService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}