package com.example.online_store_backend.service.impl;

import com.example.online_store_backend.dto.CustomerTypeDto;
import com.example.online_store_backend.entity.CustomerType;
import com.example.online_store_backend.mapper.CustomerTypeMapper;
import com.example.online_store_backend.repository.CustomerTypeRepositoy;
import com.example.online_store_backend.service.CustomerTypeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerTypeImpl implements CustomerTypeService {

    private final CustomerTypeRepositoy customerTypeRepositoy;

    public CustomerTypeImpl(CustomerTypeRepositoy customerTypeRepositoy) {
        this.customerTypeRepositoy = customerTypeRepositoy;
    }

    @Override
    public List<CustomerTypeDto> getAllCustomerTypes() {
        return customerTypeRepositoy.findAll().stream()
                .map(CustomerTypeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerTypeDto getCustomerTypeById(Long id) {
        CustomerType customerType = customerTypeRepositoy.findById(id)
                .orElseThrow();
        return CustomerTypeMapper.toDto(customerType);
    }

    @Override
    public CustomerTypeDto createCustomerType(CustomerTypeDto customerTypeDto) {
        CustomerType customerType = CustomerTypeMapper.toEntity(customerTypeDto);
        CustomerType savedCustomerType = customerTypeRepositoy.save(customerType);
        return CustomerTypeMapper.toDto(savedCustomerType);
    }

    @Override
    public CustomerTypeDto updateCustomerType(Long id, CustomerTypeDto customerTypeDto) {
        CustomerType existingCustomerType = customerTypeRepositoy.findById(id)
                .orElseThrow();

        existingCustomerType.setCustomerTypeName(customerTypeDto.getCustomerTypeName());
        existingCustomerType.setCustomerDescription(customerTypeDto.getCustomerDescription());

        CustomerType updatedCustomerType = customerTypeRepositoy.save(existingCustomerType);
        return CustomerTypeMapper.toDto(updatedCustomerType);
    }

    @Override
    public void deleteCustomerType(Long id) {
        CustomerType customerType = customerTypeRepositoy.findById(id)
                .orElseThrow();
        customerTypeRepositoy.delete(customerType);
    }
}