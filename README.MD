# Training Center Management - REST API

This project is a REST API for managing the operations of a training center, including handling learners, trainers, courses, and classrooms. Developed using Spring Boot, this application enables CRUD operations for each entity and follows RESTful API best practices to standardize data access.

## Table of Contents
- [Project Overview](#project-overview)
- [Installation](#installation)
- [Technologies Used](#technologies-used)
---

## Project Overview

**Context**:  
A training center seeks to digitalize its processes by creating an API that facilitates efficient management of learners, trainers, courses, and classrooms. This API will streamline operations, allowing for standardized and easy access to the center’s data.

**Application Layers**

- **Entities**: Defined with JPA annotations and validation.
- **Repositories**: Extend `JpaRepository` for data access.
- **Services**: Business logic for each entity.
- **Controllers**: REST endpoints.
- **Exceptions**: Centralized error handling.
- **Utilities**: Helper classes for the project.
- **Tests**: Unit and integration tests.

**Entity Structure**

- **Learner**: `firstName`, `lastName`, `email`, `level`, `course`, `classroom`
- **Trainer**: `firstName`, `lastName`, `email`, `specialty`, `course`, `classroom`
- **Classroom**: `name`, `roomNumber`
- **Course**: `title`, `level`, `prerequisites`, `minCapacity`, `maxCapacity`, `startDate`, `endDate`, `trainer`, `learners`, `status` (e.g., SCHEDULED, IN_PROGRESS, COMPLETED, CANCELED)

---

## Installation

**Prerequisites**
- **Java 8** or higher
- **Maven** for build management
- **PostgreSQL** for production or **H2 Database** for development
- **Git** to clone the repository

### Steps to Install
1. **Clone the Project**:
   ```bash
   git clone https://github.com/JavaAura/Med_Hachami_S3_B2_Formations.git
   cd Med_Hachami_S3_B2_Formations

2. **Setup the Project**:
Setup environment variable

1. **Windows:**
   ```cmd
    set DB_URL= "database url"
   set DB_USER= "username"
   set DB_PASSWORD = "password"

2. **Compile the application:**

**For windows:**
   ```sh
   set DB_URL= "database url"
   set DB_USER= "username"
   set DB_PASSWORD = "password"
  ```

**For linux based:**
   ```bash
   export DB_URL= "database url"
   export DB_USER= "username"
   export DB_PASSWORD = "password"
  ```

3. **Build the Project**:
   ```bash
    mvn clean install

4. **Ten the application**:
```bash
mvn test
```

5. **Run the application**:

**4. Run the Application:**
***4.1. DEV profile:***
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```
***4.2. Prod profile:***
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```








#### Technologies Used

### Technologies Used

- **Spring Boot**
  - Project initialized with Spring Initializer.
  - Configured via `application.properties` and profiles (`dev`, `prod`):
    - `application-dev.properties` for development.
    - `application-prod.properties` for production.
  - Annotations for dependency injection and configuration.

- **Spring Data JPA**
  - Utilizes `JpaRepository` for repository classes.
  - Custom search methods using `@Query` and `@Param`.
  - Pagination implemented for better data handling.

- **REST API**
  - CRUD endpoints for each entity.
  - API documented with Swagger.
  - Testing via Postman and/or Swagger.

- **Exception Handling**
  - Custom classes for specific error management.

### Database

- **H2 Database**: Used in development mode.
- **PostgreSQL**: Used in production mode.

### Other Tools and Libraries

- **Java 8**: Uses Java 8 features like lambda expressions and Stream API.
- **Maven**: Dependency management.
- **JUnit & Mockito**: Unit and integration testing.
- **JaCoCo**: Code coverage reporting.
- **Lombok**: Reduces boilerplate code with annotations.
- **SLF4J**: Logging framework.

