# Nodiki Backend

This is the backend service for [nodiki.com](https://nodiki.com), built with **Java 17**, **Spring Boot**, and **Gradle**.

## 🚀 Technologies

- Spring Boot
- Spring Data JPA
- Lombok
- Swagger/OpenAPI
- Spotless (Google Java Format)
- Manual Git pre-commit hook

## 🍜 Git Hooks Setup (pre-commit)

To ensure code is formatted correctly before committing, we use a manual `pre-commit` hook.

### 🔧 Setup (once per developer)

```bash
cd backend
git config core.hooksPath githooks
chmod +x githooks/pre-commit
```

This will run Spotless formatting and validation before each commit.

## 🎩 Run the App

```bash
./gradlew bootRun
```

## 🧹 Format the Code

```bash
./gradlew spotlessApply
./gradlew spotlessCheck
```

## 📦 Build the Project

```bash
./gradlew build
```

## 🕒 API Documentation

After running the app, you can access the Swagger UI at:

[Swagger UI](http://localhost:8080/swagger-ui/index.html)

---

Happy coding! 🚀
