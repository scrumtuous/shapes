# Online Store JPA + CSV + REST API Exercise

This project includes the complete teaching exercise:

- `Address` as `@Embeddable`
- `Customer`, `Purchase`, and `Product` JPA entities
- H2 in-memory database
- CSV import in `StoreService`
- `PURCHASE` and `REFUND` rows
- partial refunds that reduce quantity
- full refunds that remove products using `orphanRemoval`
- a `CommandLineRunner` that imports the CSV and prints a report
- a REST API under `/api`
- hardcoded REST stub data in `StoreApiService`
- `openapi.yaml`, documenting every REST endpoint
- Swagger UI support through springdoc-openapi
- the original `trades.csv` in `reference/original-trades.csv`

## Data files

- `src/main/resources/store-purchases.csv` - the CSV imported by the application
- `store-purchases.csv` - convenient root copy for students
- `reference/original-trades.csv` - the source exercise retained for comparison

## REST endpoints

- `GET /api`
- `GET /api/customers`
- `GET /api/customers?city=Boston`
- `GET /api/customers/{customerId}`
- `GET /api/customers/{customerId}/purchases`
- `GET /api/customers/{customerId}/refunds`
- `GET /api/cities`
- `GET /api/cities/{city}/customers`
- `GET /api/cities/{city}/purchases`
- `GET /api/cities/{city}/refunds`
- `GET /api/products`
- `GET /api/products?name=mouse`
- `GET /api/products/{productId}`
- `GET /api/purchases`
- `GET /api/purchases?customerId=1`
- `GET /api/purchases?city=Boston`
- `GET /api/purchases/{purchaseId}`
- `GET /api/refunds`
- `GET /api/refunds?customerId=1`
- `GET /api/refunds?city=Boston`
- `GET /api/refunds/{refundId}`
- `GET /api/summary`

## OpenAPI

The hand-authored OpenAPI contract is available in two places:

- `openapi.yaml`
- `src/main/resources/static/openapi.yaml`

When the application is running, the static file is available at:

`http://localhost:8080/openapi.yaml`

springdoc also generates documentation from the Java REST API:

- Swagger UI: `http://localhost:8080/swagger-ui.html`
- Generated JSON: `http://localhost:8080/v3/api-docs`
- Generated YAML: `http://localhost:8080/v3/api-docs.yaml`

## Run

```bash
mvn spring-boot:run
```

H2 console:

`http://localhost:8080/h2-console`

JDBC URL:

`jdbc:h2:mem:storedb`

## This version: OpenAPI annotations included

This version deliberately demonstrates Swagger/OpenAPI annotations in Java.

Look at:

- `@OpenAPIDefinition` on `StoreApplication`
- `@Tag` on `StoreRestController`
- `@Operation` on controller methods
- `@ApiResponse` / `@ApiResponses`
- `@Parameter` on query and path parameters
- `@Schema` on REST DTOs and their fields

Normal Spring and JPA annotations are also present.
