package com.example.online_store_backend.dto;

import jakarta.validation.constraints.NotBlank;
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
    
    @NotBlank(message = "El nombre del tipo no puede estar vacío.")
    private String customerTypeName;
    
    @NotBlank(message = "La descripción no puede estar vacía.")
    private String customerDescription;
}
