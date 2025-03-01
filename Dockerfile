FROM maven:3.8.5-openjdk-17 AS JARBUILDER

WORKDIR build

COPY src src
COPY pom.xml pom.xml

RUN mvn clean package -DskipTests

# above we are builing the project jar file and consuming it below to launch the application

FROM bellsoft/liberica-openjre-alpine

WORKDIR springApplication/app

COPY --from=JARBUILDER build/target/*.jar app.jar

CMD ["java", "-jar", "app.jar"]

