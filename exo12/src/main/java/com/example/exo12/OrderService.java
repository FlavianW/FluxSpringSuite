package com.example.exo12;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Flux<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Mono<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public Mono<Order> createOrder(Order order) {
        order.setStatus("PENDING");
        order.setCreatedAt(LocalDateTime.now());
        return orderRepository.save(order);
    }

    public Mono<Order> updateOrderStatus(Long id, String status) {
        return orderRepository.findById(id)
                .flatMap(existing -> {
                    existing.setStatus(status);
                    return orderRepository.save(existing);
                });
    }

    public Mono<Void> deleteOrder(Long id) {
        return orderRepository.deleteById(id);
    }

    public Flux<Order> getOrdersByStatus(String status) {
        return orderRepository.findByStatus(status);
    }

    public Flux<Order> getOrdersPaged(int page, int size) {
        return orderRepository.findAll()
                .skip((long) page * size)
                .take(size);
    }
}

