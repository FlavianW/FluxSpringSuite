package com.example.exo13.service;

import com.example.exo13.domain.Product;
import com.example.exo13.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Flux<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Mono<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Mono<Product> createProduct(Product product) {
        return productRepository.save(product);
    }

    public Mono<Product> updateProduct(Long id, Product productDetails) {
        return productRepository.findById(id)
                .flatMap(existingProduct -> {
                    if (productDetails.getName() != null) {
                        existingProduct.setName(productDetails.getName());
                    }
                    if (productDetails.getPrice() != null) {
                        existingProduct.setPrice(productDetails.getPrice());
                    }
                    if (productDetails.getStock() != null) {
                        existingProduct.setStock(productDetails.getStock());
                    }
                    return productRepository.save(existingProduct);
                });
    }

    public Mono<Void> deleteProduct(Long id) {
        return productRepository.deleteById(id);
    }

    public Flux<Product> searchProductsByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    public Mono<Product> buyProduct(Long id, Integer quantity) {
        return productRepository.findById(id)
                .flatMap(product -> {
                    if (quantity <= 0) {
                        return Mono.error(new IllegalArgumentException("La quantité doit être supérieure à 0"));
                    }
                    if (product.getStock() < quantity) {
                        return Mono.error(new IllegalArgumentException("Stock insuffisant. Stock disponible: " + product.getStock()));
                    }
                    product.setStock(product.getStock() - quantity);
                    return productRepository.save(product);
                });
    }
}

