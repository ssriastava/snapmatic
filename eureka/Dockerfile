FROM openjdk:17
RUN mkdir -p /logs
RUN mkdir -p /logs/archived
EXPOSE 8888
ADD target/snapmatic-eureka.jar snapmatic-eureka.jar
ENTRYPOINT ["java", "-jar", "snapmatic-eureka.jar"]