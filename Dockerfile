# ================================
# Stage 1: Build the application
# ================================
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copy Maven wrapper and pom first
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Make mvnw executable
RUN chmod +x mvnw

# Download dependencies (cache layer)
RUN ./mvnw dependency:go-offline -B || true

# Copy source code
COPY src ./src

# Build and verify JAR file exists
RUN ./mvnw clean package -DskipTests -B && \
    ls -la /app/target/ && \
    echo "JAR file created:"

# ================================
# Stage 2: Run the application
# ================================
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copy ALL jar files from target (including dependencies)
COPY --from=build /app/target/*.jar app.jar

# Verify JAR was copied
RUN ls -la /app/ && echo "Files in /app:"

# Create non-root user
RUN addgroup -g 1001 -S appgroup && \
    adduser -u 1001 -S appuser -G appgroup

USER appuser

EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]