FROM eclipse-temurin:25-jdk AS builder
WORKDIR /workspace

COPY .mvn/ .mvn/
COPY mvnw pom.xml ./
COPY common/pom.xml common/pom.xml
COPY domain/pom.xml domain/pom.xml
COPY infrastructure/pom.xml infrastructure/pom.xml
COPY service/pom.xml service/pom.xml
COPY web/pom.xml web/pom.xml

RUN chmod +x mvnw && ./mvnw -q -pl web -am dependency:go-offline

COPY common common
COPY domain domain
COPY infrastructure infrastructure
COPY service service
COPY web web

RUN ./mvnw -q -pl web -am package -DskipTests

FROM eclipse-temurin:25-jre
WORKDIR /app
COPY --from=builder /workspace/web/target/*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
