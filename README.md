# csc340-CRUD-demo


A comprehensive RESTful API for managing servant details, built with Spring Boot, Spring Data JPA, and PostgreSQL. This project demonstrates fundamental concepts for building APIs with Spring Boot.

## Table of Contents

- [What is This Project?](#what-is-this-project)
- [Installation & Setup](#installation--setup)
- [API Endpoints](#api-endpoints)
- [Key Spring Boot Concepts](#key-spring-boot-concepts)
- [Database Schema](#database-schema)

---

## What is This Project?

This is a **CRUD API** (Create, Read, Update, Delete) that manages servant details. It demonstrates:

- How to build a REST API with Spring Boot
- How to connect to a PostgreSQL database using JPA
- How to structure a Spring Boot application with layers (Controller, Service, Repository)
- How to handle HTTP requests and responses
- How to perform database operations

**CRUD** stands for:

- **C**reate - Add new student records
- **R**ead - Retrieve student records
- **U**pdate - Modify existing student records
- **D**elete - Remove student records

---

## Technology Stack

| Technology          | Version | Purpose                                |
| ------------------- | ------- | -------------------------------------- |
| **Java**            | 25      | Programming language                   |
| **Spring Boot**     | 4.0.3   | Framework for building the application |
| **Spring Data JPA** | Latest  | ORM layer for database access          |
| **Hibernate**       | Latest  | JPA implementation                     |
| **PostgreSQL**      | Latest  | Relational database                    |
| **Maven**           | Latest  | Build and dependency management        |

### Java - [Spring ORM with JPA and Hibernate](https://medium.com/@burakkocakeu/jpa-hibernate-and-spring-data-jpa-efa71feb82ac)
- We are using ORM (Object-Relational Mapping) to deal with databases. This is a technique that allows us to interact with a relational database using object-oriented programming principles.
- JPA (Jakarta Persistence, formerly Java Persistence API) is a specification that defines ORM standards in Java. It provides an abstraction layer for ORM frameworks to make concrete implementations.
- Hibernate: Hibernate is a popular ORM framework that implements JPA. It simplifies database operations by mapping Java objects to database tables and handling queries efficiently.
- Spring ORM allows seamless integration of Hibernate and JPA, making database interactions more manageable and reducing boilerplate code.

### Key Dependencies Explained

**spring-boot-starter-data-jpa**: Provides Spring Data JPA for simplified database access through repositories and automatic query generation.

**spring-boot-starter-webmvc**: Provides Spring Web MVC for building REST APIs with annotations like `@Controller`, `@GetMapping`, etc.

**postgresql**: JDBC driver to connect to PostgreSQL database.

---

## Installation & Setup

### Prerequisites

Before you begin, ensure you have installed:

1. **Java 25 JDK**
   - Download from [Oracle Java](https://www.oracle.com/java/technologies/downloads/) or use a package manager
   - Verify installation: `java -version`

2. **Neon.tech PostgreSQL Database** (Cloud-based, Serverless)
   - This project uses [Neon.tech](https://neon.tech), a serverless PostgreSQL database in the cloud
   - You don't need to install PostgreSQL locally
   - Sign up for a free account at [Neon.tech](https://neon.tech)
   - You only need an internet connection to connect to the database

3. **Git** (optional, for cloning the project)
   - Download from [Git Official Site](https://git-scm.com/)

### About Maven Wrapper

**Good news!** This project includes the **Maven Wrapper** (`mvnw` on Mac/Linux and `mvnw.cmd` on Windows). This means you **do not need to install Maven separately**. The wrapper automatically downloads the correct Maven version for you.

The Maven Wrapper is a handy tool that ensures everyone working on the project uses the same Maven version, reducing compatibility issues.

### Setup Instructions

1. **Clone or Download the Project**

   ```bash
   git clone <repository-url>
   cd sp26-crud-api-demo
   ```

2. **Install Dependencies**
   The Maven Wrapper will automatically download dependencies from the `pom.xml` file:

   **On Windows**:

   ```cmd
   mvnw.cmd clean install
   ```

   **On Mac/Linux**:

   ```bash
   ./mvnw clean install
   ```

   This command:
   - `clean`: Removes previous build artifacts
   - `install`: Downloads all dependencies and compiles the project
   - First run may take a few minutes as Maven is downloaded

3. **Database Configuration (Neon.tech Serverless PostgreSQL)**

   #### Step 1: Get Your Neon.tech Connection String

   1. Navigate to [Neon.tech](https://neon.tech)
   2. Sign in to your account
   3. In your project dashboard, find your connection string
   4. It will look like: `postgresql://username:password@host:5432/dbname`

   #### Step 2: Stop Tracking `application.properties` Locally

   To prevent accidentally committing your database credentials to Git, use `git skip-worktree` to exclude your local copy:

   ```bash
   git update-index --skip-worktree src/main/resources/application.properties
   ```

   This tells Git to ignore any changes you make to this file locally. You can now safely edit the file without worrying about committing sensitive data.

   #### Step 3: Update Your Connection String

   Edit `src/main/resources/application.properties` and add your Neon.tech PostgreSQL connection string:

   ```properties
   spring.application.name=crud-api
   spring.datasource.url=jdbc:postgresql://host:5432/dbname
   spring.datasource.username=your_neon_username
   spring.datasource.password=your_neon_password
   spring.jpa.hibernate.ddl-auto=update

   #Log out sql queries
   logging.level.org.hibernate.SQL=DEBUG
   logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
   logging.level.org.hibernate.orm.jdbc.bind=TRACE
   ```

   Replace with your actual Neon.tech credentials:
   - `host`: Your Neon.tech host (e.g., `some-cool-projectName-pooler.c-7.us-east-1.aws.neon.tech`)
   - `dbname`: Your database name (usually `neondb`)
   - `your_neon_username`: Your Neon.tech username
   - `your_neon_password`: Your Neon.tech password

   #### Example Connection String

   ```properties
   spring.datasource.url=jdbc:postgresql://ep-cool-cherry-ai9ih0ua-pooler.c-7.us-east-1.aws.neon.tech:5432/neondb
   spring.datasource.username=neondb_owner
   spring.datasource.password=your_password_here
   ```

   #### To Resume Tracking the File

   If you need to revert and track the file again:

   ```bash
   git update-index --no-skip-worktree src/main/resources/application.properties
   ```

   **Important Note**: This approach (using `git skip-worktree`) keeps credentials safe locally while the file can be tracked in Git. However, in production environments, database credentials should be managed using environment variables or cloud-based secret management services like AWS Secrets Manager or Azure Key Vault.

4. **Verify Setup**

   **On Windows (PowerShell)**:

   ```cmd
   mvnw.cmd compile
   ```

   **On Mac/Linux (Bash/zsh)**:

   ```bash
   ./mvnw compile
   ```

   If successful, you'll see `BUILD SUCCESS` at the end.

---



## API Endpoints

All endpoints use the base URL: `http://localhost:8080/api/students`

### 1. Get All Students

```http
GET /api/students/
```

**Description**: Retrieve a list of all students in the database.

**Parameters**: None

**Response**:

- **Status Code**: `200 OK`
- **Body**: Array of Student objects

#### Example Request

```bash
curl http://localhost:8080/api/students/
```

#### Example Response (Status: 200 OK)

```json
[
  {
    "studentId": 1,
    "name": "Alice Johnson",
    "email": "alice@university.edu",
    "major": "Computer Science",
    "gpa": 3.8
  },
  {
    "studentId": 2,
    "name": "Bob Smith",
    "email": "bob@university.edu",
    "major": "Mathematics",
    "gpa": 3.5
  }
]
```

---

### 2. Get Student by ID

```http
GET /api/students/{id}
```

**Description**: Retrieve a single student by their ID.

**Path Parameters**:

- `id` (Long, required): The unique identifier of the student

**Response**:

- **Status Code**: `200 OK` (if found) or `404 Not Found` (if not found)
- **Body**: Student object

#### Example Request

```bash
curl http://localhost:8080/api/students/1
```

#### Example Response (Status: 200 OK)

```json
{
  "studentId": 1,
  "name": "Alice Johnson",
  "email": "alice@university.edu",
  "major": "Computer Science",
  "gpa": 3.8
}
```

#### Example Response if not found (Status: 404 Not Found)

```
(Empty body)
```

---

### 3. Create a New Student

```http
POST /api/students/
```

**Description**: Create a new student record in the database.

**Request Body**: Student object with the following fields:

- `name` (String, required): Student's full name
- `email` (String, required, unique): Student's email address
- `major` (String, optional): Student's major
- `gpa` (Double, optional): Student's GPA

**Response**:

- **Status Code**: `200 OK` (if created successfully)
- **Body**: Created Student object with assigned `studentId`

#### Example Request

```bash
curl -X POST http://localhost:8080/api/students/ \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Charlie Brown",
    "email": "charlie@university.edu",
    "major": "Physics",
    "gpa": 3.7
  }'
```

#### Example Response (Status: 200 OK)

```json
{
  "studentId": 3,
  "name": "Charlie Brown",
  "email": "charlie@university.edu",
  "major": "Physics",
  "gpa": 3.7
}
```

---

### 4. Get Students by Major

```http
GET /api/students/major/{major}
```

**Description**: Retrieve all students with a specific major.

**Path Parameters**:

- `major` (String, required): The major to filter by (e.g., "Computer Science")

**Response**:

- **Status Code**: `200 OK`
- **Body**: Array of Student objects

---

### 5. Get Honors Students

```http
GET /api/students/honors/{gpa}
```

**Description**: Retrieve students with a GPA greater than or equal to the specified value.

**Path Parameters**:

- `gpa` (Double, required): Minimum GPA for honors (e.g., 3.5)

**Response**:

- **Status Code**: `200 OK`
- **Body**: Array of Student objects meeting the GPA requirement

#### Example Request

```bash
curl http://localhost:8080/api/students/honors/3.7
```

#### Example Response (Status: 200 OK)

```json
[
  {
    "studentId": 1,
    "name": "Alice Johnson",
    "email": "alice@university.edu",
    "major": "Computer Science",
    "gpa": 3.8
  },
  {
    "studentId": 3,
    "name": "Charlie Brown",
    "email": "charlie@university.edu",
    "major": "Physics",
    "gpa": 3.7
  }
]
```

---

### 6. Search Students by Name

```http
GET /api/students/search?name={name}
```

**Description**: Search for students by name (partial match supported) or retrieve all students if no name is provided.

**Query Parameters**:

- `name` (String, optional): The name or part of the name to search for

**Response**:

- **Status Code**: `200 OK`
- **Body**: Array of matched Student objects

#### Example Request

```bash
curl "http://localhost:8080/api/students/search?name=Alice"
```

#### Example Response (Status: 200 OK)

```json
[
  {
    "studentId": 1,
    "name": "Alice Johnson",
    "email": "alice@university.edu",
    "major": "Computer Science",
    "gpa": 3.8
  }
]
```

---

### 7. Get Student by Email

```http
GET /api/students/email/{email}
```

**Description**: Retrieve a student by their email address.

**Path Parameters**:

- `email` (String, required): The student's email address

**Response**:

- **Status Code**: `200 OK` (if found) or `404 Not Found` (if not found)
- **Body**: Student object

---

### 8. Update a Student

```http
PUT /api/students/{id}
```

**Description**: Update an existing student's information.

**Path Parameters**:

- `id` (Long, required): The ID of the student to update

**Request Body**: Student object with fields to update:

- `name` (String): Updated name
- `email` (String): Updated email
- `major` (String): Updated major
- `gpa` (Double): Updated GPA

**Response**:

- **Status Code**: `200 OK` (if updated successfully) or `404 Not Found` (if student not found)
- **Body**: Updated Student object

#### Example Request

```bash
curl -X PUT http://localhost:8080/api/students/1 \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Alice Johnson",
    "email": "alice.johnson@university.edu",
    "major": "Computer Science",
    "gpa": 3.9
  }'
```

#### Example Response (Status: 200 OK)

```json
{
  "studentId": 1,
  "name": "Alice Johnson",
  "email": "alice.johnson@university.edu",
  "major": "Computer Science",
  "gpa": 3.9
}
```

---

### 9. Delete a Student

```http
DELETE /api/students/{id}
```

**Description**: Delete an existing student record from the database.

**Path Parameters**:

- `id` (Long, required): The ID of the student to delete

**Response**:

- **Status Code**: `204 No Content` (successful deletion)
- **Body**: Empty

#### Example Request

```bash
curl -X DELETE http://localhost:8080/api/students/1
```

#### Example Response (Status: 204 No Content)

```
(Empty body)
```

---


## Database Schema

The application uses a single table to store student data:

### STUDENTS Table

| Column       | Type             | Constraints      | Description                         |
| ------------ | ---------------- | ---------------- | ----------------------------------- |
| `student_id` | SERIAL           | PRIMARY KEY      | Auto-incrementing unique identifier |
| `name`       | VARCHAR(255)     | NOT NULL         | Student's full name                 |
| `email`      | VARCHAR(255)     | NOT NULL, UNIQUE | Student's email (must be unique)    |
| `major`      | VARCHAR(255)     | Can be NULL      | Student's major (optional)          |
| `gpa`        | DOUBLE PRECISION | Can be NULL      | Student's GPA (optional)            |

### SQL (for reference)

```sql
CREATE TABLE students (
  student_id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL UNIQUE,
  major VARCHAR(255),
  gpa DOUBLE PRECISION
);
```

**Note**: This schema is automatically created by Hibernate based on the entity class when `spring.jpa.hibernate.ddl-auto=update` is set in `application.properties`.

---

## Testing the API

### Using Postman/Echo API/Bruno (GUI)

1. Create a new request
2. Select HTTP method (GET, POST, PUT, DELETE)
3. Enter URL (e.g., http://localhost:8080/api/students/)
4. If POST/PUT, go to "Body" tab → select "raw" and "JSON"
5. Enter JSON data and click "Send"

---



## Additional Resources

- [Spring Boot Official Documentation](https://spring.io/projects/spring-boot)
- [Spring Data JPA Guide](https://spring.io/projects/spring-data-jpa)
- [REST API Best Practices](https://restfulapi.net/)
- [PostgreSQL Documentation](https://www.postgresql.org/docs/)