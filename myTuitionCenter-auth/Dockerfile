
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target /app/target

RUN ls -la /app/target

COPY target/myTuitionCenter-auth-0.0.1-SNAPSHOT.jar /app/gdmsnimaven-wrapper.jar

CMD ["java", "-jar", "/app/gdmsnimaven-wrapper.jar"]

