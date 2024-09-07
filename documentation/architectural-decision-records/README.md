# Index of Architectural Decision Records (ADRs)

This document provides a list of all Architectural Decision Records (ADRs) for the project. Each entry is linked to the
detailed ADR document.

## ADRs

1. [ADR-001: Use Liquibase for Database Schema Management](ADR-01-use-liquibase-for-schema-management.md)
2. [ADR-002: Use Testcontainers for Integration Tests](ADR-01-use-liquibase-for-schema-management.md)
3. [ADR-003: Decision on the Use of Service Layer Between Controller and Repository Layers](ADR-03-do-not-introduce-service-layer-for-simple-crud.md)

## How to Read ADRs

Each ADR document follows a structured format that outlines a specific architectural decision made during the project's
lifecycle. The ADRs include details about the context, decision, rationale, consequences, and any alternatives
considered. They provide a historical record of decisions and their reasoning, which can be useful for new team members,
future projects, or for revisiting past decisions.

## Guidelines for ADRs

- **Status**: Each ADR has a status indicating its current state (e.g., Proposed, Accepted, Deprecated).
- **Decision Owner**: Each ADR should have an assigned decision owner, typically the person or role responsible for the
  implementation and outcome of the decision.
- **Review Date**: Scheduled reviews of ADRs help ensure they remain relevant and are updated to reflect any new
  circumstances or insights.

## Adding New ADRs

To add a new ADR:

1. Create a new Markdown file using the naming convention `ADR-XXX-title.md`, where `XXX` is the next sequential number.
2. Document the decision using the standard ADR template.
3. Add a link to the new ADR in this index file under the ADRs section.
4. Ensure the new ADR is reviewed and approved by relevant stakeholders.

## Why Maintain ADRs?

Maintaining ADRs helps ensure that all significant architectural decisions are documented and accessible. This practice
supports:

- **Transparency**: Making the reasoning behind decisions clear to all stakeholders.
- **Consistency**: Helping maintain architectural consistency throughout the project lifecycle.
- **Accountability**: Holding decision-makers accountable for their choices.
- **Learning**: Serving as a knowledge repository that can inform future decisions and prevent repeating past mistakes.

By following these guidelines and keeping the ADR index up-to-date, we can ensure a well-documented and thoughtful
architectural strategy.
