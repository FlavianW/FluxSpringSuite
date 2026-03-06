# Exo 12 — API réactive de gestion des commandes

API réactive avec Spring WebFlux et R2DBC (base H2 en mémoire).

## Structure du projet

- `Order.java` — Entité commande (id, customerName, totalAmount, status, createdAt)
- `OrderRepository.java` — Repository réactif (ReactiveCrudRepository)
- `OrderService.java` — Logique métier
- `OrderController.java` — Contrôleur REST
- `schema.sql` — Script de création de la table

## Lancer l'application

```
./mvnw spring-boot:run
```

## Tester avec Postman

### 1. Créer une commande

```
POST http://localhost:8080/api/orders
Content-Type: application/json

{
    "customerName": "Alice Dupont",
    "totalAmount": 99.99
}
```

### 2. Créer une seconde commande

```
POST http://localhost:8080/api/orders
Content-Type: application/json

{
    "customerName": "Bob Martin",
    "totalAmount": 149.50
}
```

### 3. Récupérer toutes les commandes

```
GET http://localhost:8080/api/orders
```

### 4. Récupérer une commande par ID

```
GET http://localhost:8080/api/orders/1
```

### 5. Récupérer une commande inexistante (404)

```
GET http://localhost:8080/api/orders/999
```

### 6. Mettre à jour le statut d'une commande

```
PUT http://localhost:8080/api/orders/1
Content-Type: application/json

{
    "status": "SHIPPED"
}
```

### 7. Mettre à jour un statut vers DELIVERED

```
PUT http://localhost:8080/api/orders/1
Content-Type: application/json

{
    "status": "DELIVERED"
}
```

### 8. Mettre à jour une commande inexistante (404)

```
PUT http://localhost:8080/api/orders/999
Content-Type: application/json

{
    "status": "SHIPPED"
}
```

### 9. Rechercher par statut

```
GET http://localhost:8080/api/orders/search?status=PENDING
```

### 10. Pagination

```
GET http://localhost:8080/api/orders/paged?page=0&size=5
```

### 11. Supprimer une commande (204)

```
DELETE http://localhost:8080/api/orders/2
```

### 12. Vérifier la suppression

```
GET http://localhost:8080/api/orders
```
