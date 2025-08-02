# Stage 1: Build the jar
FROM maven:3.8.5-openjdk-17 AS JARBUILDER

WORKDIR /build

COPY src src
COPY pom.xml pom.xml

RUN mvn clean package -DskipTests

# Stage 2: Run the app
FROM bellsoft/liberica-openjre-alpine

WORKDIR /springApplication/app

# Copy the jar file from the builder stage
COPY --from=JARBUILDER /build/target/*.jar app.jar


# Use entrypoint
ENTRYPOINT ["java", "-jar", "app.jar"]
