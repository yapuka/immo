FROM eclipse-temurin:25-jdk AS builder
WORKDIR /workspace

COPY .mvn/ .mvn/
COPY mvnw pom.xml ./
COPY common/pom.xml common/pom.xml
COPY domain/pom.xml domain/pom.xml
COPY infrastructure/pom.xml infrastructure/pom.xml
COPY service/pom.xml service/pom.xml
COPY exposition/pom.xml exposition/pom.xml

RUN chmod +x mvnw && ./mvnw -q -pl exposition -am dependency:go-offline

COPY common common
COPY domain domain
COPY infrastructure infrastructure
COPY service service
COPY exposition exposition

RUN ./mvnw -q -pl exposition -am package -DskipTests

FROM eclipse-temurin:25-jre
WORKDIR /app
COPY --from=builder /workspace/exposition/target/*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
