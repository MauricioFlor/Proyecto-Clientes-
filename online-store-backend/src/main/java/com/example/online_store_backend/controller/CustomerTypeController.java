package com.example.online_store_backend.controller;

import com.example.online_store_backend.dto.CustomerTypeDto;
import com.example.online_store_backend.service.CustomerTypeService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/customer-types")
public class CustomerTypeController {

    private final CustomerTypeService customerTypeService;

    @GetMapping
    public ResponseEntity<List<CustomerTypeDto>> getAllCustomerTypes() {
        return ResponseEntity.ok(customerTypeService.getAllCustomerTypes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerTypeDto> getCustomerTypeById(@PathVariable Long id) {
        return ResponseEntity.ok(customerTypeService.getCustomerTypeById(id));
    }

    @PostMapping
    public ResponseEntity<CustomerTypeDto> createCustomerType(@Valid @RequestBody CustomerTypeDto customerTypeDto) {
        return ResponseEntity.ok(customerTypeService.createCustomerType(customerTypeDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerTypeDto> updateCustomerType(
            @PathVariable Long id,
            @Valid @RequestBody CustomerTypeDto customerTypeDto) {
        return ResponseEntity.ok(customerTypeService.updateCustomerType(id, customerTypeDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomerType(@PathVariable Long id) {
        customerTypeService.deleteCustomerType(id);
        return ResponseEntity.ok("Customer type deleted successfully");
    }
}