FROM eclipse-temurin:17-jdk-focal

WORKDIR /src

COPY target/lab-0.0.1-SNAPSHOT.jar /src/book-management-system.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "book-management-system.jar"]
