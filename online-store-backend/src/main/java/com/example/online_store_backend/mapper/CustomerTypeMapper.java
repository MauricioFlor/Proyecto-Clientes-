package com.example.online_store_backend.mapper;

import com.example.online_store_backend.dto.CustomerTypeDto;
import com.example.online_store_backend.entity.CustomerType;

public class CustomerTypeMapper {

    // Convierte de entidad a DTO
    public static CustomerTypeDto toDto(CustomerType customerType) {
        if (customerType == null) {
            return null;
        }
        return new CustomerTypeDto(
                customerType.getId(),
                customerType.getCustomerTypeName(),
                customerType.getCustomerDescription()
        );
    }

    // Convierte de DTO a entidad
    public static CustomerType toEntity(CustomerTypeDto customerTypeDto) {
        if (customerTypeDto == null) {
            return null;
        }
        CustomerType customerType = new CustomerType();
        customerType.setId(customerTypeDto.getId());
        customerType.setCustomerTypeName(customerTypeDto.getCustomerTypeName());
        customerType.setCustomerDescription(customerTypeDto.getCustomerDescription());
        return customerType;
    }
}