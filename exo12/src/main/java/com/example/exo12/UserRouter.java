package com.example.exo12;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class UserRouter {

    @Bean
    public RouterFunction<ServerResponse> userRoutes(UserHandler handler) {
        return RouterFunctions.route()
                .path("/api/users", builder -> builder
                        .GET("", accept(MediaType.APPLICATION_JSON), handler::getAllUsers)
                        .GET("/{id}", accept(MediaType.APPLICATION_JSON), handler::getUserById)
                        .POST("", contentType(MediaType.APPLICATION_JSON), handler::createUser)
                        .PUT("/{id}", contentType(MediaType.APPLICATION_JSON), handler::updateUser)
                        .DELETE("/{id}", handler::deleteUser)
                )
                .build();
    }
}

