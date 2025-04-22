
package com.example.online_store_backend.repository;

import com.example.online_store_backend.entity.CustomerType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerTypeRepositoy extends JpaRepository<CustomerType, Long> {
    // Custom query methods can be defined here if needed

}

    

