# T1 Kafka Application

## Description

This project is a Spring Boot application that uses Kafka for processing and storing metrics. It includes microservices for producing and consuming metrics, as well as integration with PostgreSQL and Swagger for API documentation.

## Project Structure

1. **Kafka Producer Service**: Sends metrics to Kafka.
2. **Kafka Consumer Service**: Consumes metrics from Kafka and stores them in the database.
3. **Swagger UI**: Interactive API documentation.

## Setup and Configuration

### 1. Environment Setup

- **Kafka** and **PostgreSQL** are configured and running in Docker containers.
- **JDK 17** should be installed on your system.

### 2. Configuration

Kafka and PostgreSQL settings should be specified in `application.properties` or `application.yml`:

```properties
# Kafka settings
kafka.bootstrap-servers=localhost:9092
kafka.group-id=metrics-topic

# PostgreSQL settings
spring.datasource.url=jdbc:postgresql://localhost:5432/mydatabase
spring.datasource.username=myusername
spring.datasource.password=mypassword
```
### 3. Swagger 
Swagger UI is available at http://localhost:8080/swagger-ui/index.html
