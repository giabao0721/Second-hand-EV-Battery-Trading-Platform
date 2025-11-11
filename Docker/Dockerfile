# ================================
# Stage 1: Build the application
# ================================
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copy Maven wrapper and pom first (cache dependencies)
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Ensure mvnw is executable and pre-fetch dependencies
RUN mvn clean package -DskipTests -B

# Copy source code
COPY src ./src

# Ensure mvnw is executable again (some CI/CD resets permissions)
RUN chmod +x mvnw

# Build the application
RUN ./mvnw clean package -DskipTests -B

# ================================
# Stage 2: Run the application
# ================================
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copy the built jar from build stage
COPY --from=build /app/target/*.jar app.jar

# Create non-root user
RUN addgroup -g 1001 -S appgroup && \
    adduser -u 1001 -S appuser -G appgroup

USER appuser

# Expose port
EXPOSE 8080

# Run the application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
