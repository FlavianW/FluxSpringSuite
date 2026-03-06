package com.example.exo14.room;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class RoomService {

    private final Map<Long, Room> rooms = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(0);

    public RoomService() {
        Room first = new Room(idGenerator.incrementAndGet(), "Salle A");
        Room second = new Room(idGenerator.incrementAndGet(), "Salle B");
        rooms.put(first.id(), first);
        rooms.put(second.id(), second);
    }

    public Flux<Room> findAll() {
        return Flux.fromIterable(rooms.values());
    }

    public Mono<Room> create(String name) {
        Room room = new Room(idGenerator.incrementAndGet(), name);
        rooms.put(room.id(), room);
        return Mono.just(room);
    }

    public Mono<Boolean> deleteById(Long id) {
        return Mono.just(rooms.remove(id) != null);
    }
}

