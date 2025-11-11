# ================================
# Stage 1: Build the application
# ================================
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copy Maven wrapper and pom first
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Ensure mvnw is executable and pre-fetch dependencies
RUN chmod +x mvnw && ./mvnw dependency:go-offline -B || true

# Copy source code
COPY src ./src

# ✅ Ensure again (important for some CI/CD environments like Railway)
RUN chmod +x mvnw

# Build the application
RUN ./mvnw clean package -DskipTests -B
