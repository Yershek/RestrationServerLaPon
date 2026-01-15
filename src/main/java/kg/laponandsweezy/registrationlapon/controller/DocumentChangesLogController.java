package kg.laponandsweezy.registrationlapon.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.laponandsweezy.registrationlapon.dto.request.DocumentChangesLogRequest;
import kg.laponandsweezy.registrationlapon.dto.response.DocumentChangesLogResponse;
import kg.laponandsweezy.registrationlapon.service.DocumentChangesLogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/document-changes-log")
@Tag(name = "Document Changes Log", description = "Endpoints for managing document changes logs")
public class DocumentChangesLogController {

    private final DocumentChangesLogService documentChangesLogService;

    public DocumentChangesLogController(DocumentChangesLogService documentChangesLogService) {
        this.documentChangesLogService = documentChangesLogService;
    }

    @PostMapping("/create")
    @Operation(summary = "Create document changes log", description = "Creates a new document changes log entry")
    public ResponseEntity<DocumentChangesLogResponse> createDocumentChangesLog(@RequestBody DocumentChangesLogRequest request) {
        DocumentChangesLogResponse response = documentChangesLogService.save(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/get-by/{id}")
    @Operation(summary = "Get document changes log by ID", description = "Retrieves a document changes log entry by its ID")
    public ResponseEntity<DocumentChangesLogResponse> getDocumentChangesLogById(@PathVariable Long id) {
        DocumentChangesLogResponse response = documentChangesLogService.findById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-all")
    @Operation(summary = "Get all document changes logs", description = "Retrieves a list of all document changes log entries")
    public ResponseEntity<List<DocumentChangesLogResponse>> getAllDocumentChangesLogs() {
        List<DocumentChangesLogResponse> responses = documentChangesLogService.findAll();
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update document changes log", description = "Updates an existing document changes log entry")
    public ResponseEntity<DocumentChangesLogResponse> updateDocumentChangesLog(@PathVariable Long id, @RequestBody DocumentChangesLogRequest request) {
        DocumentChangesLogResponse response = documentChangesLogService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete document changes log", description = "Deletes a document changes log entry by its ID")
    public ResponseEntity<Void> deleteDocumentChangesLog(@PathVariable Long id) {
        documentChangesLogService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}