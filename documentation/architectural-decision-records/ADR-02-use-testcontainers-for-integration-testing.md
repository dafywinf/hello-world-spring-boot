# ADR-02: Use Testcontainers for Integration Tests Instead of Mocking Services

## Status: Accepted ✅

## Context

In our application, we need a strategy for testing external dependencies like the database and Kafka. We have two
primary approaches to choose from:

- **Mocking**: Mocking these services during integration tests.
- **Testcontainers**: Using Testcontainers to run real instances of services like the database and Kafka in Docker
  containers.

Unit tests will specifically focus on testing the business logic, with mocked services where necessary. Integration
tests, on the other hand, should test the interaction with external systems to ensure everything works as expected.

## Decision

We have decided to use **Testcontainers** to spin up real instances of external services (e.g., databases, Kafka) in
Docker containers during our integration tests rather than mocking them.

## Rationale

1. **Realistic Testing Environment**
    - **Testcontainers**: By using Testcontainers, we create a real environment that closely mimics production. The
      database, Kafka broker, or other services will behave exactly as they would in a live system, ensuring that
      integration tests are highly reliable and uncover any issues that may arise from differences in service
      configurations.
    - **Mocking**: Mocks and stubs are often incomplete representations of real services. While effective in unit tests,
      they can miss edge cases or incorrect assumptions about the service’s behavior, leading to integration issues that
      may only surface in production.

2. **Improved Confidence in Integration**
    - **Testcontainers**: Since we are testing against real service instances, Testcontainers ensures that our tests
      check actual interactions with the database, Kafka, etc., verifying end-to-end integration. This results in a much
      higher level of confidence that the system will behave as expected in production.
    - **Mocking**: Mocks can simulate interactions but can never fully replicate the behavior of external services. This
      leaves room for unexpected errors during deployment when the real services are used, such as database connection
      issues or Kafka broker configurations not being tested properly.

3. **Reduced Risk of Production Failures**
    - **Testcontainers**: By using real services in our tests, we significantly reduce the risk of issues arising in
      production that were missed during testing. For example, schema mismatches, misconfigured Kafka topics, or
      unsupported SQL queries are detected during the integration tests when using actual services.
    - **Mocking**: When services are mocked, it's easy to overlook production-specific issues such as missing
      environment variables, incompatible configurations, or unsupported behaviours, which can lead to errors
      post-deployment.

4. **Dynamic Configuration and Portability**
    - **Testcontainers**: Testcontainers is highly flexible and portable. It allows us to dynamically configure and test
      different environments (e.g., different versions of databases or Kafka). Moreover, it ensures that tests run
      consistently across local machines, CI pipelines, and different environments.
    - **Mocking**: Mocks do not provide this level of flexibility, as they are static representations of service
      behaviour. They also need to be manually updated to reflect any external changes in the services, leading to
      maintenance overhead and potential desynchronisation with the real services.

5. **Simpler Setup Compared to Embedded Servers**
    - **Testcontainers**: Unlike embedded databases or Kafka brokers, Testcontainers runs real services in Docker
      containers, which makes the setup much closer to a real production environment. It avoids some of the pitfalls
      associated with embedded databases or Kafka instances that may have subtle differences from their standalone
      counterparts.
    - **Mocking**: While mocking requires less resource usage, it adds complexity in terms of configuring realistic mock
      behaviours and ensuring that mocks stay in sync with real services, which is error-prone.

6. **Separation of Concerns Between Unit and Integration Tests**
    - **Unit Tests**: Unit tests will continue to focus solely on testing business logic using mocks or stubs where
      appropriate. This will keep unit tests fast, lightweight, and focused on internal logic.
    - **Integration Tests with Testcontainers**: Integration tests will focus on ensuring that the components of the
      system work together correctly, using real external services to catch issues that unit tests cannot cover.

## Consequences

- **Increased Reliability of Integration Tests**: Using Testcontainers ensures a high degree of reliability in testing
  integrations with external services. Potential issues with real services can be detected early in the development
  cycle.
- **Longer Test Execution Times**: Running real services in Docker containers will increase the execution time of
  integration tests compared to mocks. However, this trade-off is acceptable given the increase in test accuracy and
  reliability.
- **Docker Dependency**: Testcontainers relies on Docker, so developers and the CI/CD pipeline will require Docker to be
  installed. This adds a slight setup overhead but is manageable given Docker’s ubiquity in modern development
  environments.

## Alternatives Considered

1. **Mocking External Services**: While fast and simple, mocks often fail to represent the full behaviour of external
   services, making them less suitable for integration tests. Mocks are best suited for unit tests where testing
   external dependencies is not a priority.

2. **Embedded Databases and Kafka**: We could use embedded versions of services like H2 (for databases) or an in-memory
   Kafka broker. However, these solutions do not fully replicate the behaviour of real-world services and can lead to
   subtle issues when deployed in production. Additionally, they often don’t support the full feature set of the actual
   services, limiting their effectiveness for integration tests.

## Related Decisions

- We will use **mocks** and **stubs** in unit tests to ensure that business logic is tested in isolation.
- **Testcontainers** will be integrated into our CI/CD pipeline to ensure consistency and reliability of integration
  tests.

## Next Steps

- Configure Testcontainers for the required services, such as PostgreSQL (or other relational databases) and Kafka.
- Ensure the development and CI environments have Docker installed and running to support Testcontainers.
- Define a clear distinction between unit tests (focusing on business logic with mocks) and integration tests (focusing
  on real service interactions with Testcontainers).

**Decision Date**: September 7, 2024

# Appendix

## Supporting Technical Research

* [How to use TestContainers with Spring Boot applcations for Integration Testing](https://bell-sw.com/blog/how-to-use-testcontainers-with-spring-boot-applications-for-integration-testing/)
* [Spring Boot @ServiceConnection Example](https://mkyong.com/spring-boot/spring-boot-serviceconnection-example/#:~:text=Spring%20Boot%203.1%20introduced%20%40ServiceConnection,a%20remote%20service%20(Testcontainers).)
* [Configuration Configuration with Context Customizers](https://docs.spring.io/spring-framework/reference/testing/testcontext-framework/ctx-management/context-customizers.html)
