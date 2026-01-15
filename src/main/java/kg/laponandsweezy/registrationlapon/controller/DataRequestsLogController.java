package kg.laponandsweezy.registrationlapon.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.laponandsweezy.registrationlapon.dto.request.DataRequestsLogRequest;
import kg.laponandsweezy.registrationlapon.dto.response.DataRequestsLogResponse;
import kg.laponandsweezy.registrationlapon.service.DataRequestsLogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/data-requests-log")
@Tag(name = "Data Requests Log", description = "Endpoints for managing data requests logs")
public class DataRequestsLogController {

    private final DataRequestsLogService dataRequestsLogService;

    public DataRequestsLogController(DataRequestsLogService dataRequestsLogService) {
        this.dataRequestsLogService = dataRequestsLogService;
    }

    @PostMapping("/create")
    @Operation(summary = "Create data requests log", description = "Creates a new data requests log entry")
    public ResponseEntity<DataRequestsLogResponse> createDataRequestsLog(@RequestBody DataRequestsLogRequest request) {
        DataRequestsLogResponse response = dataRequestsLogService.save(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/get-by/{id}")
    @Operation(summary = "Get data requests log by ID", description = "Retrieves a data requests log entry by its ID")
    public ResponseEntity<DataRequestsLogResponse> getDataRequestsLogById(@PathVariable Long id) {
        DataRequestsLogResponse response = dataRequestsLogService.findById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-all")
    @Operation(summary = "Get all data requests logs", description = "Retrieves a list of all data requests log entries")
    public ResponseEntity<List<DataRequestsLogResponse>> getAllDataRequestsLogs() {
        List<DataRequestsLogResponse> responses = dataRequestsLogService.findAll();
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update data requests log", description = "Updates an existing data requests log entry")
    public ResponseEntity<DataRequestsLogResponse> updateDataRequestsLog(@PathVariable Long id, @RequestBody DataRequestsLogRequest request) {
        DataRequestsLogResponse response = dataRequestsLogService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete data requests log", description = "Deletes a data requests log entry by its ID")
    public ResponseEntity<Void> deleteDataRequestsLog(@PathVariable Long id) {
        dataRequestsLogService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}