package com.example.online_store_backend.mapper;

import com.example.online_store_backend.dto.CustomerDto;
import com.example.online_store_backend.entity.Customer;
import com.example.online_store_backend.entity.CustomerType;

public class CustomerMapper {
    public static CustomerDto mapToCustomerDto(Customer customer) {
        return new CustomerDto(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getCustomerType() != null ? customer.getCustomerType().getId() : null
        );
    }

    public static Customer mapToCustomer(CustomerDto customerDto, CustomerType customerType) {
        Customer customer = new Customer();
        customer.setId(customerDto.getId());
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setCustomerType(customerType);
        return customer;
    }
}