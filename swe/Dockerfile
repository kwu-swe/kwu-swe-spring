FROM openjdk:17-jdk-slim
WORKDIR /app
COPY build/libs/swe-0.0.1-SNAPSHOT.jar app.jar
COPY build/.env .env         # ✅ .env 파일 복사
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
