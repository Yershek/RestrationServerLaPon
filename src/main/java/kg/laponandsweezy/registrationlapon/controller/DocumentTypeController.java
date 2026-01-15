package kg.laponandsweezy.registrationlapon.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.laponandsweezy.registrationlapon.dto.request.DocumentTypeRequest;
import kg.laponandsweezy.registrationlapon.dto.response.DocumentTypeResponse;
import kg.laponandsweezy.registrationlapon.service.DocumentTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/document-types")
@Tag(name = "Document Types", description = "Endpoints for managing document types")
public class DocumentTypeController {

    private final DocumentTypeService documentTypeService;

    public DocumentTypeController(DocumentTypeService documentTypeService) {
        this.documentTypeService = documentTypeService;
    }

    @PostMapping("/create")
    @Operation(summary = "Create document type", description = "Creates a new document type")
    public ResponseEntity<DocumentTypeResponse> createDocumentType(@RequestBody DocumentTypeRequest request) {
        DocumentTypeResponse response = documentTypeService.save(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/get-by/{id}")
    @Operation(summary = "Get document type by ID", description = "Retrieves a document type by its ID")
    public ResponseEntity<DocumentTypeResponse> getDocumentTypeById(@PathVariable int id) {
        DocumentTypeResponse response = documentTypeService.findById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-all")
    @Operation(summary = "Get all document types", description = "Retrieves a list of all document types")
    public ResponseEntity<List<DocumentTypeResponse>> getAllDocumentTypes() {
        List<DocumentTypeResponse> responses = documentTypeService.findAll();
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update document type", description = "Updates an existing document type")
    public ResponseEntity<DocumentTypeResponse> updateDocumentType(@PathVariable int id, @RequestBody DocumentTypeRequest request) {
        DocumentTypeResponse response = documentTypeService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete document type", description = "Deletes a document type by its ID")
    public ResponseEntity<Void> deleteDocumentType(@PathVariable int id) {
        documentTypeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}