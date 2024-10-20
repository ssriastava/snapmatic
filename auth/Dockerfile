FROM openjdk:17
ENV EUREKA_INSTANCE_PREFER_IP_ADDRESS=true
ENV EUREKA_INSTANCE_IP_ADDRESS=${HOST_IP}
RUN mkdir -p /logs
RUN mkdir -p /logs/archived
EXPOSE 8888
ADD target/snapmatic-auth.jar snapmatic-auth.jar
ENTRYPOINT ["java", "-jar", "snapmatic-auth.jar"]