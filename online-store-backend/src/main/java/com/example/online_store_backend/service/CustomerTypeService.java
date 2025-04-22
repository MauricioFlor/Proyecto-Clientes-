package com.example.online_store_backend.service;

import com.example.online_store_backend.dto.CustomerTypeDto;
import java.util.List;

public interface CustomerTypeService {
    List<CustomerTypeDto> getAllCustomerTypes();
    CustomerTypeDto getCustomerTypeById(Long id);
    CustomerTypeDto createCustomerType(CustomerTypeDto customerTypeDto);
    CustomerTypeDto updateCustomerType(Long id, CustomerTypeDto customerTypeDto);
    void deleteCustomerType(Long id);
}