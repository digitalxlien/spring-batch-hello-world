FROM openjdk:11.0.8-slim-buster
COPY target/simple-batch-job-0.0.1-SNAPSHOT.jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]