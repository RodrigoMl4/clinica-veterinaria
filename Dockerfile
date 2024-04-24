FROM maven:3.8.4-openjdk-17 AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src /app/src
RUN mvn package -DskipTests
# RUN mvn clean compile flyway:migrate package -DskipTests -Dflyway.url=$SPRING_DATASOURCE_URL -Dflyway.user=$SPRING_DATASOURCE_USERNAME -Dflyway.password=$SPRING_DATASOURCE_PASSWORD

FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=builder /app/target/clinica-veterinaria-0.0.1-SNAPSHOT.jar .
CMD ["java", "-jar", "clinica-veterinaria-0.0.1-SNAPSHOT.jar"]
