FROM openjdk:17-jdk

WORKDIR /app

COPY build/libs/XENO_backend-0.0.1-SNAPSHOT.jar /app/app.jar

ENV SPRING_PROFILES_ACTIVE=prod

EXPOSE 8090

CMD ["java", "-jar", "app.jar"]
