FROM maven:3.6.3-jdk-8-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -Dmaven.test.skip=true

FROM openjdk:8-alpine
COPY --from=build /home/app/target/crawler-api-0.0.1-SNAPSHOT.jar /usr/local/lib/crawler-api.jar
EXPOSE 8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/crawler-api.jar"]
