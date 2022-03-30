FROM openjdk:11

WORKDIR /docker


COPY target/tqi_evolution_avaliacao-0.0.1-SNAPSHOT.jar /docker/tqi_evolution.jar

EXPOSE 8080

SHELL ["/bin/sh", "-c"]

ENTRYPOINT ["java", "-jar", "/docker/tqi_evolution.jar"]