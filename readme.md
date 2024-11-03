## crud-neo4j

### Description
This project is a Spring Boot application using Kotlin, with Neo4j as the database. It includes repositories for managing `Hero` and `Villain` nodes.

### Technologies Used
- Kotlin
- Java
- Spring Boot
- Neo4j
- Gradle

## Project Structure
```
src/
├── main/
│   ├── kotlin/
│   │   └── com/
│   │       └── flipedds/
│   │           └── crudneo4j/
│   │               ├── nodes/
│   │               │   ├── Hero.kt
│   │               │   └── Villain.kt
│   │               ├── repositories/
│   │               │   ├── HeroRepository.kt
│   │               │   └── VillainRepository.kt
│   │               └── CrudNeo4jApplication.kt
│   └── resources/
│       └── application.properties
```

## Some API Endpoints
The application exposes RESTful endpoints for managing heroes, villains and relationships.

### Heroes
- `GET /heroes`: Retrieve all heroes
- `GET /heroes/{id}`: Retrieve a hero by ID
- `POST /heroes`: Create a new hero
- `PUT /heroes/{id}`: Update an existing hero
- `DELETE /heroes/{id}`: Delete a hero

### Villains
- `GET /villains`: Retrieve all villains
- `GET /villains/{id}`: Retrieve a villain by ID
- `POST /villains`: Create a new villain
- `PUT /villains/{id}`: Update an existing villain
- `DELETE /villains/{id}`: Delete a villain

## Nodes

### Hero
Represents a hero in the system.

```kotlin
package com.flipedds.crudneo4j.nodes

import org.springframework.data.neo4j.core.schema.GeneratedValue
import org.springframework.data.neo4j.core.schema.Id
import org.springframework.data.neo4j.core.schema.Node
import org.springframework.data.neo4j.core.schema.Relationship

@Node
class Hero(var name: String?, var realName: String?) {
    @Id
    @GeneratedValue
    var id: Long? = null

    @Relationship(type = "CONFLICT_WITH", direction = Relationship.Direction.OUTGOING)
    var conflictsWith: MutableSet<Villain>? = null
}
```

### Villain
Represents a villain in the system.

```kotlin
package com.flipedds.crudneo4j.nodes

import org.springframework.data.neo4j.core.schema.GeneratedValue
import org.springframework.data.neo4j.core.schema.Id
import org.springframework.data.neo4j.core.schema.Node
import org.springframework.data.neo4j.core.schema.Relationship

@Node
class Villain(var name: String?, var realName: String?) {
    @Id
    @GeneratedValue
    var id: Long? = null

    @Relationship(type = "CONFLICT_WITH", direction = Relationship.Direction.INCOMING)
    var conflictedBy: MutableSet<Hero>? = null
}
```

## Repositories

### HeroRepository
Repository interface for managing `Hero` nodes.

```kotlin
package com.flipedds.crudneo4j.repositories

import com.flipedds.crudneo4j.nodes.Hero
import org.springframework.data.neo4j.repository.Neo4jRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(collectionResourceRel = "heroes", path = "heroes")
interface HeroRepository : Neo4jRepository<Hero, Long> {
    fun findByName(name: String): Hero?
    fun findByRealName(realName: String): Hero?
}
```

### VillainRepository
Repository interface for managing `Villain` nodes.

```kotlin
package com.flipedds.crudneo4j.repositories

import com.flipedds.crudneo4j.nodes.Villain
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(collectionResourceRel = "villains", path = "villains")
interface VillainRepository : PagingAndSortingRepository<Villain, Long>, CrudRepository<Villain, Long> {
    fun findByName(name: String): Villain?
    fun findByRealName(realName: String): Villain?
}
```

## Application Properties
Configuration for the Spring Boot application.

```properties
# application.properties
spring.application.name=crud-neo4j
spring.neo4j.uri=bolt://localhost:7687
spring.neo4j.authentication.username=user
spring.neo4j.authentication.password=pass
spring.data.neo4j.database=neo4j
```

## Running the Application
1. Ensure Neo4j is running on your local machine.
2. Update `application.properties` with your Neo4j credentials.
3. Build the project using Gradle:
   ```sh
   ./gradlew build
   ```
4. Run the application:
   ```sh
   ./gradlew bootRun
   ```