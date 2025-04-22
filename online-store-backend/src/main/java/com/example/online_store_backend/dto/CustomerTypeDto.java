package com.example.online_store_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerTypeDto {
    private Long id;
    private String customerTypeName;
    private String customerDescription;
}
