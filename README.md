# 💳 DemoBank API - Secure Banking Backend with JWT Authentication

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?logo=springboot)
![Java](https://img.shields.io/badge/Java-17-orange?logo=openjdk)
![Security](https://img.shields.io/badge/Security-JWT%20Auth-blue?logo=jsonwebtokens)
![License](https://img.shields.io/badge/license-MIT-lightgrey)

## 📘 Overview

**DemoBank** is a secure RESTful backend application built with **Spring Boot 3**, featuring **JWT-based authentication and authorization**.  
It provides endpoints for user login, registration, and role-based access control (`ADMIN` and `USER` roles).  
Swagger UI is integrated for easy API exploration.

---

## 🧩 Features

- 🔐 **JWT Authentication & Authorization**
- 🧑‍💻 Role-based access (`USER`, `ADMIN`)
- 🗄️ In-memory H2 / MySQL Database
- ⚙️ Stateless session management
- 📜 Centralized Exception Handling
- 📚 Integrated Swagger UI for API documentation

---

## 🏗️ Project Structure

```
src/main/java/com/sachin/demobank/
│
├── controller/             # REST controllers (Auth, User, Admin)
├── entity/                 # JPA entities (User)
├── repository/             # Spring Data repositories
├── security/               # Security configuration and JWT utilities
│   ├── JwtAuthFilter.java
│   ├── JwtUtil.java
│   ├── CustomerUserDetailsService.java
│   └── SecurityConfig.java
├── service/                # Business logic layer
└── DemoBankApplication.java
```

---

## ⚙️ Configuration

### `application.properties`

```properties
server.port=8081

spring.datasource.url=jdbc:mysql://localhost:3306/demobank
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# JWT Configuration
app.jwt.secret=your_very_long_secret_key_here_which_should_be_32_chars_minimum
app.jwt.expiration-ms=3600000  # 1 hour

# Swagger Configuration
springdoc.api-docs.path=/b3/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
```

---

## 🔑 JWT Flow

### 1️⃣ Login  
User sends credentials to `/api/auth/login`

**Request**
```json
{
  "email": "user@example.com",
  "password": "password123"
}
```

**Response**
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "tokenType": "Bearer"
}
```

---

### 2️⃣ Accessing Protected Endpoints  
- Use the token received above.
- In Postman or Swagger, go to **Authorization → Bearer Token** and paste the token.
- Example endpoints:
  - `/api/users` → Accessible by `USER` and `ADMIN`
  - `/api/admin/**` → Accessible by `ADMIN` only

---

## 🔒 Security Configuration

```java
http.csrf(csrf -> csrf.disable())
    .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
    .authorizeHttpRequests(auth -> auth
        .requestMatchers("/api/auth/login", "/swagger-ui.html", "/swagger-ui/**", "/b3/api-docs/*").permitAll()
        .requestMatchers("/api/admin/**").hasRole("ADMIN")
        .anyRequest().authenticated()
    )
    .userDetailsService(userDetailsService)
    .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
```

---

## 🧠 Common Issues

| Issue | Possible Cause | Solution |
|-------|----------------|-----------|
| `403 Forbidden` | Missing or invalid JWT | Ensure Bearer Token is added in header |
| `User is disabled` | `isActive` flag is false | Verify DB column `is_active = true` |
| Swagger not loading | Blocked by Security | Permit `/swagger-ui/**` and `/b3/api-docs/*` in `SecurityConfig` |

---

## 🧰 Tools & Technologies

- **Spring Boot 3**
- **Spring Security**
- **JWT (io.jsonwebtoken)**
- **Spring Data JPA**
- **MySQL / H2 Database**
- **Swagger (springdoc-openapi)**

---

## 🚀 How to Run Locally

1. Clone the repository:
   ```bash
   git clone https://github.com/<your-username>/demobank.git
   cd demobank
   ```

2. Configure database and JWT secret in `application.properties`

3. Build and run:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

4. Access:
   - **Swagger UI:** [http://localhost:8081/swagger-ui.html](http://localhost:8081/swagger-ui.html)
   - **API Docs:** [http://localhost:8081/b3/api-docs](http://localhost:8081/b3/api-docs)

---

## 🧾 Example Roles & Users

| Email | Password | Role  | Active |
|--------|-----------|-------|--------|
| admin@demobank.com | admin123 | ADMIN | true |
| user@demobank.com  | user123  | USER  | true |

---

## 🛡️ License

This project is licensed under the **MIT License** – feel free to use, modify, and distribute.

---

## ✨ Author

**Sachin Mishra**  
👨‍💻 Backend Developer | Java | Spring Boot | AWS  
📫 [LinkedIn](https://www.linkedin.com/in/sachin-kumar-java) • [GitHub](https://github.com/Sachin-Mishra007)
