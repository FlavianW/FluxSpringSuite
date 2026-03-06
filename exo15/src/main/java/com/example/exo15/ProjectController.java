package com.example.exo15;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final Map<String, List<String>> userProjects = Map.of(
            "admin", List.of("Projet A", "Projet B")
    );

    @GetMapping
    public Mono<Map<String, List<String>>> getProjects(@AuthenticationPrincipal String username) {
        List<String> projects = userProjects.getOrDefault(username, List.of());
        return Mono.just(Map.of("projects", projects));
    }
}

