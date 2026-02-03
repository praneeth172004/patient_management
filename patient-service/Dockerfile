FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

COPY pom.xml .
RUN mvn -B -e -C dependency:go-offline

COPY src ./src
RUN mvn -B clean package -DskipTests

FROM eclipse-temurin:21-jre AS runner

WORKDIR /app

# Wildcard copy to avoid name mismatch
COPY --from=builder ./app/target/*.jar app.jar

EXPOSE 8004

ENTRYPOINT ["java", "-jar", "app.jar"]