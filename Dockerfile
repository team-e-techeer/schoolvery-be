ARG JAR_FILE=/home/source/java/build/libs/schoolvery-server-0.0.1-SNAPSHOT.jar

FROM gradle:jdk11 as compile
WORKDIR /home/source/java
COPY . .
RUN chown -R gradle .
USER gradle
RUN ./gradlew build

FROM openjdk:11-jdk
ARG JAR_FILE
COPY --from=compile ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]