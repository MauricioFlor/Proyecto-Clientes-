package com.example.online_store_backend.service;

import com.example.online_store_backend.dto.CustomerDto;
import java.util.List;

public interface CustomerService {
    List<CustomerDto> getAllCustomers();
    CustomerDto createCustomer(CustomerDto customerDto);
    void deleteCustomer(Long customerId);
    CustomerDto updateCustomer(Long customerId, CustomerDto customerDto);
}