Exercice 14 — Securisation d'une API de reservation de salles

API reactive avec Spring WebFlux + Spring Security.

Structure du projet

- `Room.java` — Modele salle (`id`, `name`)
- `RoomRequest.java` — Corps de requete pour creation
- `RoomService.java` — Logique metier (memoire)
- `RoomController.java` — Controleur REST
- `SecurityConfig.java` — Configuration des roles + erreurs 401/403

Utilisateurs de test

- USER : `user` / `user123`
- ADMIN : `admin` / `admin123`

Lancer l'application

```bash
./mvnw spring-boot:run
```

Tester avec Postman

1. Liste des salles (USER ou ADMIN)
GET http://localhost:8080/api/rooms
Authorization: Basic Auth (`user` / `user123`)

2. Ajouter une salle (ADMIN)
POST http://localhost:8080/api/rooms
Authorization: Basic Auth (`admin` / `admin123`)
Content-Type: application/json

{
  "name": "Salle Innovation"
}

3. Supprimer une salle (ADMIN)
DELETE http://localhost:8080/api/rooms/1
Authorization: Basic Auth (`admin` / `admin123`)

4. Test non authentifie (401)
GET http://localhost:8080/api/rooms

Reponse attendue:

{
  "error": "UNAUTHORIZED",
  "message": "Vous devez vous connecter pour acceder a cette ressource"
}

5. Test acces interdit (403)
POST http://localhost:8080/api/rooms
Authorization: Basic Auth (`user` / `user123`)
Content-Type: application/json

{
  "name": "Salle RH"
}

Reponse attendue:

{
  "error": "FORBIDDEN",
  "message": "Vous n'avez pas les droits necessaires pour acceder a cette ressource"
}

