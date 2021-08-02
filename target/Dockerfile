FROM adoptopenjdk/openjdk11:ubi
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/weather-0.0.1-SNAPSHOT.jar localhost 8083 7ad6640f2d4b76480afbe652df3a7efa 60000"]