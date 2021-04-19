FROM maven:3.6.3-jdk-14 AS BUILD
COPY pom.xml /build/
COPY src /build/src
WORKDIR /build
RUN mvn package

FROM openjdk:14-alpine
COPY --from=BUILD /build/target/retro-library-client-1.0-SNAPSHOT-jar-with-dependencies.jar client.jar

ENTRYPOINT [ "/bin/sh", "-c", "java -jar client.jar" ]