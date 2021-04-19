FROM maven:3.6.3-jdk-14 AS BUILD
COPY pom.xml /build/
COPY src /build/src
WORKDIR /build
RUN mvn package

FROM openjdk:14-alpine
COPY --from=BUILD /build/target/library-1.0-SNAPSHOT.jar app.jar

COPY exec.sh exec.sh
COPY config.yml config.yml

RUN /bin/sh -c 'chmod +x /exec.sh'

HEALTHCHECK --interval=10s --timeout=3s \
  CMD curl -f http://localhost:9000/books || exit 1

EXPOSE 9000 9001
ENTRYPOINT [ "/bin/sh", "-c", "./exec.sh" ]