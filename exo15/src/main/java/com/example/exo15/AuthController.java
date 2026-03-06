package com.example.exo15;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    private final Map<String, String> users = Map.of(
            "admin", "admin123"
    );

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public Mono<AuthResponse> login(@RequestBody AuthRequest request) {
        if (request.username() == null || request.password() == null) {
            return Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Identifiants invalides"));
        }

        String storedPassword = users.get(request.username());

        if (storedPassword != null && storedPassword.equals(request.password())) {
            String token = jwtUtil.generateToken(request.username());
            return Mono.just(new AuthResponse(token));
        }

        return Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Identifiants invalides"));
    }
}

