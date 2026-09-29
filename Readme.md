# GateFort
### Custom API Gateway & Management Platform

GateFort is an educational API Gateway project built with Java and Spring Boot. It is designed to centralize API request routing, security, traffic controls, configuration management, and access analytics across multiple backend services.

The architecture separates real-time request handling from management and analytics workloads, helping keep the gateway focused on forwarding requests while supporting asynchronous processing for events and configuration changes.

> **Project status:** Update the feature checklist below to reflect what is implemented in your repository. Some capabilities may still be planned.

---

## Architecture

GateFort is designed around three independent Spring Boot services:

| Service | Responsibility |
|---|---|
| **Smart Gateway Service (Data Plane)** | Receives client requests, resolves configured routes, forwards requests to downstream services, and publishes access events. |
| **API Management Service (Control Plane)** | Manages users, roles, route definitions, and policies; issues JWTs and publishes configuration-change events. |
| **Analytics Service** | Consumes access events, deduplicates them, stores analytics data, and supports usage statistics. |

The platform also uses small downstream **Catalog** and **Order** services as demo targets.

### High-level request flow

```text
Client
  |
  v
Smart Gateway
  |-- Validate JWT and route-level permissions
  |-- Apply traffic policies (e.g., rate limits)
  |-- Resolve active route
  |-- Forward request
  v
Catalog / Order Services
  |
  v
Gateway publishes access event
  |
  v
Kafka --> Analytics Service --> MongoDB
```

Route and policy configuration is managed through the API Management Service and stored in MySQL. The gateway is designed to keep active routes in memory and refresh them when it receives configuration-change notifications.

---

## Technology Stack

| Area | Technologies |
|---|---|
| Language | Java |
| Backend framework | Spring Boot, Spring Security |
| API security | JWT, role-based access control |
| Databases | MySQL, MongoDB |
| Caching and shared state | Redis |
| Event streaming | Apache Kafka |
| Communication | REST APIs, HTTP |
| Build tool | Maven |
| Version control | Git, GitHub |
| Frontend (management UI, if enabled) | React |

---

## Key Capabilities

Mark each item as complete only after it has been implemented and tested.

- [ ] Dynamic routing to multiple downstream services
- [ ] JWT validation and route-level role authorization
- [ ] Correlation/request IDs for request tracing
- [ ] Redis-backed distributed rate limiting
- [ ] POST request idempotency
- [ ] Configurable path and header transformations
- [ ] Configurable downstream timeouts
- [ ] Consistent error responses
- [ ] MySQL-backed route and policy configuration
- [ ] Kafka notifications for route configuration changes
- [ ] Kafka-based access-event publishing and consumption
- [ ] MongoDB-backed analytics storage and statistics
- [ ] Audit logging for management operations

---

## Data and Event Flow

- **MySQL:** Stores route definitions, policies, and management configuration.
- **Redis:** Stores shared, short-lived state such as rate-limit counters and idempotency records.
- **Kafka:** Carries access events and route-configuration change notifications asynchronously.
- **MongoDB:** Stores processed access analytics.

The gateway is designed not to make a synchronous analytics-database call for every incoming request. Instead, it publishes events for asynchronous processing.

---

## Getting Started

### Prerequisites

Install the following before running the services:

- Java (use the version configured by the project)
- Maven
- MySQL
- Redis
- Apache Kafka
- MongoDB
- Git

### Clone the repository

```bash
git clone https://github.com/sonivipin119/StreamWatcher.git
cd StreamWatcher
```

> If GateFort is moved to its own repository, replace the repository URL and directory name above.

### Configure environment

Create or update each service's configuration with the appropriate database, Redis, Kafka, JWT, and downstream-service settings.

**Do not commit secrets** such as database passwords, signing keys, or API tokens. Prefer environment variables or an untracked local configuration file.

Example configuration values (replace with your actual property names):

```properties
# Database
MYSQL_URL=jdbc:mysql://localhost:3306/gatefort
MYSQL_USERNAME=your_username
MYSQL_PASSWORD=your_password

# Redis
REDIS_HOST=localhost
REDIS_PORT=6379

# Kafka
KAFKA_BOOTSTRAP_SERVERS=localhost:9092

# MongoDB
MONGODB_URI=mongodb://localhost:27017/gatefort_analytics

# JWT
JWT_SECRET=replace_with_a_secure_local_secret
```

### Build and run

Build each Spring Boot service from its own directory:

```bash
mvn clean package
```

Then start the service using the command below (adjust the JAR name to match the generated artifact):

```bash
java -jar target/<service-name>.jar
```

Start the supporting infrastructure (MySQL, Redis, Kafka, and MongoDB) before launching the services. Use the actual ports and application profiles configured in your project.

---

## API Documentation and Testing

Use Postman or another HTTP client to test the endpoints. Add links here once API documentation is available:

- Gateway API: `TODO`
- Management API: `TODO`
- Analytics API: `TODO`

---

## Project Structure

The intended repository organization is:

```text
GateFort/
├── smart-gateway-service/
├── api-management-service/
├── analytics-service/
├── catalog-service/
├── order-service/
└── README.md
```

Adjust this tree to match the actual repository structure.

---

## Roadmap

- [ ] Complete service startup and health checks
- [ ] Implement and verify dynamic route registration
- [ ] Add gateway authentication and authorization
- [ ] Add Redis-backed rate limiting and idempotency
- [ ] Integrate Kafka event publishing and consumption
- [ ] Persist and expose analytics
- [ ] Add integration tests and API documentation
- [ ] Add local development orchestration (e.g., Docker Compose)

---

## Learning Objectives

GateFort is intended to provide hands-on experience with:

- API Gateway and reverse-proxy fundamentals
- Data-plane and control-plane separation
- Secure API design with JWT and role-based access control
- Distributed rate limiting and idempotent request handling
- Event-driven architecture with Kafka
- Choosing storage systems for operational configuration and analytics
- Designing modular Spring Boot services

---

## Author

**Vipin Soni**

- GitHub: [sonivipin119](https://github.com/sonivipin119)
- LinkedIn: [Vipin Soni](https://www.linkedin.com/in/vipin-soni-416a61257/)
