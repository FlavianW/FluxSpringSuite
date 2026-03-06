# Exo 12 — API de gestion des commandes


## Lancer l'application

```
./mvnw spring-boot:run
```

## Tester avec Postman

### Créer un utilisateur

```
POST http://localhost:8080/api/users
Content-Type: application/json

{
    "name": "Alice Dupont",
    "email": "alice@example.com",
    "active": true
}
```

### Créer un second utilisateur

```
POST http://localhost:8080/api/users
Content-Type: application/json

{
    "name": "Bob Martin",
    "email": "bob@example.com",
    "active": false
}
```

### Récupérer tous les utilisateurs

```
GET http://localhost:8080/api/users
```

### Récupérer un utilisateur par ID

```
GET http://localhost:8080/api/users/1
```

### Récupérer un utilisateur inexistant (doit renvoyer 404)

```
GET http://localhost:8080/api/users/999
```

### Modifier un utilisateur

```
PUT http://localhost:8080/api/users/1
Content-Type: application/json

{
    "name": "Alice Durand",
    "email": "alice.durand@example.com",
    "active": false
}
```

### Modifier un utilisateur inexistant (doit renvoyer 404)

```
PUT http://localhost:8080/api/users/999
Content-Type: application/json

{
    "name": "Inconnu",
    "email": "inconnu@example.com",
    "active": true
}
```

### Supprimer un utilisateur (doit renvoyer 204)

```
DELETE http://localhost:8080/api/users/2
```

### Vérifier la suppression

```
GET http://localhost:8080/api/users
```

