FROM bellsoft/liberica-openjdk-debian:17

WORKDIR /app
COPY target/registrationlapon-latest.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]