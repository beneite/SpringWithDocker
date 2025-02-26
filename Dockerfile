FROM bellsoft/liberica-openjre-alpine

WORKDIR springApplication/app

COPY target/*.jar app.jar

CMD ["java", "-jar", "app.jar"]

