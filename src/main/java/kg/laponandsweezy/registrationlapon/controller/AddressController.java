package kg.laponandsweezy.registrationlapon.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.laponandsweezy.registrationlapon.dto.request.AddressRequest;
import kg.laponandsweezy.registrationlapon.dto.response.AddressResponse;
import kg.laponandsweezy.registrationlapon.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
@Tag(name = "Addresses", description = "Endpoints for managing addresses")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/create")
    @Operation(summary = "Create address", description = "Creates a new address")
    public ResponseEntity<AddressResponse> createAddress(@RequestBody AddressRequest request) {
        AddressResponse response = addressService.save(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/get-by/{id}")
    @Operation(summary = "Get address by ID", description = "Retrieves an address by its ID")
    public ResponseEntity<AddressResponse> getAddressById(@PathVariable int id) {
        AddressResponse response = addressService.findById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-all")
    @Operation(summary = "Get all addresses", description = "Retrieves a list of all addresses")
    public ResponseEntity<List<AddressResponse>> getAllAddresses() {
        List<AddressResponse> responses = addressService.findAll();
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update address", description = "Updates an existing address")
    public ResponseEntity<AddressResponse> updateAddress(@PathVariable int id, @RequestBody AddressRequest request) {
        AddressResponse response = addressService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete address", description = "Deletes an address by its ID")
    public ResponseEntity<Void> deleteAddress(@PathVariable int id) {
        addressService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}