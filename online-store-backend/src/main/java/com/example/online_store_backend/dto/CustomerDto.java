package com.example.online_store_backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío.")
    private String name;
    
    @NotBlank(message = "El correo electrónico no puede estar vacío.")
    @Email(message = "El correo electrónico debe estar en un formato válido.", regexp = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
    private String email;
    
    private Long customerTypeId; // ID del tipo de cliente
}