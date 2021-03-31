FROM openjdk:11-jdk-slim
VOLUME /tmp
ARG BUILD_STAGE
ARG JAR_FILE
ENV ENTRY_ENV=${BUILD_STAGE}
COPY ${JAR_FILE} app.jar


ENTRYPOINT ["java","-jar","/app.jar"]