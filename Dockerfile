FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean install

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/cadastro-api-1.0.0.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
