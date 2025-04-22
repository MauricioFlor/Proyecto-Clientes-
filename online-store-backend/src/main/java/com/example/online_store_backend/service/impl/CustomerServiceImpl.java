package com.example.online_store_backend.service.impl;

import com.example.online_store_backend.dto.CustomerDto;
import com.example.online_store_backend.entity.Customer;
import com.example.online_store_backend.entity.CustomerType;
import com.example.online_store_backend.mapper.CustomerMapper;
import com.example.online_store_backend.repository.CustomerRepository;
import com.example.online_store_backend.repository.CustomerTypeRepositoy;
import com.example.online_store_backend.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerTypeRepositoy customerTypeRepositoy;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerTypeRepositoy customerTypeRepositoy) {
        this.customerRepository = customerRepository;
        this.customerTypeRepositoy = customerTypeRepositoy;
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(CustomerMapper::mapToCustomerDto)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        CustomerType customerType = customerTypeRepositoy.findById(customerDto.getCustomerTypeId())
                .orElseThrow();

        Customer customer = CustomerMapper.mapToCustomer(customerDto, customerType);
        Customer savedCustomer = customerRepository.save(customer);
        return CustomerMapper.mapToCustomerDto(savedCustomer);
    }

    @Override
    public void deleteCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow();
        customerRepository.delete(customer);
    }

    @Override
    public CustomerDto updateCustomer(Long customerId, CustomerDto customerDto) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow();

        CustomerType customerType = customerTypeRepositoy.findById(customerDto.getCustomerTypeId())
                .orElseThrow();

        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setCustomerType(customerType);

        Customer updatedCustomer = customerRepository.save(customer);
        return CustomerMapper.mapToCustomerDto(updatedCustomer);
    }
}