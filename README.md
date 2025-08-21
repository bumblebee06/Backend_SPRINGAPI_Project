docker-compose up -d


docker exec -it postgres_db psql -U demo -d demo

CREATE TABLE users (
  id SERIAL PRIMARY KEY,
  name VARCHAR(100),
  email VARCHAR(100) UNIQUE
);


./gradlew clean build
./gradlew bootRun


# Create a user
curl -X POST http://localhost:8080/users \
  -H "Content-Type: application/json" \
  -d '{"name":"Ashutosh","email":"ashu@example.com"}'

# Get all users
curl http://localhost:8080/users

# Get single user (replace 1 with actual id)
curl http://localhost:8080/users/1

# Update a user
curl -X PUT http://localhost:8080/users/1 \
  -H "Content-Type: application/json" \
  -d '{"name":"Ashu Updated","email":"ashu@demo.com"}'

# Delete a user
curl -X DELETE http://localhost:8080/users/1



🔹 Flow of Execution
1. DemoApplication.kt

Bootstraps the whole Spring Boot application.

Starts the Netty web server on port 8080.

Scans packages, finds your UserController, UserService, UserRepository, User entity.

Sets up DB connection to Postgres (via R2DBC).

👉 Think of it like turning the ignition key in your bike 🚴 — it powers up the whole engine.

2. User.kt (Model/Entity)

This represents the table schema in Postgres.

When Spring Data R2DBC runs, it knows:

Table name = users

Columns = id, name, email

So whenever you call userRepository.save(User(...)), Spring knows how to insert into the users table.

👉 This is like your bike’s chassis — defines what the structure looks like.

3. UserRepository.kt

Directly talks to the database.

Provides CRUD methods (findAll, findById, save, deleteById).

It is like the wiring that connects your bike throttle to the engine — no logic, just connection.

👉 Without this, you’d have to write raw SQL queries for everything.

4. UserService.kt

This is the business logic layer.

Uses the repository to talk with DB.

Example:

When updating, service first fetches old record, modifies fields, then calls save() on repo.

Acts as a middle manager between controller and repository.

👉 Think of it like your bike’s ECU (engine control unit) — it decides how much fuel to give, how to balance things.

5. UserController.kt

Defines HTTP endpoints.

Maps browser/REST calls → to service methods.

Example:

GET /users → calls userService.getAllUsers().

POST /users → calls userService.createUser().

Sends response back to the client in JSON.

👉 This is like your bike handle and display — you control the system here, but it sends signals inside to service & repo.

🔹 Simplified Flow (Step by Step)

Browser/Postman/curl hits http://localhost:8080/users.

UserController receives the request.

UserController calls UserService.

UserService calls UserRepository.

UserRepository fetches/saves data in Postgres DB.

Result comes back → Service → Controller → JSON Response.

✅ So your summary was spot on:

DemoApplication starts everything.

User.kt defines DB mapping.

Repository talks to DB.

Service uses repository to add logic.

Controller maps it all to HTTP routes so the browser/curl/Postman can use it.



3. @SpringBootApplication

Annotation placed above a class.

It is a combo of:

@Configuration → this class provides Spring configuration.

@EnableAutoConfiguration → Spring Boot will guess and set up beans (like DataSource, WebFlux, etc.).

@ComponentScan → automatically finds your @Service, @Repository, @Controller classes in the same package or subpackages.
