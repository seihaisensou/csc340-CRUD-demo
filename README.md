# csc340-CRUD-demo


A comprehensive RESTful API for managing servant details, built with Spring Boot, Spring Data JPA, and PostgreSQL. This project demonstrates fundamental concepts for building APIs with Spring Boot.

## Table of Contents

- [What is This Project?](#what-is-this-project)
- [Installation & Setup](#installation--setup)
- [API Endpoints](#api-endpoints)
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

- **C**reate - Add new servant records
- **R**ead - Retrieve servant records
- **U**pdate - Modify existing servant records
- **D**elete - Remove servant records

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
   cd csc340-CRUD-demo
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
   spring.application.name=chaldea
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

All endpoints use the base URL: `http://localhost:8080/api/servants`

### 1. Get All Servants

```http
GET /api/servants
```

**Description**: Retrieve a list of all servants in the database.

**Parameters**: None

**Response**:

- **Status Code**: `200 OK`
- **Body**: Array of Servant objects

#### Example Request

```bash
curl http://localhost:8080/api/servants/
```

#### Example Response (Status: 200 OK)

```json
[
  {
		"name": "Tamamo-no-Mae",
		"type": "Caster",
		"species": "Kitsune",
		"origin": "Fate/EXTRA",
		"servantId": 1
	},
	{
		"name": "Okita Souji",
		"type": "Saber",
		"species": "Human",
		"origin": "Koha-Ace",
		"servantId": 2
	}
]
```

---

### 2. Get Servant by ID

```http
GET /api/servants/{id}
```

**Description**: Retrieve a single servant by their ID.

**Path Parameters**:

- `id` (Long, required): The unique identifier of the servant

**Response**:

- **Status Code**: `200 OK` (if found) or `404 Not Found` (if not found)
- **Body**: servant object

#### Example Request

```bash
curl http://localhost:8080/api/servants/1
```

#### Example Response (Status: 200 OK)

```json
{
	"name": "Tamamo-no-Mae",
	"type": "Caster",
	"species": "Kitsune",
	"origin": "Fate/EXTRA",
	"servantId": 1
}
```

#### Example Response if not found (Status: 404 Not Found)

```
(Empty body)
```

---

### 3. Create a New Servant

```http
POST /api/servants/
```

**Description**: Create a new servant record in the database.

**Request Body**: Servant object with the following fields:

- `name` (String, required): Servant's full name
- `type` (String, required, unique): Servant's class type
- `species` (String, required): Servant's species
- `origin` (String, required): Servant's origin

**Response**:

- **Status Code**: `200 OK` (if created successfully)
- **Body**: Created Servant object with assigned `servantId`

#### Example Request

```bash
curl -X POST http://localhost:8080/api/servants/ \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Nero Claudius",
    "type": "Saber",
    "species": "Human",
    "origin": "Fate/EXTRA"
  }'
```

#### Example Response (Status: 200 OK)

```json
{
  "servantId": 3,
  "name": "Nero Claudius",
  "type": "Saber",
  "species": "Human",
  "origin": "Fate/EXTRA"
}
```

---

### 4. Get Servants by Type

```http
GET /api/servants/type/{type}
```

**Description**: Retrieve all servants with a specific class type.

**Path Parameters**:

- `type` (String, required): The type to filter by (e.g., "Saber")

**Response**:

- **Status Code**: `200 OK`
- **Body**: Array of Servant objects

---

### 5. Get Servants by Origin

```http
GET /api/servants/origin/{origin}
```

**Description**: Retrieve servants with origins that contain the substring {origin}.

**Path Parameters**:

- `origin` (String, required): Substring or string of origin.

**Response**:

- **Status Code**: `200 OK`
- **Body**: Array of Servant objects in which the origin contains the substring {origin}

#### Example Request

```bash
curl http://localhost:8080/api/servants/origin/EXTRA
```

#### Example Response (Status: 200 OK)

```json
[
 	{
		"name": "Tamamo-no-Mae",
		"type": "Caster",
		"species": "Kitsune",
		"origin": "Fate/EXTRA",
		"servantId": 1
	},
	{
		"name": "Nero Claudius",
		"type": "Saber",
		"species": "Human",
		"origin": "Fate/EXTRA",
		"servantId": 3
	}
]
```

---

### 6. Search Servants by Name

```http
GET /api/servants/search?name={name}
```

**Description**: Search for Servants by name (partial match supported) or retrieve all servants if no name is provided.

**Query Parameters**:

- `name` (String, optional): The name or part of the name to search for

**Response**:

- **Status Code**: `200 OK`
- **Body**: Array of matched Servant objects

#### Example Request

```bash
curl "http://localhost:8080/api/servants/search?name=Nero"
```

#### Example Response (Status: 200 OK)

```json
[
  {
		"name": "Nero Claudius",
		"type": "Saber",
		"species": "Human",
		"origin": "Fate/EXTRA",
		"servantId": 3
	}
]
```

---

### 7. Get Servants by Species

```http
GET /api/servants/species/{species}
```

**Description**: Retrieve a servant by their species.

**Path Parameters**:

- `species` (String, required): The servant's species

**Response**:

- **Status Code**: `200 OK` (if found) or `404 Not Found` (if not found)
- **Body**: Array of matched Servant objects

---

### 8. Update a Servant

```http
PUT /api/servants/{id}
```

**Description**: Update an existing servant's information.

**Path Parameters**:

- `id` (Long, required): The ID of the servant to update

**Request Body**: Servant object with fields to update:

- `name` (String): Updated name
- `type` (String): Updated type
- `species` (String): Updated species
- `origin` (Double): Updated origin

**Response**:

- **Status Code**: `200 OK` (if updated successfully) or `404 Not Found` (if servant not found)
- **Body**: Updated Servant object

#### Example Request

```bash
curl -X PUT http://localhost:8080/api/servants/3 \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Nero Claudius",
    "type": "Beast",
    "species": "Human",
    "origin": "Fate/EXTRA"
  }'
```

#### Example Response (Status: 200 OK)

```json
{
  "servantId": 3,
  "name": "Nero Claudius",
  "type": "Beast",
  "species": "Human",
  "origin": "Fate/EXTRA"
}
```

---

### 9. Delete a Servant

```http
DELETE /api/servant/{id}
```

**Description**: Delete an existing servant record from the database.

**Path Parameters**:

- `id` (Long, required): The ID of the Servant to delete

**Response**:

- **Status Code**: `204 No Content` (successful deletion)
- **Body**: Empty

#### Example Request

```bash
curl -X DELETE http://localhost:8080/api/Servant/1
```

#### Example Response (Status: 204 No Content)

```
(Empty body)
```

---


## Database Schema

The application uses a single table to store servant data:

### SERVANTS Table

| Column       | Type             | Constraints      | Description                         |
| ------------ | ---------------- | ---------------- | ----------------------------------- |
| `servantId`  | SERIAL           | PRIMARY KEY      | Auto-incrementing unique identifier |
| `name`       | VARCHAR(255)     | NOT NULL         | Servant's full name                 |
| `type`       | VARCHAR(255)     | NOT NULL         | Servant's class type                |
| `species`    | VARCHAR(255)     | NOT NULL         | Servant's species                   |
| `origin`     | VARCHAR(255)     | NOT NULL         | Servant's origin                    |

### SQL (for reference)

```sql
CREATE TABLE servants (
  servantId SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  type VARCHAR(255) NOT NULL,
  species VARCHAR(255) NOT NULL,
  origin VARCHAR(255) NOT NULL,
);
```

**Note**: This schema is automatically created by Hibernate based on the entity class when `spring.jpa.hibernate.ddl-auto=update` is set in `application.properties`.

---

## Testing the API

### Using Postman/Echo API/Bruno (GUI)

1. Create a new request
2. Select HTTP method (GET, POST, PUT, DELETE)
3. Enter URL (e.g., http://localhost:8080/api/servants/)
4. If POST/PUT, go to "Body" tab → select "raw" and "JSON"
5. Enter JSON data and click "Send"

---



## Additional Resources

- [Spring Boot Official Documentation](https://spring.io/projects/spring-boot)
- [Spring Data JPA Guide](https://spring.io/projects/spring-data-jpa)
- [REST API Best Practices](https://restfulapi.net/)
- [PostgreSQL Documentation](https://www.postgresql.org/docs/)

## Video Demonstration
https://uncg-my.sharepoint.com/:v:/g/personal/m_reyes2_uncg_edu/IQBH1DHS7YuuRpemjouJzGkhAVQhDLxIFWGMrXquDujth3Q?e=HTF6bF&nav=eyJyZWZlcnJhbEluZm8iOnsicmVmZXJyYWxBcHAiOiJTdHJlYW1XZWJBcHAiLCJyZWZlcnJhbE1vZGUiOiJtaXMiLCJyZWZlcnJhbFZpZXciOiJwb3N0cm9sbC1jb3B5bGluayIsInJlZmVycmFsUGxheWJhY2tTZXNzaW9uSWQiOiIxNmJhYzMwNi1lZTljLTQxYTgtODM4ZC00NzM3YjAxYzkzMzQifX0%3D
