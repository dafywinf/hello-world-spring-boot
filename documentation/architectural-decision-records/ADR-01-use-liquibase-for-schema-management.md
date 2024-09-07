# ADR-01: Use Liquibase for Database Schema Management instead of JPA/Hibernate Auto-DDL

## Status: Accepted ✅

## Context

In our Spring Boot-based application, we have the choice of using JPA/Hibernate's automatic schema generation (
`hibernate.hbm2ddl.auto`) or an external database migration tool like **Liquibase** for managing database schema
updates. Both approaches aim to keep the application’s database schema in sync with the entities defined in the
application. However, each approach has different implications in terms of control, reliability, and suitability for
production environments.

## Decision

We will use **Liquibase** for managing and updating the database schema instead of relying on JPA/Hibernate’s automatic
schema updates.

## Rationale

1. **Version Control and Traceability**
    - **Liquibase**: Liquibase uses changelogs that are version-controlled and explicitly defined in files (`XML`,
      `YAML`, `JSON`, or SQL). This allows the entire history of database changes to be tracked, reviewed, and audited.
      Every schema change is intentional and well-documented.
    - **Hibernate Auto-DDL**: Hibernate’s automatic schema generation is based on the current state of the JPA entities
      and cannot be version-controlled in a straightforward manner. It applies changes dynamically on startup, which
      could
      lead to accidental or unexpected changes without a proper audit trail.

2. **Predictability and Stability in Production**
    - **Liquibase**: Liquibase allows for carefully controlled database updates with rollback functionality. It is
      possible to define when and how schema changes are applied in various environments (development, testing,
      production), ensuring that only well-tested changes are deployed.
    - **Hibernate Auto-DDL**: In a production environment, relying on Hibernate’s auto-generation (
      `hibernate.hbm2ddl.auto`) can lead to unpredictable outcomes, such as dropping or altering tables automatically.
      This
      could result in loss of data or unexpected behaviors. The risks associated with automatic schema updates are
      generally not acceptable in production environments.

3. **Rollback and Migration Flexibility**
    - **Liquibase**: Liquibase supports rollbacks, making it easier to revert schema changes if something goes wrong.
      This is particularly useful for managing hotfixes, rollbacks, or downgrades in complex production systems.
    - **Hibernate Auto-DDL**: Hibernate’s `hbm2ddl.auto` doesn’t provide an out-of-the-box rollback mechanism for schema
      changes. Once changes are applied, reversing them becomes complicated and often involves manual intervention.

4. **Managing Complex Schema Changes**
    - **Liquibase**: Liquibase supports more complex schema operations, such as stored procedures, triggers, indexes,
      and
      even raw SQL, which may not directly map to entity changes in JPA. It also offers pre- and post-conditions,
      ensuring
      that database changes only happen when certain criteria are met.
    - **Hibernate Auto-DDL**: Hibernate’s schema update feature is limited to the automatic creation and alteration of
      tables based on entity annotations. It doesn’t handle more complex database operations well, making it unsuitable
      for
      handling advanced schema changes.

5. **Cross-team Collaboration**
    - **Liquibase**: By using Liquibase, database changes can be managed collaboratively between developers, database
      administrators (DBAs), and DevOps teams. The changelogs are shared across teams, providing transparency and
      allowing
      different roles to participate in the database update process.
    - **Hibernate Auto-DDL**: Since Hibernate auto-DDL relies on the state of the Java code, it doesn’t allow for the
      same level of collaboration or review by DBAs or operations teams. Changes happen implicitly based on the Java
      entity
      state, limiting visibility outside of the development team.

6. **Better for CI/CD Pipelines**
    - **Liquibase**: Liquibase fits well into continuous integration and continuous deployment (CI/CD) pipelines. It
      allows for running schema migrations as part of the deployment process, providing better automation and control
      over
      database changes in different environments (e.g., dev, QA, staging, production).
    - **Hibernate Auto-DDL**: Auto-DDL may introduce changes at startup without testing them in a proper environment,
      leading to issues during runtime. It also doesn’t fit well into CI/CD pipelines where more explicit database
      change
      management is required.

## Consequences

- **Increased Control Over Schema Changes**: Using Liquibase provides us with better control and insight into how and
  when the database schema is modified.
- **Initial Overhead**: Liquibase introduces some upfront configuration and maintenance overhead, as developers need to
  create and maintain changelogs. However, this overhead is outweighed by the benefits in production stability and
  long-term maintainability.
- **Rollback Capability**: We gain the ability to roll back schema changes in case of issues, which is difficult to
  achieve with Hibernate auto-DDL.
- **Separation of Concerns**: This approach separates database schema management from entity lifecycle management,
  making the system easier to maintain and reason about.

## Alternatives Considered

1. **Hibernate `hbm2ddl.auto` in Production**: While convenient for rapid development, Hibernate’s automatic schema
   update is risky and unsuitable for production use, especially in scenarios where we need strict control over database
   modifications.
2. **Flyway**: Flyway is another database migration tool similar to Liquibase. It’s a valid alternative and works with
   SQL migrations. However, we chose Liquibase because of its flexibility with multiple changelog formats (YAML, XML,
   JSON), advanced features, and richer support for rollback functionality.

## Related Decisions

- The decision to use **Liquibase** will impact how we handle database migrations in all environments (local
  development, staging, production).
- Liquibase will be integrated into our CI/CD pipelines to ensure schema migrations are applied consistently across
  environments.

## Next Steps

- Set up Liquibase with a master changelog (`db.changelog-master.yaml`), and define all future database changes in
  individual changelog files.
- Ensure all developers are familiar with Liquibase syntax and changelog management.
- Integrate Liquibase migrations into the CI/CD pipeline, allowing for automated schema updates as part of the
  deployment process.

**Decision Date**: September 7, 2024
