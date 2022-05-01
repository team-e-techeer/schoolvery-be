ARG JAR_FILE=/home/source/java/build/libs/schoolvery-server-0.0.1-SNAPSHOT.jar

FROM gradle:jdk11 as compile
WORKDIR /home/source/java
COPY . .
RUN chown -R gradle .
USER gradle
RUN gradle wrapper
RUN ./gradlew build

FROM openjdk:11-jdk
ARG JAR_FILE
## Add the wait script to the image
ADD https://github.com/ufoscout/docker-compose-wait/releases/download/2.9.0/wait /wait
RUN chmod +x /wait

COPY --from=compile ${JAR_FILE} app.jar
CMD /wait && java -jar /app.jar