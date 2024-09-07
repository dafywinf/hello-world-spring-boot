# ADR-03: Decision on the Use of Service Layer Between Controller and Repository Layers

## Status: Accepted ✅

## Context

In our application architecture, the choice of whether to introduce a service layer between the controller and
repository layers needs careful consideration. The goal is to determine when the additional layer is necessary and when
it may be redundant.

## Decision

We have decided not to always introduce a service layer between the controller and repository layers. Instead, we will
implement the service layer selectively, based on its value in managing business logic.

## Rationale

1. **Simplicity vs. Complexity**:
   Introducing a service layer adds an extra layer of abstraction. For simple CRUD operations where business logic is
   minimal or non-existent, this additional layer can be redundant. In such cases, connecting the controller directly to
   the repository can simplify the architecture without compromising functionality.

2. **Maintainability**:
   A service layer centralises business logic and improves modularity. However, for operations with minimal business
   logic, maintaining this layer might not provide significant benefits and could add unnecessary maintenance overhead.
   For these simpler scenarios, direct interaction between controllers and repositories helps keep the codebase more
   manageable.

3. **Significant Business Logic**:
   For scenarios where there is significant business logic, introducing a service layer is essential. It provides a
   clear separation of concerns, encapsulates complex logic, and ensures that business rules are applied consistently.
   In these cases, the service layer should be well-defined, thoroughly tested with unit tests, and clearly separated
   from the controller and repository layers.

4. **Flexibility**:
   By selectively using the service layer, we retain the flexibility to optimise our architecture based on specific
   needs. This approach allows us to apply additional layers where they add clear value, without overcomplicating
   simpler scenarios.

## Consequences

- **Increased Flexibility**: Allows for optimisation of the architecture according to the complexity of business logic.
- **Reduced Complexity**: Avoids unnecessary layers in simple scenarios, keeping the codebase straightforward.
- **Well-Defined Service Layer**: When significant business logic is present, the service layer will be implemented and
  well-tested with unit tests to ensure proper functionality and maintainability.

**Decision Date**: September 7, 2024
