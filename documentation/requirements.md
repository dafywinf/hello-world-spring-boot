# Hello World - Spring Boot Application

## 1. Functional Requirements

### Hello World Endpoint:

- **URL:** `/api/hello`
- **HTTP Method:** `GET`
- **Response:**
    - A JSON object containing:
        - A `message` field with the value `"Hello World"`.
        - A `date` field with the current date and time, fetched from the PostgreSQL database.

## 2. Non-Functional Requirements

### Performance:

- The application should be able to handle concurrent requests efficiently.

### Scalability:

- The application should be easily scalable through Docker containerization.

### Maintainability:

- Code should be well-documented and follow best practices for Spring Boot applications.

### Security:

- The endpoint should be accessible without authentication but follow security best practices to prevent
  vulnerabilities (e.g., SQL injection).

## 3. Design Requirements

### Spring Boot Framework:

- Use Spring Boot to build the application, leveraging its auto-configuration and simplicity for RESTful service
  creation.

### PostgreSQL Database:

- The current date and time should be fetched from a PostgreSQL database.
- Use an appropriate table to store and retrieve the date.

### Data Model:

- A table named `hello_logs` with columns:
    - `id`: Primary key (auto-generated).
    - `generation_date`: Timestamp, storing the date and time of generation.

### Docker Integration:

- Create a `Dockerfile` to containerize the Spring Boot application.
- Use Docker Compose to manage the application and PostgreSQL database containers.

### Date Generation Logic:

- The date should be stored to the database using a Spring Data repository.
- Implement a service layer that interacts with the repository to fetch the date.

## 4. Implementation Details

### Spring Boot Application Structure:

- **Controller Layer:**
    - A REST controller class with a method to handle GET requests at `/api/hello`.
- **Service Layer:**
    - A service class responsible for interacting with the repository to fetch the date.
- **Repository Layer:**
    - A repository interface extending `JpaRepository` to handle database interactions.
- **Model Layer:**
    - An entity class representing the `generation_dates` table.

### Docker Configuration:

- **Dockerfile:** Defines the environment and dependencies needed to run the Spring Boot application.
- **app.yml:** Configuration to run the Spring Boot application and PostgreSQL database in separate containers.

## 5. Environment Setup

### Development Tools:

- **Java 17 or higher** for development.
- **Spring Boot 3.x** for building the application.
- **PostgreSQL 13 or higher** as the database.
- **Docker & Docker Compose** for containerization.

### Dependencies:

- Spring Boot Starter Web (for creating RESTful services).
- Spring Boot Starter Data JPA (for database interaction).
- PostgreSQL Driver (for connecting to the PostgreSQL database).
- Spring Boot Starter Test (for testing purposes).

## 6. Testing Requirements

### Unit Testing:

- Write unit tests for the controller, service, and repository layers.

### Integration Testing:

- Use **Testcontainers** to spin up a temporary PostgreSQL database for integration testing.
    - **Testcontainers** allows the integration tests to run in an isolated environment by using lightweight, disposable
      containers.
    - The temporary PostgreSQL container should mirror the production environment as closely as possible.
    - Integration tests should verify that the application can correctly interact with the PostgreSQL database,
      including saving and retrieving the date.

### Docker Testing:

- Test the Dockerized application to ensure it runs correctly in a containerized environment.
    - Ensure the Spring Boot application and PostgreSQL containers communicate effectively.
    - Validate that the Docker setup behaves consistently across different environments.

### Additional Testing Considerations:

- **Mocking:** Use mocking frameworks like Mockito for unit tests where database interaction is not required.
- **Test Coverage:** Ensure high code coverage with tests across all layers of the application (Controller, Service,
  Repository).

## 7. Documentation

### README File:

- Provide setup instructions for running the application locally and within a Docker container.

### API Documentation:

- Use Swagger or similar tools to document the API endpoint.

## 8. Deployment

### Docker Deployment:

- The application should be deployable using Docker, with PostgreSQL running in a separate container.

### Port Configuration:

- The application should run on port `8080` by default, with PostgreSQL on port `5432`.

## 9. Version Control

### Git Repository:

- The source code should be managed in a Git repository, with appropriate version control practices (branches, commits,
  pull requests).

## 10. Continuous Integration (CI)

### CI Integration

- The project should include a Continuous Integration (CI) pipeline to automate the build, test, and deployment
  processes.
- The CI pipeline should be configured to:
    - Trigger builds on each push to the repository or when pull requests are created.
    - Run automated tests to ensure code quality and functionality before merging changes.
    - Build Docker images and push them to a container registry as part of the deployment process.
    - Deploy the application to a staging environment for further testing before production deployment.

### CI Tools

- The CI pipeline should be implemented using a CI/CD tool such as GitHub Actions, GitLab CI, Jenkins, or CircleCI.
- The CI configuration files (e.g., `.github/workflows`, `.gitlab-ci.yml`, `Jenkinsfile`) should be included in the
  repository and maintained alongside the source code.

### Monitoring and Notifications

- The CI system should provide notifications for build statuses, test results, and deployment statuses to the
  development team via email, Slack, or another communication channel.
- The CI pipeline should include steps to monitor and log build and deployment processes for troubleshooting and
  auditing purposes.

## 11. Dependency Management

### Automatic Dependency Updates

- The project should use Renovate or a similar tool to automatically update dependencies.
- Renovate should be configured to:
    - Regularly check for updates to dependencies and propose pull requests for new versions.
    - Include clear release notes and changelog information in the update pull requests.
    - Allow manual review of updates before they are merged into the main branch.
- Renovate configuration files (e.g., `renovate.json`) should be included in the repository and maintained alongside the
  source code.
