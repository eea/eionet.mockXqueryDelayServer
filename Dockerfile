FROM openjdk:11-jdk
VOLUME /tmp
ADD wait-for-it.sh wait-for-it.sh
ARG ARTIFACT_NAME
COPY /target/app.jar /app.jar
ENV SPRING_PROFILES_ACTIVE docker
RUN bash -c 'touch /app.jar'
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]