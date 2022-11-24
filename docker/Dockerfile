FROM openjdk:17-ea-jdk-oracle
ARG JAR_FILE=target/MathSimpleBackend-0.0.1.jar


RUN mkdir -p /opt/java/lib
COPY /docker/lib /opt/java/lib

RUN mkdir -p /opt/java

COPY ${JAR_FILE} /opt/java/MathSimpleBackend-0.0.1.jar
#ENTRYPOINT ["java","-jar","opt/java/MathSimpleBackend-0.0.1.jar"]
#"-Dotel.javaagent.debug=true", "-Dotel.traces.exporter=jaeger",
ENTRYPOINT ["java", "-javaagent:/opt/java/lib/opentelemetry-javaagent.jar","-Dotel.service.name=MathSimpleBackend", "-jar","opt/java/MathSimpleBackend-0.0.1.jar", "com.simple.backend.math.config.MathProxyApplication"]
