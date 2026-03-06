package com.example.exo11;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {

    private final Map<Long, User> users = new ConcurrentHashMap<>();
    private final AtomicLong counter = new AtomicLong(1);

    public Flux<User> getAllUsers() {
        return Flux.fromIterable(users.values());
    }

    public Mono<User> getUserById(Long id) {
        User user = users.get(id);
        return user != null ? Mono.just(user) : Mono.empty();
    }

    public Mono<User> createUser(User user) {
        long id = counter.getAndIncrement();
        user.setId(id);
        users.put(id, user);
        return Mono.just(user);
    }

    public Mono<User> updateUser(Long id, User user) {
        if (!users.containsKey(id)) {
            return Mono.empty();
        }
        user.setId(id);
        users.put(id, user);
        return Mono.just(user);
    }

    public Mono<Void> deleteUser(Long id) {
        users.remove(id);
        return Mono.empty();
    }
}
