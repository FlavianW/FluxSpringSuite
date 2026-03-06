# Exo 15 — Authentification JWT pour un service de gestion de projets

API réactive avec Spring WebFlux et authentification par token JWT.

## Structure du projet

- **AuthRequest.java** — Objet de requête pour le login (username, password)
- **AuthResponse.java** — Objet de réponse contenant le token JWT
- **JwtUtil.java** — Utilitaire pour générer et valider les tokens JWT
- **JwtAuthenticationFilter.java** — Filtre qui intercepte les requêtes et vérifie le JWT
- **SecurityConfig.java** — Configuration Spring Security (routes protégées / publiques)
- **AuthController.java** — Contrôleur pour l'authentification (`/api/auth/login`)
- **ProjectController.java** — Contrôleur protégé pour les projets (`/api/projects`)

## Lancer l'application

```
./mvnw spring-boot:run
```

## Tester avec Postman

### 1. Se connecter (obtenir un token)

```
POST http://localhost:8080/api/auth/login
Content-Type: application/json

{
    "username": "admin",
    "password": "admin123"
}
```

Reponse :
```json
{
    "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

### 2. Mauvais identifiants (401)

```
POST http://localhost:8080/api/auth/login
Content-Type: application/json

{
    "username": "admin",
    "password": "mauvaismdp"
}
```

### 3. Accéder aux projets avec le token

```
GET http://localhost:8080/api/projects
Authorization: Bearer <token_recu>
```

Reponse :
```json
{
    "projects": ["Projet A", "Projet B"]
}
```

### 4. Accéder aux projets sans token (401)

```
GET http://localhost:8080/api/projects
```

### 5. Accéder aux projets avec un token invalide (401)

```
GET http://localhost:8080/api/projects
Authorization: Bearer tokenbidon123
```

