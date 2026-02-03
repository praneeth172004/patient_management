# -------- Build Stage --------
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

COPY pom.xml .
RUN mvn -B -q dependency:go-offline

COPY src ./src
RUN mvn -B -q clean package -DskipTests


# -------- Runtime Stage --------
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# ⚠️ MUST be billing-service jar
COPY --from=build /app/target/*.jar app.jar

EXPOSE 5001

ENTRYPOINT ["java","-jar","/app/app.jar"]