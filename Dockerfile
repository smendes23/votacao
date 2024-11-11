FROM openjdk:21-jdk-slim

WORKDIR /app

COPY pom.xml ./
COPY src ./src

RUN ./mvnw package -DskipTests

COPY target/votacao-0.0.1-SNAPSHOT.jar /app/votacao.jar

EXPOSE 8084

ENTRYPOINT ["java", "-jar", "/app/votacao.jar"]