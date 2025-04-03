FROM openjdk:17-jdk-slim
WORKDIR /app
COPY build/libs/swe-0.0.1-SNAPSHOT.jar app.jar
COPY .env .env         
EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java -jar app.jar || sleep 600"]
