FROM openjdk:17
RUN mkdir -p /logs
RUN mkdir -p /logs/archived
EXPOSE 8888
ADD target/snapmatic-auth.jar snapmatic-auth.jar
ENTRYPOINT ["java", "-jar", "snapmatic-auth.jar"]