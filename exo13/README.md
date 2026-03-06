# Exo 13 — API de gestion des produits avec stock

API réactive avec Spring WebFlux et R2DBC (base H2 en mémoire).

## Structure du projet

- **Product.java** — Entité produit (id, name, price, stock)
- **ProductRepository.java** — Repository réactif (R2dbcRepository)
- **ProductService.java** — Logique métier
- **ProductController.java** — Contrôleur REST
- **schema.sql** — Script de création de la table

## Lancer l'application

```bash
./mvnw spring-boot:run
```

## Tester avec Postman

### 1. Créer un produit

```http
POST http://localhost:8080/api/products
Content-Type: application/json

{
    "name": "IPhone 17",
    "price": 999.99,
    "stock": 50
}
```

### 2. Créer un second produit

```http
POST http://localhost:8080/api/products
Content-Type: application/json

{
    "name": "Samsung Galaxy",
    "price": 799.99,
    "stock": 30
}
```

### 3. Récupérer tous les produits

```http
GET http://localhost:8080/api/products
```

### 4. Récupérer un produit par ID

```http
GET http://localhost:8080/api/products/1
```

### 5. Récupérer un produit inexistant (404)

```http
GET http://localhost:8080/api/products/999
```

### 6. Mettre à jour le prix d'un produit

```http
PUT http://localhost:8080/api/products/1
Content-Type: application/json

{
    "price": 899.99
}
```

### 7. Mettre à jour le stock d'un produit

```http
PUT http://localhost:8080/api/products/1
Content-Type: application/json

{
    "stock": 45
}
```

### 8. Mettre à jour un produit inexistant (404)

```http
PUT http://localhost:8080/api/products/999
Content-Type: application/json

{
    "name": "Produit inexistant"
}
```

### 9. Rechercher par nom

```http
GET http://localhost:8080/api/products/search?name=iPhone
```

### 10. Acheter un produit (réduire le stock)

```http
PUT http://localhost:8080/api/products/1/buy?quantity=5
```

### 11. Essayer d'acheter plus que le stock disponible (erreur)

```http
PUT http://localhost:8080/api/products/1/buy?quantity=100
```

### 12. Supprimer un produit (204)

```http
DELETE http://localhost:8080/api/products/2
```

### 13. Vérifier la suppression

```http
GET http://localhost:8080/api/products
```

