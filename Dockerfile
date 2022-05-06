ARG JAR_FILE=${pwd}/build/libs/schoolvery-server-0.0.1-SNAPSHOT.jar

FROM openjdk:11-jdk
ARG JAR_FILE
## Add the wait script to the image
ADD https://github.com/ufoscout/docker-compose-wait/releases/download/2.9.0/wait /wait
RUN chmod +x /wait

COPY ${JAR_FILE} app.jar
CMD /wait && java -jar /app.jar