package com.example.exo14.room;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@SpringBootTest
@AutoConfigureWebTestClient
class RoomApiSecurityTests {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void getRoomsWithoutAuthReturns401() {
        webTestClient.get()
                .uri("/api/rooms")
                .exchange()
                .expectStatus().isUnauthorized()
                .expectBody()
                .jsonPath("$.error").isEqualTo("UNAUTHORIZED");
    }

    @Test
    void userCanReadRooms() {
        webTestClient.get()
                .uri("/api/rooms")
                .header(HttpHeaders.AUTHORIZATION, basic("user", "user123"))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].id").exists();
    }

    @Test
    void userCannotCreateRoomReturns403() {
        webTestClient.post()
                .uri("/api/rooms")
                .header(HttpHeaders.AUTHORIZATION, basic("user", "user123"))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("{\"name\":\"Salle C\"}")
                .exchange()
                .expectStatus().isForbidden()
                .expectBody()
                .jsonPath("$.error").isEqualTo("FORBIDDEN");
    }

    @Test
    void adminCanCreateAndDeleteRoom() {
        Room created = webTestClient.post()
                .uri("/api/rooms")
                .header(HttpHeaders.AUTHORIZATION, basic("admin", "admin123"))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("{\"name\":\"Salle D\"}")
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Room.class)
                .returnResult()
                .getResponseBody();

        webTestClient.delete()
                .uri("/api/rooms/{id}", created.id())
                .header(HttpHeaders.AUTHORIZATION, basic("admin", "admin123"))
                .exchange()
                .expectStatus().isNoContent();
    }

    private String basic(String username, String password) {
        String credentials = username + ":" + password;
        return "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes(StandardCharsets.UTF_8));
    }
}
