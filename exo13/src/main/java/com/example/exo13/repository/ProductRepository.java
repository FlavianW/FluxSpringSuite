package com.example.exo13.repository;

import com.example.exo13.domain.Product;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;

public interface ProductRepository extends R2dbcRepository<Product, Long> {
    Flux<Product> findByNameContainingIgnoreCase(String name);
}

