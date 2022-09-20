FROM eclipse-temurin:17
VOLUME /redis-service
WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
COPY src ./src
COPY redis.conf ./

RUN chmod +x ./mvnw
RUN ./mvnw dependency:resolve

EXPOSE 8080
CMD ./mvnw spring-boot:run