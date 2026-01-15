package kg.laponandsweezy.registrationlapon.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.laponandsweezy.registrationlapon.dto.request.DocumentRequest;
import kg.laponandsweezy.registrationlapon.dto.response.DocumentResponse;
import kg.laponandsweezy.registrationlapon.service.DocumentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/documents")
@Tag(name = "Documents", description = "Endpoints for managing documents")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping("/create")
    @Operation(summary = "Create document", description = "Creates a new document")
    public ResponseEntity<DocumentResponse> createDocument(@RequestBody DocumentRequest request) {
        DocumentResponse response = documentService.save(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/get-by/{id}")
    @Operation(summary = "Get document by ID", description = "Retrieves a document by its ID")
    public ResponseEntity<DocumentResponse> getDocumentById(@PathVariable int id) {
        DocumentResponse response = documentService.findById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-all")
    @Operation(summary = "Get all documents", description = "Retrieves a list of all documents")
    public ResponseEntity<List<DocumentResponse>> getAllDocuments() {
        List<DocumentResponse> responses = documentService.findAll();
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update document", description = "Updates an existing document")
    public ResponseEntity<DocumentResponse> updateDocument(@PathVariable int id, @RequestBody DocumentRequest request) {
        DocumentResponse response = documentService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete document", description = "Deletes a document by its ID")
    public ResponseEntity<Void> deleteDocument(@PathVariable int id) {
        documentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}